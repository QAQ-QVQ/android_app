package com.hjy.gamecommunity.enumclass;


/**
 * 作者: zhangqingyou
 * 时间: 2020/6/15 11:26
 * 描述: 搜索分类枚举
 */
public enum TaskSearchType {
    TYPE0("", "全部"),
    TYPE1("10", "直播"),
    TYPE2("20", "视频"),
    TYPE3("30", "家族"),
    TYPE4("40", "游戏"),
    TYPE5("50", "资讯");

    private final String key;
    private final String desc;

    private TaskSearchType(String key, String desc) {
        this.key = key;
        this.desc = desc;

    }

    /**
     * 根据key查value
     *
     * @param key
     * @return
     */
    public static String searchDesc(String key) {
        for (TaskSearchType temp : values()) {
            if (temp.key.equals(key)) {
                return temp.desc;
            }
        }
        return "";
    }

    /**
     * 根据value查 key
     *
     * @param _desc
     * @return
     */
    public static String searchKey(String _desc) {
        for (final TaskSearchType temp : values()) {
            if (temp.desc.equals(_desc)) {
                return temp.key;
            }
        }
        return "";
    }

    public String getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }

}
