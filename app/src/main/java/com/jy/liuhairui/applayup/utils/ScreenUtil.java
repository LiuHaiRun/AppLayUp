package com.jy.liuhairui.applayup.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 *  屏幕适配工具类
 */
public class ScreenUtil {

    private static DisplayMetrics getDisplayMetrics(Context pContext){
        Resources resources = pContext.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        return dm;
    }

    public static int getDpHeight(Context pContext){
        return (int)(getPxHeight(pContext) / getDensity(pContext));
    }

    public static int getDpWidth(Context pContext){
        return (int)(getPxWidth(pContext) / getDensity(pContext));
    }

    public static float getDensity(Context pContext){
        return getDisplayMetrics(pContext).density;
    }

    public static int getDensityDpi(Context pContext){
        return getDisplayMetrics(pContext).densityDpi;
    }

    public static int getPxWidth(Context pContext){
        return getDisplayMetrics(pContext).widthPixels;
    }

    public static int getPxHeight(Context pContext){
        return getDisplayMetrics(pContext).heightPixels;
    }

}
