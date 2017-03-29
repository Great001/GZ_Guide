package com.lhc.android.gz_guide.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.util.ToastUtil;

public class EditUserInfoActivity extends BaseActivity {

    public static final String EDIT_ITEM = "edit_item";
    private EditText editText;
    private String editItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_info);
        editText = (EditText) findViewById(R.id.et_user_info);
        editItem = getIntent().getStringExtra(EDIT_ITEM);
    }

    @Override
    public int getTitleRes() {
        return R.string.edit_user_info;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_user_info_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_user_info:
                onSaveSuccess();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onSaveSuccess() {
        ToastUtil.show(this, "保存成功");
        finish();
    }
}
