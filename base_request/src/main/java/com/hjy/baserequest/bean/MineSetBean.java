package com.hjy.baserequest.bean;

/**
 * 设置对象
 * CREATED BY DY ON 2020/6/29.
 * TIME BY 9:10.
 *
 * @author DY
 **/
public class MineSetBean {
    /**
     * 头像
     */
    private String icon;
    /**
     * 设置标题
     */
    private String title;
    /**
     * 设置信息
     */
    private String msg;
    /**
     * 下一个图标
     */
    private int next;

    public MineSetBean(String icon, String title, String msg, int next) {
        this.icon = icon;
        this.title = title;
        this.msg = msg;
        this.next = next;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }
}
