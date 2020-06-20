package com.hjy.gamecommunity.enumclass;

import com.hjy.baseutil.enumclass.Enum;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/20 9:48
 * 描述: 短信验证码类型 Enum
 */
public class TabEnum extends Enum<Integer, String> {
    private static TabEnum mInstance;
    public final static String VALUE1 = "发现";
    public final static String VALUE2 = "视频";
    public final static String VALUE3 = "家族";
    public final static String VALUE4 = "消息";
    public final static String VALUE5 = "我的";

    private TabEnum() {
        super();
    }

    public static TabEnum i() {
        if (mInstance == null) {
            synchronized (TabEnum.class) {
                if (mInstance == null) {
                    mInstance = new TabEnum();
                }
            }
        }
        return mInstance;
    }

    @Override
    protected Map<Integer, String> addEnum() {
        Map<Integer, String> kvMap = new LinkedHashMap<>();
        kvMap.put(0, VALUE1);
        kvMap.put(1, VALUE2);
        kvMap.put(2, VALUE3);
        kvMap.put(3, VALUE4);
        kvMap.put(4, VALUE5);
        return kvMap;
    }


}
