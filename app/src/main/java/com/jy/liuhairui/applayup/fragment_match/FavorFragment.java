package com.jy.liuhairui.applayup.fragment_match;


import android.support.v4.app.Fragment;

import com.jy.liuhairui.applayup.R;
import com.jy.liuhairui.applayup.frame.BaseMvpFragment;
import com.jy.liuhairui.applayup.mvp.NavigationViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavorFragment extends BaseMvpFragment<NavigationViewModel> {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_favor;
    }

    @Override
    public void initView() {
        String api = getArguments().getString("api");
    }

    @Override
    public void initData() {

    }

    @Override
    public NavigationViewModel getModel() {
        return null;
    }

    @Override
    public void onError(int whichApi, Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

    }
}
