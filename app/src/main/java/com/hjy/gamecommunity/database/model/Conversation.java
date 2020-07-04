package com.hjy.gamecommunity.database.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * 作者: zhangqingyou
 * 时间: 2020/7/2 20:26
 * 描述:
 */
public class Conversation extends RealmObject {
    @PrimaryKey // 设置为主键
    private String id;//群聊ID
    private String conversationID;//会话ID
    private String faceUrll;//会话头像
    private String name;//会话名
    private long topTimestamp;//该会话置顶时间（预留-后期可能根据置顶时间排序）
    private long timestamp;//会话最新消息时间
    private String textElem;//会话最新消息内容
    private int unreadCount;//未读消息数
    private String isRemind;//是否消息提醒 1:提醒  2：不提醒


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConversationID() {
        return conversationID;
    }

    public void setConversationID(String conversationID) {
        this.conversationID = conversationID;
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

    public long getTopTimestamp() {
        return topTimestamp;
    }

    public void setTopTimestamp(long topTimestamp) {
        this.topTimestamp = topTimestamp;
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

    public String getIsRemind() {
        return isRemind;
    }

    public void setIsRemind(String isRemind) {
        this.isRemind = isRemind;
    }

    public int getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(int unreadCount) {
        this.unreadCount = unreadCount;
    }
}
