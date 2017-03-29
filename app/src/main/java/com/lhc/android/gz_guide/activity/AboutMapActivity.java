package com.lhc.android.gz_guide.activity;

import android.os.Bundle;

import com.lhc.android.gz_guide.R;

public class AboutMapActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_map);
    }

    @Override
    public int getTitleRes() {
        return R.string.map;
    }
}
