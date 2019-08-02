package com.jy.liuhairui.applayup.adapter3;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.jy.liuhairui.applayup.bean3.CircleTabData;

import java.util.ArrayList;
import java.util.List;

public class VpCircleAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mList;
    private List<CircleTabData.DataBean.ListBean> mTitles;

    public VpCircleAdapter(FragmentManager fm, List<Fragment> list, List<CircleTabData.DataBean.ListBean> titles) {
        super(fm);
        mList = list;
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position).getLabel();
    }
}
