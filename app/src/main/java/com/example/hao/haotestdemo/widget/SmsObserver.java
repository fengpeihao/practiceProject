package com.example.hao.haotestdemo.widget;

import android.content.Context;

import android.database.ContentObservable;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.hao.haotestdemo.acticity.MainActivity;
import com.example.hao.haotestdemo.presenter.LoginPresenter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Hao on 2016/12/6.
 */

public class SmsObserver extends ContentObserver {

    private Context context;
    private Handler handler;

    public SmsObserver(Context context, Handler handler) {
        super(handler);
        this.context = context;
        this.handler = handler;
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        Log.e("Debug", "onChange");
        Uri uri = Uri.parse("content://sms/inbox");
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, "date desc");
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int address = cursor.getInt(cursor.getColumnIndex("address"));
                String body = cursor.getString(cursor.getColumnIndex("body"));
                Log.e("Debug", address + "  " + body);
                Pattern pattern = Pattern.compile("\\d{4}");
                Matcher matcher = pattern.matcher(body);
                if (matcher.find(0)) {
                    String code = matcher.group(0);
                    handler.obtainMessage(LoginPresenter.SMSCODE, code).sendToTarget();
                }
            }
            cursor.close();

        }
    }
}
