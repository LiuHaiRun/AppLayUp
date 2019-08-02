package com.jy.liuhairui.applayup.fragment_home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.jy.liuhairui.applayup.R;
import com.jy.liuhairui.applayup.adapter.RlvVideoAdapter;
import com.jy.liuhairui.applayup.api.ApiConfig;
import com.jy.liuhairui.applayup.bean.NormalData;
import com.jy.liuhairui.applayup.frame.BaseMvpFragment;
import com.jy.liuhairui.applayup.mvp.NavigationViewModel;
import com.jy.liuhairui.applayup.utils.AppConstants;
import com.jy.liuhairui.applayup.utils.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends BaseMvpFragment<NavigationViewModel> {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private String mApi;
    private NormalData mVideoData;
    private ArrayList<NormalData.ArticlesBean> mList;
    private RlvVideoAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    public void initView() {
        //获取Tab栏传递的数据
        if (getArguments() != null) {
            Bundle arguments = getArguments();
            mApi = arguments.getString("api");
        }
        //绑定自动刷新
        initRecycleView(recyclerView,refreshLayout);
        mList = new ArrayList<>();
        mAdapter = new RlvVideoAdapter(getContext(), mList);
        recyclerView.setAdapter(mAdapter);
    }

    //刷新
    @Override
    public void refresh() {
        super.refresh();
        if (!TextUtils.isEmpty(mVideoData.getFresh())){
            mPresenter.getData(ApiConfig.TAB_NORMAL_FIRST_PAGE_VIDEO_INFO, mVideoData.getFresh(), AppConstants.REFRESH_LOAD);
        } else {
            //关闭刷新头
            refreshLayout.finishRefresh();
        }
    }

    //加载更多
    @Override
    public void loadMore() {
        super.loadMore();
        if (mVideoData != null && !TextUtils.isEmpty(mVideoData.getNext())){
            mPresenter.getData(ApiConfig.TAB_NORMAL_FIRST_PAGE_VIDEO_INFO, mVideoData.getNext(), AppConstants.MORE_LOAD);
        } else{
            //关闭加载尾
            refreshLayout.setNoMoreData(true);
        }
    }

    @Override
    public void initData() {
        //
        mPresenter.getData(ApiConfig.TAB_NORMAL_FIRST_PAGE_VIDEO_INFO,mApi,AppConstants.NORMAL_LOAD);
    }



    @Override
    public NavigationViewModel getModel() {
        return new NavigationViewModel();
    }

    @Override
    public void onError(int whichApi, Throwable e) {
        Logger.logD("TAG","网络请求视频数据失败："+e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        mVideoData = (NormalData) t[0];
        Logger.logD("TAG","网络请求视频数据成功："+mVideoData);
        int loadType = getLoadType(t);
        if (loadType == AppConstants.REFRESH_LOAD){
            mList.clear();
            refreshLayout.finishRefresh();
        } else if (loadType ==  AppConstants.MORE_LOAD){
            refreshLayout.finishLoadMore();
        }

        mList.addAll(mVideoData.getArticles());
        mAdapter.notifyDataSetChanged();
    }


}
