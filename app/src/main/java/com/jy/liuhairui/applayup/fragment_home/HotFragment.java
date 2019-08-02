package com.jy.liuhairui.applayup.fragment_home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import com.jy.liuhairui.applayup.R;
import com.jy.liuhairui.applayup.adapter.RlvHotFatherAdapter;
import com.jy.liuhairui.applayup.api.ApiConfig;
import com.jy.liuhairui.applayup.bean.HotData;
import com.jy.liuhairui.applayup.frame.BaseMvpFragment;
import com.jy.liuhairui.applayup.mvp.NavigationViewModel;
import com.jy.liuhairui.applayup.utils.AppConstants;
import com.jy.liuhairui.applayup.utils.Logger;
import com.jy.liuhairui.applayup.utils.SuspensionDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotFragment extends BaseMvpFragment<NavigationViewModel> {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smartrefreshlayout) //刷新
    SmartRefreshLayout smartrefreshlayout;


    private String mApi;
    private ArrayList<HotData.ContentsBean>  mHotList = new ArrayList<>();
    private RlvHotFatherAdapter mAdapter;
    private HotData mHotData;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    public void initView() {
       //获取Tab栏传递的数据
        if (getArguments() != null){
            Bundle arguments = getArguments();
            mApi = arguments.getString("api");
        }


        //RecyclerView
        initRecycleView(recyclerView, smartrefreshlayout);
        mAdapter = new RlvHotFatherAdapter(getContext(), mHotList);
        recyclerView.setAdapter(mAdapter);
        //SuspensionDecoration这个类的作用添加粘性头部局
        recyclerView.addItemDecoration(new SuspensionDecoration(getContext(),mHotList));


    }

    private void getData(int pRefresh, String url) {
        if (mHotData != null)
            mPresenter.getData(ApiConfig.TAB_HOT_DATA, url,pRefresh);
        else showLog(getString(R.string.dont_found_url));
    }

    //下拉刷新
    @Override
    public void refresh() {
        super.refresh();
        if(mHotData == null)return;
        getData(AppConstants.REFRESH_LOAD, mHotData.getFresh());
    }
    //上拉加载更多
    @Override
    public void loadMore() {
        super.loadMore();
        if(mHotData == null)return;
        getData(AppConstants.MORE_LOAD, mHotData.getNext());
    }

    @Override
    public void initData() {
        if (mApi != null)
            mPresenter.getData(ApiConfig.TAB_HOT_DATA, mApi,AppConstants.NORMAL_LOAD );
    }

    @Override
    public NavigationViewModel getModel() {
        return new NavigationViewModel();
    }

    @Override
    public void onError(int whichApi, Throwable e) {
        Logger.logD("TAG","HotFragment失败："+e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

        switch (whichApi) {
            case ApiConfig.TAB_HOT_DATA:
                mHotData = (HotData) t[0];
                Logger.logD("TAG","HotFragment成功："+mHotData.toString());
                int loadType = getLoadType(t);
                if (loadType == AppConstants.MORE_LOAD){
                    smartrefreshlayout.finishLoadMore();
                } else if (loadType == AppConstants.REFRESH_LOAD){
                    smartrefreshlayout.finishRefresh();
                    mHotList.clear();
                }
                mHotList.addAll(mHotData.getContents());
                mAdapter.notifyDataSetChanged();
                break;
        }
    }

}
