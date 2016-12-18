package com.example.hao.haotestdemo.app;

import android.app.Application;

import cn.sharesdk.framework.ShareSDK;
import cn.smssdk.SMSSDK;

/**
 * Created by Hao on 2016/12/6.
 */

public class App extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        ShareSDK.initSDK(this,"19d384362e1f2");
    }
}
