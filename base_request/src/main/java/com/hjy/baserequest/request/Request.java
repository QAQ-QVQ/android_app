package com.hjy.baserequest.request;

import android.annotation.SuppressLint;
import android.util.Log;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.MapUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hjy.baserequest.bean.AccountsLoginUserBean;
import com.hjy.baserequest.bean.AnchorList;
import com.hjy.baserequest.bean.DescAndCode;
import com.hjy.baserequest.bean.FindBanner;
import com.hjy.baserequest.bean.MessagePush;
import com.hjy.baserequest.bean.NewsDetail;
import com.hjy.baserequest.bean.NewsList;
import com.hjy.baserequest.bean.PhoneLoginUserBean;
import com.hjy.baserequest.bean.SearchBean;
import com.hjy.baserequest.bean.VideoList;
import com.hjy.baserequest.data.UserData;
import com.hjy.baserequest.data.UserDataContainer;
import com.hjy.baserequest.util.ListToStringUtil;
import com.hjy.baserequest.util.RequestResponseUtil;
import com.hjy.baseutil.GetSystemInfoUtil;
import com.hjy.baseutil.code.impl.RSAEncrypt;
import com.hjy.baseutil.code.impl.URLCode;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.request.PostRequest;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * APP和SDK通用请求
 * <p>
 * Author: zhangqingyou
 * Date: 2020/4/7
 * Des:
 */
public class Request {

    private static Request request;
    private boolean signSwitch = true;//请求参数加密开关

    private Request() {
    }

    public static Request getInstance() {
        if (request == null) {
            synchronized (Request.class) {
                if (request == null){
                    request = new Request();
                }
            }
        }
        return request;
    }


    //--------------------------------------------------基本请求 start-------------------------------------------------
    public final int GET = 1;
    public final int POST = 2;
    public final int POST_JSON = 3;


    /**
     * 下载
     *
     * @param appUrl 下载地址
     */
    public void download(String appUrl, FileCallback callback) {
        OkGo.<File>get(appUrl)
                .tag(this)
                .execute(callback);
    }

    /**
     * json格式的请求方式
     *
     * @param url
     * @param json
     * @param callback
     */
    private void okgo_postJson(String url, String json, Callback callback) {
        OkGo.<String>post(url)
                //.tag(requestTag(url))
                .upJson(json)
                .execute(callback);
    }

    public void okgo_get(String url, Map<String, String> params, Callback callback) {
        OkGo.<String>get(url)
                // .tag(requestTag(url))
                .params(params)
                .execute(callback);
    }

    public void okgo_post(String url, Map<String, String> params, Callback callback) {
        Log.d(url, "请求参数:" + MapUtils.toString(params));
        OkGo.<String>post(url)
                //.tag(requestTag(url))
                .params(params)
                .execute(callback);

    }
    //--------------------------------------------------基本请求 end-------------------------------------------------

    //--------------------------------------------------加密方式和公共请求 start-------------------------------------------------


    /**
     * * 使用 Map按key进行排序
     *      
     */

    public static Map<String, String> sortMapByKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, String> sortMap = new TreeMap<>(
                new Comparator<String>() {
                    public int compare(String obj1, String obj2) {
                        // 降序排序
                        return obj1.compareTo(obj2);
                    }
                });

