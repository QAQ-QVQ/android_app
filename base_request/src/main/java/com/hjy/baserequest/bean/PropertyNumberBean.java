package com.hjy.baserequest.bean;

import java.io.Serializable;

/**
 * CREATED BY DY ON 2020/7/1.
 * TIME BY 10:27.
 *
 * @author DY
 **/
public class PropertyNumberBean implements Serializable {

    /**
     * code : 200
     * msg : 我的账户
     * data : {"coin":100,"user_credit":0,"flower_number":1}
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
         * coin : 100
         * user_credit : 0
         * flower_number : 1
         */

        private int coin;
        private int user_credit;
        private int flower_number;

        public int getCoin() {
            return coin;
        }

        public void setCoin(int coin) {
            this.coin = coin;
        }

        public int getUser_credit() {
            return user_credit;
        }

        public void setUser_credit(int user_credit) {
            this.user_credit = user_credit;
        }

        public int getFlower_number() {
            return flower_number;
        }

        public void setFlower_number(int flower_number) {
            this.flower_number = flower_number;
        }
    }
}
