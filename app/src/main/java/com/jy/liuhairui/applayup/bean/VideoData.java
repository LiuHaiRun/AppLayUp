package com.jy.liuhairui.applayup.bean;

import java.util.List;

public class VideoData {


    /**
     * id : 11
     * label : 集锦
     * prev : https://bkbapi.dqdgame.com/app/tabs/android/11.json?before=1560602907&mark=gif&version=132&from=tab_11
     * fresh : https://bkbapi.dqdgame.com/app/tabs/android/11.json?action=fresh&mark=gif&version=132&from=tab_11
     * next : https://bkbapi.dqdgame.com/app/tabs/android/11.json?after=1560490398&page=2&mark=gif&version=132&from=tab_11
     * max : 1560602907
     * min : 1560490398
     * page : 1
     */

    private int id;
    private String label;
    private String prev;
    private String fresh;
    private String next;
    private int max;
    private int min;
    private int page;
    private List<ArticlesBean> articles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPrev() {
        return prev;
    }

    public void setPrev(String prev) {
        this.prev = prev;
    }

    public String getFresh() {
        return fresh;
    }

    public void setFresh(String fresh) {
        this.fresh = fresh;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<ArticlesBean> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticlesBean> articles) {
        this.articles = articles;
    }

    public static class ArticlesBean {
        /**
         * id : 55233
         * title : 北境之王多伦多猛龙夺冠赛季回顾
         * share_title : 北境之王多伦多猛龙夺冠赛季回顾
         * b_description :
         * comments_total : 7
         * share : https://bkbapi.dqdgame.com/article/55233
         * thumb : https://vthumb.ykimg.com/054206085D04E7F300000145B00A01EC
         * top : false
         * top_color :
         * url : shanglan://video_detail/55233
         * url1 : shanglan://video_detail/55233
         * scheme : shanglan://video_detail/55233
         * is_video : true
         * new_video_detail : 1
         * collection_type : null
         * add_to_tab : null
         * is_redirect_h5 : false
         * ignore :
         * video_info : {"video_src":"https://v.youku.com/v_show/id_XNDIyOTUwMTQzMg==.html?refer=ykty-dqd","video_hash":"eb67cf38f0c24d81fb2b80cfe5446c3e","video_time":"","video_mode":"h5","video_width":0,"video_height":0,"visit_total":1927,"vertical_screen":0}
         * cover : {"pic":"https://vthumb.ykimg.com/054206085D04E7F300000145B00A01EC"}
         * template : top.html
         * show_comments : true
         * published_at : 2019-06-15 20:48:27
         * sort_timestamp : 1560602907
         * channel : video
         * description :
         */

        private int id;
        private String title;
        private String share_title;
        private String b_description;
        private int comments_total;
        private String share;
        private String thumb;
        private boolean top;
        private String top_color;
        private String url;
        private String url1;
        private String scheme;
        private boolean is_video;
        private int new_video_detail;
        private Object collection_type;
        private Object add_to_tab;
        private boolean is_redirect_h5;
        private String ignore;
        private VideoInfoBean video_info;
        private CoverBean cover;
        private String template;
        private boolean show_comments;
        private String published_at;
        private int sort_timestamp;
        private String channel;
        private String description;

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

        public String getShare_title() {
            return share_title;
        }

        public void setShare_title(String share_title) {
            this.share_title = share_title;
        }

        public String getB_description() {
            return b_description;
        }

        public void setB_description(String b_description) {
            this.b_description = b_description;
        }

        public int getComments_total() {
            return comments_total;
        }

        public void setComments_total(int comments_total) {
            this.comments_total = comments_total;
        }

        public String getShare() {
            return share;
        }

