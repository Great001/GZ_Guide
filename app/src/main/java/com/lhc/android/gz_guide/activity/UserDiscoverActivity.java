package com.lhc.android.gz_guide.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.util.ToastUtil;

/**
 * Created by Administrator on 2017/3/28.
 */
public class UserDiscoverActivity extends BaseActivity {

    private TextView mTvGoForDiscover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_discover);
        mTvGoForDiscover = (TextView) findViewById(R.id.tv_go_for_discover);
        mTvGoForDiscover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.show(UserDiscoverActivity.this,"生活中留点心，处处都是美的发现");
            }
        });
    }

    @Override
    public int getTitleRes() {
        return R.string.user_discover;
    }
}
