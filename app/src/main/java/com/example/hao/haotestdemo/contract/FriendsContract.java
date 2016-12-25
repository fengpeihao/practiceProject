package com.example.hao.haotestdemo.contract;

import com.example.hao.haotestdemo.acticity.BaseView;
import com.example.hao.haotestdemo.bean.BaseModel;
import com.example.hao.haotestdemo.presenter.BasePresenter;

/**
 * Created by Hao on 2016/12/23.
 */

public interface FriendsContract {
    interface View extends BaseView{

    }

    interface Model extends BaseModel{

    }

    abstract static class Presenter extends BasePresenter<View,Model>{
        public abstract void addFriends();
    }
}
