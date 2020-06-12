package com.hjy.baserequest.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/12 14:11
 * 描述:
 */
public class NewsList implements Serializable {


    /**
     * code : 200
     * msg : 资讯列表
     * data : {"list":[{"id":1,"title":"test","cover_picture":"https://qiyuapp.oss-cn-beijing.aliyuncs.com/images/1.png"}],"count":1}
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
         * list : [{"id":1,"title":"test","cover_picture":"https://qiyuapp.oss-cn-beijing.aliyuncs.com/images/1.png"}]
         * count : 1
         */

        private int count;
        private List<ListBean> list;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable {
            /**
             * id : 1
             * title : test
             * cover_picture : https://qiyuapp.oss-cn-beijing.aliyuncs.com/images/1.png
             */

            private int id;
            private String title;
            private String cover_picture;

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
        }
    }
}
