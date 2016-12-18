package com.example.hao.haotestdemo.acticity;

import android.net.Uri;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hao.haotestdemo.R;
import com.example.hao.haotestdemo.bean.LoginModel;
import com.example.hao.haotestdemo.contract.LoginContract;
import com.example.hao.haotestdemo.presenter.LoginPresenter;
import com.example.hao.haotestdemo.widget.SmsObserver;
import com.example.hao.haotestdemo.widget.TimeButton;

import butterknife.Bind;
import butterknife.OnClick;
import cn.smssdk.SMSSDK;

public class LoginActivity extends BaseActivity<LoginPresenter, LoginModel> implements LoginContract.View {
    @Bind(R.id.edt_phone)
    EditText edt_phone;
    @Bind(R.id.edt_code)
    EditText edt_code;
    @Bind(R.id.btn_login)
    Button btn_login;
    @Bind(R.id.qq_login)
    TextView qq_login;
    @Bind(R.id.wechat_login)
    TextView wechat_login;
    @Bind(R.id.btn_get_code)
    TimeButton btn_get_code;
    private SmsObserver smsObserver;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initViews() {
        SMSSDK.initSDK(this, "19a77fb658d00", "0695b56d4874dc7a8961cca09ba945e0");
        smsObserver = new SmsObserver(this, mPresenter.mHandler);
        Uri uri = Uri.parse("content://sms");
        getContentResolver().registerContentObserver(uri, true, smsObserver);
        SMSSDK.registerEventHandler(mPresenter.eh); //注册短信回调
    }

    @Override
    public void destroy() {
        getContentResolver().unregisterContentObserver(smsObserver);
    }

    @Override
    public String phone() {
        return edt_phone.getText().toString().trim();
    }

    @Override
    public String code() {
        return edt_code.getText().toString().trim();
    }

    @Override
    public void setCode(String code) {
        edt_code.setText(code);
    }

    @Override
    public void clearPhone() {
        edt_phone.setText("");
    }

    @Override
    public void clearCode() {
        edt_code.setText("");
    }

    @Override
    public void isStart(boolean isStart) {
        btn_get_code.isStart(isStart);
    }

    @OnClick(R.id.btn_get_code)
    public void getCode() {
        mPresenter.getCode();
    }

    @OnClick(R.id.btn_login)
    public void login() {
        mPresenter.phoneLogin();
    }

    @OnClick(R.id.qq_login)
    public void qqLogin() {
        mPresenter.qqLogin();
    }

    @OnClick(R.id.wechat_login)
    public void wechatLogin() {
        mPresenter.wechatLogin();
    }
}
