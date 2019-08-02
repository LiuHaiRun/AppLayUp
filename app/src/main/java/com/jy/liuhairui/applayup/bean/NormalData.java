package com.jy.liuhairui.applayup.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class NormalData {

    /**
     * id : 1
     * label : 头条
     * prev : https://bkbapi.dqdgame.com/app/tabs/android/1.json?before=1718437134&mark=gif&version=132&from=tab_1
     * fresh : https://bkbapi.dqdgame.com/app/tabs/android/1.json?action=fresh&mark=gif&version=132&from=tab_1
     * next : https://bkbapi.dqdgame.com/app/tabs/android/1.json?after=1560500810&page=2&mark=gif&version=132&from=tab_1
     * max : 1718437134
     * min : 1560500810
     * page : 1
     * hotwords : 湖人
     */

    private int id;
    private String label;
    private String prev;
    private String fresh;
    private String next;
    private int max;
    private int min;
    private int page;
    private String hotwords;
    private List<ArticlesBean> articles;
    private List<RecommendBean> recommend;

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

    public String getHotwords() {
        return hotwords;
    }

    public void setHotwords(String hotwords) {
        this.hotwords = hotwords;
    }

    public List<ArticlesBean> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticlesBean> articles) {
        this.articles = articles;
    }

    public List<RecommendBean> getRecommend() {
        return recommend;
    }

    public void setRecommend(List<RecommendBean> recommend) {
        this.recommend = recommend;
    }

    public static class ArticlesBean implements Parcelable {

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
        private VideoInfoBean video_info;
        private CoverBean cover;
        private String label;
        private String label_color;

        //关注的数据
        public FavTeamEntity mFavTeamEntity;

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

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getLabel_color() {
            return label_color;
        }

        public void setLabel_color(String label_color) {
            this.label_color = label_color;
        }

        public static class VideoInfoBean {

            private String video_src;
            private String video_hash;
            private String video_time;
            private String video_mode;
            private int video_width;
            private int video_height;
            private int visit_total;
            private int vertical_screen;
            private int size;

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

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }
        }

        public static class CoverBean {
            /**
             * pic : https://img1.dqdgame.com/fastdfs/M00/04/88/640x400/-/-/rB8AdV0Dmz-AbSZvAAI8p_cW7pU944.jpg
             */

            private String pic;

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.title);
            dest.writeString(this.share_title);
            dest.writeString(this.description);
            dest.writeString(this.b_description);
            dest.writeInt(this.comments_total);
            dest.writeString(this.share);
            dest.writeString(this.thumb);
            dest.writeByte(this.top ? (byte) 1 : (byte) 0);
            dest.writeString(this.top_color);
            dest.writeString(this.url);
            dest.writeString(this.url1);
            dest.writeString(this.scheme);
            dest.writeByte(this.is_video ? (byte) 1 : (byte) 0);
            dest.writeString(this.add_to_tab);
            dest.writeByte(this.is_redirect_h5 ? (byte) 1 : (byte) 0);
            dest.writeString(this.ignore);
            dest.writeString(this.template);
            dest.writeByte(this.show_comments ? (byte) 1 : (byte) 0);
            dest.writeString(this.published_at);
            dest.writeInt(this.sort_timestamp);
            dest.writeString(this.channel);
            dest.writeString(this.label);
            dest.writeString(this.label_color);
            dest.writeParcelable(this.mFavTeamEntity, flags);
        }

        public ArticlesBean() {
        }

        protected ArticlesBean(Parcel in) {
            this.id = in.readInt();
            this.title = in.readString();
            this.share_title = in.readString();
            this.description = in.readString();
            this.b_description = in.readString();
            this.comments_total = in.readInt();
            this.share = in.readString();
            this.thumb = in.readString();
            this.top = in.readByte() != 0;
            this.top_color = in.readString();
            this.url = in.readString();
            this.url1 = in.readString();
            this.scheme = in.readString();
            this.is_video = in.readByte() != 0;
            this.add_to_tab = in.readString();
            this.is_redirect_h5 = in.readByte() != 0;
            this.ignore = in.readString();
            this.template = in.readString();
            this.show_comments = in.readByte() != 0;
            this.published_at = in.readString();
            this.sort_timestamp = in.readInt();
            this.channel = in.readString();
            this.video_info = in.readParcelable(VideoInfoBean.class.getClassLoader());
            this.cover = in.readParcelable(CoverBean.class.getClassLoader());
            this.label = in.readString();
            this.label_color = in.readString();
            this.mFavTeamEntity = in.readParcelable(FavTeamEntity.class.getClassLoader());
        }

        public static final Parcelable.Creator<ArticlesBean> CREATOR = new Parcelable.Creator<ArticlesBean>() {
            @Override
            public ArticlesBean createFromParcel(Parcel source) {
                return new ArticlesBean(source);
            }

            @Override
            public ArticlesBean[] newArray(int size) {
                return new ArticlesBean[size];
            }
        };
    }

    public static class RecommendBean {
        /**
         * id : 55063
         * title : 猛龙赛季总结：最佳经理乌杰里+FMVP小卡=猛龙总冠军
         * share_title : 猛龙赛季总结：最佳经理乌杰里+FMVP小卡=猛龙总冠军
         * description :
         * b_description :
         * comments_total : 55
         * share : https://www.dqdgame.com/article/55063
         * thumb : https://img1.dqdgame.com/fastdfs/M00/04/88/640x400/-/-/rB8AdV0DZhuALu8IAAThJKyYang116.png
         * top : false
         * top_color :
         * url : https://bkbapi.dqdgame.com/article/55063.html
         * url1 : https://bkbapi.dqdgame.com/article/55063.html
         * scheme : shanglan://news/55063
         * is_video : false
         * new_video_detail : null
         * collection_type : null
         * add_to_tab : 0
         * is_redirect_h5 : false
         * ignore :
         * template : news.html
         * show_comments : true
         * published_at : 2019-06-15 10:00:43
         * sort_timestamp : 1560564043
         * channel : article
         * label : 深度
         * label_color : #4782c4
         */

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
        private String label;
        private String label_color;

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

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getLabel_color() {
            return label_color;
        }

        public void setLabel_color(String label_color) {
            this.label_color = label_color;
        }


    }
}
