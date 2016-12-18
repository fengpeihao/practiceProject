package com.example.hao.haotestdemo.presenter;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.hao.haotestdemo.acticity.MainActivity;
import com.example.hao.haotestdemo.contract.LoginContract;
import com.example.hao.haotestdemo.utils.PhoneUtils;
import com.mob.tools.utils.UIHandler;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by Hao on 2016/12/12.
 */

public class LoginPresenter extends LoginContract.Presenter {
    private Platform platform;
    public EventHandler eh;
    public static final int SMSCODE = -1;
    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Toast.makeText(mContext, "授权成功", Toast.LENGTH_SHORT).show();
                    authorize(platform);
                    break;
                case 2:
                    Toast.makeText(mContext, "onError", Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    Toast.makeText(mContext, "onCancel", Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                    Toast.makeText(mContext, "验证提交成功", Toast.LENGTH_SHORT).show();
                    break;
                case 5:
                    Toast.makeText(mContext, "验证码已发送", Toast.LENGTH_SHORT).show();
                    break;
                case 6:
                    Toast.makeText(mContext, "输入错误验证码", Toast.LENGTH_SHORT).show();
                    break;
                case SMSCODE:
                    mView.setCode((String) msg.obj);
                    break;
            }
        }
    };

    {
        eh = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                Log.e("Debug", "afterEvent");
                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功
                        mHandler.sendEmptyMessage(4);
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        //获取验证码成功
                        mHandler.sendEmptyMessage(5);
                    } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                        //返回支持发送验证码的国家列表
                    } else {
                        mHandler.sendEmptyMessage(6);
                    }
                } else {
                    ((Throwable) data).printStackTrace();
                }
            }
        };
    }

    @Override
    public void getCode() {
        Log.e("Debug", mView.phone());
        if (!TextUtils.isEmpty(mView.phone())) {
            if (PhoneUtils.isPhone(mView.phone())) {
                mView.isStart(true);
                SMSSDK.getVerificationCode("86", mView.phone());
            } else {
                Toast.makeText(mContext, "请输入正确格式的手机号", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(mContext, "手机号不能为空", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void phoneLogin() {
        SMSSDK.submitVerificationCode("86", mView.phone(), mView.code());
    }

    @Override
    public void qqLogin() {
        platform = ShareSDK.getPlatform(QQ.NAME);
        platform.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                mHandler.sendEmptyMessage(1);
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                mHandler.sendEmptyMessage(2);
                Toast.makeText(mContext, "onError", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel(Platform platform, int i) {
                mHandler.sendEmptyMessage(3);

            }
        });
        //authorize与showUser单独调用一个即可
        platform.authorize();//单独授权,OnComplete返回的hashmap是空的
//        qq.showUser(null);//授权并获取用户信息
        //移除授权
        //qq.removeAccount(true);
    }

    @Override
    public void wechatLogin() {
        platform = ShareSDK.getPlatform(Wechat.NAME);
        platform.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                mHandler.sendEmptyMessage(1);
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                mHandler.sendEmptyMessage(2);
            }

            @Override
            public void onCancel(Platform platform, int i) {
                mHandler.sendEmptyMessage(3);
            }
        });
    }

    private void authorize(final Platform plat) {
        if (plat == null) {
//            popupOthers();
            Toast.makeText(mContext, "plat is null", Toast.LENGTH_SHORT).show();
            return;
        }
//判断指定平台是否已经完成授权
        if (plat.isAuthValid()) {
            String userId = plat.getDb().getUserId();
            if (userId != null) {
                UIHandler.sendEmptyMessage(1, new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message msg) {
                        plat.getDb().getUserName();
                        return false;
                    }
                });
//                login(plat.getName(), userId, null);
                Toast.makeText(mContext, "login", Toast.LENGTH_SHORT).show();
                return;
            }
        }
//        plat.setPlatformActionListener(LoginActivity.this);
        // true不使用SSO授权，false使用SSO授权
        plat.SSOSetting(true);
        //获取用户资料
        plat.showUser(null);
    }
}
