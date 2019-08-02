package com.jy.liuhairui.applayup.bean;

import java.util.List;

public class WallData {


    /**
     * id : 106
     * label : 出墙
     * prev : http://bkbapi.dqdgame.com/app/tabs/wall.json?kind=1&before=2019-06-30 11:43:43
     * next : http://bkbapi.dqdgame.com/app/tabs/wall.json?kind=1&page=2&after=1561762914
     * page : 1
     */

    private int id;
    private String label;
    private String prev;
    private String next;
    private int page;
    private List<FeedlistBean> feedlist;

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

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<FeedlistBean> getFeedlist() {
        return feedlist;
    }

    public void setFeedlist(List<FeedlistBean> feedlist) {
        this.feedlist = feedlist;
    }

    public static class FeedlistBean {
        /**
         * id : 1240204
         * account : kevinlove
         * relate_type : instagram
         * relate_ico : https://img1.dqdgame.com/fastdfs/M00/10/44/ooYBAFhiSbOAA8fQAAAVwh42ydg307.jpg
         * avatar : https://img1.dqdgame.com/fastdfs/M00/01/2D/ChOlBlvm1Z-AFsivAAAg5ztF1RE692.jpg
         * note : 乐福
         * original_text : Before it got Ugly. Thank you to @lauren503 and @channingfrye for a beautiful night!!! #channingsalreadyugly 🍷🍷🍷
         * translation_text : null
         * auto_translation : 在它变得丑陋之前。感谢@lauren503,@channingfrye度过一个美好的夜晚!!!#channingsalreadyugly🍷🍷🍷
         * auto_translation_img : https://img1.dongqiudi.com/fastdfs2/M00/3F/DD/ChNy21pUYy2AKJWxAAAKXO7ObNw197.png
         * comments_total : 0
         * scheme : shanglan://instagram/1240204?sytle=wall
         * user_id : 194908318
         * published_at : 2019-06-30 11:43:43
         * channel : wall
         * album : {"total":1,"pics":[{"url":"https://img1.dqdgame.com/fastdfs/M00/04/90/rB8AdV0YNQ-AEmx9AAKOwlk0Bpo611.jpg","width":640,"height":800,"is_video":false}]}
         */

        private String id;
        private String account;
        private String relate_type;
        private String relate_ico;
        private String avatar;
        private String note;
        private String original_text;
        private Object translation_text;
        private String auto_translation;
        private String auto_translation_img;
        private int comments_total;
        private String scheme;
        private String user_id;
        private String published_at;
        private String channel;
        private AlbumBean album;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getRelate_type() {
            return relate_type;
        }

        public void setRelate_type(String relate_type) {
            this.relate_type = relate_type;
        }

        public String getRelate_ico() {
            return relate_ico;
        }

        public void setRelate_ico(String relate_ico) {
            this.relate_ico = relate_ico;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getOriginal_text() {
            return original_text;
        }

        public void setOriginal_text(String original_text) {
            this.original_text = original_text;
        }

        public Object getTranslation_text() {
            return translation_text;
        }

        public void setTranslation_text(Object translation_text) {
            this.translation_text = translation_text;
        }

        public String getAuto_translation() {
            return auto_translation;
        }

        public void setAuto_translation(String auto_translation) {
            this.auto_translation = auto_translation;
        }

        public String getAuto_translation_img() {
            return auto_translation_img;
        }

        public void setAuto_translation_img(String auto_translation_img) {
            this.auto_translation_img = auto_translation_img;
        }

        public int getComments_total() {
            return comments_total;
        }

        public void setComments_total(int comments_total) {
            this.comments_total = comments_total;
        }

        public String getScheme() {
            return scheme;
        }

        public void setScheme(String scheme) {
            this.scheme = scheme;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getPublished_at() {
            return published_at;
        }

        public void setPublished_at(String published_at) {
            this.published_at = published_at;
        }

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public AlbumBean getAlbum() {
            return album;
        }

        public void setAlbum(AlbumBean album) {
            this.album = album;
        }

        public static class AlbumBean {
            /**
             * total : 1
             * pics : [{"url":"https://img1.dqdgame.com/fastdfs/M00/04/90/rB8AdV0YNQ-AEmx9AAKOwlk0Bpo611.jpg","width":640,"height":800,"is_video":false}]
             */

            private int total;
            private List<PicsBean> pics;

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public List<PicsBean> getPics() {
                return pics;
            }

            public void setPics(List<PicsBean> pics) {
                this.pics = pics;
            }

            public static class PicsBean {
                /**
                 * url : https://img1.dqdgame.com/fastdfs/M00/04/90/rB8AdV0YNQ-AEmx9AAKOwlk0Bpo611.jpg
                 * width : 640
                 * height : 800
                 * is_video : false
                 */

                private String url;
                private int width;
                private int height;
                private boolean is_video;

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }

                public boolean isIs_video() {
                    return is_video;
                }

                public void setIs_video(boolean is_video) {
                    this.is_video = is_video;
                }
            }
        }
    }
}
