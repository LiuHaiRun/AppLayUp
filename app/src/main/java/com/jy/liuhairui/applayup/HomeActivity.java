package com.jy.liuhairui.applayup;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jy.liuhairui.applayup.adapter.RlvNavigationViewAdapter;
import com.jy.liuhairui.applayup.api.ApiConfig;
import com.jy.liuhairui.applayup.bean.NavigationViewData;
import com.jy.liuhairui.applayup.design.RoundImage;
import com.jy.liuhairui.applayup.fragment.CircleFragment;
import com.jy.liuhairui.applayup.fragment.DataFragment;
import com.jy.liuhairui.applayup.fragment.HomeFragment;
import com.jy.liuhairui.applayup.fragment.MatchFragment;
import com.jy.liuhairui.applayup.frame.BaseMvpActivity;
import com.jy.liuhairui.applayup.mvp.NavigationViewModel;
import com.jy.liuhairui.applayup.utils.AppConstants;
import com.jy.liuhairui.applayup.utils.FragmentUtils;
import com.jy.liuhairui.applayup.utils.Logger;
import com.tencent.qcloud.tim.demo.login.LoginForDevActivity;
import com.tencent.qcloud.tim.demo.main.MainActivity;
import com.tencent.qcloud.tim.demo.signature.GenerateTestUserSig;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.base.IUIKitCallBack;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCMediaManager;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class HomeActivity extends BaseMvpActivity<NavigationViewModel> implements RlvNavigationViewAdapter.OnItem {


    @BindView(R.id.tool_img)
    RoundImage toolImg;
    @BindView(R.id.tool_text)
    TextView toolText;
    @BindView(R.id.tool)
    Toolbar tool;
    @BindView(R.id.radiogroup)
    RadioGroup radiogroup;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.btn_dl)
    TextView btnDl;
    @BindView(R.id.btn_notification)
    LinearLayout btnNotification;
    @BindView(R.id.btn_add)
    LinearLayout btnAdd;
    @BindView(R.id.btn_message)
    LinearLayout btnMessage;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.btn_feedback)
    LinearLayout btnFeedback;
    @BindView(R.id.btn_seek)
    LinearLayout btnSeek;
    @BindView(R.id.btn_set)
    LinearLayout btnSet;
    @BindView(R.id.navigationView)
    NavigationView navigationView;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.na_img)
    RoundImage naImg;
    private ArrayList<NavigationViewData.ModulesBean> mModulesBeans;
    private RlvNavigationViewAdapter mAdapter;
    private boolean mWhenComeIsDisConnected;
    private NavigationViewData mData;
    public JCVideoPlayerStandard mVideoPlayer;
    public boolean mSaveInstanceHasPerform;

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void initView() {
        //绑定ToolBar
        initTool();
        //初始化侧滑
        initDrawerLayout();
        //初始化Tab栏
        initTab();
        ///第一次进去默认显示首页
        FragmentUtils.addFragment(getSupportFragmentManager(), HomeFragment.class, R.id.fl);
    }

    private void initTab() {
        //点击单选按钮切换Fragment
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.main_home:
                        FragmentUtils.addFragment(getSupportFragmentManager(), HomeFragment.class, R.id.fl);
                        toolText.setText(R.string.home_tab_activity);
                        break;
                    case R.id.main_match:
                        FragmentUtils.addFragment(getSupportFragmentManager(), MatchFragment.class, R.id.fl);
                        toolText.setText(R.string.match_tab_activity);
                        break;
                    case R.id.main_circle:
                        FragmentUtils.addFragment(getSupportFragmentManager(), CircleFragment.class, R.id.fl);
                        toolText.setText(R.string.circle_tab_activity);
                        break;
                    case R.id.main_data:
                        FragmentUtils.addFragment(getSupportFragmentManager(), DataFragment.class, R.id.fl);
                        toolText.setText(R.string.data_tab_activity);
                        break;
                }
            }
        });

    }

    //在onPause()-onStop()之间执行
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mSaveInstanceHasPerform = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSaveInstanceHasPerform = false;
        if (mVideoPlayer != null && mVideoPlayer.currentState == JCVideoPlayer.CURRENT_STATE_PAUSE) {
            mVideoPlayer.startVideo();
        }
        //调用父类的方法注册广播
        registerNetWorkStatusReceiver();

        //判断登录
        if (!TextUtils.isEmpty(mApplication.mToKen)) {
            btnDl.setVisibility(View.GONE);
            naImg.setVisibility(View.VISIBLE);
            Glide.with(this).load(mApplication.zcImgUrl).into(naImg);
            Glide.with(this).load(mApplication.zcImgUrl).into(toolImg);
        } else {
            btnDl.setVisibility(View.VISIBLE);
            naImg.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseVideo();
        //注销广播
        if (mReceiver != null) unregisterReceiver(mReceiver);
    }

    //网络下的播放
    public void releaseVideo() {
        if (mVideoPlayer != null) {
            if (mVideoPlayer.currentState == JCVideoPlayer.CURRENT_STATE_PLAYING) {
//                mApplication.mVideoPlayer.startButton.performClick();
                JCMediaManager.instance().mediaPlayer.pause();
                mVideoPlayer.currentState = JCVideoPlayer.CURRENT_STATE_PAUSE;
            } else if (mVideoPlayer.currentState == JCVideoPlayer.CURRENT_STATE_PREPARING) {
                JCVideoPlayer.releaseAllVideos();
            }
        }
    }

    //判断网络有网
    @Override
    public void onNetConnected() {
        super.onNetConnected();
        if (mData == null && mWhenComeIsDisConnected) {
            mPresenter.getData(ApiConfig.NAVIGATIONVIEW_DATA);
            mWhenComeIsDisConnected = false;
        }
    }

    //判断网络没网
    @Override
    public void onNetDisConnected() {
        super.onNetDisConnected();
        mWhenComeIsDisConnected = true;
    }

    private void initTool() {
        tool.setTitle("");
        setSupportActionBar(tool);
        Glide.with(this)
                .load(R.drawable.icon_reward_default_head)
                .into(toolImg);
        toolImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击图片打开侧滑
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

    }

    private void initDrawerLayout() {
        //初始化侧滑菜单中的RecyclerView
        mModulesBeans = new ArrayList<>();
        //管理器
        rv.setLayoutManager(new GridLayoutManager(this, 3));
        //禁止滑动
        rv.setNestedScrollingEnabled(false);
        //适配器
        mAdapter = new RlvNavigationViewAdapter(this, mModulesBeans);
        rv.setAdapter(mAdapter);
        //接口回调
        mAdapter.setOnItem(this);
        //开关侧滑
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                //主界面同步侧滑移动
                rl.setX(navigationView.getWidth() * slideOffset);
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        //解决图片不显示的BUG
        navigationView.setItemIconTintList(null);
        //侧滑菜单的点击事件
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                }

                return false;
            }
        });
    }

    @Override
    public void initData() {
        //显示进度条
        showLoadingDialog();
        //调用P层
        mPresenter.getData(ApiConfig.NAVIGATIONVIEW_DATA);
    }


    @Override
    public NavigationViewModel getModel() {
        return new NavigationViewModel();
    }

    //网络请求失败
    @Override
    public void onError(int whichApi, Throwable e) {
        Logger.logD("TAG", "侧滑菜单中的Recycler数据onError:" + e.getMessage());
    }

    //网络请求成功
    @Override
    public void onResponse(int whichApi, Object[] t) {
        switch (whichApi) {
            case ApiConfig.NAVIGATIONVIEW_DATA:
                //关闭进度条
                hideLoadingDialog();
                mData = (NavigationViewData) t[0];
                Logger.logD("TAG", "侧滑菜单中的Recycler数据" + mData.toString());
                List<NavigationViewData.ModulesBean> modules = mData.getModules();

                mModulesBeans.addAll(modules);
                mAdapter.notifyDataSetChanged();
                break;
        }

    }


    @OnClick({R.id.btn_dl, R.id.btn_notification, R.id.btn_add, R.id.btn_message, R.id.btn_feedback, R.id.btn_seek, R.id.btn_set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_dl://登录
                Intent intent1 = new Intent(this, LogonActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_notification://我的通知，跳转到第三方腾讯云通信
                startActivity(new Intent(this, LoginForDevActivity.class));
                break;
            case R.id.btn_add://收藏/历史
                Toast.makeText(mApplication, "收藏/历史", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_message://我的消息
                Toast.makeText(mApplication, "我的消息", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_feedback://反馈
                Toast.makeText(mApplication, "反馈", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_seek://搜索
                Toast.makeText(mApplication, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_set://设置
                //跳转到设置页面设置WiFi网络状态
                Intent intent = new Intent(this, SetActivity.class);
                startActivity(intent);
                break;
        }
    }

    //接口回调,设置RecyclerView子条目点击事件
    @Override
    public void OnItemClick(View view, int position) {
        NavigationViewData.ModulesBean modulesBean = mModulesBeans.get(position);
        int id = modulesBean.getId();
        switch (id) {
            case AppConstants.CONSTANT_ID_ZERO:
                Toast.makeText(mApplication, modulesBean.getName(), Toast.LENGTH_SHORT).show();
                break;
            case AppConstants.CONSTANT_ID_ONE:
                //关注球队
                Intent intent = new Intent(this, FavTeamActivity.class);
                startActivity(intent);
                break;
            case AppConstants.CONSTANT_ID_TWO:
                Toast.makeText(mApplication, modulesBean.getName(), Toast.LENGTH_SHORT).show();
                break;
            case AppConstants.CONSTANT_ID_THREE:
                Toast.makeText(mApplication, modulesBean.getName(), Toast.LENGTH_SHORT).show();
                break;
            case AppConstants.CONSTANT_ID_FOUR:
                Toast.makeText(mApplication, modulesBean.getName(), Toast.LENGTH_SHORT).show();
                break;
            case AppConstants.CONSTANT_ID_FIVE:
                Toast.makeText(mApplication, modulesBean.getName(), Toast.LENGTH_SHORT).show();
                break;
            case AppConstants.CONSTANT_ID_SIX:
                Toast.makeText(mApplication, modulesBean.getName(), Toast.LENGTH_SHORT).show();
                break;
        }

    }

}
