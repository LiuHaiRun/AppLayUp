package com.jy.liuhairui.applayup.frame;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jy.liuhairui.applayup.R;
import com.jy.liuhairui.applayup.design.LoadingDialogWithContent;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;


import static com.scwang.smartrefresh.layout.util.DensityUtil.px2dp;

/**
 *
 */
public class BaseFragment extends Fragment {
    public LinearLayoutManager mManager;
    private LoadingDialogWithContent mDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //获取网络数据进度工具类对象
        mDialog = new LoadingDialogWithContent(getContext(), getString(R.string.loading));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    //导致空指针异常
    public void showLoadingDialog() {
        if (!mDialog.isShowing()) mDialog.show();
    }
    public void showToast(String content) {
        Toast.makeText(getContext().getApplicationContext(), content, Toast.LENGTH_SHORT).show();
    }
    public void showLongToast(String content) {
        Toast.makeText(getContext().getApplicationContext(), content, Toast.LENGTH_LONG).show();
    }
    public void hideLoadingDialog() {
        if (mDialog.isShowing()) mDialog.dismiss();
    }


    //View控件绑定刷新
    public void initRecycleView(RecyclerView recyclerView, RefreshLayout refreshLayout) {
        //获取布局管理器
        mManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mManager);
        if (refreshLayout != null) {
            refreshLayout.setHeaderHeight(px2dp(120));
            refreshLayout.setFooterHeight(px2dp(100));
            refreshLayout.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                    refresh();
                }
            });
            refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                    loadMore();
                }
            });
        }
    }

    //下拉刷新
    public void refresh() {
    }

    //上拉加载更多
    public void loadMore() {
    }
    //吐司
    public void showLog(String content) {
        Log.e(NormalConfig.log1, content);
    }
    public void showLog(boolean content) {
        Log.e(NormalConfig.log1, "" + content);
    }
}
