package com.lhc.android.gz_guide.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.lhc.android.gz_guide.R;

public class AboutAppActivity extends BaseActivity {

    private TextView tvAboutApp;
    private TextView tvAppVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);
        tvAboutApp = (TextView) findViewById(R.id.tv_about_app);
        tvAppVersion  = (TextView) findViewById(R.id.tv_app_version);

        tvAboutApp.setText("一款打造广州旅行的超实用好用的app");
        tvAppVersion.setText("当前是最新版 1.0.0");
    }

    @Override
    public int getTitleRes() {
        return R.string.about_app;
    }
}
