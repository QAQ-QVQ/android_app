package com.hjy.baserequest.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Author: zhangqingyou
 * Date: 2020/4/7
 * Des: 手机登录
 */

public class PhoneLoginUserBean implements Serializable {

    /**
     * code : 200
     * msg : phone_user_list
     * data : {"user_list":[{"id":44,"nickname":"u1000044","username":"u1000044"},{"id":41,"nickname":"u1000041","username":"u1000041"},{"id":1,"nickname":"u1000001","username":"u1000001"}],"userinfo":{"user_id":44,"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1OTA2NTY4NzAsIm5iZiI6MTU5MDY1Njg3MCwiZXhwIjoxNTkxMjg2Mzk5LCJkYXRhIjp7InVzZXJfaWQiOjQ0fX0.7uY42X4i6R_hzjy0kifWLvIdJHZTfA5qtY7oZaJFW1o"},"sign":"ceVUaOMZLcJNnNKeCnMau2U+YKEpZfoPXCqzYTgXrWtpKZUev0ZoLvFgfVwUh/utfyWEXghHlfUHMAA6//rSCA=="}
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

    public static class DataBean implements Serializable {
        /**
         * user_list : [{"id":44,"nickname":"u1000044","username":"u1000044"},{"id":41,"nickname":"u1000041","username":"u1000041"},{"id":1,"nickname":"u1000001","username":"u1000001"}]
         * userinfo : {"user_id":44,"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1OTA2NTY4NzAsIm5iZiI6MTU5MDY1Njg3MCwiZXhwIjoxNTkxMjg2Mzk5LCJkYXRhIjp7InVzZXJfaWQiOjQ0fX0.7uY42X4i6R_hzjy0kifWLvIdJHZTfA5qtY7oZaJFW1o"}
         * sign : ceVUaOMZLcJNnNKeCnMau2U+YKEpZfoPXCqzYTgXrWtpKZUev0ZoLvFgfVwUh/utfyWEXghHlfUHMAA6//rSCA==
         */

        private UserinfoBean userinfo;
        private String sign;
        private List<UserListBean> user_list;

        public UserinfoBean getUserinfo() {
            return userinfo;
        }

        public void setUserinfo(UserinfoBean userinfo) {
            this.userinfo = userinfo;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public List<UserListBean> getUser_list() {
            return user_list;
        }

        public void setUser_list(List<UserListBean> user_list) {
            this.user_list = user_list;
        }

        public static class UserinfoBean implements Serializable {
            /**
             * user_id : 44
             * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1OTA2NTY4NzAsIm5iZiI6MTU5MDY1Njg3MCwiZXhwIjoxNTkxMjg2Mzk5LCJkYXRhIjp7InVzZXJfaWQiOjQ0fX0.7uY42X4i6R_hzjy0kifWLvIdJHZTfA5qtY7oZaJFW1o
             */

            private int user_id;
            private String token;

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }
        }

        public static class UserListBean implements Serializable {
            /**
             * id : 44
             * nickname : u1000044
             * username : u1000044
             */

            private int id;
            private String nickname;
            private String username;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }
        }
    }
}
