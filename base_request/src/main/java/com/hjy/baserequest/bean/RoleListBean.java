package com.hjy.baserequest.bean;

import java.io.Serializable;
import java.util.List;

/**
 * CREATED BY DY ON 2020/7/3.
 * TIME BY 9:49.
 *
 * @author DY
 **/
public class RoleListBean implements Serializable {

    /**
     * code : 200
     * msg : 角色查询
     * data : [{"id":41,"cp_role_id":"1","cp_role_name":"123","game_name":"剑侠情缘III","cp_server_id":"电信八区","cp_server_name":"绝代天骄"}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{

        public DataBean(int id, String cp_role_id, String cp_role_name, String game_name, String cp_server_id, String cp_server_name, int is_default) {
            this.id = id;
            this.cp_role_id = cp_role_id;
            this.cp_role_name = cp_role_name;
            this.game_name = game_name;
            this.cp_server_id = cp_server_id;
            this.cp_server_name = cp_server_name;
            this.is_default = is_default;
        }

        /**
         * id : 41
         * cp_role_id : 1
         * cp_role_name : 123
         * game_name : 剑侠情缘III
         * cp_server_id : 电信八区
         * cp_server_name : 绝代天骄
         */
        private int id;
        private String cp_role_id;
        private String cp_role_name;
        private String game_name;
        private String cp_server_id;
        private String cp_server_name;
        private int is_default;

        public int getIs_default() {
            return is_default;
        }

        public void setIs_default(int is_default) {
            this.is_default = is_default;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCp_role_id() {
            return cp_role_id;
        }

        public void setCp_role_id(String cp_role_id) {
            this.cp_role_id = cp_role_id;
        }

        public String getCp_role_name() {
            return cp_role_name;
        }

        public void setCp_role_name(String cp_role_name) {
            this.cp_role_name = cp_role_name;
        }

        public String getGame_name() {
            return game_name;
        }

        public void setGame_name(String game_name) {
            this.game_name = game_name;
        }

        public String getCp_server_id() {
            return cp_server_id;
        }

        public void setCp_server_id(String cp_server_id) {
            this.cp_server_id = cp_server_id;
        }

        public String getCp_server_name() {
            return cp_server_name;
        }

        public void setCp_server_name(String cp_server_name) {
            this.cp_server_name = cp_server_name;
        }
    }
}
