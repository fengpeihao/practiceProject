package com.example.hao.haotestdemo.app;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import cn.sharesdk.framework.ShareSDK;
import cn.smssdk.SMSSDK;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;

/**
 * Created by Hao on 2016/12/6.
 */

public class App extends Application {
    public static String userId = "";

    @Override
    public void onCreate() {
        super.onCreate();
        ShareSDK.initSDK(this, "19d384362e1f2");
        RongIM.init(this);
        initRongIm();
//        RongIM.getInstance().setOnReceiveMessageListener(new RongIMClient.OnReceiveMessageListener() {
//            @Override
//            public boolean onReceived(Message message, int i) {
//                return false;
//            }
//        });
    }

    /**
     * 获得当前进程的名字
     *
     * @param context context
     * @return 进程号
     */
    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }


    /**
     * 初始化融云即时通讯
     */
    private void initRongIm() {
        /**
         * OnCreate 会被多个进程重入，这段保护代码，确保只有您需要使用 RongIMClient 的进程和 Push 进程执行了 init。
         * io.rong.push 为融云 push 进程名称，不可修改。
         */
        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext())) ||
                "io.rong.push".equals(getCurProcessName(getApplicationContext()))) {
            RongIMClient.init(this);
        }
    }
}


