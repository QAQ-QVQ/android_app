package com.hjy.baserequest.bean;

/**
 * CREATED BY DY ON 2020/6/28.
 * TIME BY 17:48.
 *
 * @author DY
 **/
public class MineUserSetBean {
    private String title;
    private String msg;
    private int next;

    public MineUserSetBean(String title, String msg, int next) {
        this.title = title;
        this.msg = msg;
        this.next = next;
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
