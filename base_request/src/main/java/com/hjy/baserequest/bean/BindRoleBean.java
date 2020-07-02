package com.hjy.baserequest.bean;

import java.io.Serializable;

/**
 * CREATED BY DY ON 2020/7/1.
 * TIME BY 11:49.
 *
 * @author DY
 **/
public class BindRoleBean implements Serializable {

    /**
     * code : 200
     * msg : 默认角色
     * data : {"id":1,"game_name":"剑侠情缘IIItest","cp_server_name":"绝代天骄","cp_role_name":"仓鼠号"}
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
         * id : 1
         * game_name : 剑侠情缘IIItest
         * cp_server_name : 绝代天骄
         * cp_role_name : 仓鼠号
         */

        private int id;
        private String game_name;
        private String cp_server_name;
        private String cp_role_name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGame_name() {
            return game_name;
        }

        public void setGame_name(String game_name) {
            this.game_name = game_name;
        }

        public String getCp_server_name() {
            return cp_server_name;
        }

        public void setCp_server_name(String cp_server_name) {
            this.cp_server_name = cp_server_name;
        }

        public String getCp_role_name() {
            return cp_role_name;
        }

        public void setCp_role_name(String cp_role_name) {
            this.cp_role_name = cp_role_name;
        }
    }
}
