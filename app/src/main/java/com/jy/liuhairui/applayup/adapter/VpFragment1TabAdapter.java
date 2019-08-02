package com.jy.liuhairui.applayup.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.jy.liuhairui.applayup.bean.HomeTabData;

import java.util.ArrayList;
import java.util.List;

public class VpFragment1TabAdapter extends FragmentStatePagerAdapter {


    private List<Fragment> mFragmentList;
    private List<HomeTabData.DataBean.ListBean> tabList;


    public VpFragment1TabAdapter(FragmentManager fm, List<Fragment> fragmentList,
                                 List<HomeTabData.DataBean.ListBean> tabList) {
        super(fm);
        mFragmentList = fragmentList;
        this.tabList = tabList;
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
        return tabList.get(position).getLabel();
    }
}
