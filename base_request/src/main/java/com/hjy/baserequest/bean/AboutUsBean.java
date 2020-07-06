package com.hjy.baserequest.bean;

import java.io.Serializable;

/**
 * CREATED BY DY ON 2020/7/6.
 * TIME BY 18:08.
 *
 * @author DY
 **/
public class AboutUsBean implements Serializable {

    /**
     * code : 200
     * msg : 关于我们
     * data : 关于我们：我们是一家xxx的xxx 坐落于xxx
     */

    private int code;
    private String msg;
    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
