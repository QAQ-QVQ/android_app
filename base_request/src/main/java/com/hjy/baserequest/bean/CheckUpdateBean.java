package com.hjy.baserequest.bean;

import java.io.Serializable;

/**
 * CREATED BY DY ON 2020/7/1.
 * TIME BY 11:03.
 *
 * @author DY
 **/
public class CheckUpdateBean implements Serializable {

    /**
     * code : 200
     * msg : 操作成功
     * data : {"id":1,"android_url":"http://test.com","android_package_size":"11.25M","ios_url":"http://test.com","ios_package_size":"11.25M","is_strong_update":2,"type":3,"remark":"更新了首页部分样式","version_name":"1.0.1","version_code":2,"update_time":1593419175,"create_time":1593419175}
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
         * android_url : http://test.com
         * android_package_size : 11.25M
         * ios_url : http://test.com
         * ios_package_size : 11.25M
         * is_strong_update : 2
         * type : 3
         * remark : 更新了首页部分样式
         * version_name : 1.0.1
         * version_code : 2
         * update_time : 1593419175
         * create_time : 1593419175
         */

        private int id;
        private String android_url;
        private String android_package_size;
        private String ios_url;
        private String ios_package_size;
        private int is_strong_update;
        private int type;
        private String remark;
        private String version_name;
        private int version_code;
        private int update_time;
        private int create_time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAndroid_url() {
            return android_url;
        }

        public void setAndroid_url(String android_url) {
            this.android_url = android_url;
        }

        public String getAndroid_package_size() {
            return android_package_size;
        }

        public void setAndroid_package_size(String android_package_size) {
            this.android_package_size = android_package_size;
        }

        public String getIos_url() {
            return ios_url;
        }

        public void setIos_url(String ios_url) {
            this.ios_url = ios_url;
        }

        public String getIos_package_size() {
            return ios_package_size;
        }

        public void setIos_package_size(String ios_package_size) {
            this.ios_package_size = ios_package_size;
        }

        public int getIs_strong_update() {
            return is_strong_update;
        }

        public void setIs_strong_update(int is_strong_update) {
            this.is_strong_update = is_strong_update;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getVersion_name() {
            return version_name;
        }

        public void setVersion_name(String version_name) {
            this.version_name = version_name;
        }

        public int getVersion_code() {
            return version_code;
        }

        public void setVersion_code(int version_code) {
            this.version_code = version_code;
        }

        public int getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(int update_time) {
            this.update_time = update_time;
        }

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }
    }
}
