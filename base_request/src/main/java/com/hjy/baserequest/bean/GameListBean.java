package com.hjy.baserequest.bean;

import java.io.Serializable;
import java.util.List;

/**
 * CREATED BY DY ON 2020/7/2.
 * TIME BY 10:48.
 *
 * @author DY
 **/
public class GameListBean implements Serializable {

    /**
     * code : 200
     * msg : 游戏列表
     * data : [{"id":1,"name":"剑侠情缘III"}]
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
         * name : 剑侠情缘III
         */

        private int id;
        private String name;
        private String icon;

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

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
    }
}
