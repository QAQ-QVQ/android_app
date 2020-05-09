package com.hjy.baserequest.bean;

import java.io.Serializable;

/**
 * Author: zhangqingyou
 * Date: 2020/4/7
 * Des:
 */

public class User implements Serializable {

    /**
     * code : 200
     * msg : 登陆成功
     * data : {"is_phone":true,"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1ODY1NzQxNTQsIm5iZiI6MTU4NjU3NDE1NCwiZXhwIjoxNTg3MjI1NTk5LCJkYXRhIjp7InVpZCI6IjEyMzQ1NiJ9fQ.TijGEkTE7Y7MQQWkyME2ReTGRPd7yrZhU9Adb6hbU0w"}
     */

    private int code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * is_phone : true
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1ODY1NzQxNTQsIm5iZiI6MTU4NjU3NDE1NCwiZXhwIjoxNTg3MjI1NTk5LCJkYXRhIjp7InVpZCI6IjEyMzQ1NiJ9fQ.TijGEkTE7Y7MQQWkyME2ReTGRPd7yrZhU9Adb6hbU0w
         */

        private boolean is_phone;
        private String token;
        private int user_id;

        public boolean isIs_phone() {
            return is_phone;
        }

        public void setIs_phone(boolean is_phone) {
            this.is_phone = is_phone;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }
    }
}
