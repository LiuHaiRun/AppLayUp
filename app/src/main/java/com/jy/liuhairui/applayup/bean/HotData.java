package com.jy.liuhairui.applayup.bean;

import java.util.List;

public class HotData {

    /**
     * id : 104
     * label : 热门
     * prev : https://bkbapi.dqdgame.com/app/tabs/android/104.json?before=1718437134&mark=gif&version=132&from=tab_104
     * fresh : https://bkbapi.dqdgame.com/app/tabs/android/104.json?action=fresh&mark=gif&version=132&from=tab_104
     * next : https://bkbapi.dqdgame.com/app/tabs/android/104.json?after=2041.5486937037&page=2&mark=gif&version=132&from=tab_104
     * max : 1718437134
     * min : 2041.548693703704
     * page : 1
     */

    private int id;
    private String label;
    private String prev;
    private String fresh;
    private String next;
    private int max;
    private double min;
    private int page;
    private List<ContentsBean> contents;

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

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<ContentsBean> getContents() {
        return contents;
    }

    public void setContents(List<ContentsBean> contents) {
        this.contents = contents;
    }

    public static class ContentsBean {
        /**
         * day : 2019-06-17
         * articles : [{"id":55303,"title":"声明：上篮APP正式停止内容更新，请大家移步懂球帝D站","share_title":"声明：上篮APP正式停止内容更新，请大家移步懂球帝D站","description":"","b_description":"","comments_total":1205,"share":"https://bkbapi.dqdgame.com/article/55303","thumb":"https://img1.dqdgame.com/fastdfs/M00/04/42/180x135/crop/-/ChOlBlxmdzmAU_WFAACFM_SaJO8692.jpg","top":false,"top_color":"#4782c4","url":"https://bkbapi.dqdgame.com/article/55303.html?from=tab_104","url1":"https://bkbapi.dqdgame.com/article/55303.html?from=tab_104","scheme":"shanglan://news/55303","is_video":false,"new_video_detail":null,"collection_type":null,"add_to_tab":"0","is_redirect_h5":false,"ignore":"","template":"news.html","show_comments":true,"published_at":"2024-06-15 15:38:54","sort_timestamp":1718437134,"channel":"article"}]
         */

        private String day;
        private List<ArticlesBean> articles;

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public List<ArticlesBean> getArticles() {
            return articles;
        }

        public void setArticles(List<ArticlesBean> articles) {
            this.articles = articles;
        }

        //my添加的
        public static class VideoInfo {
            public String video_src;
            public String video_time;
            public String video_width;
            public String video_height;
            public String visit_total;
        }

        public static class ArticlesBean {
            /**
             * id : 55303
             * title : 声明：上篮APP正式停止内容更新，请大家移步懂球帝D站
             * share_title : 声明：上篮APP正式停止内容更新，请大家移步懂球帝D站
             * description :
             * b_description :
             * comments_total : 1205
             * share : https://bkbapi.dqdgame.com/article/55303
             * thumb : https://img1.dqdgame.com/fastdfs/M00/04/42/180x135/crop/-/ChOlBlxmdzmAU_WFAACFM_SaJO8692.jpg
             * top : false
             * top_color : #4782c4
             * url : https://bkbapi.dqdgame.com/article/55303.html?from=tab_104
             * url1 : https://bkbapi.dqdgame.com/article/55303.html?from=tab_104
             * scheme : shanglan://news/55303
             * is_video : false
             * new_video_detail : null
             * collection_type : null
             * add_to_tab : 0
             * is_redirect_h5 : false
             * ignore :
             * template : news.html
             * show_comments : true
             * published_at : 2024-06-15 15:38:54
             * sort_timestamp : 1718437134
             * channel : article
             */

            //my添加的
            public VideoInfo video_info;
            public String label;


            private int id;
            private String title;
            private String share_title;
            private String description;
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
            private Object new_video_detail;
            private Object collection_type;
            private String add_to_tab;
            private boolean is_redirect_h5;
            private String ignore;
            private String template;
            private boolean show_comments;
            private String published_at;
            private int sort_timestamp;
            private String channel;

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

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
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

            public Object getNew_video_detail() {
                return new_video_detail;
            }

            public void setNew_video_detail(Object new_video_detail) {
                this.new_video_detail = new_video_detail;
            }

            public Object getCollection_type() {
                return collection_type;
            }

            public void setCollection_type(Object collection_type) {
                this.collection_type = collection_type;
            }

            public String getAdd_to_tab() {
                return add_to_tab;
            }

            public void setAdd_to_tab(String add_to_tab) {
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
        }
    }
}
