package com.lhc.android.gz_guide.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.util.ToastUtil;
import com.lhc.android.gz_guide.util.ValidChecker;

public class RegisterActivity extends AppCompatActivity {

    private EditText mEtAccount;
    private EditText mEtPassword;
    private Button mBtnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    public void initView(){
        mEtAccount = (EditText) findViewById(R.id.et_input_account);
        mEtPassword = (EditText) findViewById(R.id.et_input_password);
        mBtnRegister = (Button) findViewById(R.id.btn_register);

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRegister();
            }
        });
    }

    public void onRegister(){
        String account= mEtAccount.getText().toString().trim();
        String password = mEtPassword.getText().toString().trim();
        if(onRegisterCheck(account,password)){
            //注册
        }


    }

    public boolean onRegisterCheck(String account,String password){
        int checkAccountResult = ValidChecker.checkAccount(account);

        if(account.isEmpty()){
            ToastUtil.show(this,R.string.account_can_not_empty);
            return false;
        }
        if(password.isEmpty()){
            ToastUtil.show(this,R.string.password_can_not_empty);
            return false;
        }
        if(checkAccountResult != ValidChecker.VALID){
            ToastUtil.show(this,R.string.account_invalid);
            return false;
        }
        return true;
    }

    public void onRegisterSucc(){
        finish();
    }




}
