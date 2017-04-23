package com.lhc.android.gz_guide.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.util.ToastUtil;

public class UserReportActivity extends BaseActivity {

    private EditText mEtUserReport;
    private TextView mTvSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_report);

        mEtUserReport = (EditText) findViewById(R.id.et_user_report);
        mTvSubmit = (TextView) findViewById(R.id.tv_submit_user_report);

        mTvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.show(UserReportActivity.this,R.string.submit_success);
            }
        });
    }

    @Override
    public int getTitleRes() {
        return R.string.user_report;
    }
}
