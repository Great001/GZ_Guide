package com.lhc.android.gz_guide.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.model.UserModel;
import com.lhc.android.gz_guide.util.ToastUtil;

public class PrivicySettingActivity extends BaseActivity {

    private TextView tvRightSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privicy_setting);
        tvRightSetting = (TextView) findViewById(R.id.tv_privicy_right_setting);
        tvRightSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String [] choice = {getString(R.string.yes),getString(R.string.no)};

                AlertDialog.Builder builder = new AlertDialog.Builder(PrivicySettingActivity.this);
                builder.setSingleChoiceItems(choice, 0, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       ToastUtil.show(PrivicySettingActivity.this,R.string.right_setting_success);
                       //权限状态更新
                       UserModel.getInstance().updateUserProperty(PrivicySettingActivity.this,"isPublic",which+"");
                       dialog.cancel();
                   }
               });
                builder.create().show();
            }
        });
    }

    @Override
    public int getTitleRes() {
        return R.string.privicy_setting;
    }
}
