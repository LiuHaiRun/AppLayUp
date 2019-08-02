package com.jy.liuhairui.applayup.fragment_datas;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jy.liuhairui.applayup.R;
import com.jy.liuhairui.applayup.adapter4.MatchProgressAdapter;
import com.jy.liuhairui.applayup.api.ApiConfig;
import com.jy.liuhairui.applayup.bean4.MatchProgressions;
import com.jy.liuhairui.applayup.frame.BaseMvpFragment;
import com.jy.liuhairui.applayup.mvp.DataModel;
import com.jy.liuhairui.applayup.utils.Logger;


import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;
import razerdp.design.WheelViewPopup;

/**
 *  赛程
 */
public class MatchProgressFragment extends BaseMvpFragment<DataModel> {

    private static final String TAB_NAME = "tab_name";
    private static final String TAB_URL = "tab_url";
    @BindView(R.id.recyclerView_used)
    RecyclerView recyclerViewUsed;
    @BindView(R.id.next_day_text)
    TextView mNextText;
    @BindView(R.id.pre_day_text)
    TextView mPreText;
    @BindView(R.id.pre_day_used)
    RelativeLayout mPreRl;
    @BindView(R.id.next_day_used)
    LinearLayout mNextLL;
    @BindView(R.id.center_text)
    TextView mCenterText;
    private String tabName;
    private String tabUrl;
    private List<MatchProgressions.ContentBean.RoundsBean> mRounds;
    private List<MatchProgressions.ContentBean.MatchesBean> matches = new ArrayList<>();
    private MatchProgressAdapter mAdapter;
    private int mCurrentPos;
    private WheelViewPopup mPop;
    private List<String> mWheelData = new ArrayList<>();

    public static MatchProgressFragment newInstance(String param1, String param2) {
        MatchProgressFragment fragment = new MatchProgressFragment();
        Bundle args = new Bundle();
        args.putString(TAB_NAME, param1);
        args.putString(TAB_URL, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tabName = getArguments().getString(TAB_NAME);
            tabUrl = getArguments().getString(TAB_URL);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.match_progress_layout;
    }

    @Override
    public void initView() {
        initRecycleView(recyclerViewUsed, null);
        mAdapter = new MatchProgressAdapter(getContext(), matches);
        recyclerViewUsed.setAdapter(mAdapter);
        mPop = new WheelViewPopup(getActivity());
        mPop.mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPop.dismiss();
                mCurrentPos = mPop.getSelectedPos();
                getMatchWithDay();
            }
        });
    }

    @Override
    public void initData() {
//        showLoadingDialog();
        mPresenter.getData(ApiConfig.MATCH_PROGRESS_INFO, tabUrl);
    }

    @Override
    public DataModel getModel() {
        return new DataModel();
    }

    @Override
    public void onError(int whichApi, Throwable e) {
        Logger.logD("TAG","赛程数据失败："+e.getMessage());
    }

    //网络请求成功
    @Override
    public void onResponse(int whichApi, Object[] t) {
//        hideLoadingDialog();
        MatchProgressions matchProgressions = (MatchProgressions) t[0];
        List<MatchProgressions.ContentBean.MatchesBean> matches = matchProgressions.getContent().getMatches();
        if (!TextUtils.isEmpty(matchProgressions.getContent().getPrev())) {
            if (mPreRl.getVisibility() != View.VISIBLE) mPreRl.setVisibility(View.VISIBLE);
            mPreText.setText(matchProgressions.getContent().getPrev());
        } else mPreRl.setVisibility(View.INVISIBLE);
        if (!TextUtils.isEmpty(matchProgressions.getContent().getNext())) {
            mNextText.setText(matchProgressions.getContent().getNext());
            if (mNextLL.getVisibility() != View.VISIBLE)
                mNextLL.setVisibility(View.VISIBLE);
        } else mNextLL.setVisibility(View.INVISIBLE);
        if (this.matches.size() != 0) this.matches.clear();
        this.matches.addAll(matches);
        mAdapter.notifyDataSetChanged();
        switch (whichApi) {
            case ApiConfig.MATCH_PROGRESS_INFO:
                mRounds = matchProgressions.getContent().getRounds();
                mCenterText.setText(mRounds.size() > 1 ? mRounds.get(mRounds.size() - 1).getName() : matches.get(0).getRound_name());
                mCurrentPos = mRounds.size() > 1 ? mRounds.size() - 1 : 0;
                initPopData();
                break;
            case ApiConfig.GET_MATCH_WITH_DAY:
                mCenterText.setText(mRounds.get(mCurrentPos).getName());
                break;
        }
    }

    private void initPopData() {
        if (mWheelData.size() != 0) mWheelData.clear();
        for (int i = 0; i < mRounds.size(); i++) {
            mWheelData.add(mRounds.get(i).getName());
        }
        mPop.setWheelData(mWheelData);
        mPop.setDefaultSelected(mCurrentPos);
    }

    @OnClick({R.id.pre_day_used, R.id.next_day_used, R.id.center_used})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pre_day_used:
                mCurrentPos--;
                if (mCurrentPos < 0) mCurrentPos = 0;
                if (mCurrentPos == 0) mPreRl.setVisibility(View.INVISIBLE);
                if (mCurrentPos < mRounds.size() - 1 && mNextLL.getVisibility() == View.INVISIBLE)
                    mNextLL.setVisibility(View.VISIBLE);
                getMatchWithDay();
                mPop.setDefaultSelected(mCurrentPos);
                break;
            case R.id.next_day_used:
                mCurrentPos++;
                if (mCurrentPos >= mRounds.size()) mCurrentPos = mRounds.size() - 1;
                if (mCurrentPos == mRounds.size() - 1) mNextLL.setVisibility(View.INVISIBLE);
                if (mCurrentPos == 1 && mPreRl.getVisibility() == View.INVISIBLE)
                    mPreRl.setVisibility(View.VISIBLE);
                getMatchWithDay();
                mPop.setDefaultSelected(mCurrentPos);
                break;
            case R.id.center_used:
                mPop.showPopupWindow();
                break;
        }
    }

    private void getMatchWithDay() {
//        showLoadingDialog();
        mPresenter.getData(ApiConfig.GET_MATCH_WITH_DAY, mRounds.get(mCurrentPos).getUrl());
    }
}
