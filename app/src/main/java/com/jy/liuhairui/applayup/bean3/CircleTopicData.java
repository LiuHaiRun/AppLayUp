package com.jy.liuhairui.applayup.bean3;

import java.util.List;

public class CircleTopicData {

    private int errno;
    private String errmsg;
    private DataBean data;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<TopicListBean> topic_list;

        public List<TopicListBean> getTopic_list() {
            return topic_list;
        }

        public void setTopic_list(List<TopicListBean> topic_list) {
            this.topic_list = topic_list;
        }

        public static class TopicListBean {
            /**
             * logo_url : http://img1.dqdgame.com/fastdfs/M00/03/04/ChM9m1weFLKAYIxbAAAH30sJJdE633.png
             * title : 今日热点
             * type : line_two
             * list : [{"id":89,"content":"#火箭","scheme":"shanglan://circle/feedlist/89/#火箭"},{"id":90,"content":"#湖人","scheme":"shanglan://circle/feedlist/90/#湖人"},{"id":91,"content":"#勇士","scheme":"shanglan://circle/feedlist/91/#勇士"},{"id":93,"content":"#马刺","scheme":"shanglan://circle/feedlist/93/#马刺"},{"id":94,"content":"#凯尔特人","scheme":"shanglan://circle/feedlist/94/#凯尔特人"},{"id":95,"content":"#猛龙","scheme":"shanglan://circle/feedlist/95/#猛龙"},{"id":160,"content":"#独行侠","scheme":"shanglan://circle/feedlist/160/#独行侠"},{"id":404,"content":"#NBA季后赛","scheme":"shanglan://circle/feedlist/404/#NBA季后赛"}]
             */

            private String logo_url;
            private String title;
            private String type;
            private List<ListBean> list;

            public String getLogo_url() {
                return logo_url;
            }

            public void setLogo_url(String logo_url) {
                this.logo_url = logo_url;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * id : 89
                 * content : #火箭
                 * scheme : shanglan://circle/feedlist/89/#火箭
                 */

                private int id;
                private String content;
                private String scheme;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public String getScheme() {
                    return scheme;
                }

                public void setScheme(String scheme) {
                    this.scheme = scheme;
                }
            }
        }
    }
}
