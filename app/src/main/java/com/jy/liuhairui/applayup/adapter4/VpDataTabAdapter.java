package com.jy.liuhairui.applayup.adapter4;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;

import com.jy.liuhairui.applayup.bean2.MatchTabData;

import java.util.List;

public class VpDataTabAdapter extends FragmentStatePagerAdapter {


    private FragmentManager mManager;
    private List<Fragment> mFragmentList;
    private List<MatchTabData.DataTabsBean> data_tabs;

    public VpDataTabAdapter(FragmentManager fm, List<Fragment> fragmentList, List<MatchTabData.DataTabsBean> data_tabs) {
        super(fm);
        mManager = fm;
        mFragmentList = fragmentList;
        this.data_tabs = data_tabs;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = mFragmentList.get(position);
        if (!fragment.isAdded()) { // 如果fragment还没有added
            FragmentTransaction transaction = mManager.beginTransaction();//通过fragment管理器获取事务
            transaction.add(fragment, fragment.getClass().getSimpleName());//
            transaction.commit();
            mManager.executePendingTransactions();
        }
        if (fragment.getView().getParent() == null) {
            container.addView(fragment.getView()); // 为viewpager增加布局
        }

        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (mFragmentList != null && mFragmentList.get(position).getView() != null)
            container.removeView(mFragmentList.get(position).getView());

    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList != null ? mFragmentList.get(position): null;
    }

    @Override
    public int getCount() {
        return mFragmentList != null ? mFragmentList.size(): 0;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return  data_tabs.get(position).getLabel();
    }


    /*
     * getItemPosition() 函数，返回 POSITION_NONE，fragmentpageradapter和fragmentstatepageradapter的解决方案都需要的。
     * 二者不同之处在于，FragmentStatePagerAdapter 会在因 POSITION_NONE 触发调用的 destroyItem() 中真正的释放资源，
     * 重新建立一个新的 Fragment；而 FragmentPagerAdapter 仅仅会在 destroyItem() 中 detach 这个 Fragment，
     * 在 instantiateItem() 时会使用旧的 Fragment，并触发 attach，因此没有释放资源及重建的过程。
     * */
    @Override
    public int getItemPosition(@NonNull Object object) {
        return PagerAdapter.POSITION_NONE;
    }
}
