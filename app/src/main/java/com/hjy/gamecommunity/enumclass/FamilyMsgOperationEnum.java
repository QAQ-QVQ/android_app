package com.hjy.gamecommunity.enumclass;

import com.hjy.baseutil.enumclass.Enum;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 作者: zhangqingyou
 * 时间: 2020/7/2 9:04
 * 描述:
 */
public class FamilyMsgOperationEnum extends Enum<Integer, String> {
    private static FamilyMsgOperationEnum mInstance;
    public final static String READ = "标为已读";
    public final static String UN_READ = "标为未读";
    public final static String TOP = "消息置顶";
    public final static String UN_TOP = "取消置顶";
    public final static String REMOVE = "移除消息";
    public final static String REMIND = "新消息提醒";
    public final static String UN_REMIND = "消息免打扰";

    private FamilyMsgOperationEnum() {
        super();
    }

    public static FamilyMsgOperationEnum i() {
        if (mInstance == null) {
            synchronized (FamilyMsgOperationEnum.class) {
                if (mInstance == null) {
                    mInstance = new FamilyMsgOperationEnum();
                }
            }
        }
        return mInstance;
    }

    @Override
    protected Map<Integer, String> addEnum() {
        Map<Integer, String> kvMap = new LinkedHashMap<>();
        kvMap.put(1, READ);
        kvMap.put(2, UN_READ);
        kvMap.put(3, TOP);
        kvMap.put(4, UN_TOP);
        kvMap.put(5, REMOVE);
        kvMap.put(6, REMIND);
        kvMap.put(7, UN_REMIND);
        return kvMap;
    }
}
