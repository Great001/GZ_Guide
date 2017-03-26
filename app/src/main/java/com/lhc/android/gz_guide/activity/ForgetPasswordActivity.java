package com.lhc.android.gz_guide.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.util.ToastUtil;
import com.lhc.android.gz_guide.util.ValidChecker;

import java.util.Timer;
import java.util.TimerTask;

public class ForgetPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEtAccount;
    private EditText mEtVerifyCode;
    private Button mBtnGetCode;
    private Button mBtnSubmitCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        initView();
    }

    public void initView(){
        mEtAccount = (EditText) findViewById(R.id.et_input_account);
        mEtVerifyCode = (EditText) findViewById(R.id.et_input_verifycode);
        mBtnGetCode = (Button) findViewById(R.id.btn_get_verifycode);
        mBtnSubmitCode = (Button) findViewById(R.id.btn_submit_verifycode);

        mBtnGetCode.setOnClickListener(this);
        mBtnSubmitCode.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btn_get_verifycode:
                onGetCodeClick();
                break;
            case R.id.btn_submit_verifycode:
                onSubmitCodeClick();
                break;
            default:
                break;
        }

    }

    public void onGetCodeClick(){
        String account = mEtAccount.getText().toString().trim();
        if(accountCheck(account)){
            //根据账号请求验证码

        }

    }

    public void onSubmitCodeClick(){
        String verifyCode = mEtVerifyCode.getText().toString().trim();
        //提交验证码
    }

    public boolean accountCheck(String account){
        int checkAccountResult = ValidChecker.checkAccount(account);
        if(account.isEmpty()){
            ToastUtil.show(this,R.string.account_can_not_empty);
            return false;
        }
        if(checkAccountResult != ValidChecker.VALID){
            ToastUtil.show(this,R.string.account_invalid);
            return false;
        }
        return true;
    }

    public void waitForCode(){
        final int remainTime = 60;
        final Timer timer = new Timer();
        mBtnGetCode.setClickable(false);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                mBtnGetCode.setText(String.format("还剩%d秒",remainTime));
                if(remainTime == 0){
                    timer.cancel();
                    mBtnGetCode.setClickable(true);
                }
            }
        },1000);
    }



}
