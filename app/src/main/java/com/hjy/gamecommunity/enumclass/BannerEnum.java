package com.hjy.gamecommunity.enumclass;

import com.hjy.baseutil.enumclass.Enum;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/22 14:10
 * 描述: banner广告类型 ---   //类型：1=图片，2=视频，3=直播
 */
public class BannerEnum extends Enum<Integer, String> {
    private static BannerEnum mInstance;
    public final static String IMG = "图片";
    public final static String VIDEO = "视频";
    public final static String LIVE = "直播";

    private BannerEnum() {
        super();
    }

    public static BannerEnum i() {
        if (mInstance == null) {
            synchronized (BannerEnum.class) {
                if (mInstance == null) {
                    mInstance = new BannerEnum();
                }
            }
        }
        return mInstance;
    }

    @Override
    protected Map<Integer, String> addEnum() {
        Map<Integer, String> kvMap = new LinkedHashMap<>();
        kvMap.put(1, IMG);
        kvMap.put(2, VIDEO);
        kvMap.put(3, LIVE);
        return kvMap;
    }
}
