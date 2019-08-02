package com.jy.liuhairui.applayup.fragment_home;


import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import com.jy.liuhairui.applayup.DetailsActivity;
import com.jy.liuhairui.applayup.HomeActivity;
import com.jy.liuhairui.applayup.R;
import com.jy.liuhairui.applayup.adapter.RlvNormalAdapter;
import com.jy.liuhairui.applayup.api.ApiConfig;
import com.jy.liuhairui.applayup.api.INetService;
import com.jy.liuhairui.applayup.bean.FavTeamEntity;
import com.jy.liuhairui.applayup.bean.HomeTabData;
import com.jy.liuhairui.applayup.bean.MatchData;
import com.jy.liuhairui.applayup.bean.NormalData;
import com.jy.liuhairui.applayup.bean.ZipImportNewInfo;
import com.jy.liuhairui.applayup.frame.BaseMvpFragment;
import com.jy.liuhairui.applayup.frame.OnRecyclerItemClick;
import com.jy.liuhairui.applayup.mvp.NavigationViewModel;
import com.jy.liuhairui.applayup.utils.AppConstants;
import com.jy.liuhairui.applayup.utils.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;
import static com.jy.liuhairui.applayup.utils.NetworkUtils.isWiFiConnect;

/**
 * A simple {@link Fragment} subclass.
 */
public class NormalFragment extends BaseMvpFragment<NavigationViewModel> implements OnRecyclerItemClick {

    private static final String FRAGMENT_ID = "listBean";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private List<NormalData.ArticlesBean>  mRlvList = new ArrayList<>();
    private List<NormalData.RecommendBean>  mBannList = new ArrayList<>();
    private List<MatchData> mMatchList = new ArrayList<>();
    private RlvNormalAdapter mAdapter;
    private ZipImportNewInfo mZipNormal;
    private String faveUrl = "https://bkbapi.dqdgame.com/v2/league/favlists";//关注的url
    public int firstPosition = 0, visibleCount = 0, lastPosition = 0;
    private HomeActivity mActivity;
    private HomeTabData.DataBean.ListBean mParcelable;
    private String mLabel;
    private String mApi;
    private String mIndex_match_url;

