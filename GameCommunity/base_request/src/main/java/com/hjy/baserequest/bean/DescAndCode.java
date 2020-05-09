package com.hjy.baserequest.bean;

import java.io.Serializable;

/**
 * Author: zhangqingyou
 * Date: 2020/4/7
 * Des:
 */

public class DescAndCode implements Serializable {



    private int code;
    private String msg;

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
}
