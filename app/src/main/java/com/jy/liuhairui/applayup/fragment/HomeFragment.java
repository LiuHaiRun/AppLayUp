package com.jy.liuhairui.applayup.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;


import com.jy.liuhairui.applayup.R;
import com.jy.liuhairui.applayup.adapter.VpFragment1TabAdapter;
import com.jy.liuhairui.applayup.api.ApiConfig;
import com.jy.liuhairui.applayup.bean.HomeTabData;
import com.jy.liuhairui.applayup.frame.BaseMvpFragment;
import com.jy.liuhairui.applayup.fragment_home.ClassiFicationFragment;
import com.jy.liuhairui.applayup.fragment_home.HotFragment;
import com.jy.liuhairui.applayup.fragment_home.NormalFragment;
import com.jy.liuhairui.applayup.fragment_home.VideoFragment;
import com.jy.liuhairui.applayup.fragment_home.WallFragment;
import com.jy.liuhairui.applayup.mvp.NavigationViewModel;
import com.jy.liuhairui.applayup.utils.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseMvpFragment<NavigationViewModel> {

    static HomeFragment  fragment;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    private ArrayList<Fragment> mFragmentList;
    private VpFragment1TabAdapter mAdapter;

    public static HomeFragment newInstance() {
        if (fragment == null) fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }


    @Override
    public void initView() {
        //创建ViewPager容器
        mFragmentList = new ArrayList<>();
        //修改下划线宽度
        tab.setTabIndicatorFullWidth(false);
    }


    @Override
    public void initData() {
       mPresenter.getData(ApiConfig.Tab_DATA);
    }

    @Override
    public NavigationViewModel getModel() {
        return new NavigationViewModel();
    }


    @Override
    public void onError(int whichApi, Throwable e) {
        Logger.logD("TAG","Tab栏数据失败："+e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.Tab_DATA:
                HomeTabData data = (HomeTabData) t[0];
                Logger.logD("TAG","Tab栏数据成功："+data.toString());
                List<HomeTabData.DataBean.ListBean> tabNameList = data.getData().getList();
                for (int i = 0; i < tabNameList.size(); i++) {
                    HomeTabData.DataBean.ListBean listBean = tabNameList.get(i);
                    //获取tan栏对象，将属性封装到Bundle容器中传递到子Fragment
                    String index_match_url = listBean.getIndex_match_url();
                    String api = listBean.getApi();
                    String label = listBean.getLabel();
                    String type = listBean.getType();
                    Bundle bundle = new Bundle();
                    bundle.putString("index_match_url",index_match_url);
                    bundle.putString("api",api);
                    bundle.putString("label",label);
                    if (listBean.getType().equals("hot")){
                        HotFragment hotFragment = new HotFragment();
                        hotFragment.setArguments(bundle);
                        mFragmentList.add(hotFragment);
                    }else if (listBean.getType().equals("normal")){
                        //传对象
                        mFragmentList.add(NormalFragment.newInstance(listBean));
                    }else if (listBean.getType().equals("video")){
                        VideoFragment videoFragment = new VideoFragment();
                        videoFragment.setArguments(bundle);
                        mFragmentList.add(videoFragment);
                    }else if (listBean.getType().equals("wall")){
                        WallFragment wallFragment = new WallFragment();
                        wallFragment.setArguments(bundle);
                        mFragmentList.add(wallFragment);
                    }else if (listBean.getType().equals("classification")){
                        ClassiFicationFragment classiFicationFragment = new ClassiFicationFragment();
                        classiFicationFragment.setArguments(bundle);
                        mFragmentList.add(classiFicationFragment);
                    }
                }
                //绑定
                tab.setupWithViewPager(vp);
                //适配器
                mAdapter = new VpFragment1TabAdapter(getChildFragmentManager(), mFragmentList, tabNameList);
                vp.setAdapter(mAdapter);
                //首次进入显示Tab栏，子页面2
                tab.getTabAt(1).select();

                break;
        }

    }

}
