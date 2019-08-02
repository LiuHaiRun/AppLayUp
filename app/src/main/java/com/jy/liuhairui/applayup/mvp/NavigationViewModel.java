package com.jy.liuhairui.applayup.mvp;

import com.jy.liuhairui.applayup.api.ApiConfig;
import com.jy.liuhairui.applayup.api.INetService;
import com.jy.liuhairui.applayup.bean.FavTeamEntity;
import com.jy.liuhairui.applayup.bean.MatchData;
import com.jy.liuhairui.applayup.bean.NormalData;
import com.jy.liuhairui.applayup.bean.ZipImportNewInfo;
import com.jy.liuhairui.applayup.frame.ICommonModel;
import com.jy.liuhairui.applayup.frame.ICommonView;
import com.jy.liuhairui.applayup.frame.NetManager;
import com.jy.liuhairui.applayup.utils.Logger;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class NavigationViewModel implements ICommonModel {

    //
    NetManager mManager = NetManager.getNetManager();
    ZipImportNewInfo mZip = null;

    @Override
    public void getData(final ICommonView view, final int whichApi, Object[] t) {

        switch (whichApi) {
            case ApiConfig.NAVIGATIONVIEW_DATA:
                //获取侧滑菜单中的Recycler数据
                mManager.method(mManager.getNetService().getNavigationViewData(), view, whichApi);
                break;
            case ApiConfig.Tab_DATA:
                //获取Tab栏数据
                mManager.method(mManager.getNetService().getTabData(), view, whichApi);
                break;
            case ApiConfig.TAB_HOT_DATA:
                //获取Tab栏中hot的数据
                mManager.method(mManager.getNetService().getHotData((String) t[0]), view, whichApi, (int) t[1]);
                break;
            case ApiConfig.TAB_CLASSIFICATION_DATA:
                //获取Tab栏中ClassFication的数据
                mManager.method(mManager.getNetService().getClassFicationData((String) t[0]), view, whichApi);
                break;
            case ApiConfig.TAB_WALL_DATA:
                //获取Tab栏中well的数据
                mManager.method(mManager.getNetService().getWallData((String) t[0]), view, whichApi, (int) t[1]);
                break;
            case ApiConfig.TAB_NORMAL_DATA:
            case ApiConfig.TAB_NORMAL_FIRST_PAGE_VIDEO_INFO:
                //获取Tab栏中头条以外的数据
                mManager.method(mManager.getNetService().getPublicNormalData((String) t[0]), view, whichApi, (int) t[1]);
                break;
            case ApiConfig.TAB_NORMAL_ZIP_DATA:
                //获取Tab栏中头条的压缩数据
                String mApi = (String) t[0];
                String match_url = (String) t[1];
                String faveUrl = (String) t[2];
                final int load = (int) t[3];
                INetService service = mManager.getNetService();
                Observable<NormalData> normalData = service.getNormalData(mApi);
                Observable<List<MatchData>> matchData = service.getMatchData(match_url);
                Observable<FavTeamEntity> favData = service.getFavData(faveUrl);
                //压缩3个集合的数据
                Observable.concat(normalData, matchData, favData)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Object>() {
                            @Override
                            public void accept(Object o) throws Exception {
                                if (mZip == null) {
                                    mZip = new ZipImportNewInfo();
                                }
                                if (o instanceof NormalData) {
                                    NormalData normalData1 = (NormalData) o;
                                    mZip.fresh = normalData1.getFresh();
                                    mZip.hotwords = normalData1.getHotwords();
                                    mZip.id = normalData1.getId();
                                    mZip.label = normalData1.getLabel();
                                    mZip.max = normalData1.getMax();
                                    mZip.min = normalData1.getMin();
                                    mZip.next = normalData1.getNext();
                                    mZip.page = normalData1.getPage();

                                } else if (o instanceof FavTeamEntity) {
                                    //
                                    FavTeamEntity favTeamEntity = (FavTeamEntity) o;
                                    mZip.mFavTeamEntity = favTeamEntity;
                                } else {
                                    List<MatchData> matchData1 = (List<MatchData>) o;
                                    mZip.matchInfo = matchData1;
                                }

                                if (mZip != null && mZip.articles != null && mZip.matchInfo != null && mZip.mFavTeamEntity != null) {
                                    //网络请求成功
                                    view.onResponse(whichApi, mZip, load);
                                    Logger.logD("TAG", "Normal头条M层的压缩数据成功：" + mZip.toString());
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                //网络请求失败
                                view.onError(whichApi, throwable);
                            }
                        });
                //压缩2个请求的数据
                /*Observable<ZipImportNewInfo> zip = Observable.zip(normalData, matchData, new BiFunction<NormalData, List<MatchData>, ZipImportNewInfo>() {
                    @Override
                    public ZipImportNewInfo apply(NormalData pNormalNewsInfo, List<MatchData> pMatchInfo) throws Exception {
                        ZipImportNewInfo zipImportNewInfo = new ZipImportNewInfo();
                        List<NormalData.RecommendBean> recommend = pNormalNewsInfo.getRecommend();
                        List<NormalData.ArticlesBean> articles = pNormalNewsInfo.getArticles();
                        zipImportNewInfo.recommend = recommend;
                        zipImportNewInfo.matchInfo = pMatchInfo;
                        zipImportNewInfo.articles = articles;
                        return zipImportNewInfo;
                    }
                });
                NetManager.getNetManager().netMethod(zip,view,whichApi,loadType);*/

                break;
            case ApiConfig.MATCH_TAB_DATA9:
                //比赛Tab栏数据 和 数据Tab栏
                String url = "http://sport-data.dqdgame.com/sd/biz/index";
                mManager.method(mManager.getNetService().getMatchTabData(url), view, whichApi);
                break;
            case ApiConfig.MATCH_TAB_LEAGUE12:
                //比赛Tab栏league字段
                mManager.method(mManager.getNetService().getLeagueData((String) t[0]), view, whichApi,(int) t[1]);
                break;
            case ApiConfig.CIRCLE_TAB13:
                //圈子Tab栏数据
                String circleUrl = "https://bkbapi.dqdgame.com/group/app/thread/tabs";
                mManager.method(mManager.getNetService().getCircleTabData(circleUrl), view, whichApi);
                break;
            case ApiConfig.CIRCLE_TAB_RECOMMEND15:
            case ApiConfig.CIRCLE_TAB_VIDEO16:
                //圈子Tab栏中normal字段数据
                mManager.method(mManager.getNetService().getCircleNormalData((String) t[0]), view, whichApi);
                break;
            case ApiConfig.CIRCLE_TAB_TOPIC17:
                //圈子Tab栏中topic字段数据
                mManager.method(mManager.getNetService().getCirclrTopicData((String) t[0]), view, whichApi);
                break;
        }
    }
}
