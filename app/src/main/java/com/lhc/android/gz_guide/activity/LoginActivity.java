package com.lhc.android.gz_guide.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.util.NavigationUtil;
import com.lhc.android.gz_guide.util.ToastUtil;
import com.lhc.android.gz_guide.util.ValidChecker;


public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText mEtAccount;
    private EditText mEtPassword;
    private Button mBtnLogin;
    private TextView mTvForgetPsw, mTvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    @Override
    public int getTitleRes() {
        return R.string.login;
    }

    public void initView() {
        mEtAccount = (EditText) findViewById(R.id.et_input_account);
        mEtPassword = (EditText) findViewById(R.id.et_input_password);
        mBtnLogin = (Button) findViewById(R.id.btn_submit_login);
        mTvForgetPsw = (TextView) findViewById(R.id.tv_register_now);
        mTvRegister = (TextView) findViewById(R.id.tv_forget_password);

        mBtnLogin.setOnClickListener(this);
        mTvForgetPsw.setOnClickListener(this);
        mTvRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_submit_login:
                onLoginClick();
                break;
            case R.id.tv_register_now:
                onRegisterClick();
                break;
            case R.id.tv_forget_password:
                onForgetPswClick();
                break;
            default:
                break;
        }
    }

    public void onLoginClick() {
        String account = mEtAccount.getText().toString().trim();
        String password = mEtPassword.getText().toString().trim();
        if (loginCheck(account, password)) {
            //登录
        }
    }

    public void onForgetPswClick() {
        NavigationUtil.navigateToForgetPswActivity(this);
    }

    public void onRegisterClick() {
        NavigationUtil.navigateToRegisterActivity(this);
    }


    public boolean loginCheck(String account, String password) {
        int checkAccountResult = ValidChecker.checkAccount(account);
        if (account.isEmpty()) {
            ToastUtil.show(this, R.string.account_can_not_empty);
            return false;
        }
        if (password.isEmpty()) {
            ToastUtil.show(this, R.string.password_can_not_empty);
            return false;
        }
        if (checkAccountResult != ValidChecker.VALID) {
            ToastUtil.show(this, R.string.account_invalid);
            return false;
        }
        if (!ValidChecker.checkPassword(password)) {
            ToastUtil.show(this, R.string.password_invalid);
            return false;
        }
        return true;
    }


}
