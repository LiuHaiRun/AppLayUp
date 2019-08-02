package com.jy.liuhairui.applayup.frame;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 任小龙 on 2019/4/1.
 */
public abstract class BaseMvpActivity<M> extends BaseActivity implements ICommonView {

    public Unbinder mBind;
    public CommonPresenter mPresenter;
    public M mModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //绑定布局
        setContentView(getLayoutId());
        //获取id
        mBind = ButterKnife.bind(this);
        mPresenter = getPresenter();
        mModel = getModel();
        if (mPresenter != null) mPresenter.attach(this, (ICommonModel) mModel);
        //初始化View
        initView();
        //初始化数据
        initData();
    }

    public abstract int getLayoutId();

    public abstract void initView();

    public abstract void initData();

    public abstract M getModel();

    //创建P层对象
    public CommonPresenter getPresenter(){
        return new CommonPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
        mPresenter.detach();
    }

    public void netErrorToast(Throwable e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
