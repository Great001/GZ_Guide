package com.lhc.android.gz_guide.activity;

import android.os.Bundle;

import com.lhc.android.gz_guide.R;

public class UserReportActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_report);
    }

    @Override
    public int getTitleRes() {
        return R.string.user_report;
    }
}
