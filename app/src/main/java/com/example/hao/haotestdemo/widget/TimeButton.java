package com.example.hao.haotestdemo.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Hao on 2016/12/13.
 */

public class TimeButton extends Button implements View.OnClickListener {

    private long second = 60;
    private String textBefore = "点击获取验证码";
    private String textAfter = "秒后重新获取";
    private long time;
    private boolean isStart;
    private OnClickListener mOnClickListener;

    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    if (time > 0) {
                        time--;
                        setText(time + textAfter);
                    } else {
                        setEnabled(true);
                        setText(textBefore);
                        time = second;
                        clearTimer();
                    }
                    break;
            }
        }
    };
    private Timer mTimer;
    private TimerTask mTimerTask;

    public TimeButton(Context context) {
        super(context);
        setOnClickListener(this);
    }

    public TimeButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(this);
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        if (l instanceof TimeButton) {
            super.setOnClickListener(l);
        } else {
            mOnClickListener = l;
        }
    }

    private void initTimer() {
        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(1);
            }
        };
        mTimer = new Timer();
        time = second;
        if (time > 0) {
            mTimer.schedule(mTimerTask, 0, 1000);
            setEnabled(false);
        }
    }

    private void clearTimer() {
        if (mTimerTask != null) {
            mTimerTask.cancel();
            mTimerTask = null;
        }
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    @Override
    public void onClick(View v) {
        if (mOnClickListener != null) {
            mOnClickListener.onClick(v);
        }
        if (isStart) {
            initTimer();
        }
    }

    /**
     * 是否开始倒计时
     *
     * @param isStart
     */
    public void isStart(boolean isStart) {
        this.isStart = isStart;
    }

    /**
     * 设置点击前的文本
     *
     * @param text
     */
    public void setTextBefore(String text) {
        textBefore = text;
        setText(textBefore);
    }

    /**
     * 设置点击后的文本
     *
     * @param text
     */
    public void setTextAfter(String text) {
        textAfter = text;
        setText(textAfter);
    }

    /**
     * 设置倒计时时间，默认60秒
     *
     * @param second
     */
    public void setTime(long second) {
        this.second = second;
    }
}
