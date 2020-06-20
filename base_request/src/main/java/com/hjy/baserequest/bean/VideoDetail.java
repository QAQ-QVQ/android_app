package com.hjy.baserequest.bean;

import java.io.Serializable;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/20 14:38
 * 描述:
 */
public class VideoDetail implements Serializable {


    /**
     * code : 200
     * msg : 视频详情
     * data : {"id":1,"title":"此为标题","cover_picture":"beijing.aliyuncs.com/images/1.png","video_url":"","sum_play":0,"sum_like":1,"release_time":1590995524,"status":1,"create_time":1590995524}
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
        /**
         * id : 1
         * title : 此为标题
         * cover_picture : beijing.aliyuncs.com/images/1.png
         * video_url :
         * sum_play : 0
         * sum_like : 1
         * release_time : 1590995524
         * status : 1
         * create_time : 1590995524
         */

        private int id;
        private String title;
        private String cover_picture;
        private String video_url;
        private int sum_play;
        private int sum_like;
        private int release_time;
        private int status;
        private int create_time;

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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }
    }
}
