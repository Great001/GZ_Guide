package com.lhc.android.gz_guide.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.model.UserModel;
import com.lhc.android.gz_guide.util.ToastUtil;
import com.lhc.android.gz_guide.util.ValidChecker;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;

public class ForgetPasswordActivity extends BaseActivity implements View.OnClickListener {

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

    @Override
    public int getTitleRes() {
        return R.string.forget_password;
    }

    public void initView() {
        mEtAccount = (EditText) findViewById(R.id.et_input_email);
        mEtVerifyCode = (EditText) findViewById(R.id.et_input_verifycode);
        mBtnGetCode = (Button) findViewById(R.id.btn_get_verifycode);
        mBtnSubmitCode = (Button) findViewById(R.id.btn_submit_verifycode);

        mBtnGetCode.setOnClickListener(this);
        mBtnSubmitCode.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
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

    public void onGetCodeClick() {
        String email = mEtAccount.getText().toString().trim();
//        if (accountCheck(account)) {
//        }
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("email",email);
        }catch (JSONException e){
            e.printStackTrace();
        }
        UserModel.getInstance().resetPassword(ForgetPasswordActivity.this, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                ToastUtil.show(ForgetPasswordActivity.this,"请及时接受邮件进行密码重置");
                ForgetPasswordActivity.this.finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });


    }

    public void onSubmitCodeClick() {
        String verifyCode = mEtVerifyCode.getText().toString().trim();
        //提交验证码
    }

    public boolean accountCheck(String account) {
        int checkAccountResult = ValidChecker.checkAccount(account);
        if (account.isEmpty()) {
            ToastUtil.show(this, R.string.account_can_not_empty);
            return false;
        }
        if (checkAccountResult == ValidChecker.INVALID_ACCOUNT) {
            ToastUtil.show(this, R.string.account_invalid);
            return false;
        }
        return true;
    }

    public void waitForCode() {
        final int remainTime = 60;
        final Timer timer = new Timer();
        mBtnGetCode.setClickable(false);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                mBtnGetCode.setText(String.format("还剩%d秒", remainTime));
                if (remainTime == 0) {
                    timer.cancel();
                    mBtnGetCode.setClickable(true);
                }
            }
        }, 1000);
    }


}
