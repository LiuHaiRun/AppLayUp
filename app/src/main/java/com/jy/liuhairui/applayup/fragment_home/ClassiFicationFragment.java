package com.jy.liuhairui.applayup.fragment_home;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.jy.liuhairui.applayup.R;
import com.jy.liuhairui.applayup.adapter.RlvClassificationAdapter;
import com.jy.liuhairui.applayup.api.ApiConfig;
import com.jy.liuhairui.applayup.bean.ClassificationData;
import com.jy.liuhairui.applayup.frame.BaseMvpFragment;
import com.jy.liuhairui.applayup.mvp.NavigationViewModel;
import com.jy.liuhairui.applayup.utils.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * to handle interaction events.
 */
public class ClassiFicationFragment extends BaseMvpFragment<NavigationViewModel> {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private String mApi;
    private ArrayList<ClassificationData.ArticlesBean>  mClassiFicationList = new ArrayList<>();
    private RlvClassificationAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_classi_fication;
    }


    @Override
    public void initView() {
        //获取Tab栏传递的数据
        if (getArguments() != null) {
            Bundle arguments = getArguments();
            mApi = arguments.getString("api");
        }

        //
        initRecycleView(recyclerView,refreshLayout);
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setEnableLoadMore(false);
        //
        mAdapter = new RlvClassificationAdapter(getContext(), mClassiFicationList);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initData() {

        mPresenter.getData(ApiConfig.TAB_CLASSIFICATION_DATA,mApi);
    }

    @Override
    public NavigationViewModel getModel() {
        return new NavigationViewModel();
    }

    @Override
    public void onError(int whichApi, Throwable e) {
        Logger.logD("TAG","ClassiFicationFragment失败："+e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

        switch (whichApi) {
            case ApiConfig.TAB_CLASSIFICATION_DATA:
                ClassificationData data = (ClassificationData) t[0];
                Logger.logD("TAG","ClassiFicationFragment成功："+data.toString());
                List<ClassificationData.ArticlesBean> articles = data.getArticles();

                mClassiFicationList.addAll(articles);
                mAdapter.notifyDataSetChanged();
                break;
        }
    }
}
