package com.hjy.baserequest.bean;

import java.util.List;

/**
 * CREATED BY DY ON 2020/7/1.
 * TIME BY 9:56.
 *
 * @author DY
 **/
public class FamilyInfoBean {

    /**
     * code : 200
     * msg : 我的家族
     * data : [{"id":1,"name":"夜阑听风雨@绝代天骄","avatar":"avatar.png"}]
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

    public static class DataBean {
        /**
         * id : 1
         * name : 夜阑听风雨@绝代天骄
         * avatar : avatar.png
         */

        private int id;
        private String name;
        private String avatar;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
}
