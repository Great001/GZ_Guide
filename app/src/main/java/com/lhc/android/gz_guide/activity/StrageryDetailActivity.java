package com.lhc.android.gz_guide.activity;

import android.os.Bundle;

import com.lhc.android.gz_guide.R;

public class StrageryDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stragery_detail);
    }

    @Override
    public int getTitleRes() {
        return R.string.stragery_detail;
    }
}
