package com.lhc.android.gz_guide.activity;

import android.os.Bundle;
import android.util.Log;
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

public class RegisterActivity extends BaseActivity {

    private EditText mEtAccount;
    private EditText mEtPassword;
    private Button mBtnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    public void initView() {
        mEtAccount = (EditText) findViewById(R.id.et_input_user_name);
        mEtPassword = (EditText) findViewById(R.id.et_input_user_password);
        mBtnRegister = (Button) findViewById(R.id.btn_register);

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRegister();
            }
        });
    }

    public void onRegister() {
        String account = mEtAccount.getText().toString().trim();
        String password = mEtPassword.getText().toString().trim();
        if (onRegisterCheck(account, password)) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("username", account);
                jsonObject.put("password", password);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            UserModel.getInstance().register(this, jsonObject, new Response.Listener() {
                @Override
                public void onResponse(Object o) {
                    ToastUtil.show(RegisterActivity.this, "注册成功");
                    RegisterActivity.this.finish();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    ToastUtil.show(RegisterActivity.this, "注册失败");
                    Log.e("login", volleyError.networkResponse.toString());
                }
            });
        }
    }


    public boolean onRegisterCheck(String account, String password) {
        int checkAccountResult = ValidChecker.checkAccount(account);

        if (account.isEmpty()) {
            ToastUtil.show(this, R.string.account_can_not_empty);
            return false;
        }
        if (password.isEmpty()) {
            ToastUtil.show(this, R.string.password_can_not_empty);
            return false;
        }
        if(!ValidChecker.checkPassword(password)){
            ToastUtil.show(this,R.string.password_format_invalid);
            return false;
        }
        if (checkAccountResult == ValidChecker.INVALID_ACCOUNT) {
            ToastUtil.show(this, R.string.account_invalid);
            return false;
        }
        return true;
    }

    public void onRegisterSucc() {
        finish();
    }


    @Override
    public int getTitleRes() {
        return R.string.register;
    }
}
