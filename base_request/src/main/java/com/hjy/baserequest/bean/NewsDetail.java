package com.hjy.baserequest.bean;

import java.io.Serializable;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/17 16:04
 * 描述:
 */
public class NewsDetail implements Serializable {


    /**
     * code : 200
     * msg : 资讯详情
     * data : {"id":1,"title":"资讯1test","cover_picture":"[\"https://dss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2534506313,1688529724&fm=26&gp=0.jpg\",\"https://dss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3892521478,1695688217&fm=26&gp=0.jpg\"]","content":"笑死哈哈哈哈哈哈哈哈哈哈哈哈哈哈\u2026\u2026\r\n\r\n转载自微博@白头叔，更新后续。\r\n![](https://pic2.zhimg.com/80/v2-d0a804c73b217c48b84c349c5743d25a_720w.jpg)\r\n![](https://pic4.zhimg.com/80/v2-2d7b5b4c1d59d88d8fc6d31289761867_720w.jpg)\r\n![](https://pic3.zhimg.com/80/v2-3f9f4ad0cc004d62923a7fd11709bed6_720w.jpg)\r\n![](https://pic2.zhimg.com/80/v2-03b269974651b42c041359a800e1be27_720w.jpg)\r\n![](https://pic3.zhimg.com/80/v2-0cab7c44888ce9cb1b19018772595353_720w.jpg)\r\n![](https://pic3.zhimg.com/80/v2-cf7bec8d38ada7ba374fb162e553882d_720w.jpg)\r\n![](https://pic3.zhimg.com/80/v2-77c57aade1353dcdf2d03e31a2374004_720w.jpg)\r\n![](https://pic4.zhimg.com/80/v2-85910b43c1277341b6216c2340780737_720w.jpg)\r\n![](https://pic3.zhimg.com/80/v2-6d0d79c3e44ac2447209e93b647c14f8_720w.jpg)","sum_view":2,"release_time":1591601209}
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
         * title : 资讯1test
         * cover_picture : ["https://dss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2534506313,1688529724&fm=26&gp=0.jpg","https://dss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3892521478,1695688217&fm=26&gp=0.jpg"]
         * content : 笑死哈哈哈哈哈哈哈哈哈哈哈哈哈哈……
         * <p>
         * 转载自微博@白头叔，更新后续。
         * ![](https://pic2.zhimg.com/80/v2-d0a804c73b217c48b84c349c5743d25a_720w.jpg)
         * ![](https://pic4.zhimg.com/80/v2-2d7b5b4c1d59d88d8fc6d31289761867_720w.jpg)
         * ![](https://pic3.zhimg.com/80/v2-3f9f4ad0cc004d62923a7fd11709bed6_720w.jpg)
         * ![](https://pic2.zhimg.com/80/v2-03b269974651b42c041359a800e1be27_720w.jpg)
         * ![](https://pic3.zhimg.com/80/v2-0cab7c44888ce9cb1b19018772595353_720w.jpg)
         * ![](https://pic3.zhimg.com/80/v2-cf7bec8d38ada7ba374fb162e553882d_720w.jpg)
         * ![](https://pic3.zhimg.com/80/v2-77c57aade1353dcdf2d03e31a2374004_720w.jpg)
         * ![](https://pic4.zhimg.com/80/v2-85910b43c1277341b6216c2340780737_720w.jpg)
         * ![](https://pic3.zhimg.com/80/v2-6d0d79c3e44ac2447209e93b647c14f8_720w.jpg)
         * sum_view : 2
         * release_time : 1591601209
         */

        private int id;
        private String title;
        private String cover_picture;
        private String content;
        private int sum_view;
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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getSum_view() {
            return sum_view;
        }

        public void setSum_view(int sum_view) {
            this.sum_view = sum_view;
        }

        public int getRelease_time() {
            return release_time;
        }

        public void setRelease_time(int release_time) {
            this.release_time = release_time;
        }
    }
}
