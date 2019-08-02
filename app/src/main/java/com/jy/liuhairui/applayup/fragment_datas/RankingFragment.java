package com.jy.liuhairui.applayup.fragment_datas;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;


import com.jy.liuhairui.applayup.R;
import com.jy.liuhairui.applayup.adapter4.CbaTeamQuenAdapter;
import com.jy.liuhairui.applayup.adapter4.RankAdapter;
import com.jy.liuhairui.applayup.adapter4.TeamQuenAdapter;
import com.jy.liuhairui.applayup.api.ApiConfig;
import com.jy.liuhairui.applayup.bean4.RankingData;
import com.jy.liuhairui.applayup.bean4.TempRankInfo;
import com.jy.liuhairui.applayup.frame.BaseMvpFragment;
import com.jy.liuhairui.applayup.mvp.DataInnerPresenter;
import com.jy.liuhairui.applayup.mvp.DataModel;
import com.jy.liuhairui.applayup.utils.AppConstants;
import com.jy.liuhairui.applayup.utils.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/*
*
*   除了CUBA以外其他的排名
*/

public class RankingFragment extends BaseMvpFragment<DataModel> {
    private String tabName;
    private String tabUrl;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.recyclerView_rank)
    RecyclerView mRecyclerViewRank;
    @BindView(R.id.recyclerView_west)
    RecyclerView mRecyclerViewWest;
    @BindView(R.id.recyclerView_east)
    RecyclerView mRecyclerViewEast;

    private DataInnerPresenter mDataInnerPresenter;
    List<RankingData.ContentBeanX.RoundsBean.ContentBean.DataBean.DataBean2> westList = new ArrayList<>();
    List<RankingData.ContentBeanX.RoundsBean.ContentBean.DataBean> cbaList = new ArrayList<>();
    List<RankingData.ContentBeanX.RoundsBean.ContentBean.DataBean.DataBean2> eastList = new ArrayList<>();
    private RankAdapter mAdapter;
    private TeamQuenAdapter mAdapterWest;
    private TeamQuenAdapter mAdapterEast;
    private static final String TAB_NAME = "tab_name";
    private static final String TAB_URL = "tab_url";
    private static final String FATHER_TAB_NAME = "label";
    private RankingData mRankingData;
    private String mFatherTab;
    private CbaTeamQuenAdapter mCbaTeamQuenAdapter;

    public static RankingFragment newInstance(String param1, String param2, String label) {
        RankingFragment fragment = new RankingFragment();
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
        mDataInnerPresenter = new DataInnerPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_data_tab_inner;
    }

    @Override
    public void initView() {
        //只有NBA和CBA才有晋级图
        if (mFatherTab.equals("NBA") || mFatherTab.equals("CBA")) {
            initRecycleView(mRecyclerViewRank, null);
            mRecyclerViewRank.setNestedScrollingEnabled(false);
        }
        //公用recycleview
        initRecycleView(mRecyclerViewWest, null);
        if (mFatherTab.equals("NBA")) {
            mAdapterWest = new TeamQuenAdapter(getContext(), westList, "西部球队");
            mRecyclerViewWest.setAdapter(mAdapterWest);
        } else if (mFatherTab.equals("CBA") || mFatherTab.equals("欧冠联")|| mFatherTab.equals("日甲")|| mFatherTab.equals("韩甲")) {
            mCbaTeamQuenAdapter = new CbaTeamQuenAdapter(getContext(), mFatherTab, cbaList);
            mRecyclerViewWest.setAdapter(mCbaTeamQuenAdapter);
        }
        mRecyclerViewWest.setNestedScrollingEnabled(false);
        //NBA有东西部区分
        if (mFatherTab.equals("NBA")) {
            initRecycleView(mRecyclerViewEast, null);
            mAdapterEast = new TeamQuenAdapter(getContext(), eastList, "东部球队");
            mRecyclerViewEast.setAdapter(mAdapterEast);
            mRecyclerViewEast.setNestedScrollingEnabled(false);
        }

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
        getData(AppConstants.NORMAL_LOAD);
    }

    private void getData(int loadMode) {
        if (tabName.equals("排名")) {
//            showLoadingDialog();
            mPresenter.getData(ApiConfig.GET_PAI_MING_DATA, loadMode, tabUrl);
        }
    }

    @Override
    public DataModel getModel() {
        return new DataModel();
    }

    @Override
    public void onError(int whichApi, Throwable e) {
        Logger.logD("TAG","排名数据失败："+e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.GET_PAI_MING_DATA:
                mRankingData = (RankingData) t[0];
                if ((int) t[1] == AppConstants.REFRESH_LOAD) {
                    mRefreshLayout.setRefreshing(false);
                    if (mFatherTab.equals("NBA")) {
                        westList.clear();
                        eastList.clear();
                    } else if (mFatherTab.equals("CBA")) {
                        cbaList.clear();
                    }
                }
                List<RankingData.ContentBeanX.RoundsBean> rounds = mRankingData.getContent().getRounds();
                if (rounds.size() == 2) {//当接口返回的集合中包含两个对象，说明该页面既有晋级图又有列表展示
                    List<TempRankInfo> rankList = null;
                    if (mFatherTab.equals("NBA"))
                        rankList = mDataInnerPresenter.getRankList(rounds.get(0));
                    else if (mFatherTab.equals("CBA"))
                        rankList = mDataInnerPresenter.getRankCbaList(rounds.get(0));
                    mAdapter = new RankAdapter(getContext(), rankList, mFatherTab);
                    mRecyclerViewRank.setAdapter(mAdapter);
                    setUnderData(rounds, 1);
                } else {
                    setUnderData(rounds, 0);
                }
                if (mAdapter != null) mAdapter.notifyDataSetChanged();
                if (mAdapterEast != null) mAdapterEast.notifyDataSetChanged();
                if (mAdapterWest != null) mAdapterWest.notifyDataSetChanged();
                if (mCbaTeamQuenAdapter != null) mCbaTeamQuenAdapter.notifyDataSetChanged();
//                hideLoadingDialog();
                break;
        }
    }

    /**
     * @param rounds
     * @param index  0:没有晋级图 1： 有晋级图
     */
    public void setUnderData(List<RankingData.ContentBeanX.RoundsBean> rounds, int index) {
        List<RankingData.ContentBeanX.RoundsBean.ContentBean.DataBean> underListData = rounds.get(index).getContent().getData();
        if (mFatherTab.equals("NBA")) {
            List<RankingData.ContentBeanX.RoundsBean.ContentBean.DataBean.DataBean2> westList = underListData.get(0).data;
            this.westList.addAll(westList);
            if (underListData.size() == 2) {
                List<RankingData.ContentBeanX.RoundsBean.ContentBean.DataBean.DataBean2> eastList = underListData.get(1).data;
                this.eastList.addAll(eastList);
            }
        } else if (mFatherTab.equals("CBA") || mFatherTab.equals("欧冠联")|| mFatherTab.equals("日甲")|| mFatherTab.equals("韩甲")) {
            cbaList.addAll(underListData);
        }
    }
}
