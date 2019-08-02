package com.jy.liuhairui.applayup.bean2;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class MatchTabData implements Parcelable {


    /**
     * data_tabs : [{"id":1,"label":"NBA","logo":"","competition_id":"1","season":{"title":"2019季后赛","url":"http://sport-data.dqdgame.com/sd/biz/data/seasons?competition_id=1&version=0&platform="},"position":1,"last_modify":1537895402,"country_code":"","sub_tabs":[{"title":"排名","url":"http://sport-data.dqdgame.com/sd/biz/data/standing?season_id=119&version=0&platform="},{"title":"球员","url":"http://sport-data.dqdgame.com/sd/biz/data/ranking/person?season_id=119&version=0&platform=&type=person"},{"title":"球队","url":"http://sport-data.dqdgame.com/sd/biz/data/ranking/team?season_id=119&version=0&platform=&type=team"},{"title":"赛程","url":"http://sport-data.dqdgame.com/sd/biz/data/schedule?season_id=119&version=0&platform="}]},{"id":2,"label":"CBA","logo":"","competition_id":"2","season":{"title":"2018-19","url":"http://sport-data.dqdgame.com/sd/biz/data/seasons?competition_id=2&version=0&platform="},"position":2,"last_modify":1537895403,"country_code":"","sub_tabs":[{"title":"排名","url":"http://sport-data.dqdgame.com/sd/biz/data/standing?season_id=103&version=0&platform="},{"title":"球员","url":"http://sport-data.dqdgame.com/sd/biz/data/ranking/person?season_id=103&version=0&platform=&type=person"},{"title":"赛程","url":"http://sport-data.dqdgame.com/sd/biz/data/schedule?season_id=103&version=0&platform="}]},{"id":6,"label":"CUBA","logo":"","competition_id":"8","season":{"title":"2018-19","url":"http://sport-data.dqdgame.com/sd/biz/data/seasons?competition_id=8&version=0&platform="},"position":3,"last_modify":1552581178,"country_code":"","sub_tabs":[{"title":"排名","url":"http://sport-data.dqdgame.com/sd/biz/data/standing?season_id=116&version=0&platform="},{"title":"赛程","url":"http://sport-data.dqdgame.com/sd/biz/data/schedule?season_id=116&version=0&platform="}]},{"id":3,"label":"欧冠联","logo":"","competition_id":"3","season":{"title":"2018-19","url":"http://sport-data.dqdgame.com/sd/biz/data/seasons?competition_id=3&version=0&platform="},"position":4,"last_modify":1552581190,"country_code":"","sub_tabs":[{"title":"排名","url":"http://sport-data.dqdgame.com/sd/biz/data/standing?season_id=105&version=0&platform="},{"title":"赛程","url":"http://sport-data.dqdgame.com/sd/biz/data/schedule?season_id=105&version=0&platform="}]},{"id":4,"label":"日甲","logo":"","competition_id":"4","season":{"title":"2018-19","url":"http://sport-data.dqdgame.com/sd/biz/data/seasons?competition_id=4&version=0&platform="},"position":5,"last_modify":1552581187,"country_code":"","sub_tabs":[{"title":"排名","url":"http://sport-data.dqdgame.com/sd/biz/data/standing?season_id=109&version=0&platform="},{"title":"赛程","url":"http://sport-data.dqdgame.com/sd/biz/data/schedule?season_id=109&version=0&platform="}]},{"id":5,"label":"韩甲","logo":"","competition_id":"5","season":{"title":"2018-19","url":"http://sport-data.dqdgame.com/sd/biz/data/seasons?competition_id=5&version=0&platform="},"position":6,"last_modify":1552581182,"country_code":"","sub_tabs":[{"title":"排名","url":"http://sport-data.dqdgame.com/sd/biz/data/standing?season_id=110&version=0&platform="},{"title":"赛程","url":"http://sport-data.dqdgame.com/sd/biz/data/schedule?season_id=110&version=0&platform="}]}]
     * default_index : 1
     * live_index : http://sport-data.dqdgame.com/sd/biz/live/index?platform=&version=0
     * live_tabs : [{"id":5,"label":"关注","sort":1,"api":"https://bkbapi.dqdgame.com/data/tab/fav_new?version=0&platform=","type":"favor"},{"id":3,"label":"重要","sort":2,"api":"http://sport-data.dqdgame.com/sd/biz/live/important?version=0&platform=","type":"important","default_index":"1"},{"id":1,"label":"NBA","sort":3,"api":"http://sport-data.dqdgame.com/sd/biz/live/league/1?version=0&platform=","type":"league"},{"id":2,"label":"CBA","sort":4,"api":"http://sport-data.dqdgame.com/sd/biz/live/league/2?version=0&platform=","type":"league"},{"id":10,"label":"CUBA","sort":5,"api":"http://sport-data.dqdgame.com/sd/biz/live/league/8?version=0&platform=","type":"league"},{"id":12,"label":"3x3","sort":6,"api":"http://sport-data.dqdgame.com/sd/biz/live/league/9?version=0&platform=","type":"league"},{"id":7,"label":"欧冠联","sort":8,"api":"http://sport-data.dqdgame.com/sd/biz/live/league/3?version=0&platform=","type":"league"},{"id":8,"label":"日甲","sort":9,"api":"http://sport-data.dqdgame.com/sd/biz/live/league/4?version=0&platform=","type":"league"},{"id":9,"label":"韩甲","sort":10,"api":"http://sport-data.dqdgame.com/sd/biz/live/league/5?version=0&platform=","type":"league"}]
     */

    private String default_index;
    private String live_index;
    private List<DataTabsBean> data_tabs;
    private List<LiveTabsBean> live_tabs;

    public String getDefault_index() {
        return default_index;
    }

    public void setDefault_index(String default_index) {
        this.default_index = default_index;
    }

    public String getLive_index() {
        return live_index;
    }

    public void setLive_index(String live_index) {
        this.live_index = live_index;
    }

    public List<DataTabsBean> getData_tabs() {
        return data_tabs;
    }

    public void setData_tabs(List<DataTabsBean> data_tabs) {
        this.data_tabs = data_tabs;
    }

    public List<LiveTabsBean> getLive_tabs() {
        return live_tabs;
    }

    public void setLive_tabs(List<LiveTabsBean> live_tabs) {
        this.live_tabs = live_tabs;
    }

    public static class DataTabsBean implements Parcelable {
        /**
         * id : 1
         * label : NBA
         * logo :
         * competition_id : 1
         * season : {"title":"2019季后赛","url":"http://sport-data.dqdgame.com/sd/biz/data/seasons?competition_id=1&version=0&platform="}
         * position : 1
         * last_modify : 1537895402
         * country_code :
         * sub_tabs : [{"title":"排名","url":"http://sport-data.dqdgame.com/sd/biz/data/standing?season_id=119&version=0&platform="},{"title":"球员","url":"http://sport-data.dqdgame.com/sd/biz/data/ranking/person?season_id=119&version=0&platform=&type=person"},{"title":"球队","url":"http://sport-data.dqdgame.com/sd/biz/data/ranking/team?season_id=119&version=0&platform=&type=team"},{"title":"赛程","url":"http://sport-data.dqdgame.com/sd/biz/data/schedule?season_id=119&version=0&platform="}]
         */

        private int id;
        private String label;
        private String logo;
        private String competition_id;
        private SeasonBean season;
        private int position;
        private int last_modify;
        private String country_code;
        private List<SubTabsBean> sub_tabs;

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

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getCompetition_id() {
            return competition_id;
        }

        public void setCompetition_id(String competition_id) {
            this.competition_id = competition_id;
        }

        public SeasonBean getSeason() {
            return season;
        }

        public void setSeason(SeasonBean season) {
            this.season = season;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public int getLast_modify() {
            return last_modify;
        }

        public void setLast_modify(int last_modify) {
            this.last_modify = last_modify;
        }

        public String getCountry_code() {
            return country_code;
        }

        public void setCountry_code(String country_code) {
            this.country_code = country_code;
        }

        public List<SubTabsBean> getSub_tabs() {
            return sub_tabs;
        }

        public void setSub_tabs(List<SubTabsBean> sub_tabs) {
            this.sub_tabs = sub_tabs;
        }

        public static class SeasonBean implements Parcelable {
            /**
             * title : 2019季后赛
             * url : http://sport-data.dqdgame.com/sd/biz/data/seasons?competition_id=1&version=0&platform=
             */

            private String title;
            private String url;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.title);
                dest.writeString(this.url);
            }

            public SeasonBean() {
            }

            protected SeasonBean(Parcel in) {
                this.title = in.readString();
                this.url = in.readString();
            }

            public static final Creator<SeasonBean> CREATOR = new Creator<SeasonBean>() {
                @Override
                public SeasonBean createFromParcel(Parcel source) {
                    return new SeasonBean(source);
                }

                @Override
                public SeasonBean[] newArray(int size) {
                    return new SeasonBean[size];
                }
            };
        }

        public static class SubTabsBean implements Parcelable {
            /**
             * title : 排名
             * url : http://sport-data.dqdgame.com/sd/biz/data/standing?season_id=119&version=0&platform=
             */

            private String title;
            private String url;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.title);
                dest.writeString(this.url);
            }

            public SubTabsBean() {
            }

            protected SubTabsBean(Parcel in) {
                this.title = in.readString();
                this.url = in.readString();
            }

            public static final Creator<SubTabsBean> CREATOR = new Creator<SubTabsBean>() {
                @Override
                public SubTabsBean createFromParcel(Parcel source) {
                    return new SubTabsBean(source);
                }

                @Override
                public SubTabsBean[] newArray(int size) {
                    return new SubTabsBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.label);
            dest.writeString(this.logo);
            dest.writeString(this.competition_id);
            dest.writeParcelable(this.season, flags);
            dest.writeInt(this.position);
            dest.writeInt(this.last_modify);
            dest.writeString(this.country_code);
            dest.writeList(this.sub_tabs);
        }

        public DataTabsBean() {
        }

        protected DataTabsBean(Parcel in) {
            this.id = in.readInt();
            this.label = in.readString();
            this.logo = in.readString();
            this.competition_id = in.readString();
            this.season = in.readParcelable(SeasonBean.class.getClassLoader());
            this.position = in.readInt();
            this.last_modify = in.readInt();
            this.country_code = in.readString();
            this.sub_tabs = new ArrayList<SubTabsBean>();
            in.readList(this.sub_tabs, SubTabsBean.class.getClassLoader());
        }

        public static final Parcelable.Creator<DataTabsBean> CREATOR = new Parcelable.Creator<DataTabsBean>() {
            @Override
            public DataTabsBean createFromParcel(Parcel source) {
                return new DataTabsBean(source);
            }

            @Override
            public DataTabsBean[] newArray(int size) {
                return new DataTabsBean[size];
            }
        };
    }

    public static class LiveTabsBean implements Parcelable {
        /**
         * id : 5
         * label : 关注
         * sort : 1
         * api : https://bkbapi.dqdgame.com/data/tab/fav_new?version=0&platform=
         * type : favor
         * default_index : 1
         */

        private int id;
        private String label;
        private int sort;
        private String api;
        private String type;
        private String default_index;

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

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getApi() {
            return api;
        }

        public void setApi(String api) {
            this.api = api;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDefault_index() {
            return default_index;
        }

        public void setDefault_index(String default_index) {
            this.default_index = default_index;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.label);
            dest.writeInt(this.sort);
            dest.writeString(this.api);
            dest.writeString(this.type);
            dest.writeString(this.default_index);
        }

        public LiveTabsBean() {
        }

        protected LiveTabsBean(Parcel in) {
            this.id = in.readInt();
            this.label = in.readString();
            this.sort = in.readInt();
            this.api = in.readString();
            this.type = in.readString();
            this.default_index = in.readString();
        }

        public static final Parcelable.Creator<LiveTabsBean> CREATOR = new Parcelable.Creator<LiveTabsBean>() {
            @Override
            public LiveTabsBean createFromParcel(Parcel source) {
                return new LiveTabsBean(source);
            }

            @Override
            public LiveTabsBean[] newArray(int size) {
                return new LiveTabsBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.default_index);
        dest.writeString(this.live_index);
        dest.writeTypedList(this.data_tabs);
        dest.writeTypedList(this.live_tabs);
    }

    public MatchTabData() {
    }

    protected MatchTabData(Parcel in) {
        this.default_index = in.readString();
        this.live_index = in.readString();
        this.data_tabs = in.createTypedArrayList(DataTabsBean.CREATOR);
        this.live_tabs = in.createTypedArrayList(LiveTabsBean.CREATOR);
    }

    public static final Parcelable.Creator<MatchTabData> CREATOR = new Parcelable.Creator<MatchTabData>() {
        @Override
        public MatchTabData createFromParcel(Parcel source) {
            return new MatchTabData(source);
        }

        @Override
        public MatchTabData[] newArray(int size) {
            return new MatchTabData[size];
        }
    };
}
