package com.lhc.android.gz_guide.activity;

import android.os.Bundle;

import com.lhc.android.gz_guide.R;

public class PrivicySettingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privicy_setting);
    }

    @Override
    public int getTitleRes() {
        return R.string.privicy_setting;
    }
}
