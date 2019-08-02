package com.jy.liuhairui.applayup.api;


import com.jy.liuhairui.applayup.bean.ClassificationData;
import com.jy.liuhairui.applayup.bean.FavTeamEntity;
import com.jy.liuhairui.applayup.bean.HomeTabData;
import com.jy.liuhairui.applayup.bean.HotData;
import com.jy.liuhairui.applayup.bean.MatchData;
import com.jy.liuhairui.applayup.bean.NavigationViewData;
import com.jy.liuhairui.applayup.bean.NormalData;
import com.jy.liuhairui.applayup.bean.VideoData;
import com.jy.liuhairui.applayup.bean.WallData;
import com.jy.liuhairui.applayup.bean2.FavorData;
import com.jy.liuhairui.applayup.bean2.LeagueData;
import com.jy.liuhairui.applayup.bean2.MatchTabData;
import com.jy.liuhairui.applayup.bean3.CircleNormalData;
import com.jy.liuhairui.applayup.bean3.CircleTabData;
import com.jy.liuhairui.applayup.bean3.CircleTopicData;
import com.jy.liuhairui.applayup.bean4.RankingCubaData;
import com.jy.liuhairui.applayup.bean4.DataTabs;
import com.jy.liuhairui.applayup.bean4.MatchProgressions;
import com.jy.liuhairui.applayup.bean4.NbaData;
import com.jy.liuhairui.applayup.bean4.RankingData;
import com.jy.liuhairui.applayup.bean4.TeamPlayeData;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 *
 */
public interface INetService {

    /*侧滑Recycler
    https://bkbapi.dqdgame.com/app/global/2/android.json?mark=gif&platform=android&version=132&android-channel=website
    */
    @GET("app/global/2/android.json?mark=gif&platform=android&version=132&android-channel=website")
    Observable<NavigationViewData> getNavigationViewData();

    /*首页tab栏
          https://bkbapi.dqdgame.com/v2/config/index_menu?mark=gif&platform=android&version=132&android-channel=website
          */
    @GET("v2/config/index_menu?mark=gif&platform=android&version=132&android-channel=website")
    Observable<HomeTabData> getTabData();

//---------------------------------首页Tab栏下数据源-----------------------------------------------
    /*id: 104,
    label: "热门",
    type: "hot",*/

    @GET
    Observable<HotData> getHotData(@Url String url);

        /*label: "头条",
         label: "NBA",
         label: "中国篮球",
         label: "深度",
         label: "闲情",
          label: "世界杯",
        type: "normal",*/

    @GET()
    Observable<NormalData> getPublicNormalData(@Url String url);
    //头条的数据
    @GET
    Observable<List<MatchData>> getMatchData(@Url String url);
    @GET()
    Observable<NormalData> getNormalData(@Url String url);
    @GET
    Observable<FavTeamEntity> getFavData(@Url String url);


    @GET("sd/biz/live/index?platform=android&version=132")
    Observable<List<MatchData>> getMatchData2();
    @GET("app/tabs/android/1.json?mark=gif&version=132")
    Observable<NormalData> getNormalData2();
        /*id: 11,
        label: "集锦",
        type: "video",*/

    @GET
    Observable<VideoData> getVideoData(@Url String url);

        /*id: 106,
       label: "INS"
       type: "wall"*/

    @GET
    Observable<WallData> getWallData(@Url String url);

        /*id: 99,
        label: "专题",
        type: "classification"*/

    @GET
    Observable<ClassificationData> getClassFicationData(@Url String url);

    //-------------------------------------比赛Tab栏下数据源---------------------------------------

    //比赛Tab栏  http://sport-data.dqdgame.com/sd/biz/index
    @GET
    Observable<MatchTabData>  getMatchTabData(@Url String url);

  @GET
    Observable<FavorData>  getFavorData(@Url String url);

    @GET
    Observable<LeagueData>  getLeagueData(@Url String url);

    //------------------------------------圈子Tab栏下数据源----------------------------------------

    //圈子Tab栏  https://bkbapi.dqdgame.com/group/app/thread/tabs

    @GET
    Observable<CircleTabData>  getCircleTabData(@Url String url);

    @GET
    Observable<CircleNormalData>  getCircleNormalData(@Url String url);

    @GET
    Observable<CircleTopicData>  getCirclrTopicData(@Url String url);

    //-----------------------------------数据Tab栏下-----------------------------------------------

    // Tab栏 与比赛网址相同
    @GET
    Observable<RankingCubaData> getCUBAResult(@Url String url);
    @GET()
    Observable<MatchProgressions> getProgress(@Url String url);
    @GET()
    Observable<List<DataTabs>> getTabFather(@Url String url);
    @GET
    Observable<RankingData> getRankInfo(@Url String url);
    @GET
    Observable<TeamPlayeData> getTeamerResult(@Url String url);
 /*   @GET("sd/biz/index")
    Observable<TabFatherInfo> getTabFatherInfo();*/
    @GET
    Observable<NbaData> getPersonInfo(@Url String url);

    //---------------------------------------------------------------------------------------
    // 版本升级   https://www.cpzs.org/admin/apkversion/getxml/17


}
