package com.lhc.android.gz_guide.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextClock;
import android.widget.TextView;

import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.util.NavigationUtil;

/**
 * Created by Administrator on 2017/3/28.
 */
public class UserFriendActivity extends BaseActivity {

    private TextView mTvGoForFriend;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_friend);
        mTvGoForFriend = (TextView) findViewById(R.id.tv_go_for_friend);
        mTvGoForFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationUtil.navigateToAboutLocalPartnerActivity(UserFriendActivity.this);
            }
        });
    }

    @Override
    public int getTitleRes() {
        return R.string.user_friend;
    }
}
