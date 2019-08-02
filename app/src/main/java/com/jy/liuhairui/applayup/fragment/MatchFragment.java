package com.jy.liuhairui.applayup.fragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jy.liuhairui.applayup.R;
import com.jy.liuhairui.applayup.adapter2.VpFragment1MacthTabAdapter;
import com.jy.liuhairui.applayup.api.ApiConfig;
import com.jy.liuhairui.applayup.bean2.MatchTabData;
import com.jy.liuhairui.applayup.fragment_match.FavorFragment;
import com.jy.liuhairui.applayup.fragment_match.LeagueFragment;
import com.jy.liuhairui.applayup.frame.BaseMvpFragment;
import com.jy.liuhairui.applayup.mvp.NavigationViewModel;
import com.jy.liuhairui.applayup.utils.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MatchFragment extends BaseMvpFragment<NavigationViewModel> {

    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.fab)
    FloatingActionButton fab;



    private ArrayList<Fragment> mList;
    private VpFragment1MacthTabAdapter mAdapter;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_match;
    }

    @Override
    public void initView() {
        mList = new ArrayList<>();

        //悬浮按钮点击事件
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "你点击了悬浮按钮", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void initData() {

        mPresenter.getData(ApiConfig.MATCH_TAB_DATA9);
    }

    @Override
    public NavigationViewModel getModel() {
        return new NavigationViewModel();
    }

    @Override
    public void onError(int whichApi, Throwable e) {
        Logger.logD("TAG", "比赛Tab栏数据失败：" + e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.MATCH_TAB_DATA9:
                MatchTabData tabData = (MatchTabData) t[0];
                Logger.logD("TAG", "比赛Tab栏数据成功：" + tabData.toString());
                List<MatchTabData.LiveTabsBean> live_tabs = tabData.getLive_tabs();
                for (int i = 0; i < live_tabs.size(); i++) {
                    //获取Tab对象数据
                    MatchTabData.LiveTabsBean liveTabsBean = live_tabs.get(i);
                    int id = liveTabsBean.getId();
                    String label = liveTabsBean.getLabel();
                    String type = liveTabsBean.getType();
                    String api = liveTabsBean.getApi();
                    Bundle bundle = new Bundle();
                    bundle.putString("api", api);
                    bundle.putString("type", type);
                    bundle.putString("label", label);
                    bundle.putString("id", id+"");
                    if (type.equals("favor")) {
                        FavorFragment favorFragment = new FavorFragment();
                        favorFragment.setArguments(bundle);
                        mList.add(favorFragment);
                    } else if (type.equals("league")) {
                        LeagueFragment leagueFragment = new LeagueFragment();
                        leagueFragment.setArguments(bundle);
                        mList.add(leagueFragment);
                    }
                }
                //绑定
                tablayout.setupWithViewPager(vp);
                //适配器
                mAdapter = new VpFragment1MacthTabAdapter(getChildFragmentManager(), mList, live_tabs);
                vp.setAdapter(mAdapter);
                //首次进入显示Tab栏，子页面2
                tablayout.getTabAt(1).select();

                break;

        }
    }

}
