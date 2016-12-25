package com.example.hao.haotestdemo.widget;

import android.Manifest;
import android.app.Activity;
import android.content.Context;

import android.content.pm.PackageManager;
import android.database.ContentObservable;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.hao.haotestdemo.acticity.MainActivity;
import com.example.hao.haotestdemo.presenter.LoginPresenter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Hao on 2016/12/6.
 */

public class SmsObserver extends ContentObserver {

    private Activity context;
    private Handler handler;
    private static final int READSMS = -1;
    private static final int MY_PERMISSIONS_REQUEST_READ_SMS = 1;

    public SmsObserver(Activity context, Handler handler) {
        super(handler);
        this.context = context;
        this.handler = handler;
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.READ_SMS}, MY_PERMISSIONS_REQUEST_READ_SMS);
        } else {
            handler.sendEmptyMessage(READSMS);

        }
    }
}
