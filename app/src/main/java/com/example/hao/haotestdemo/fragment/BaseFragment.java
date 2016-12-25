package com.example.hao.haotestdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hao.haotestdemo.bean.BaseModel;
import com.example.hao.haotestdemo.presenter.BasePresenter;
import com.example.hao.haotestdemo.utils.TUtils;

import butterknife.ButterKnife;

/**
 * Created by Hao on 2016/12/20.
 */

public abstract class BaseFragment<F extends BasePresenter,E extends BaseModel> extends Fragment {

    public View rootView;
    public F mPresenter;
    public E mModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
        rootView = inflater.inflate(getLayoutId(),null);
        }
        ButterKnife.bind(this, rootView);
        mPresenter = TUtils.getT(this,0);
        mModel = TUtils.getT(this,1);
        if(mPresenter!=null){
            mPresenter.mContext = getContext();
        }
        initPresenter();
        initView();
        return rootView;
    }

    protected abstract void initPresenter();

    public abstract int getLayoutId();

    public abstract void initView();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
