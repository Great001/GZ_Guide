package com.lhc.android.gz_guide.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.util.NavigationUtil;

public class SplashActivity extends Activity {

    private Button btnEnter;
    private ImageView ivPic;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        initView();
        loadConfig();
    }

    public void initView() {
        ivPic = (ImageView) findViewById(R.id.iv_splash);
        progressBar = (ProgressBar) findViewById(R.id.pb_splash);
        btnEnter = (Button) findViewById(R.id.btn_enter_main_page);

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationUtil.navigateToMainActivity(SplashActivity.this);
                SplashActivity.this.finish();
            }
        });

    }


    //加载app的一些配置
    public void loadConfig() {

    }

}
