package com.hjy.baserequest.data;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/11 16:30
 * 描述:
 */
public class UserData {

    private String user_id;
    private String user_token;

    public UserData(String user_id, String user_token) {
        this.user_id = user_id;
        this.user_token = user_token;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_token() {
        return user_token;
    }

    public void setUser_token(String user_token) {
        this.user_token = user_token;
    }


}
