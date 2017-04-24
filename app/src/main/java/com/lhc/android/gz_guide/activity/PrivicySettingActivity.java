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
    private int selectItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privicy_setting);

        tvRightSetting = (TextView) findViewById(R.id.tv_privicy_right_setting);
        if(UserModel.getInstance().getIsPublic(this)){
            selectItem = 0;
        }else{
            selectItem = 1;
        }

        tvRightSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String [] choice = {getString(R.string.yes),getString(R.string.no)};
                AlertDialog.Builder builder = new AlertDialog.Builder(PrivicySettingActivity.this);
                builder.setSingleChoiceItems(choice, selectItem, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       switch (which){
                           case 0:
                               UserModel.getInstance().updateIsPublic(PrivicySettingActivity.this,true);
                               break;
                           case 1:
                               UserModel.getInstance().updateIsPublic(PrivicySettingActivity.this,false);
                               break;
                           default:
                               break;
                       }

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
