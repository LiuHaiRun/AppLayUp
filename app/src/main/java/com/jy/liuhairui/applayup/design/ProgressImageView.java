package com.jy.liuhairui.applayup.design;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.jy.liuhairui.applayup.R;

/*
* 自定义View
*  加载方形图片
*/
@SuppressLint("AppCompatCustomView")
public class ProgressImageView extends ImageView {
    private Animation animation;

    public ProgressImageView(Context context) {
        super(context);
    }

    public ProgressImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //View初始化完成时执行
    @Override
    protected void onFinishInflate() {
        animation = AnimationUtils.loadAnimation(getContext(), R.anim.progress);
        if (getVisibility() == VISIBLE)
            setAnimation(animation);
        super.onFinishInflate();
    }


    @Override
    public void setVisibility(int visibility) {
        if (visibility == VISIBLE) {
            startAnimation(animation);
        } else {
            clearAnimation();
        }
        super.setVisibility(visibility);
    }
}