        sortMap.putAll(map);
        return sortMap;

    }

    /**
     * 公共请求
     *
     * @param map
     * @return
     */
    public Map<String, String> getParams(Map<String, String> map) {
        // 添加公共信息
        String user_token = "";
        UserData userData = UserDataContainer.getInstance().getUserData();
        if (userData != null) {
            user_token = userData.getUser_token();
        }


        map.put("request_time", String.valueOf(System.currentTimeMillis() / 1000));
        map.put("token", user_token);//
        map.put("ext_info", getExtInfo());//扩展参数
        return map;
    }

    /**
     * 加密请求参数
     *
     * @param params
     * @return
     */
    private Map<String, String> getSign(Map<String, String> params) {
        Map<String, String> map = getParams(params);

        if (signSwitch) {
            map.put("is_rsa", "0");//加密
        } else {
            map.put("is_rsa", "10");//不加密
        }

        StringBuffer stringBuffer = new StringBuffer();
        Map<String, String> stringMap = sortMapByKey(map);
        for (Map.Entry<String, String> entry : stringMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            stringBuffer.append(key).append("=").append(value).append("&");
        }
        String string = stringBuffer.toString();
        String substring = string.substring(0, string.length() - 1);
        String toURLEncoded = URLCode.toURLEncoded(substring);

        String encrypt = RSAEncrypt.encrypt(toURLEncoded);
        map.put("sign", encrypt);//加密
        return map;
    }

    /**
     * 扩展参数
     *
     * @return
     */
    @SuppressLint("MissingPermission")
    public String getExtInfo() {
        // 添加公共信息
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("app_version", AppUtils.getAppVersionName());
        jsonObject.addProperty("app_version_code", AppUtils.getAppVersionCode());
        jsonObject.addProperty("rom_platform", "android");
        jsonObject.addProperty("device_id", GetSystemInfoUtil.getUniqueDeviceId(true));
        jsonObject.addProperty("network", NetworkUtils.isMobileData() ? "4g" : "WIFI");
        jsonObject.addProperty("phone_model", DeviceUtils.getManufacturer() + "-" + DeviceUtils.getModel());
        return jsonObject.toString();
    }


    /**
     * 添加加密丶公共请求后的通用请求
     * * <p>
     * * 在泛型类中声明了一个泛型方法，使用泛型E，这种泛型E可以为任意类型。可以类型与T相同，也可以不同。
     * * 由于泛型方法在声明的时候会声明泛型<E>，因此即使在泛型类中并未声明泛型，编译器也能够正确识别泛型方法中识别的泛型。
     *
     * @param requestType 请求方式
     * @param url         请求地址
     * @param jsonObject  请求参数合集
     * @param absCallback 请求结果回调
     */

    public void request(int requestType, String url, JsonObject jsonObject, AbsCallback absCallback) {
        if (RequestResponseUtil.getIsRequest(url)) {//防止同一个接口频繁请求，当前请求响应后才能继续下个请求
            Map<String, String> header = new LinkedHashMap<>();
            for (Map.Entry<String, JsonElement> jsonElement : jsonObject.entrySet()) {
                String key = jsonElement.getKey();
                String value = jsonElement.getValue().toString().replaceAll("\"", "");
                header.put(key, value);
            }


            Map<String, String> signMap = getSign(header);
            if (requestType == GET) {
                okgo_get(url, signMap, absCallback);
            } else if (requestType == POST) {
                okgo_post(url, signMap, absCallback);
            } else if (requestType == POST_JSON) {
                JsonObject jsonObjectString = new JsonObject();
                for (Map.Entry<String, String> entry : signMap.entrySet()) {
                    jsonObjectString.addProperty(entry.getKey(), entry.getValue());
                }
                //Log.d("jsonObject", "jsonObject:" + jsonObjectString.toString());
                okgo_postJson(url, jsonObjectString.toString(), absCallback);
            }
        } else{
            absCallback.onFinish();
        }
    }


    //--------------------------------------------------加密方式和公共请求 end-------------------------------------------------

    /**
     * 检查app更新
     */
