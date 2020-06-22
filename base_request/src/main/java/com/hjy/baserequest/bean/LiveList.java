package com.hjy.baserequest.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/22 10:53
 * 描述:
 */
public class LiveList implements Serializable {


    /**
     * code : 200
     * msg : 直播列表
     * data : [{"id":2,"title":"大威天龙","cover_picture":"http://tupian.com/setu2.png","heat":124,"anchor_nickname":"特斯特","type":1},{"id":1,"title":"进来康美女嗷","cover_picture":"http://tupian.com/setu.png","heat":123,"anchor_nickname":"大脑斧"}]
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

    public static class DataBean implements Serializable {
        /**
         * id : 2
         * title : 大威天龙
         * cover_picture : http://tupian.com/setu2.png
         * heat : 124
         * anchor_nickname : 特斯特
         * type : 1
         */

        private int id;
        private String title;
        private String cover_picture;
        private int heat;
        private String anchor_nickname;
        private int type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCover_picture() {
            return cover_picture;
        }

        public void setCover_picture(String cover_picture) {
            this.cover_picture = cover_picture;
        }

        public int getHeat() {
            return heat;
        }

        public void setHeat(int heat) {
            this.heat = heat;
        }

        public String getAnchor_nickname() {
            return anchor_nickname;
        }

        public void setAnchor_nickname(String anchor_nickname) {
            this.anchor_nickname = anchor_nickname;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
