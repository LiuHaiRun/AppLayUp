package com.jy.liuhairui.applayup.api;

/**
 *   Retrofit 接口的 Url工具类
 */
public class NetConfig {

    public static String BaseUrl;

    public static int API_TYPE = 1;//1:正式服务器 2：外测服务器 3：内测服务器
    public static String DQD_BASE1 = "http://sport-data.dqdgame.com/";

    static {
        if (API_TYPE == 1) BaseUrl = "https://bkbapi.dqdgame.com/";//首页
        else if (API_TYPE == 2) BaseUrl = "";
        else BaseUrl = "";
    }
}
