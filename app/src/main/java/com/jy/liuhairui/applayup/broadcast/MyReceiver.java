package com.jy.liuhairui.applayup.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.jy.liuhairui.applayup.utils.NetworkUtils;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ///判断网络状态
        if(intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)){
            int state = NetworkUtils.getNetWorkState(context);
            if (mState != null){
                //调用接口传状态
                mState.onChanged(state);
            }
        }
    }
    //定义接口
    private NetworkState mState;
    public void setState(NetworkState state) {
        mState = state;
    }
    public interface NetworkState{
        void onChanged(int state);
    }
}
