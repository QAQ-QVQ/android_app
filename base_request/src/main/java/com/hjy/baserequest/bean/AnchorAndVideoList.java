package com.hjy.baserequest.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/17 17:39
 * 描述:
 */
public class AnchorAndVideoList implements Serializable {


    /**
     * code : 200
     * msg : 搜索
     * data : {"anchor_list":[{"id":1,"type":1,"nickname":"大脑斧","live_id":1,"cover_picture":"http://tupian.com/setu.png","heat":123},{"id":2,"type":1,"nickname":"特斯特","live_id":2,"cover_picture":"http://tupian.com/setu2.png","heat":124}],"video_list":[{"id":4,"title":"test4","cover_picture":"test4","video_url":"","sum_play":1,"sum_like":0,"release_time":0},{"id":3,"title":"test3","cover_picture":"test3","video_url":"","sum_play":1,"sum_like":0,"release_time":0},{"id":2,"title":"test2","cover_picture":"test2","video_url":"","sum_play":1,"sum_like":0,"release_time":0}]}
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
        private List<AnchorListBean> anchor_list;
        private List<VideoListBean> video_list;

        public List<AnchorListBean> getAnchor_list() {
            return anchor_list;
        }

        public void setAnchor_list(List<AnchorListBean> anchor_list) {
            this.anchor_list = anchor_list;
        }

        public List<VideoListBean> getVideo_list() {
            return video_list;
        }

        public void setVideo_list(List<VideoListBean> video_list) {
            this.video_list = video_list;
        }

        public static class AnchorListBean implements Serializable {
            /**
             * id : 1
             * type : 1
             * nickname : 大脑斧
             * live_id : 1
             * cover_picture : http://tupian.com/setu.png
             * heat : 123
             */

            private int id;
            private int type;
            private String nickname;
            private int live_id;
            private String cover_picture;
            private int heat;

            private String title;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

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

        public static class VideoListBean implements Serializable {
            /**
             * id : 4
             * title : test4
             * cover_picture : test4
             * video_url :
             * sum_play : 1
             * sum_like : 0
             * release_time : 0
             */

            private int id;
            private String title;
            private String cover_picture;
            private String video_url;
            private int sum_play;
            private int sum_like;
            private int release_time;

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

            public String getVideo_url() {
                return video_url;
            }

            public void setVideo_url(String video_url) {
                this.video_url = video_url;
            }

            public int getSum_play() {
                return sum_play;
            }

            public void setSum_play(int sum_play) {
                this.sum_play = sum_play;
            }

            public int getSum_like() {
                return sum_like;
            }

            public void setSum_like(int sum_like) {
                this.sum_like = sum_like;
            }

            public int getRelease_time() {
                return release_time;
            }

            public void setRelease_time(int release_time) {
                this.release_time = release_time;
            }
        }
    }
}
