package com.hjy.gamecommunity.database;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * 作者: zhangqingyou
 * 时间: 2020/7/2 20:26
 * 描述:
 */
public class Conversation extends RealmObject {
    @PrimaryKey
    private String groupID;//会话ID 设置为主键
    private String faceUrll;//会话头像
    private String name;//会话名
    private long timestamp;//会话最新消息时间
    private String textElem;//会话最新消息内容
    private boolean isRemind;//是否消息提醒

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getFaceUrll() {
        return faceUrll;
    }

    public void setFaceUrll(String faceUrll) {
        this.faceUrll = faceUrll;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getTextElem() {
        return textElem;
    }

    public void setTextElem(String textElem) {
        this.textElem = textElem;
    }

    public boolean isRemind() {
        return isRemind;
    }

    public void setRemind(boolean remind) {
        isRemind = remind;
    }
}
