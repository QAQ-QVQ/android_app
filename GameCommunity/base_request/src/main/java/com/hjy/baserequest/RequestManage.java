package com.hjy.baserequest;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.Gravity;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.hjy.baserequest.bean.User;
import com.hjy.baseutil.CacheUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.SPCookieStore;
import com.lzy.okgo.model.HttpHeaders;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class RequestManage {
    private static Application application;
    private static User userObject;
    private static boolean DEBUG;

    public static void init(Application application) {
        RequestManage.application = application;
        OkGoInit();
    }

    public static Application getApplication() {
        return application;
    }

    public static boolean isDEBUG() {
        return DEBUG;
    }

    public static void setDEBUG(boolean dEBUG) {
        DEBUG = dEBUG;
    }

    /**
     * 消息提示
     *
     * @param s
     */
    public static void tost(final String s) {
        if (!TextUtils.isEmpty(s)) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    ToastUtils.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                    ToastUtils.showShort(s);
                }
            });
        }
    }

    /**
     * 消息提示
     *
     * @param s
     */
    public static void tostLong(final String s) {
        if (!TextUtils.isEmpty(s)) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    ToastUtils.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                    ToastUtils.showLong(s);
                }
            });
        }
    }

    private static void OkGoInit() {
        try {
            Class c = Class.forName("java.lang.Daemons");
            Field maxField = c.getDeclaredField("MAX_FINALIZE_NANOS");
            maxField.setAccessible(true);
            maxField.set(null, Long.MAX_VALUE);
        } catch (Exception e) {

        }

        OkHttpClient.Builder buildeOkHttpClient = new OkHttpClient.Builder();

        buildeOkHttpClient.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);    //全局的读取超时时间
        buildeOkHttpClient.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);    //全局的写入超时时间
        buildeOkHttpClient.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);  //全局的连接超时时间
        buildeOkHttpClient.cookieJar(new CookieJarImpl(new SPCookieStore(getApplication())));
        OkHttpClient build = buildeOkHttpClient.build();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.put("charset", "utf-8");
        OkGo.getInstance().init(getApplication())
                //可以全局统一设置缓存模式,默认是不使用缓存,可以不传,具体其他模式看 github 介绍 https://github.com/jeasonlzy/
                .setCacheMode(CacheMode.NO_CACHE)
                //可以全局统一设置缓存时间,默认永不过期,具体使用方法看 github 介绍
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
                .setOkHttpClient(build)
                .addCommonHeaders(httpHeaders)
                .setRetryCount(3);

    }

    /**
     * //从本地获取用户信息
     *
     * @return 返回用户对象，用户未登录返回null
     */
    public static User getUserData() {
        //如果用户已登陆，从本地读取
        if (userObject == null) {
            String userJson = CacheUtil.readString(CacheUtil.getUserPath(), "user");
            if (userJson != null) {
                userObject = new Gson().fromJson(userJson, User.class);
                return userObject;
            } else {
                return null;
            }
        } else
            return userObject;
    }


    /**
     * 写入用户信息（json字符）
     *
     * @param jsonString
     */
    public static synchronized void writeUserData(String jsonString) {
        userObject = null;
        CacheUtil.writeString(CacheUtil.getUserPath(), "user", jsonString);

    }
}
