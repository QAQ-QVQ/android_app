package com.hjy.baserequest.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/11 18:09
 * 描述:
 */
public class FindBanner implements Serializable {


    /**
     * code : 200
     * msg : 广告轮播列表
     * data : {"list":[{"id":2,"title":"中华小当佳","type":1,"resource":"{\"http://img2.178.com/news/201610/271839032469/271909322202.jpg\",\"http://img4.178.com/news/201610/270804954065/o_270804986412.jpg\"}","is_jump":1,"link_url":"http://baidu.com"},{"id":1,"title":"无极键道","type":0,"resource_url":"https://qiyuapp.oss-cn-beijing.aliyuncs.com/images/1.png","is_jump":0,"link_url":"http://baidu.com"}],"count":2}
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
         * list : [{"id":2,"title":"中华小当佳","type":1,"resource":"{\"http://img2.178.com/news/201610/271839032469/271909322202.jpg\",\"http://img4.178.com/news/201610/270804954065/o_270804986412.jpg\"}","is_jump":1,"link_url":"http://baidu.com"},{"id":1,"title":"无极键道","type":0,"resource_url":"https://qiyuapp.oss-cn-beijing.aliyuncs.com/images/1.png","is_jump":0,"link_url":"http://baidu.com"}]
         * count : 2
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
             * title : 广告1
             * type : 1
             * resource : http://img2.178.com/news/201610/271839032469/271909322202.jpg
             * is_jump : 1
             * link_url : http://img5.178.com/news/201610/271839032469/271909440225.jpg
             * second_resource : http://img4.178.com/news/201610/270804954065/o_270804986412.jpg
             */

            private int id;
            private String title;
            private int type;
            private String resource;
            private int is_jump;
            private String link_url;
            private String second_resource;

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

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getResource() {
                return resource;
            }

            public void setResource(String resource) {
                this.resource = resource;
            }

            public int getIs_jump() {
                return is_jump;
            }

            public void setIs_jump(int is_jump) {
                this.is_jump = is_jump;
            }

            public String getLink_url() {
                return link_url;
            }

            public void setLink_url(String link_url) {
                this.link_url = link_url;
            }

            public String getSecond_resource() {
                return second_resource;
            }

            public void setSecond_resource(String second_resource) {
                this.second_resource = second_resource;
            }
        }
    }
}
