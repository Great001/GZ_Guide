package com.lhc.android.gz_guide.activity;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.util.NavigationUtil;

import java.util.Locale;

/**
 * Created by Administrator on 2017/3/28.
 */
public class UserSettingActivity extends BaseActivity implements View.OnClickListener {


    private TextView mTvGeneral;
    private TextView mTvPrivicy;
    private TextView mTvSecurity;

    private TextView mTvAboutApp;
    private TextView mTvUserReport;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);
        initView();

    }

    @Override
    public int getTitleRes() {
        return R.string.user_setting;
    }

    public void initView(){
        mTvAboutApp = (TextView) findViewById(R.id.tv_about_app);
        mTvGeneral = (TextView) findViewById(R.id.tv_general_settings);
        mTvPrivicy = (TextView) findViewById(R.id.tv_privicy_settings);
        mTvSecurity = (TextView) findViewById(R.id.tv_security_settings);
        mTvUserReport = (TextView) findViewById(R.id.tv_user_report);

        mTvUserReport.setOnClickListener(this);
        mTvAboutApp.setOnClickListener(this);
        mTvGeneral.setOnClickListener(this);
        mTvSecurity.setOnClickListener(this);
        mTvPrivicy.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_general_settings:
                NavigationUtil.navigateToGenaralSetttingActivity(this);
                break;
            case R.id.tv_privicy_settings:
                NavigationUtil.navigateToPrivicySettingActivity(this);
                break;
            case R.id.tv_security_settings:
                NavigationUtil.navigateToSecureSettingActivity(this);
                break;
            case R.id.tv_about_app:
                NavigationUtil.navigateToAboutAppActivity(this);
                break;
            case R.id.tv_user_report:
                NavigationUtil.navigateToUserReportActivity(this);
                break;
            default:
                break;
        }

    }
}
