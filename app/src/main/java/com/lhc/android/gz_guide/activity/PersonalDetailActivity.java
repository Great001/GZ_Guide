package com.lhc.android.gz_guide.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;

import com.lhc.android.gz_guide.R;

public class PersonalDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_detail);

    }

    @Override
    public int getTitleRes() {
        return R.string.personal_detail;
    }
}
