package com.example.hao.haotestdemo.acticity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import com.example.hao.haotestdemo.bean.BaseModel;
import com.example.hao.haotestdemo.presenter.BasePresenter;
import com.example.hao.haotestdemo.utils.TUtils;

import butterknife.ButterKnife;

public abstract class BaseActivity<T extends BasePresenter, E extends BaseModel> extends FragmentActivity{
    public Context mContext;
    public T mPresenter;
    public E mModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        mContext = this;
        mPresenter = TUtils.getT(this,0);
        mModel = TUtils.getT(this,1);
        if(mPresenter!=null){
            mPresenter.mContext = mContext;
        }
        this.initPresenter();
        this.initViews();
    }

    public abstract int  getLayoutId();

    public abstract void initPresenter();

    public abstract void initViews();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        destroy();
    }

    public abstract void destroy();
}
