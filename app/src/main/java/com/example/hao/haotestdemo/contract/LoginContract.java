package com.example.hao.haotestdemo.contract;

import android.app.Activity;

import com.example.hao.haotestdemo.acticity.BaseView;
import com.example.hao.haotestdemo.bean.BaseModel;
import com.example.hao.haotestdemo.presenter.BasePresenter;

/**
 * Created by Hao on 2016/12/12.
 */

public interface LoginContract {
    interface View extends BaseView {
        String phone();

        String code();

        void setCode(String code);

        Activity getActivity();

        void clearPhone();

        void clearCode();

        void isStart(boolean isStart);
    }

    interface Model extends BaseModel {

    }

    abstract static class Presenter extends BasePresenter<View, Model> {
        public abstract void phoneLogin();

        public abstract void qqLogin();

        public abstract void wechatLogin();

        public abstract void getCode();

    }
}
