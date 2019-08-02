package com.jy.liuhairui.applayup.utils;

import android.util.Log;
import com.jy.liuhairui.applayup.BuildConfig;


/**
 * Log日志工具类
 */
public class Logger {

    public static void logD(String tag,String msg){
        if (BuildConfig.DEBUG){
            Log.d(tag, "logD: "+msg);
        }
    }
}
