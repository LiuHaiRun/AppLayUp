package com.jy.liuhairui.applayup.utils;

import android.app.Activity;

import java.util.ArrayList;


/*
* 这个工具类使用简单
* 只需要在 onCreate()时调用 addActivity()方法
* 在 onDestroy()时调用removeActivity()方法
* 退出登录的时候调用finishAllActivity()方法即可
**/
public class ActivityCollectorUtil {

    public static ArrayList<Activity> mActivityList = new ArrayList<Activity>();

    /**
     * onCreate()时添加
     * @param activity
     */
    public static void addActivity(Activity activity){
        //判断集合中是否已经添加，添加过的则不再添加
        if (!mActivityList.contains(activity)){
            mActivityList.add(activity);
        }
    }

    /**
     * onDestroy()时删除
     * @param activity
     */
    public static void removeActivity(Activity activity){
        mActivityList.remove(activity);
    }

    /**
     * 可以使用一个集合，在每一个Activity创建的时候将其添加到集合中。
     * 但是这样会比较麻烦，可以抽取一个BaseActivity出来，在onCreate()方法中进行添加，
     * 让其他Activity继承 BaseActivity。这样就省事多了。然后在退出登录的时候循环遍历集合，
     * 调用其finish()方法关闭Activity。
     */
    public static void finishAllActivity(){
        for (Activity activity : mActivityList){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
