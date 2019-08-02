package com.jy.liuhairui.applayup.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jy.liuhairui.applayup.R;
import com.jy.liuhairui.applayup.adapter3.VpCircleAdapter;
import com.jy.liuhairui.applayup.api.ApiConfig;
import com.jy.liuhairui.applayup.bean3.CircleTabData;
import com.jy.liuhairui.applayup.fragment_circle.CircleFavFragment;
import com.jy.liuhairui.applayup.fragment_circle.CircleNormalFragment;
import com.jy.liuhairui.applayup.fragment_circle.CircleTopicFragment;
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
public class CircleFragment extends BaseMvpFragment<NavigationViewModel> {


    @BindView(R.id.circle_tab)
    TabLayout circleTab;
    @BindView(R.id.circle_vp)
    ViewPager circleVp;
    private List<Fragment> mList;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_circle;
    }

    @Override
    public void initView() {
       //
        mList = new ArrayList<>();
    }

    @Override
    public void initData() {
        //网络请求数据
        mPresenter.getData(ApiConfig.CIRCLE_TAB13);
    }

    @Override
    public NavigationViewModel getModel() {
        return new NavigationViewModel();
    }

    @Override
    public void onError(int whichApi, Throwable e) {
        Logger.logD("TAG","圈子tab栏onError："+e.getMessage());
    }

    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.CIRCLE_TAB13:
                CircleTabData data = (CircleTabData) t[0];
                List<CircleTabData.DataBean.ListBean> mTitles = data.getData().getList();
                Logger.logD("TAG", "圈子Tab栏onResponse：" + mTitles.toString());

                for (int i = 0; i < mTitles.size(); i++) {
                    CircleTabData.DataBean.ListBean listBean = mTitles.get(i);
                    String type = listBean.getType();
                    String api = listBean.getApi();
                    String label = listBean.getLabel();
                    Bundle bundle = new Bundle();
                    bundle.putString("type",type);
                    bundle.putString("api",api);
                    bundle.putString("label",label);
                    if (type.equals("fav")){//关注
                        CircleFavFragment circleFavFragment = new CircleFavFragment();
                        circleFavFragment.setArguments(bundle);
                        mList.add(circleFavFragment);
                    }else if (type.equals("normal")){//推荐,视频
                        CircleNormalFragment circleNormalFragment = new CircleNormalFragment();
                        circleNormalFragment.setArguments(bundle);
                        mList.add(circleNormalFragment);
                    }else if (type.equals("topic")){//话题
                        CircleTopicFragment circleTopicFragment = new CircleTopicFragment();
                        circleTopicFragment.setArguments(bundle);
                        mList.add(circleTopicFragment);
                    }
                }
                //绑定
                circleTab.setupWithViewPager(circleVp);
                //适配器
                VpCircleAdapter adapter = new VpCircleAdapter(getChildFragmentManager(), mList, mTitles);
                circleVp.setAdapter(adapter);
                //首次进入显示Tab栏，子页面2
                circleTab.getTabAt(1).select();
                circleVp.setCurrentItem(1);

                break;
        }
    }

}
