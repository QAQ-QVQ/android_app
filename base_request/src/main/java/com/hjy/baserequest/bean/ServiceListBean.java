package com.hjy.baserequest.bean;

import java.io.Serializable;
import java.util.List;

/**
 * CREATED BY DY ON 2020/7/2.
 * TIME BY 10:57.
 *
 * @author DY
 **/
public class ServiceListBean implements Serializable {

    /**
     * code : 200
     * msg : 区服列表
     * data : [{"id":1,"cp_server_name":"绝代天骄"}]
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
        /**
         * id : 1
         * cp_server_name : 绝代天骄
         */

        private int id;
        private String cp_server_name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCp_server_name() {
            return cp_server_name;
        }

        public void setCp_server_name(String cp_server_name) {
            this.cp_server_name = cp_server_name;
        }
    }
}
