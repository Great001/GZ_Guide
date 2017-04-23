package com.lhc.android.gz_guide.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.util.NavigationUtil;

public class UserStrageryActivity extends BaseActivity {

    private TextView mTvGoForStrategy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_stargery);
        mTvGoForStrategy = (TextView) findViewById(R.id.tv_go_for_strategy);
        mTvGoForStrategy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationUtil.navigateToAboutStrategyActivity(UserStrageryActivity.this);
            }
        });
    }

    @Override
    public int getTitleRes() {
        return R.string.user_stargery;
    }
}
