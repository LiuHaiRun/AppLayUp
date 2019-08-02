package com.jy.liuhairui.applayup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jy.liuhairui.applayup.adapter.VpImagAdapter;
import com.jy.liuhairui.applayup.frame.BaseMvpActivity;
import com.jy.liuhairui.applayup.mvp.NavigationViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageActivity extends BaseMvpActivity<NavigationViewModel> {

    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.tv)
    TextView tv;
    private ArrayList<View> mViews;
    private ImageView mImageView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_image;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        ArrayList<String> list = intent.getStringArrayListExtra("list");
        String index = intent.getStringExtra("index");
        int i1 = Integer.parseInt(index);
        mViews = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            String uri = list.get(i);

            View inflate = LayoutInflater.from(this).inflate(R.layout.image_vp_layout, null);
            mImageView = inflate.findViewById(R.id.vp_img);
            Glide.with(this).load(uri).into(mImageView);
            tv.setText(""+(i1+1)+"/"+list.size());
            mViews.add(inflate);
        }
        //
        VpImagAdapter adapter = new VpImagAdapter(mViews);
        vp.setAdapter(adapter);
        //图片位置
        vp.setCurrentItem(i1);
        //
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
