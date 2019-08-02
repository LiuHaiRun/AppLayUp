package com.jy.liuhairui.applayup.fragment_home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import com.jy.liuhairui.applayup.R;
import com.jy.liuhairui.applayup.adapter.RlvWellAdapter;
import com.jy.liuhairui.applayup.api.ApiConfig;
import com.jy.liuhairui.applayup.bean.WallData;
import com.jy.liuhairui.applayup.frame.BaseMvpFragment;
import com.jy.liuhairui.applayup.mvp.NavigationViewModel;
import com.jy.liuhairui.applayup.utils.AppConstants;
import com.jy.liuhairui.applayup.utils.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class WallFragment extends BaseMvpFragment<NavigationViewModel> {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private String mApi;
    private ArrayList<WallData.FeedlistBean> mList;
    private RlvWellAdapter mAdapter;
    private WallData mWallData;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_wall;
    }

    @Override
    public void initView() {
        //获取Tab栏传递的数据
        if (getArguments() != null) {
            Bundle arguments = getArguments();
            mApi = arguments.getString("api");
        }

        //刷新
        initRecycleView(recyclerView,refreshLayout);
        //recyclerView容器
        mList = new ArrayList<>();
        //适配器
        mAdapter = new RlvWellAdapter(getContext(), mList);
        recyclerView.setAdapter(mAdapter);
    }

    //刷新
    @Override
    public void refresh() {
        super.refresh();
        mPresenter.getData(ApiConfig.TAB_WALL_DATA,mApi,AppConstants.REFRESH_LOAD);
    }

    //加载更多
    @Override
    public void loadMore() {
        super.loadMore();
        mPresenter.getData(ApiConfig.TAB_WALL_DATA,mApi,AppConstants.MORE_LOAD);
    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.TAB_WALL_DATA,mApi,AppConstants.NORMAL_LOAD);
    }

    @Override
    public NavigationViewModel getModel() {
        return new NavigationViewModel();
    }

    @Override
    public void onError(int whichApi, Throwable e) {
        Logger.logD("TAG","WallFragment网络请求失败："+e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        mWallData = (WallData) t[0];
        Logger.logD("TAG","WallFragment网络请求成功："+mWallData.toString());
        //刷新加载
        int loadType = getLoadType(t);
         if (loadType == AppConstants.REFRESH_LOAD){
              mList.clear();
             refreshLayout.finishRefresh();
         }else if (loadType == AppConstants.MORE_LOAD){
             refreshLayout.autoLoadMore();
         }

        List<WallData.FeedlistBean> feedlist = mWallData.getFeedlist();
        mList.addAll(feedlist);
        mAdapter.notifyDataSetChanged();
    }


}
