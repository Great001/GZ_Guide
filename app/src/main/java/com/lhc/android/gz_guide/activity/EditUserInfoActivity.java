package com.lhc.android.gz_guide.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lhc.android.gz_guide.Interface.OnUserInfoUpdateListener;
import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.model.UserModel;
import com.lhc.android.gz_guide.util.NavigationUtil;
import com.lhc.android.gz_guide.util.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

public class EditUserInfoActivity extends BaseActivity {

    public static final String EDIT_ITEM = "edit_item";
    private EditText editText;
    private String editItemKey;
    private String editItemValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_info);
        editText = (EditText) findViewById(R.id.et_user_info);
        editItemKey = getIntent().getStringExtra(EDIT_ITEM);
    }

    @Override
    public int getTitleRes() {
        return R.string.edit_user_info;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_user_info_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_user_info:
                doSave();
//                onSaveSuccess();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //将用户的更改提交到后台
    public void doSave() {
        editItemValue = editText.getText().toString().trim();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(editItemKey, editItemValue);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        UserModel.update(this, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                ToastUtil.show(EditUserInfoActivity.this, "保存成功");
                UserModel.updateUserProperty(EditUserInfoActivity.this, editItemKey, editItemValue);
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                ToastUtil.show(EditUserInfoActivity.this,"保存失败");
            }
        });
    }

    public void onSaveSuccess() {
        ToastUtil.show(this, "保存成功");
        UserModel.updateUserProperty(this, editItemKey, editItemValue);
        finish();
    }
}
