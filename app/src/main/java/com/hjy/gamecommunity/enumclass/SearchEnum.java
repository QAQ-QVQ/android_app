package com.hjy.gamecommunity.enumclass;

import com.hjy.baseutil.enumclass.Enum;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/17 9:05
 * 描述: 分类搜索Enum
 */
public class SearchEnum extends Enum<String, String> {
    private static SearchEnum mInstance;
    public final static String VALUE1 = "全部";
    public final static String VALUE2 = "直播";
    public final static String VALUE3 = "视频";
    public final static String VALUE4 = "家族";
    public final static String VALUE5 = "游戏";
    public final static String VALUE6 = "资讯";


    public static SearchEnum i() {
        if (mInstance == null) {
            synchronized (SearchEnum.class) {
                if (mInstance == null) {
                    mInstance = new SearchEnum();
                }
            }
        }
        return mInstance;
    }

    @Override
    protected Map<String, String> addEnum() {
        Map<String, String> kvMap = new LinkedHashMap<>();
        kvMap.put("", VALUE1);
        kvMap.put("10", VALUE2);
        kvMap.put("20", VALUE3);
        kvMap.put("40", VALUE4);
        kvMap.put("30", VALUE5);
        kvMap.put("50", VALUE6);
        return kvMap;
    }


}
