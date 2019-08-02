package com.jy.liuhairui.applayup;

import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.jy.liuhairui.applayup.frame.BaseActivity;
import com.jy.liuhairui.applayup.frame.NormalConfig;
import com.jy.liuhairui.applayup.utils.SharedPrefrenceUtils;

public class SetActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar mTool;
    private SwitchCompat mBtnSwitch;
    private boolean mPlayInWifi;
    /**
     * 退出当前账号
     */
    private Button mBtnDl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        initView();
    }

    private void initView() {
        mTool = (Toolbar) findViewById(R.id.tool);
        mBtnSwitch = (SwitchCompat) findViewById(R.id.btn_switch);
        //初始化ToolBar
        mTool.setTitle("");
        setSupportActionBar(mTool);
        //默认选择的状态
        mPlayInWifi = mApplication.mPlayInWifi;
        mBtnSwitch.setChecked(mPlayInWifi);
        mBtnSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPlayInWifi = isChecked;
            }
        });
        mBtnDl = (Button) findViewById(R.id.btn_dl);
        mBtnDl.setOnClickListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mApplication.mPlayInWifi = mPlayInWifi;
        //保存网络状态，在第一个页面做比较
        SharedPrefrenceUtils.saveBoolean(this, NormalConfig.IS_WIFI_PLAY, mPlayInWifi);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_dl:
                Toast.makeText(mApplication, "退出当前账号", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
