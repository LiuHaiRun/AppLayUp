package com.jy.liuhairui.applayup;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.jy.liuhairui.applayup.design.CommonTitle;
import com.jy.liuhairui.applayup.frame.BaseMvpActivity;
import com.jy.liuhairui.applayup.mvp.NavigationViewModel;

import butterknife.BindView;

/*
*  Android 调用JS方法
* */
public class TestActivity extends BaseMvpActivity<NavigationViewModel> {


    @BindView(R.id.title)
    CommonTitle title;
    @BindView(R.id.webView)
    WebView webView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_text;
    }


    @Override
    public void initView() {

        //短按做吐司
        title.mTvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.evaluateJavascript("javascript:showAlert()", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        showToast(value);
                    }
                });
            }
        });

        //长按
        title.mTvTitle.setOnLongClickListener(new View.OnLongClickListener() {
            //反斜杠在代码中表示反编译
            @Override
            public boolean onLongClick(View v) {
                webView.evaluateJavascript("javascript:showContent(\"参数\")", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        showToast(value);
                    }
                });
                return true;
            }
        });

        //获取Web设置参数的对象
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);//允许使用
        settings.setJavaScriptCanOpenWindowsAutomatically(true);//允许弹窗
        webView.loadUrl("file:///android_asset/policy.html");
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {

                new AlertDialog.Builder(TestActivity.this)
                        .setIcon(R.drawable.ic_launcher_background)
                        .setTitle("标题")
                        .setMessage(message)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //
                                result.confirm();
                            }
                        }).setCancelable(false)
                        .create().show();
                return true;
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public NavigationViewModel getModel() {
        return null;
    }

    @Override
    public void onError(int whichApi, Throwable e) {

    }

    @Override
    public void onResponse(int whichApi, Object[] t) {

    }

}
