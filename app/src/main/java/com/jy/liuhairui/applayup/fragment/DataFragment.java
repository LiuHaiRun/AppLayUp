package com.jy.liuhairui.applayup.fragment;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.jy.liuhairui.applayup.R;
import com.jy.liuhairui.applayup.adapter4.VpDataTabAdapter;
import com.jy.liuhairui.applayup.api.ApiConfig;
import com.jy.liuhairui.applayup.bean2.MatchTabData;
import com.jy.liuhairui.applayup.fragment_datas.DataPublicFragment;
import com.jy.liuhairui.applayup.frame.BaseMvpFragment;
import com.jy.liuhairui.applayup.mvp.NavigationViewModel;
import com.jy.liuhairui.applayup.utils.Logger;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DataFragment extends BaseMvpFragment<NavigationViewModel> {


    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    private ArrayList<Fragment>  mFragments = new ArrayList<>();
    public VpDataTabAdapter mAdapter;
    private static DataFragment fragment;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_data;
    }

    @Override
    public void initView() {
        //


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
        Logger.logD("TAG", "数据Tab栏请求onError：" + e.getMessage());
    }

    public static DataFragment newInstance() {
        if (fragment == null) fragment = new DataFragment();
        return fragment;
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.MATCH_TAB_DATA9:
                MatchTabData data = (MatchTabData) t[0];
                //获取tab栏集合
                List<MatchTabData.DataTabsBean> data_tabs = data.getData_tabs();
                for (int i = 0; i < data_tabs.size(); i++) {
                    MatchTabData.DataTabsBean dataTabsBean = data_tabs.get(i);
                    mFragments.add(DataPublicFragment.newInstance(dataTabsBean));
                }
                //绑定
                tab.setupWithViewPager(vp);
                //适配器
                mAdapter = new VpDataTabAdapter(getChildFragmentManager(), mFragments, data_tabs);
                vp.setAdapter(mAdapter);

                break;
        }
    }

}
