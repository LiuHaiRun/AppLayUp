package com.jy.liuhairui.applayup.fragment_circle;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jy.liuhairui.applayup.R;
import com.jy.liuhairui.applayup.adapter3.RlvCircleTopicAdapter;
import com.jy.liuhairui.applayup.api.ApiConfig;
import com.jy.liuhairui.applayup.bean3.CircleTopicData;
import com.jy.liuhairui.applayup.frame.BaseMvpFragment;
import com.jy.liuhairui.applayup.mvp.NavigationViewModel;
import com.jy.liuhairui.applayup.utils.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CircleTopicFragment extends BaseMvpFragment<NavigationViewModel> {


    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private String mApi;
    private ArrayList<CircleTopicData.DataBean.TopicListBean> mList;
    private RlvCircleTopicAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_circle_topic;
    }

    @Override
    public void initView() {
        //
        Bundle arguments = getArguments();
        mApi = arguments.getString("api");
        //容器
        mList = new ArrayList<>();
        //布局管理器
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        //适配器
        mAdapter = new RlvCircleTopicAdapter(getContext(), mList);
        recyclerview.setAdapter(mAdapter);

    }

    @Override
    public void initData() {
        mPresenter.getData(ApiConfig.CIRCLE_TAB_TOPIC17,mApi);
    }

    @Override
    public NavigationViewModel getModel() {
        return new NavigationViewModel();
    }

    @Override
    public void onError(int whichApi, Throwable e) {
        Logger.logD("TAG","圈子Tab_话题数据onError："+e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.CIRCLE_TAB_TOPIC17:
                CircleTopicData data = (CircleTopicData) t[0];
                Logger.logD("TAG","圈子Tab_话题数据onResponse："+data.toString());
                List<CircleTopicData.DataBean.TopicListBean> topic_list = data.getData().getTopic_list();
                mList.addAll(topic_list);
                mAdapter.notifyDataSetChanged();
                break;
        }
    }

}
