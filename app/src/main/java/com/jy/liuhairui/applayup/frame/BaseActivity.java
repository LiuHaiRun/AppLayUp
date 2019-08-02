package com.jy.liuhairui.applayup.frame;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.jy.liuhairui.applayup.R;
import com.jy.liuhairui.applayup.broadcast.MyReceiver;
import com.jy.liuhairui.applayup.design.LoadingDialogWithContent;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;


import static com.jy.liuhairui.applayup.utils.NetworkUtils.NETWORK_MOBILE;
import static com.jy.liuhairui.applayup.utils.NetworkUtils.NETWORK_NONE;
import static com.jy.liuhairui.applayup.utils.NetworkUtils.NETWORK_WIFI;
import static com.scwang.smartrefresh.layout.util.DensityUtil.px2dp;

/**
 *
 */
public class BaseActivity extends AppCompatActivity implements MyReceiver.NetworkState {

    public LinearLayoutManager mManager;
    public Application10 mApplication;
    public LoadingDialogWithContent mDialog;
    public MyReceiver mReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showLog("我就是一个类："+this.getClass().getSimpleName());
        mApplication = (Application10) getApplication();
        //1.初始化数据的进度条
        mDialog = new LoadingDialogWithContent(this, getString(R.string.loading));

    }

    //1.2进度条的显示
    public void showLoadingDialog() {
        if (!mDialog.isShowing()) mDialog.show();
    }
    //1.3进度条的隐藏
    public void hideLoadingDialog() {
        if (mDialog.isShowing()) mDialog.dismiss();
    }

    public void showLog(String content) {
        Log.e(NormalConfig.log1, content);
    }

    public void showLog(boolean content) {
        Log.e(NormalConfig.log1, "" + content);
    }

    //View绑定刷新
    public void initRecycleView(RecyclerView recyclerView, RefreshLayout refreshLayout) {
        mManager = new LinearLayoutManager(this);
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

    //获取参数
    protected int getLoadType( Object[] t){
        return  (int) ((Object[]) t[1])[0];
    }

    //刷新
    public void refresh() {
    }

    //加载更多
    public void loadMore() {
    }

    public void showToast(String content) {
        Toast.makeText(getApplicationContext(), content, Toast.LENGTH_SHORT).show();
    }

    public void showLongToast(String content) {
        Toast.makeText(getApplicationContext(), content, Toast.LENGTH_LONG).show();
    }

    //2.1自定义方法注册广播监听网络状态，当子类有需要时在生命周期中调用做注册使用
    public void registerNetWorkStatusReceiver(){
        //注册广播
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.jy.liuhairui.applayup.frame.CONNECTIVITY_ACTION");
        mReceiver = new MyReceiver();
        //广播中的接口回调
        mReceiver.setState(this);
        registerReceiver(mReceiver,filter);
    }

    //广播中接口回调的方法获取广播中的网络状态
    @Override
    public void onChanged(int state) {
        //判断网络状态是移动网 还是 无线网
        if (state == NETWORK_MOBILE || state == NETWORK_WIFI){
            onNetConnected();
        }else if (state == NETWORK_NONE){//没网
            onNetDisConnected();
        }

    }

    //自定义方法，有网时调用
    public void onNetConnected() {
    }
    //自定义方法，没网时调用
    public void onNetDisConnected() {
    }
}
