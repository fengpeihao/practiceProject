package com.example.hao.haotestdemo.presenter;

import android.content.Context;

/**
 * Created by Hao on 2016/12/9.
 */

public abstract class BasePresenter<T , E> {
    public Context mContext;
    public T mView;
    public E mModel;

    public void setVM(T v,E m){
        this.mView = v;
        this.mModel = m;
    }

}
