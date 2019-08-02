package com.jy.liuhairui.applayup.fragment_circle;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jy.liuhairui.applayup.R;
import com.jy.liuhairui.applayup.adapter3.RlvCircleNormalAdapter;
import com.jy.liuhairui.applayup.api.ApiConfig;
import com.jy.liuhairui.applayup.bean3.CircleNormalData;
import com.jy.liuhairui.applayup.frame.BaseMvpFragment;
import com.jy.liuhairui.applayup.mvp.NavigationViewModel;
import com.jy.liuhairui.applayup.utils.AppConstants;
import com.jy.liuhairui.applayup.utils.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class CircleNormalFragment extends BaseMvpFragment<NavigationViewModel> {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private String mType;
    private String mApi;
    private String mLabel;
    private List<CircleNormalData.DataBean.FeedsListBean> mDataList;
    private RlvCircleNormalAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_circle_normal;
    }

    @Override
    public void initView() {
        Bundle arguments = getArguments();
        mType = arguments.getString("type");
        mApi = arguments.getString("api");
        mLabel = arguments.getString("label");
        //绑定刷新
        initRecycleView(recyclerView, refreshLayout);
        mDataList = new ArrayList<>();
        //布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //适配器
        mAdapter = new RlvCircleNormalAdapter(getContext(), mDataList, mLabel);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        if (mLabel.equals("推荐"))
            mPresenter.getData(ApiConfig.CIRCLE_TAB_RECOMMEND15, mApi);
        if (mLabel.equals("视频"))
            mPresenter.getData(ApiConfig.CIRCLE_TAB_VIDEO16, mApi);

    }

    @Override
    public NavigationViewModel getModel() {
        return new NavigationViewModel();
    }

    @Override
    public void onError(int whichApi, Throwable e) {
        Logger.logD("TAG", "圈子normal字段onError" + e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

        switch (whichApi) {
            case ApiConfig.CIRCLE_TAB_RECOMMEND15://推荐
                CircleNormalData data = (CircleNormalData) t[0];
                Logger.logD("TAG", "圈子normal字段onResponse" + data.toString());
                //获取原始的FeedsListBean集合数据
                List<CircleNormalData.DataBean.FeedsListBean> mFeedslist = data.getData().getFeeds_list();
                //获取原始的TopicBannerBean集合数据
                CircleNormalData.DataBean.TopicBannerBean topic_banner = data.getData().getTopic_banner();
                if (topic_banner != null && topic_banner.getTopic_list() != null && topic_banner.getTopic_list().size()>0){
                    //创建新数据对象 将原始BannerBean数据添加进去
                    CircleNormalData.DataBean.FeedsListBean feedsListBean = new CircleNormalData.DataBean.FeedsListBean();
                    feedsListBean.topic_banner = data.getData().getTopic_banner();
                    mFeedslist.add(0,feedsListBean);
                }
                mDataList.addAll(mFeedslist);
                mAdapter.notifyDataSetChanged();
                break;
              case ApiConfig.CIRCLE_TAB_VIDEO16://视频
                  CircleNormalData data1 = (CircleNormalData) t[0];
                  List<CircleNormalData.DataBean.FeedsListBean> feeds_list = data1.getData().getFeeds_list();
                  mDataList.addAll(feeds_list);
                  mAdapter.notifyDataSetChanged();
                    break;
        }
    }
}
