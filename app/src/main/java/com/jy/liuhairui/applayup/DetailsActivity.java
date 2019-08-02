package com.jy.liuhairui.applayup;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jy.liuhairui.applayup.jscall.JSCallback;
import com.jy.liuhairui.applayup.utils.Logger;

/*
*
*    JS 调用Android
*
*    三种方法
* */
public class DetailsActivity extends BaseNewsDetailActivity {

    /*
    * 第一种：
    * 通过WebView的addJavascriptInterface（）进行映射，
    * 被js调用的方法必须加@JavascriptInterface注解
    * */
    @SuppressLint("AddJavascriptInterface")
    @Override
    public void initData() {
        super.initData();
        if (mData == null) return;
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);// 设置是否可以交互Javascript
        settings.setBuiltInZoomControls(true);//允许缩放控制
        settings.setUseWideViewPort(true);//让Webivew支持<meta>标签的viewport属性 是否任意比例缩放
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);//设置显示模式（狭窄显示）
        //辅助WebView处理JavaScript的对话框，网站图标，网站title，加载进度等
        webView.setWebChromeClient(new MyWebChromeClient());
        webView.setWebViewClient(new MyWebViewClient());
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);// 去掉底部和右边的滚动条
        settings.setDomStorageEnabled(true);//允许本地DOM存储
        settings.setAllowFileAccess(true);//允许访问文件
        settings.setAppCacheEnabled(true);//允许缓存本地
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);//设置缓存模式
        settings.setGeolocationEnabled(true);//允许定位
        settings.setDefaultTextEncodingName("UTF-8");
        settings.setTextZoom(100);//设置页面上的文本缩放百分比，默认100。
        settings.setBuiltInZoomControls(false);// 设置是否支持缩放
        settings.setSupportZoom(false);// 设置是否支持变焦,仅支持双击缩放
        settings.setDefaultFontSize(14);
        //在js中调用本地java方法
        webView.addJavascriptInterface(new JSCallback(this), "Android");
        // Android 5.0上Webview默认不允许加载Http与Https混合内容
        if (Build.VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        //添加网络页面路径
        webView.loadUrl(mData.getUrl());

    }


   /*
   * 第二种：
   * 通过 WebViewClient 的shouldOverrideUrlLoading ()方法回调拦截 url，
   * 然后解析该URL的协议，如果检测到是预先约定好的交互协议，
   * 就调用相应方法，这种方法js不能接受到Android方法的返回值。
   *
   **/

    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                Uri url = request.getUrl();
                Logger.logD("JS：","shouldOverrideUrlLoading方法url="+url);
            }
            return super.shouldOverrideUrlLoading(view, request);
        }
    }


    /*
    * 第三种：
    * 通过 WebChromeClient 的onJsAlert()、onJsConfirm()、onJsPrompt（）方法回调
    * 拦截JS对话框alert()、confirm()、prompt（） 消息，
    * 然后解析消息内容，其实这个方法和第二个差不多，最常用的就是通过拦截js的提示框prompt（）方法，
    * 因为onJSPrompt调用较少，而且可以返回任意类型的值，操作方便灵活，
    * 而alert（）对话框没有返回值，
    * confirm（）对话框只能返回确认和取消两种状态。
    *
    */
    private class MyWebChromeClient extends WebChromeClient {

        //onJSPrompt()可以返回任意类型的值
        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {

            Logger.logD("JS：","onJsPrompt重点方法url="+url+",defaultValue="+defaultValue+",JsPromptResult="+result);
            return super.onJsPrompt(view, url, message, defaultValue, result);

        }

        //confirm（）对话框只能返回确认和取消两种状态
        @Override
        public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
            Logger.logD("JS：","onJsConfirm重点方法url="+url+",message"+message+",result"+result);
            return super.onJsConfirm(view, url, message, result);
        }

        //alert（）对话框没有返回值
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            Logger.logD("JS：","onJsAlert方法url="+url+",message"+message+",result"+result);
            return super.onJsAlert(view, url, message, result);
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            Logger.logD("JS：","onProgressChanged方法newProgress="+newProgress+"");
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            Logger.logD("JS：","onReceivedTitle方法title="+title);
        }

        @Override
        public void onReceivedIcon(WebView view, Bitmap icon) {
            super.onReceivedIcon(view, icon);
            Logger.logD("JS：","onReceivedIcon方法icon="+icon.toString());
        }

        @Override
        public void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed) {
            super.onReceivedTouchIconUrl(view, url, precomposed);
            Logger.logD("JS：","onReceivedTouchIconUrl方法url="+url);
        }

        @Override
        public boolean onJsTimeout() {
            showToast("加载超时");
            return super.onJsTimeout();
        }

        @Override
        public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
            super.onGeolocationPermissionsShowPrompt(origin, callback);
        }

        @Override
        public void onGeolocationPermissionsHidePrompt() {
            super.onGeolocationPermissionsHidePrompt();
        }
    }

    @Override
    protected void onDestroy() {
        webView.loadUrl("about:blank");
        webView.removeAllViews();
        webView.destroy();
        super.onDestroy();
    }
}
