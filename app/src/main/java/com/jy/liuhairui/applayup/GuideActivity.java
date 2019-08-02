package com.jy.liuhairui.applayup;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;

import com.huawei.android.hms.agent.HMSAgent;
import com.huawei.android.hms.agent.common.handler.ConnectHandler;
import com.huawei.android.hms.agent.push.handler.GetTokenHandler;
import com.jy.liuhairui.applayup.frame.BaseActivity;
import com.jy.liuhairui.applayup.utils.AppConstants;
import com.jy.liuhairui.applayup.utils.Logger;
import com.jy.liuhairui.applayup.utils.SharedPrefrenceUtils;
import com.tencent.qcloud.tim.demo.thirdpush.ThirdPushTokenMgr;
import com.tencent.qcloud.tim.demo.utils.DemoLog;

import static com.jy.liuhairui.applayup.frame.NormalConfig.ISFIRST;
import static com.jy.liuhairui.applayup.frame.NormalConfig.IS_WIFI_PLAY;

public class GuideActivity extends BaseActivity {

    private static final String TAG = "GuideActivity";
    private Handler mHandler = new Handler();
    private int time = 2;
    private boolean mIsFirst;
    private boolean mIsWifiPlay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        //
        initTime();
    }


    private Animation.AnimationListener mAnimationListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
            //获取设置页面保存的网络状态做设置
            mIsFirst = SharedPrefrenceUtils.getBoolean(GuideActivity.this, ISFIRST, true);
            if (mIsFirst){
                SharedPrefrenceUtils.saveBoolean(GuideActivity.this,ISFIRST,false);
            }
            //WiFi网络
            mIsWifiPlay = SharedPrefrenceUtils.getBoolean(GuideActivity.this,IS_WIFI_PLAY,true);
            mApplication.mPlayInWifi = mIsWifiPlay;
            //登录后返回的个人签名
            String token = SharedPrefrenceUtils.getString(GuideActivity.this, AppConstants.USER_TOKEN, "");
             mApplication.mToKen = token;
            Logger.logD("TAG", "保存在sp的token: " + token);
            //登录后的头像
            String userImg = SharedPrefrenceUtils.getString(GuideActivity.this, AppConstants.LOGON_IMG, "");
            mApplication.zcImgUrl = userImg;
            Logger.logD("TAG", "保存在sp的UserImgUrl: " + userImg);
            //登录账户
            String name = SharedPrefrenceUtils.getString(GuideActivity.this, AppConstants.USER_NAME, "");
            mApplication.zcName = name;
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if (mIsFirst){
                startActivity(new Intent(GuideActivity.this,FavTeamActivity.class));
            } else {
                startActivity(new Intent(GuideActivity.this, HomeActivity.class));
            }
            GuideActivity.this.finish();
        }
    };

    //2秒后调整到首页
    private void initTime() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (time == 0){
                    Intent intent = new Intent(GuideActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    time--;
                    initTime();
                }
            }
        },1000);
    }

    /*if (IMFunc.isBrandHuawei()) {
        // 华为离线推送
        HMSAgent.connect(this, new ConnectHandler() {
            @Override
            public void onConnect(int rst) {
                DemoLog.i(TAG, "huawei push HMS connect end:" + rst);
            }
        });
        getHuaWeiPushToken();
    }
    if (IMFunc.isBrandVivo()) {
        // vivo离线推送
        PushClient.getInstance(getApplicationContext()).turnOnPush(new IPushActionListener() {
            @Override
            public void onStateChanged(int state) {
                if (state == 0) {
                    String regId = PushClient.getInstance(getApplicationContext()).getRegId();
                    DemoLog.i(TAG, "vivopush open vivo push success regId = " + regId);
                    ThirdPushTokenMgr.getInstance().setThirdPushToken(regId);
                    ThirdPushTokenMgr.getInstance().setPushTokenToTIM();
                } else {
                    // 根据vivo推送文档说明，state = 101 表示该vivo机型或者版本不支持vivo推送，链接：https://dev.vivo.com.cn/documentCenter/doc/156
                    DemoLog.i(TAG, "vivopush open vivo push fail state = " + state);
                }
            }
        });
    }

    private void getHuaWeiPushToken() {
        HMSAgent.Push.getToken(new GetTokenHandler() {
            @Override
            public void onResult(int rtnCode) {
                DemoLog.i(TAG, "huawei push get token: end" + rtnCode);
            }
        });
    }*/
}
