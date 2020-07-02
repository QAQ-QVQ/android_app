package com.hjy.baserequest.bean;

import java.io.Serializable;

/**
 * CREATED BY DY ON 2020/7/1.
 * TIME BY 9:48.
 *
 * @author DY
 **/
public class GiftNumBean implements Serializable {

    /**
     * code : 200
     * msg : 我的礼包数量
     * data : 1
     */

    private int code;
    private String msg;
    private int data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