        public void setShare(String share) {
            this.share = share;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public boolean isTop() {
            return top;
        }

        public void setTop(boolean top) {
            this.top = top;
        }

        public String getTop_color() {
            return top_color;
        }

        public void setTop_color(String top_color) {
            this.top_color = top_color;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrl1() {
            return url1;
        }

        public void setUrl1(String url1) {
            this.url1 = url1;
        }

        public String getScheme() {
            return scheme;
        }

        public void setScheme(String scheme) {
            this.scheme = scheme;
        }

        public boolean isIs_video() {
            return is_video;
        }

        public void setIs_video(boolean is_video) {
            this.is_video = is_video;
        }

        public int getNew_video_detail() {
            return new_video_detail;
        }

        public void setNew_video_detail(int new_video_detail) {
            this.new_video_detail = new_video_detail;
        }

        public Object getCollection_type() {
            return collection_type;
        }

        public void setCollection_type(Object collection_type) {
            this.collection_type = collection_type;
        }

        public Object getAdd_to_tab() {
            return add_to_tab;
        }

        public void setAdd_to_tab(Object add_to_tab) {
            this.add_to_tab = add_to_tab;
        }

        public boolean isIs_redirect_h5() {
            return is_redirect_h5;
        }

        public void setIs_redirect_h5(boolean is_redirect_h5) {
            this.is_redirect_h5 = is_redirect_h5;
        }

        public String getIgnore() {
            return ignore;
        }

        public void setIgnore(String ignore) {
            this.ignore = ignore;
        }

        public VideoInfoBean getVideo_info() {
            return video_info;
        }

        public void setVideo_info(VideoInfoBean video_info) {
            this.video_info = video_info;
        }

        public CoverBean getCover() {
            return cover;
        }

        public void setCover(CoverBean cover) {
            this.cover = cover;
        }

        public String getTemplate() {
            return template;
        }

        public void setTemplate(String template) {
            this.template = template;
        }

        public boolean isShow_comments() {
            return show_comments;
        }

        public void setShow_comments(boolean show_comments) {
            this.show_comments = show_comments;
        }

        public String getPublished_at() {
            return published_at;
        }

        public void setPublished_at(String published_at) {
            this.published_at = published_at;
        }

        public int getSort_timestamp() {
            return sort_timestamp;
        }

        public void setSort_timestamp(int sort_timestamp) {
            this.sort_timestamp = sort_timestamp;
        }

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public static class VideoInfoBean {
            /**
             * video_src : https://v.youku.com/v_show/id_XNDIyOTUwMTQzMg==.html?refer=ykty-dqd
             * video_hash : eb67cf38f0c24d81fb2b80cfe5446c3e
             * video_time :
             * video_mode : h5
             * video_width : 0
             * video_height : 0
             * visit_total : 1927
             * vertical_screen : 0
             */

            private String video_src;
            private String video_hash;
            private String video_time;
            private String video_mode;
            private int video_width;
            private int video_height;
            private int visit_total;
            private int vertical_screen;

            public String getVideo_src() {
                return video_src;
            }

            public void setVideo_src(String video_src) {
                this.video_src = video_src;
            }

            public String getVideo_hash() {
                return video_hash;
            }

            public void setVideo_hash(String video_hash) {
                this.video_hash = video_hash;
            }

            public String getVideo_time() {
                return video_time;
            }

            public void setVideo_time(String video_time) {
                this.video_time = video_time;
            }

            public String getVideo_mode() {
                return video_mode;
            }

            public void setVideo_mode(String video_mode) {
                this.video_mode = video_mode;
            }

            public int getVideo_width() {
                return video_width;
            }

            public void setVideo_width(int video_width) {
                this.video_width = video_width;
            }

            public int getVideo_height() {
                return video_height;
            }

            public void setVideo_height(int video_height) {
                this.video_height = video_height;
            }

            public int getVisit_total() {
                return visit_total;
            }

            public void setVisit_total(int visit_total) {
                this.visit_total = visit_total;
            }

            public int getVertical_screen() {
                return vertical_screen;
            }

            public void setVertical_screen(int vertical_screen) {
                this.vertical_screen = vertical_screen;
            }
        }

        public static class CoverBean {
            /**
             * pic : https://vthumb.ykimg.com/054206085D04E7F300000145B00A01EC
             */

            private String pic;

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }
        }
    }
}