    public static NormalFragment newInstance(HomeTabData.DataBean.ListBean listBean) {
        NormalFragment fragment = new NormalFragment();
        Bundle args = new Bundle();
        args.putParcelable(FRAGMENT_ID, listBean);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_normal;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParcelable = getArguments().getParcelable(FRAGMENT_ID);
            mLabel = mParcelable.getLabel();
            mApi = mParcelable.getApi();
            mIndex_match_url = mParcelable.getIndex_match_url();
        }
        //获取当前的Activity
        mActivity = (HomeActivity) getActivity();
    }

    @Override
    public void initView() {
        //view绑定刷新
        initRecycleView(recyclerView, refreshLayout);
        //适配器
        mAdapter = new RlvNormalAdapter(getContext(),mRlvList,mBannList,mMatchList,mLabel);
        recyclerView.setAdapter(mAdapter);
        //接口回调
        mAdapter.setClick(this);
    }

    @Override
    public void initData() {
        //比赛数据
        initBiSai();
        // 头条数据
        initNormal();
        //自动播放1
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                //判断在recyclerView滑动停止时
                if (mActivity.mApplication.mPlayInWifi && isWiFiConnect(getContext()) || !mActivity.mApplication.mPlayInWifi){
                    playVideo(recyclerView);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //  自定义变量记录item位置：firstPosition = 0, visibleCount = 0, lastPosition = 0
                //获取当前item
                firstPosition = mManager.findFirstVisibleItemPosition();
                //获取最后一个item
                lastPosition = mManager.findLastVisibleItemPosition();
                //获取中间的item数量
                visibleCount = lastPosition - firstPosition;
            }
        });
        //发起网络请求
        getData(AppConstants.NORMAL_LOAD);
    }

    //自动播放
    private void playVideo(RecyclerView view) {
        for (int i = 0; i < visibleCount; i++) {
            if (view != null && view.getChildAt(i) != null && view.getChildAt(i).findViewById(R.id.video_player) != null) {
                JCVideoPlayerStandard videoPlayer = view.getChildAt(i).findViewById(R.id.video_player);
                Rect rect = new Rect();
                videoPlayer.getLocalVisibleRect(rect);
                int height = videoPlayer.getHeight();
                if (rect.top == 0 && rect.bottom == height) {
                    if (videoPlayer.currentState == JCVideoPlayer.CURRENT_STATE_NORMAL || videoPlayer.currentState == JCVideoPlayer.CURRENT_STATE_ERROR) {
//                        videoPlayer.startButton.performClick();
                        videoPlayer.startVideo();
                        mActivity.mVideoPlayer = videoPlayer;
                    }
                    return;
                }
            }
        }
        JCVideoPlayer.releaseAllVideos();
        mActivity.mVideoPlayer = null;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if ((mActivity.mApplication.mPlayInWifi && isWiFiConnect(getContext())) || !mActivity.mApplication.mPlayInWifi)
                if (visibleCount != 0 && recyclerView != null) playVideo(recyclerView);
        } else {
            if (mActivity==null)return;
            mActivity.releaseVideo();
        }
    }
    //刷新
    @Override
    public void refresh() {
        super.refresh();
        getData(AppConstants.REFRESH_LOAD);
    }

    //加载更多
    @Override
    public void loadMore() {
        super.loadMore();
        getData(AppConstants.MORE_LOAD);
    }

    private void getData(int refreshLoad) {
        if (mZipNormal != null){
            //刷新
            if (refreshLoad == AppConstants.REFRESH_LOAD) {
                mApi = mZipNormal.fresh;
            } else if (refreshLoad == AppConstants.MORE_LOAD){
                mApi = mZipNormal.next;
            }
        }
        //网络请求
        if (mLabel.equals("头条"))
            mPresenter.getData(ApiConfig.TAB_NORMAL_ZIP_DATA, mApi, mIndex_match_url, faveUrl, refreshLoad);
        else
            mPresenter.getData(ApiConfig.TAB_NORMAL_FIRST_PAGE_VIDEO_INFO,mApi,refreshLoad);

    }

    private void initNormal() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://bkbapi.dqdgame.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        retrofit.create(INetService.class)
                .getNormalData2()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NormalData>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(NormalData value) {
                        Logger.logD("TAG","MVC.Normal数据成功:"+value.toString());
                        if (value != null){

                            List<NormalData.ArticlesBean> articles = value.getArticles();
                            List<NormalData.RecommendBean> recommend = value.getRecommend();
                            mRlvList.addAll(articles);
                            mBannList.addAll(recommend);
                            mAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.logD("TAG","MVC.Normal数据成功:"+e.getMessage());
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }

    private void initBiSai() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://sport-data.dqdgame.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        retrofit.create(INetService.class)
                .getMatchData2()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<MatchData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(List<MatchData> value) {
                        Logger.logD("TAG","MVC比赛数据成功:"+value.toString());
                        if (value != null){
                            mMatchList.addAll(value);
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        Logger.logD("TAG","MVC比赛数据失败:"+e.getMessage());
                    }
                    @Override
                    public void onComplete() {
                    }
                });

    }

    @Override
    public NavigationViewModel getModel() {
        return new NavigationViewModel();
    }

    //网络请求失败
    @Override
    public void onError(int whichApi, Throwable e) {
        Logger.logD("TAG", "NormalFragment失败：" + e.getMessage());
    }

    //网络请求成功
    @Override
    public void onResponse(int whichApi, Object[] t) {

        switch (whichApi) {
            case ApiConfig.TAB_NORMAL_ZIP_DATA://normal中头条压缩的数据
                setImportData(t);
                break;
            case ApiConfig.TAB_NORMAL_FIRST_PAGE_VIDEO_INFO://normal中头条以外的数据
                 NormalData normalData = (NormalData) t[0];
                Logger.logD("TAG", "NormalFragment头条以外的数据成功：" + normalData.toString());
                 //刷新
                int loadType = getLoadType(t);
                if (loadType == AppConstants.REFRESH_LOAD){
                    mRlvList.clear();
                    refreshLayout.finishRefresh();
                } else if (loadType == AppConstants.MORE_LOAD){
                    refreshLayout.finishLoadMore();
                }
                //
                mRlvList.addAll(normalData.getArticles());
                mAdapter.notifyDataSetChanged();
                break;
        }
    }

    private void setImportData(Object[] t) {
        mZipNormal = (ZipImportNewInfo) t[0];
        Logger.logD("TAG", "NormalFragment头条的压缩数据成功：" + mZipNormal.toString());
        //
        int load = getLoadType(t);
        if (load == AppConstants.REFRESH_LOAD) {
            mRlvList.clear();
            mBannList.clear();
            mMatchList.clear();
            refreshLayout.finishRefresh();
        } else if (load == AppConstants.MORE_LOAD) refreshLayout.finishLoadMore();
        //
        List<NormalData.ArticlesBean> articles = mZipNormal.articles;
        List<NormalData.RecommendBean> recommend = mZipNormal.recommend;
        List<MatchData> matchInfo = mZipNormal.matchInfo;
        FavTeamEntity favTeamEntity = mZipNormal.mFavTeamEntity;
        if (load != AppConstants.MORE_LOAD) {
            for (int i = 0; i < articles.size(); i++) {
                if (articles.get(i).isTop()) {
                    continue;
                } else {
                    NormalData.ArticlesBean articlesBean = new NormalData.ArticlesBean();
                    articlesBean.mFavTeamEntity = favTeamEntity;
                    articlesBean.setIs_video(false);
                    articles.add(i, articlesBean);
                    break;
                }
            }
        }
        if (matchInfo != null){
            this.mMatchList.addAll(matchInfo);
            this.mRlvList.addAll(articles);
        }
        if (recommend != null){
            this.mBannList.addAll(recommend);
        }
        mAdapter.notifyDataSetChanged();
    }


    //子条目点击事件,跳转到详情页面展示数据
    @Override
    public void onItemClick(int pos) {
        if (mRlvList != null){
            NormalData.ArticlesBean articlesBean = mRlvList.get(pos);
            Intent intent = new Intent(getActivity(), DetailsActivity.class);
            intent.putExtra("articlesBean",articlesBean);
            startActivity(intent);
        }
    }
}
