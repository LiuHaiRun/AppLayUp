package com.jy.liuhairui.applayup.utils;

import android.os.Environment;

import com.jy.liuhairui.applayup.frame.Application10;

import java.io.File;

/**
 * 常量类
 */
public interface AppConstants {

    String PATH_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath() +
            File.separator + "code" + File.separator + "GeekNews";

    String FILE_PROVIDER_AUTHORITY="com.baidu.geek.fileprovider";

    //网络缓存的地址
    String PATH_DATA =Application10.getAppContext().getCacheDir().getAbsolutePath() +
            File.separator + "data";

    String PATH_CACHE = PATH_DATA + "/NetCache";


    //HomeActivity的Tab栏下标
    public static final int CONSTANT_ZERO = 0;
    public static final int CONSTANT_ONE = 1;
    public static final int CONSTANT_TWO = 2;
    public static final int CONSTANT_THREE = 3;

    //侧滑中RecyclerView子条目的id
    public static final int CONSTANT_ID_ZERO = 1555149627;
    public static final int CONSTANT_ID_ONE = 1551665241;
    public static final int CONSTANT_ID_TWO = 1544777770;
    public static final int CONSTANT_ID_THREE = 1541763079;
    public static final int CONSTANT_ID_FOUR = 1551665267;
    public static final int CONSTANT_ID_FIVE = 1550738388;
    public static final int CONSTANT_ID_SIX = 1555140616;

    //刷新
    public static final int NORMAL_LOAD = 10086;//正常
    public static final int REFRESH_LOAD = 10087;//下拉刷新
    public static final int MORE_LOAD = 10088;//上拉加载更多

    //登录
    public static final String LOGON_IMG = "imgUrl";
    public static final String USER_TOKEN = "token";
    public static final String USER_NAME = "name";

}
