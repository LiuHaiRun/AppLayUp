package com.jy.liuhairui.applayup.adapter2;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.jy.liuhairui.applayup.bean.HomeTabData;
import com.jy.liuhairui.applayup.bean2.MatchTabData;

import java.util.List;

public class VpFragment1MacthTabAdapter extends FragmentStatePagerAdapter {


    private List<Fragment> mFragmentList;
    List<MatchTabData.LiveTabsBean> live_tabs;


    public VpFragment1MacthTabAdapter(FragmentManager fm, List<Fragment> fragmentList, List<MatchTabData.LiveTabsBean> live_tabs) {
        super(fm);
        mFragmentList = fragmentList;
        this.live_tabs = live_tabs;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return live_tabs.get(position).getLabel();
    }
}
