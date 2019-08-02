package com.jy.liuhairui.applayup.frame;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 任小龙 on 2019/7/2.
 */
public abstract class BaseMvpFragment<M> extends BaseFragment implements ICommonView {
    private Unbinder mBind;
    public CommonPresenter mPresenter;
    public M mModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //获取布局
        View inflate = inflater.inflate(getLayoutId(), null);
        //获取布局Id
        mBind = ButterKnife.bind(this, inflate);
        //初始化View
        initView();
        //创建P层对象
        mPresenter = getPresenter();
        //获取M层对象
        mModel = getModel();
        if (mPresenter != null && mModel != null) mPresenter.attach(this, (ICommonModel) mModel);
        //获取数据
        initData();
        return inflate;
    }

    public abstract int getLayoutId();

    public abstract void initView();

    public abstract void initData();

    public CommonPresenter getPresenter(){
        return new CommonPresenter();
    }

    public abstract M getModel();

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBind.unbind();
        mPresenter.detach();
    }
    //传参
    protected int getLoadType(Object[] t){
        return  t != null && t.length>1 ? (int) t[1] : 0;
    }
}
