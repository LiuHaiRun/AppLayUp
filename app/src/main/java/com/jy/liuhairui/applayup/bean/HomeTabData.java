package com.jy.liuhairui.applayup.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class HomeTabData {


    /**
     * errCode : 0
     * message :
     * data : {"list":[{"id":104,"label":"热门","recommend":false,"type":"hot","api":"https://bkbapi.dqdgame.com/app/tabs/android/104.json?mark=gif&version=132"},{"id":1,"label":"头条","recommend":true,"type":"normal","api":"https://bkbapi.dqdgame.com/app/tabs/android/1.json?mark=gif&version=132","index_match_url":"https://sport-data.dqdgame.com/sd/biz/live/index?platform=android&version=132"},{"id":121,"label":"NBA","recommend":false,"type":"normal","api":"https://bkbapi.dqdgame.com/app/tabs/android/121.json?mark=gif&version=132"},{"id":123,"label":"中国篮球","recommend":false,"type":"normal","api":"https://bkbapi.dqdgame.com/app/tabs/android/123.json?mark=gif&version=132"},{"id":11,"label":"集锦","recommend":false,"type":"video","api":"https://bkbapi.dqdgame.com/app/tabs/android/11.json?mark=gif&version=132"},{"id":106,"label":"INS","recommend":false,"type":"wall","api":"https://bkbapi.dqdgame.com/app/tabs/wall.json?version=132"},{"id":55,"label":"深度","recommend":false,"type":"normal","api":"https://bkbapi.dqdgame.com/app/tabs/android/55.json?mark=gif&version=132"},{"id":37,"label":"闲情","recommend":false,"type":"normal","api":"https://bkbapi.dqdgame.com/app/tabs/android/37.json?mark=gif&version=132"},{"id":125,"label":"世界杯","recommend":false,"type":"normal","api":"https://bkbapi.dqdgame.com/app/tabs/android/125.json?mark=gif&version=132"},{"id":99,"label":"专题","recommend":false,"type":"classification","api":"https://bkbapi.dqdgame.com/app/tabs/android/classifications.json?version=132"}],"default_index":1,"big_image_slide":0}
     */

    private int errCode;
    private String message;
    private DataBean data;

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * list : [{"id":104,"label":"热门","recommend":false,"type":"hot","api":"https://bkbapi.dqdgame.com/app/tabs/android/104.json?mark=gif&version=132"},{"id":1,"label":"头条","recommend":true,"type":"normal","api":"https://bkbapi.dqdgame.com/app/tabs/android/1.json?mark=gif&version=132","index_match_url":"https://sport-data.dqdgame.com/sd/biz/live/index?platform=android&version=132"},{"id":121,"label":"NBA","recommend":false,"type":"normal","api":"https://bkbapi.dqdgame.com/app/tabs/android/121.json?mark=gif&version=132"},{"id":123,"label":"中国篮球","recommend":false,"type":"normal","api":"https://bkbapi.dqdgame.com/app/tabs/android/123.json?mark=gif&version=132"},{"id":11,"label":"集锦","recommend":false,"type":"video","api":"https://bkbapi.dqdgame.com/app/tabs/android/11.json?mark=gif&version=132"},{"id":106,"label":"INS","recommend":false,"type":"wall","api":"https://bkbapi.dqdgame.com/app/tabs/wall.json?version=132"},{"id":55,"label":"深度","recommend":false,"type":"normal","api":"https://bkbapi.dqdgame.com/app/tabs/android/55.json?mark=gif&version=132"},{"id":37,"label":"闲情","recommend":false,"type":"normal","api":"https://bkbapi.dqdgame.com/app/tabs/android/37.json?mark=gif&version=132"},{"id":125,"label":"世界杯","recommend":false,"type":"normal","api":"https://bkbapi.dqdgame.com/app/tabs/android/125.json?mark=gif&version=132"},{"id":99,"label":"专题","recommend":false,"type":"classification","api":"https://bkbapi.dqdgame.com/app/tabs/android/classifications.json?version=132"}]
         * default_index : 1
         * big_image_slide : 0
         */

        private int default_index;
        private int big_image_slide;
        private List<ListBean> list;

        public int getDefault_index() {
            return default_index;
        }

        public void setDefault_index(int default_index) {
            this.default_index = default_index;
        }

        public int getBig_image_slide() {
            return big_image_slide;
        }

        public void setBig_image_slide(int big_image_slide) {
            this.big_image_slide = big_image_slide;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Parcelable {
            /**
             * id : 104
             * label : 热门
             * recommend : false
             * type : hot
             * api : https://bkbapi.dqdgame.com/app/tabs/android/104.json?mark=gif&version=132
             * index_match_url : https://sport-data.dqdgame.com/sd/biz/live/index?platform=android&version=132
             */

            private int id;
            private String label;
            private boolean recommend;
            private String type;
            private String api;
            private String index_match_url;

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

            public boolean isRecommend() {
                return recommend;
            }

            public void setRecommend(boolean recommend) {
                this.recommend = recommend;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getApi() {
                return api;
            }

            public void setApi(String api) {
                this.api = api;
            }

            public String getIndex_match_url() {
                return index_match_url;
            }

            public void setIndex_match_url(String index_match_url) {
                this.index_match_url = index_match_url;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.id);
                dest.writeString(this.label);
                dest.writeByte(this.recommend ? (byte) 1 : (byte) 0);
                dest.writeString(this.type);
                dest.writeString(this.api);
                dest.writeString(this.index_match_url);
            }

            public ListBean() {
            }

            protected ListBean(Parcel in) {
                this.id = in.readInt();
                this.label = in.readString();
                this.recommend = in.readByte() != 0;
                this.type = in.readString();
                this.api = in.readString();
                this.index_match_url = in.readString();
            }

            public static final Parcelable.Creator<ListBean> CREATOR = new Parcelable.Creator<ListBean>() {
                @Override
                public ListBean createFromParcel(Parcel source) {
                    return new ListBean(source);
                }

                @Override
                public ListBean[] newArray(int size) {
                    return new ListBean[size];
                }
            };
        }
    }
}
