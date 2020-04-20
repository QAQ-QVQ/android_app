package com.hjy.baserequest.request;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.hjy.baserequest.RequestManage;
import com.hjy.baserequest.util.JEventUtils;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.request.base.Request;


import okhttp3.Response;

/**
 * Author: zhangqingyou
 * Date: 2020/4/7
 * Des:
 *
 * @param <T> json对象实体类
 */

public abstract class JsonEntityCallback<T> extends AbsCallback<String> {
    private StringConvert convert;
    private Class<T> classOfBean;//json对象实体
    private String TAG;//网络请求TAG（用于日志过滤）

    public JsonEntityCallback(Class<T> classOfBean) {
        convert = new StringConvert();
        this.classOfBean = classOfBean;
    }

    public JsonEntityCallback(Class<T> classOfBean, String requestName) {
        convert = new StringConvert();
        this.classOfBean = classOfBean;
        this.TAG = requestName;
    }

    @Override
    public String convertResponse(Response response) throws Throwable {
        String s = convert.convertResponse(response);
        response.close();
        return s;
    }

    @Override
    public void onStart(Request<String, ? extends Request> request) {
        super.onStart(request);
        try {
            String baseUrl = request.getBaseUrl().replace("//", "");
            int indexOf = baseUrl.indexOf("/");
            TAG = baseUrl.substring(indexOf, baseUrl.length());
        } catch (Exception e) {
            TAG = "网络请求";
        }

    }


    @Override
    public void onError(com.lzy.okgo.model.Response<String> response) {
        super.onError(response);
        //极光计数事件（接口连接失败使用）
        JEventUtils.onCountEventError(response, TAG);
    }

    @Override
    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
        //打印完成请求链接
        JEventUtils.logRequest(response, TAG);
        try {
            T t = new Gson().fromJson(response.body(), classOfBean);
            onSuccess(t);
        } catch (JsonSyntaxException e) {
            RequestManage.tost(TAG + "_json数据格式错误");
            //极光计数事件（接口返回json数据解析错误使用）
            JEventUtils.onCountEventJsonError(response, TAG);
        }

    }


    /**
     * 对返回数据进行操作的回调， UI线程
     */
    protected abstract void onSuccess(T t);


}
