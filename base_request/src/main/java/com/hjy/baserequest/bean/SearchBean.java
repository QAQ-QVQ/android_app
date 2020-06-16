package com.hjy.baserequest.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/15 9:26
 * 描述:
 */
public class SearchBean implements Serializable {


    /**
     * code : 200
     * msg : 搜索
     * data : {"live_list":[{"id":1,"title":"木木的房间标题test","cover_picture":"","nickname":"陈木木","sum_popular":3}],"video_list":[{"id":2,"title":"test","cover_picture":"beijing.aliyuncs.com/images/1.png","sum_play":3}],"game_list":[{"id":3,"name":"test","icon":"","family_count":0,"familys":[]}],"family_list":[{"id":3,"name":"test@侠客模拟器","avatar":"","family_number":"","headcount":100,"game_name":"侠客模拟器"}],"news_list":[{"id":1,"title":"test","cover_picture":"https://qiyuapp.oss-cn-beijing.aliyuncs.com/images/1.png"}]}
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
        private List<LiveListBean> live_list;
        private List<VideoListBean> video_list;
        private List<GameListBean> game_list;
        private List<FamilyListBean> family_list;
        private List<NewsListBean> news_list;

        public List<LiveListBean> getLive_list() {
            return live_list;
        }

        public void setLive_list(List<LiveListBean> live_list) {
            this.live_list = live_list;
        }

        public List<VideoListBean> getVideo_list() {
            return video_list;
        }

        public void setVideo_list(List<VideoListBean> video_list) {
            this.video_list = video_list;
        }

        public List<GameListBean> getGame_list() {
            return game_list;
        }

        public void setGame_list(List<GameListBean> game_list) {
            this.game_list = game_list;
        }

        public List<FamilyListBean> getFamily_list() {
            return family_list;
        }

        public void setFamily_list(List<FamilyListBean> family_list) {
            this.family_list = family_list;
        }

        public List<NewsListBean> getNews_list() {
            return news_list;
        }

        public void setNews_list(List<NewsListBean> news_list) {
            this.news_list = news_list;
        }

        public static class LiveListBean implements Serializable {
            /**
             * id : 1
             * title : 木木的房间标题test
             * cover_picture :
             * nickname : 陈木木
             * sum_popular : 3
             */

            private int id;
            private String title;
            private String cover_picture;
            private String nickname;
            private int sum_popular;
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

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public int getSum_popular() {
                return sum_popular;
            }

            public void setSum_popular(int sum_popular) {
                this.sum_popular = sum_popular;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }

        public static class VideoListBean implements Serializable {
            /**
             * id : 2
             * title : test
             * cover_picture : beijing.aliyuncs.com/images/1.png
             * sum_play : 3
             */

            private int id;
            private String title;
            private String cover_picture;
            private int sum_play;
            private int sum_like;

            public int getSum_like() {
                return sum_like;
            }

            public void setSum_like(int sum_like) {
                this.sum_like = sum_like;
            }

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

            public int getSum_play() {
                return sum_play;
            }

            public void setSum_play(int sum_play) {
                this.sum_play = sum_play;
            }
        }

        public static class GameListBean implements Serializable {

            /**
             * id : 1
             * name : 剑侠情缘IIItest
             * icon : https://jx3.xoyo.com/zt/2020/05/08/fenliuye-pc/assets/img/xfe-layer-9-c0e7a587.png.webp
             * family_count : 3
             * familys : [{"id":1,"avatar":"avatar.png"},{"id":2,"avatar":"avatar"},{"id":3,"avatar":"avatar.png"}]
             */

            private int id;
            private String name;
            private String icon;
            private int family_count;
            private List<FamilysBean> familys;

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

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public int getFamily_count() {
                return family_count;
            }

            public void setFamily_count(int family_count) {
                this.family_count = family_count;
            }

            public List<FamilysBean> getFamilys() {
                return familys;
            }

            public void setFamilys(List<FamilysBean> familys) {
                this.familys = familys;
            }

            public static class FamilysBean implements Serializable {
                /**
                 * id : 1
                 * avatar : avatar.png
                 */

                private int id;
                private String avatar;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getAvatar() {
                    return avatar;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
                }
            }
        }

        public static class FamilyListBean implements Serializable {
            /**
             * id : 3
             * name : test@侠客模拟器
             * avatar :
             * family_number :
             * headcount : 100
             * game_name : 侠客模拟器
             */

            private int id;
            private String name;
            private String avatar;
            private String family_number;
            private int headcount;
            private String game_name;
            private String cp_server_name;

            public String getCp_server_name() {
                return cp_server_name;
            }

            public void setCp_server_name(String cp_server_name) {
                this.cp_server_name = cp_server_name;
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

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getFamily_number() {
                return family_number;
            }

            public void setFamily_number(String family_number) {
                this.family_number = family_number;
            }

            public int getHeadcount() {
                return headcount;
            }

            public void setHeadcount(int headcount) {
                this.headcount = headcount;
            }

            public String getGame_name() {
                return game_name;
            }

            public void setGame_name(String game_name) {
                this.game_name = game_name;
            }
        }

        public static class NewsListBean implements Serializable {
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
