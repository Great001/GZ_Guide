package com.lhc.android.gz_guide.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.util.NavigationUtil;
import com.lhc.android.gz_guide.util.ToastUtil;

public class SecureSettingActivity extends BaseActivity implements View.OnClickListener{

    private TextView tvBindEmail;
    private TextView tvBindPhone;
    private TextView tvChangePwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secure_setting);

        tvBindEmail = (TextView) findViewById(R.id.tv_bind_email);
        tvBindPhone = (TextView) findViewById(R.id.tv_bind_phone);
        tvChangePwd = (TextView) findViewById(R.id.tv_change_pwd);

        tvChangePwd.setOnClickListener(this);
        tvBindPhone.setOnClickListener(this);
        tvBindPhone.setOnClickListener(this);
    }

    @Override
    public int getTitleRes() {
        return R.string.security_setting;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_bind_email:
                NavigationUtil.navigateToEditUserInfoActivity(this,"email");
                ToastUtil.show(this,R.string.bind_success);
            case R.id.tv_bind_phone:
                NavigationUtil.navigateToEditUserInfoActivity(this,"phone");
                ToastUtil.show(this,R.string.bind_success);
            case R.id.tv_change_pwd:
                NavigationUtil.navigateToForgetPswActivity(this);
            default:
                break;

        }
    }
}
