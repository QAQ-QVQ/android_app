package com.hjy.baserequest.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/12 11:39
 * 描述:
 */
public class AnchorList implements Serializable {


    /**
     * code : 200
     * msg : 客服主播/游戏主播
     * data : [{"id":1,"type":0,"nickname":"陈木木","live_id":1,"cover_picture":""},{"id":2,"type":1,"nickname":"小佳","live_id":2,"cover_picture":""}]
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
         * id : 1
         * type : 0
         * nickname : 陈木木
         * live_id : 1
         * cover_picture :
         */

        private int id;
        private int type;
        private String nickname;
        private int live_id;
        private String cover_picture;
        private int heat;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getLive_id() {
            return live_id;
        }

        public void setLive_id(int live_id) {
            this.live_id = live_id;
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
    }
}
