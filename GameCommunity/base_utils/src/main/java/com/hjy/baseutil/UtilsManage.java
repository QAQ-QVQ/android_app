package com.hjy.baseutil;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.Gravity;

import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;
/**
 * 缓存工具类
 * Author: zhangqingyou
 * Date: 2020/4/7
 * Des:
 */
public class UtilsManage {
    private static Application application;

    public static void init(Application application) {
        Utils.init(application);//初始化android工具类
        UtilsManage.application = application;
    }

    public static Application getApplication() {
        return application;
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
}
