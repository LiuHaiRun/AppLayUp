package com.jy.liuhairui.applayup.fragment_datas;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.TextView;


import com.jy.liuhairui.applayup.R;
import com.jy.liuhairui.applayup.api.ApiConfig;
import com.jy.liuhairui.applayup.bean2.MatchTabData;
import com.jy.liuhairui.applayup.bean4.DataTabs;
import com.jy.liuhairui.applayup.design.MyTabView;
import com.jy.liuhairui.applayup.frame.BaseMvpFragment;
import com.jy.liuhairui.applayup.mvp.DataModel;
import com.jy.liuhairui.applayup.utils.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import razerdp.design.WheelViewPopup;

/*
*     数据页面Tab栏与比赛共用一个Url
*     数据页面Tab栏复用一个Fragment
* */
public class DataPublicFragment extends BaseMvpFragment<DataModel> implements MyTabView.OnTabClick {

    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.tab_view)
    MyTabView tabView;

    private MatchTabData.DataTabsBean mParam1;
    private List<DataTabs> mList;
    private FragmentManager mManager;
    private List<Fragment> mFragmentList = new ArrayList<>();
    private int SEASON_INDEX = 0;
    private WheelViewPopup mPop;
    private List<String> mMatchNameList = new ArrayList<>();
    private RankingCUBAFragment mCubaFragment;
    private MatchProgressFragment mMatchProgressCUBA;

    public static DataPublicFragment newInstance(MatchTabData.DataTabsBean dataTabsBean) {
        DataPublicFragment fragment = new DataPublicFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_PARAM1,dataTabsBean);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getParcelable(ARG_PARAM1);
        }
    }

    @Override
    public int getLayoutId() {
        //获取Fragment管理器
        mManager = getChildFragmentManager();
        return R.layout.fragment_data_public;
    }

    @Override
    public void initView() {
        //Tab的点击事件
        tabView.setOnTabClickListener(this);
        //
        titleText.setText(mParam1.getSeason().getTitle());

        if (!mParam1.getLabel().equals("CUBA")) {
            mPop = new WheelViewPopup(getActivity());
            mPop.mConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPop.dismiss();
                    SEASON_INDEX = mPop.getSelectedPos();
                    getSeasonData();
                }
            });
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && mParam1 == null) {
            showToast("fragment加载数据时尚未获取到activity传过来的参数");
        }
        if (isVisibleToUser && mParam1 != null && mList == null) {
            if (mParam1.getLabel().equals("CUBA") && mCubaFragment == null && mMatchProgressCUBA == null) {
                List<MatchTabData.DataTabsBean.SubTabsBean> sub_tabs = mParam1.getSub_tabs();
                //因为CUBA栏的数据分为多个赛区，无法复用
                setCubaData(sub_tabs);
            } else {
                String url = mParam1.getSeason().getUrl();
                mPresenter.getData(ApiConfig.DATA_FOUR_TAB_OUT, url);
            }
        }
    }

    @Override
    public DataModel getModel() {
        return new DataModel();
    }

    @Override
    public void onError(int whichApi, Throwable e) {
        Logger.logD("TAG","DataTabOutInfo失败："+e.getMessage());
    }

    //网络请求成功
    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.DATA_FOUR_TAB_OUT:
                mList = (List<DataTabs>) t[0];
                if (mList == null || mList.size() == 0) return;
                for (int i = 0; i < mList.size(); i++) {
                    mMatchNameList.add(mList.get(i).getSeason_name());
                }
                mPop.setWheelData(mMatchNameList);
                getSeasonData();
                break;
        }
    }

    //创建CUBA的Fragment
    private void setCubaData(List<MatchTabData.DataTabsBean.SubTabsBean> pSub_tabs) {
        List<String> titleList = new ArrayList<>();
        for (int i = 0; i < pSub_tabs.size(); i++) {
            titleList.add(pSub_tabs.get(i).getTitle());
        }
        tabView.setTab(titleList);
        if (mFragmentList.size() != 0) mFragmentList.clear();
        //
        for (int i = 0; i < pSub_tabs.size(); i++) {
            if (pSub_tabs.get(i).getTitle().equals("排名")) {
                mCubaFragment = RankingCUBAFragment.newInstance(pSub_tabs.get(i).getTitle(), pSub_tabs.get(i).getUrl(), mParam1.getLabel());
                mFragmentList.add(mCubaFragment);
            } else if (pSub_tabs.get(i).getTitle().equals("赛程")) {
                mMatchProgressCUBA = MatchProgressFragment.newInstance(pSub_tabs.get(i).getTitle(), pSub_tabs.get(i).getUrl());
                mFragmentList.add(mMatchProgressCUBA);
            }
        }
        mManager.beginTransaction().replace(R.id.fl, mFragmentList.get(0)).commit();
    }

    //创建CUBA以外复用的Fragment
    private void getSeasonData() {
        mPop.setDefaultSelected(SEASON_INDEX);
        tabView.resetView();
        titleText.setText(mList.get(SEASON_INDEX).getSeason_name());
        List<DataTabs.SubTabsBean> sub_tabs = mList.get(SEASON_INDEX).getSub_tabs();
        List<String> titleList = new ArrayList<>();
        for (int i = 0; i < sub_tabs.size(); i++) {
            titleList.add(sub_tabs.get(i).getTitle());
        }
        tabView.setTab(titleList);
        if (mFragmentList.size() != 0) mFragmentList.clear();
        for (int i = 0; i < sub_tabs.size(); i++) {
            if (sub_tabs.get(i).getTitle().equals("排名")) {
                RankingFragment dataTabInnerFragment = RankingFragment.newInstance(sub_tabs.get(i).getTitle(), sub_tabs.get(i).getUrl(), mParam1.getLabel());
                mFragmentList.add(dataTabInnerFragment);
            } else if (sub_tabs.get(i).getTitle().equals("赛程")) {
                MatchProgressFragment fragment = MatchProgressFragment.newInstance(sub_tabs.get(i).getTitle(), sub_tabs.get(i).getUrl());
                mFragmentList.add(fragment);
            } else {//球员和球队
                TeamPlayerFragment teamDataFragment = TeamPlayerFragment.newInstance(sub_tabs.get(i).getTitle(), sub_tabs.get(i).getUrl());
                mFragmentList.add(teamDataFragment);
            }
        }
        mManager.beginTransaction().replace(R.id.fl, mFragmentList.get(0)).commit();
    }

    @OnClick(R.id.title_text)
    public void onViewClicked() {
        if (mParam1.getLabel().equals("CUBA")) {
            showToast(getString(R.string.only_one_season));
            return;
        }
        if (mPop != null) mPop.showPopupWindow();
    }

    //----------------------------------------Tab的点击事件---------------------------------
    @Override
    public void onFirstClick() {
        if (mFragmentList.size() > 0)
            mManager.beginTransaction().replace(R.id.fl, mFragmentList.get(0)).commit();
    }

    @Override
    public void onSecondClick() {
        if (mFragmentList.size() == 4) {
            mManager.beginTransaction().replace(R.id.fl, mFragmentList.get(1)).commit();
        }
    }

    @Override
    public void onThirdClick() {
        if (mFragmentList.size() == 4) {
            mManager.beginTransaction().replace(R.id.fl, mFragmentList.get(2)).commit();
        } else if (mFragmentList.size() == 3) {
            mManager.beginTransaction().replace(R.id.fl, mFragmentList.get(1)).commit();
        }
    }

    @Override
    public void onFourthClick() {
        if (mFragmentList.size() == 4) {
            mManager.beginTransaction().replace(R.id.fl, mFragmentList.get(3)).commit();
        } else if (mFragmentList.size() == 3) {
            mManager.beginTransaction().replace(R.id.fl, mFragmentList.get(2)).commit();
        } else if (mFragmentList.size() == 2) {
            mManager.beginTransaction().replace(R.id.fl, mFragmentList.get(1)).commit();
        }
    }
}
