package com.jy.liuhairui.applayup.fragment_datas;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;


import com.jy.liuhairui.applayup.R;
import com.jy.liuhairui.applayup.adapter4.CUBASortAdapter;
import com.jy.liuhairui.applayup.api.ApiConfig;
import com.jy.liuhairui.applayup.bean4.RankingCubaData;
import com.jy.liuhairui.applayup.frame.BaseMvpFragment;
import com.jy.liuhairui.applayup.mvp.DataModel;
import com.jy.liuhairui.applayup.utils.AppConstants;
import com.jy.liuhairui.applayup.utils.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/*
*    CUBA  的排名
*
* */
public class RankingCUBAFragment extends BaseMvpFragment<DataModel> {

    private String tabName;
    private String tabUrl;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.recyclerView_west)
    RecyclerView mRecyclerViewWest;

    private static final String TAB_NAME = "tab_name";
    private static final String TAB_URL = "tab_url";
    private static final String FATHER_TAB_NAME = "label";

    private String mFatherTab;
    List<RankingCubaData.ContentBeanX.RoundsBean.ContentBean.DataBeanX> mCubaData = new ArrayList<>();
    private CUBASortAdapter mAdapter;

    public static RankingCUBAFragment newInstance(String param1, String param2, String label) {
        RankingCUBAFragment fragment = new RankingCUBAFragment();
        Bundle args = new Bundle();
        args.putString(TAB_NAME, param1);
        args.putString(TAB_URL, param2);
        args.putString(FATHER_TAB_NAME, label);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tabName = getArguments().getString(TAB_NAME);
            tabUrl = getArguments().getString(TAB_URL);
            mFatherTab = getArguments().getString(FATHER_TAB_NAME);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_data_tab_sort_cuba;
    }

    @Override
    public void initView() {
        initRecycleView(mRecyclerViewWest, null);
        mAdapter = new CUBASortAdapter(mCubaData, getContext());
        mRecyclerViewWest.setAdapter(mAdapter);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRefreshLayout.setRefreshing(true);
                getData(AppConstants.REFRESH_LOAD);
            }
        });
    }

    @Override
    public void initData() {
        if (mCubaData.size() == 0)
            getData(AppConstants.NORMAL_LOAD);
    }

    private void getData(int loadMode) {
//        showLoadingDialog();
        mPresenter.getData(ApiConfig.GET_SORT_CUBA_DATA, loadMode, tabUrl);
    }

    @Override
    public DataModel getModel() {
        return new DataModel();
    }

    @Override
    public void onError(int whichApi, Throwable e) {
        Logger.logD("TAG","CUBA的排名失败："+e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.GET_SORT_CUBA_DATA:
                if ((int) t[1] == AppConstants.REFRESH_LOAD) {
                    mCubaData.clear();
                    mRefreshLayout.setRefreshing(false);
                }
                RankingCubaData cubaData = (RankingCubaData) t[0];
                List<RankingCubaData.ContentBeanX.RoundsBean.ContentBean.DataBeanX> data = cubaData.getContent().getRounds().get(0).getContent().getData();
                mCubaData.addAll(data);
                mAdapter.notifyDataSetChanged();
//                hideLoadingDialog();
                break;
        }
    }

}
