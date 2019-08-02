package com.jy.liuhairui.applayup.frame;

import android.app.Application;
import android.content.Context;

import com.jy.liuhairui.applayup.utils.DeviceUuidFactory;
import com.jy.liuhairui.applayup.utils.Logger;
import com.tencent.qcloud.tim.demo.DemoApplication;

import java.util.UUID;

/**
 * Created by 任小龙 on 2019/6/27.
 */
public class Application10 extends DemoApplication {

    public static Application10 sApplication;
    public boolean mPlayInWifi;
    public String mToKen = "";
    public UUID mUuid;
    public String zcImgUrl = "";
    public String zcName = "";
    public boolean mLogonIM = false;
    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
        //
        mUuid = DeviceUuidFactory.getInstance(getApplication()).getDeviceUuid();
        Logger.logD("TAG","设备号："+mUuid+"\n"+mUuid.toString());
    }
    
    public static Context getAppContext(){
        return sApplication.getApplicationContext();
    }

    public static Application10 getApplication() {
        return sApplication;
    }
}
