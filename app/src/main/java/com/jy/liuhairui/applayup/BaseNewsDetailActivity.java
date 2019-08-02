package com.jy.liuhairui.applayup;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jy.liuhairui.applayup.bean.NormalData;
import com.jy.liuhairui.applayup.design.CommonTitle;
import com.jy.liuhairui.applayup.frame.BaseMvpActivity;
import com.jy.liuhairui.applayup.mvp.NavigationViewModel;

import butterknife.BindView;

public class BaseNewsDetailActivity extends BaseMvpActivity<NavigationViewModel> {


    @BindView(R.id.title)
    CommonTitle title;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.news_detail_edit_comment)
    TextView newsDetailEditComment;
    @BindView(R.id.iv_bottom_comment)
    ImageView ivBottomComment;
    @BindView(R.id.tv_bottom_comment)
    TextView tvBottomComment;
    @BindView(R.id.layout_bottom_comment)
    FrameLayout layoutBottomComment;
    @BindView(R.id.news_detail_fav)
    ImageView newsDetailFav;
    @BindView(R.id.news_detail_top_share)
    ImageView newsDetailTopShare;
    @BindView(R.id.news_detail_send_comment)
    Button newsDetailSendComment;
    @BindView(R.id.news_detail_top_share_relative)
    LinearLayout newsDetailTopShareRelative;
    public NormalData.ArticlesBean mData;

    @Override
    public int getLayoutId() {
        return R.layout.activity_base_news_detail;
    }

    @Override
    public void initView() {
       //获取传递的对象
        mData = getIntent().getParcelableExtra("articlesBean");
        if (mData == null)return;
        title.mMoreText1.setText(mData.getComments_total()+"评论");
        title.mMoreText1.setBackground(ContextCompat.getDrawable(this,R.drawable.comment_bg));
        title.mMoreText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                startActivity(new Intent(BaseNewsDetailActivity.this,TestActivity.class));
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