//    public void appSetting(JsonEntityCallback<AppSetting> jsonBeanCallback) {
//        String jpushChannel = GetSystemInfoUtil.readApplicationMetaData("JPUSH_CHANNEL");
//        String agent_id = GetSystemInfoUtil.readApplicationMetaData("AGENT_ID");
//
//        JsonObject jsonObject = new JsonObject();
//        jsonObject.addProperty("qudaoNumber", jpushChannel + "," + agent_id);
//
//        request(GET, API.appUpdates, jsonObject, jsonBeanCallback);
//
//    }


    /**
     * 获取短信验证码
     *
     * @param phone              手机号码
     * @param type               短信业务:app_quickLogin 快速登录，app_bindPhone 绑定手机号，app_forgetPassword  忘记密码，app_editpassword 修改密码
     * @param jsonEntityCallback
     */
    public void smsVerificationCode(String phone, String type, JsonEntityCallback<DescAndCode> jsonEntityCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("phone", phone);
        jsonObject.addProperty("type", type);
        request(POST, API.smsVerificationCode, jsonObject, jsonEntityCallback);
    }

    /**
     * 游客登录
     *
     * @param jsonEntityCallback
     */
    public void visitorLogin(JsonEntityCallback<AccountsLoginUserBean> jsonEntityCallback) {
        JsonObject jsonObject = new JsonObject();
        request(POST, API.visitorLogin, jsonObject, jsonEntityCallback);
    }


    /**
     * 手机号登录
     *
     * @param phone              手机号码
     * @param code               验证码
     * @param jsonEntityCallback
     */
    public void phoneLogin(String phone, String code, JsonEntityCallback<PhoneLoginUserBean> jsonEntityCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("phone", phone);
        jsonObject.addProperty("code", code);
        request(POST, API.phoneLogin, jsonObject, jsonEntityCallback);
    }


    /**
     * 账号密码登录
     *
     * @param account            账号
     * @param password           密码
     * @param jsonEntityCallback
     */
    public void accountPasswordLogin(String account, String password, JsonEntityCallback<AccountsLoginUserBean> jsonEntityCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("account", account);
        jsonObject.addProperty("password", password);
        request(POST, API.accountPasswordLogin, jsonObject, jsonEntityCallback);
    }


    /**
     * 手机验证
     *
     * @param phone              手机号码
     * @param code               验证码
     * @param jsonEntityCallback
     */
    public void phoneVerification(String phone, String code, JsonEntityCallback<DescAndCode> jsonEntityCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("phone", phone);
        jsonObject.addProperty("code", code);
        request(POST, API.phoneVerification, jsonObject, jsonEntityCallback);
    }

    /**
     * 重置密码
     *
     * @param password           密码
     * @param jsonEntityCallback
     */
    public void resetPassword(String password, JsonEntityCallback<DescAndCode> jsonEntityCallback) {
        String user_id = "";
        UserData userData = UserDataContainer.getInstance().getUserData();
        if (userData != null) {
            user_id = userData.getUser_id();
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("user_id", user_id);
        jsonObject.addProperty("password", password);
        jsonObject.addProperty("re_password", password);
        request(POST, API.resetPassword, jsonObject, jsonEntityCallback);
    }

    /**
     * 消息推送
     * <p>
     * 后台Push  json数据组格式
     */
    public void messagePush(JsonArryEntityCallback<List<MessagePush>> jsonArryBeanCallback) {
        JsonObject jsonObject = new JsonObject();
        request(GET, API.messagePush, jsonObject, jsonArryBeanCallback);
    }

    /**
     * 发现-banner
     *
     * @param jsonEntityCallback
     */
    public void findBanner(JsonEntityCallback<FindBanner> jsonEntityCallback) {
        JsonObject jsonObject = new JsonObject();
        request(POST, API.findBanner, jsonObject, jsonEntityCallback);
    }


    /**
     * （客服直播、游戏直播）
     *
     * @param jsonEntityCallback
     */
    public void anchorList(JsonEntityCallback<AnchorList> jsonEntityCallback) {
        JsonObject jsonObject = new JsonObject();
        request(POST, API.anchorList, jsonObject, jsonEntityCallback);
    }

    /**
     * 发现-视频列表
     *
     * @param page               页数
     * @param limit              页大小
     * @param jsonEntityCallback
     */
    public void videoList(int page, int limit, JsonEntityCallback<VideoList> jsonEntityCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("page", page);
        jsonObject.addProperty("limit", limit);
        request(POST, API.videoList, jsonObject, jsonEntityCallback);
    }

    /**
     * 发现-资讯列表
     *
     * @param page               页数
     * @param limit              页大小
     * @param jsonEntityCallback
     */
    public void newsList(int page, int limit, JsonEntityCallback<NewsList> jsonEntityCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("page", page);
        jsonObject.addProperty("limit", limit);
        request(POST, API.newsList, jsonObject, jsonEntityCallback);
    }

    /**
     * -资讯详情
     *
     * @param news_id
     * @param
     * @param jsonEntityCallback
     */
    public void newsDetail(int news_id, JsonEntityCallback<NewsDetail> jsonEntityCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("news_id", news_id);
        request(POST, API.newsDetail, jsonObject, jsonEntityCallback);
    }

    /**
     * 搜索
     *
     * @param type
     * @param keywords
     * @param jsonEntityCallback
     */
    public void search(String type, String keywords, JsonEntityCallback<SearchBean> jsonEntityCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", type);
        jsonObject.addProperty("keywords", keywords);
        request(POST, API.search, jsonObject, jsonEntityCallback);
    }


    /**
     * 上传成功后，会将图片URL返回回来
     * 多图上传
     *
     * @param compressPathList
     */
    public void uploadImage(List<String> compressPathList, final List<String> originalPathList, Callback callback) {
        PostRequest<String> postRequest = OkGo.<String>post(API.uploadImg)
                .tag(this)
                .isMultipart(true);//强制使用表单上传
        List<String> stringListMd5 = new ArrayList<>();//原文件MD5码
        List<String> stringListName = new ArrayList<>();//装文件扩展名
        for (String s : compressPathList) {//压缩后的图片
            postRequest.params("file", new File(s));
            stringListName.add(FileUtils.getFileExtension(s));//文件扩展名
        }
        for (String s : originalPathList) {//原文件
            stringListMd5.add(FileUtils.getFileMD5ToString(new File(s)));//原文件MD5码
        }
        postRequest.params("fileMd5", ListToStringUtil.listToString(stringListMd5));//集合转字符串（,号隔开）
        postRequest.params("name", ListToStringUtil.listToString(stringListName));//
        postRequest.execute(callback);
    }

    /**
     * 上传成功后，会将图片URL返回回来
     * <p>
     * 单图上传
     *
     * @param callback
     */
    public void uploadImageOne(String urlFile, Callback callback) {
        PostRequest<String> postRequest = OkGo.<String>post(API.uploadImg)
                .tag(this)
                .isMultipart(true);//强制使用表单上传
        postRequest.params("file", new File(urlFile));
        postRequest.execute(callback);
    }
}
