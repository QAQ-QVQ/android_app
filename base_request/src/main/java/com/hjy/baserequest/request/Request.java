package com.hjy.baserequest.request;

import android.util.Log;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.MapUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hjy.baserequest.bean.AppSetting;
import com.hjy.baserequest.bean.DescAndCode;
import com.hjy.baserequest.bean.MessagePush;
import com.hjy.baserequest.bean.User;
import com.hjy.baserequest.util.ListToStringUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.callback.FileCallback;
import com.hjy.baserequest.RequestManage;
import com.hjy.baseutil.GetSystemInfoUtil;
import com.lzy.okgo.request.PostRequest;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * APP和SDK通用请求
 * <p>
 * Author: zhangqingyou
 * Date: 2020/4/7
 * Des:
 */
public class Request {


    private static Request request;

    private Request() {
    }

    public static Request getInstance() {
        if (request == null) {
            synchronized (Request.class) {
                if (request == null)
                    request = new Request();
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
                .tag(this)
                .upJson(json)
                .execute(callback);
    }

    public void okgo_get(String url, Map<String, String> params, Callback callback) {
        OkGo.<String>get(url)
                .params(params)
                .execute(callback);
    }

    public void okgo_post(String url, Map<String, String> params, Callback callback) {
        OkGo.<String>post(url)
                .params(params)
                .execute(callback);
    }
    //--------------------------------------------------基本请求 end-------------------------------------------------

    //--------------------------------------------------加密方式和公共请求 start-------------------------------------------------

    /**
     * 接口加密参数
     *
     * @param key
     * @return
     */
    public String getSign(String key) {
        String userId = "";
        String singMD5 = "";
        //  singMD5 = EncryptUtils.encryptMD5ToString(userId + key).toLowerCase();
        return singMD5;
    }


    /**
     * 公共请求
     *
     * @return
     */
    private Map<String, String> getHeader() {
        Map<String, String> params = new LinkedHashMap<>();

//        int user_id = 0;
//        if (RequestManage.getUserData() != null && RequestManage.getUserData().getData() != null) {
//            user_id = RequestManage.getUserData().getData().getUser_id();
//        }
//        params.put("user_id", String.valueOf(user_id));
        params.put("sign", getSign(API.KEY));
        return params;
    }


    /**
     * 添加加密丶公共请求后的通用请求
     * * <p>
     * * 在泛型类中声明了一个泛型方法，使用泛型E，这种泛型E可以为任意类型。可以类型与T相同，也可以不同。
     * * 由于泛型方法在声明的时候会声明泛型<E>，因此即使在泛型类中并未声明泛型，编译器也能够正确识别泛型方法中识别的泛型。
     *
     * @param requestType 请求方式
     * @param url 请求地址
     * @param jsonObject 请求参数合集
     * @param absCallback  请求结果回调
     */
    //防止同一个接口频繁请求，当前请求响应后才能继续下个请求
    private Map<String, Boolean> stringBooleanMap = new LinkedHashMap<>();

    public void request(int requestType, String url, JsonObject jsonObject, AbsCallback absCallback) {
        if (!stringBooleanMap.containsKey(url)) {
            stringBooleanMap.put(url, true);
        }

        if (stringBooleanMap.get(url)) {//防止同一个接口频繁请求，当前请求响应后才能继续下个请求
            stringBooleanMap.put(url, false);

            Map<String, String> header = getHeader();
            for (Map.Entry<String, JsonElement> jsonElement : jsonObject.entrySet()) {
                String key = jsonElement.getKey();
                String value = jsonElement.getValue().toString().replaceAll("\"", "");
                header.put(key, value);
            }

            if (requestType == GET) {
                okgo_get(url, header, absCallback);
            } else if (requestType == POST) {
                Log.d("POST请求参数", "请求参数:" + MapUtils.toString(header));
                okgo_post(url, header, absCallback);
            } else if (requestType == POST_JSON) {
                JsonObject jsonObjectString = new JsonObject();
                for (Map.Entry<String, String> entry : header.entrySet()) {
                    jsonObjectString.addProperty(entry.getKey(), entry.getValue());
                }
                Log.d("jsonObject", "jsonObject:" + jsonObjectString.toString());
                okgo_postJson(url, jsonObjectString.toString(), absCallback);
            }
        } else
            absCallback.onFinish();
    }


    //--------------------------------------------------加密方式和公共请求 end-------------------------------------------------

    /**
     * 检查app更新
     */
    public void appSetting(JsonEntityCallback<AppSetting> jsonBeanCallback) {
        String jpushChannel = GetSystemInfoUtil.readApplicationMetaData("JPUSH_CHANNEL");
        String agent_id = GetSystemInfoUtil.readApplicationMetaData("AGENT_ID");

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("qudaoNumber", jpushChannel + "," + agent_id);

        request(GET, API.appUpdates, jsonObject, jsonBeanCallback);

    }


    /**
     * 获取短信验证码
     *
     * @param phone              手机号码
     * @param type               验证码类型：reg-注册，lost-忘记密码验证时使用，code-其他
     * @param jsonEntityCallback
     */
    public void smsVerificationCode(String phone, String type, JsonEntityCallback<DescAndCode> jsonEntityCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("phone", phone);
        jsonObject.addProperty("type", type);
        request(POST, API.getUrl(API.smsVerificationCode), jsonObject, jsonEntityCallback);
    }


    /**
     * 手机号登录
     *
     * @param phone              手机号码
     * @param code               验证码
     * @param jsonEntityCallback
     */
    public void phoneLogin(String phone, String code, JsonEntityCallback<User> jsonEntityCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("phone", phone);
        jsonObject.addProperty("code", code);
        request(POST, API.getUrl(API.phoneLogin), jsonObject, jsonEntityCallback);
    }


    /**
     * 账号密码登录
     *
     * @param username           账号
     * @param password           密码
     * @param jsonEntityCallback
     */
    public void accountPasswordLogin(String username, String password, JsonEntityCallback<User> jsonEntityCallback) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("username", username);
        jsonObject.addProperty("password", password);
        request(POST, API.getUrl(API.accountPasswordLogin), jsonObject, jsonEntityCallback);
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
        request(POST, API.getUrl(API.phoneVerification), jsonObject, jsonEntityCallback);
    }

    /**
     * 重置密码
     *
     * @param password           密码
     * @param jsonEntityCallback
     */
    public void resetPassword(String password, JsonEntityCallback<DescAndCode> jsonEntityCallback) {
        JsonObject jsonObject = new JsonObject();
        int user_id = 0;
        if (RequestManage.getUserData() != null && RequestManage.getUserData().getData() != null) {
            user_id = RequestManage.getUserData().getData().getUser_id();
        }
        jsonObject.addProperty("user_id", user_id);
        jsonObject.addProperty("password", password);
        jsonObject.addProperty("re_password", password);
        request(POST, API.getUrl(API.resetPassword), jsonObject, jsonEntityCallback);
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
