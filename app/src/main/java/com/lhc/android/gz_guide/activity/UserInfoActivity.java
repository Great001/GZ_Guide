package com.lhc.android.gz_guide.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.model.UserModel;
import com.lhc.android.gz_guide.util.ImageCropUtil;
import com.lhc.android.gz_guide.util.NavigationUtil;
import com.lhc.android.gz_guide.view.CircleImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

/**
 * Created by Administrator on 2017/3/28.
 */
public class UserInfoActivity extends BaseActivity implements View.OnClickListener {

    private CircleImageView ivAvatar;
    private ImageView ivBg;
    private TextView tvName;
    private TextView tvSex;
    private TextView tvAge;
    private TextView tvJob;
    private TextView tvTel;
    private TextView tvWechat;
    private TextView tvEmail;
    private TextView tvAddr;

    private TextView tvRange;
    private TextView tvIntergral;

    private File avatar;
    private File background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        initView();
//        loadUserInfo();
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadUserInfo();
    }

    @Override
    public int getTitleRes() {
        return R.string.user_personal_info;
    }

    public void initView() {
        ivAvatar = (CircleImageView) findViewById(R.id.iv_user_info_avatar);
        ivBg = (ImageView) findViewById(R.id.iv_user_info_background);

        tvAddr = (TextView) findViewById(R.id.tv_user_info_addr);
        tvName = (TextView) findViewById(R.id.tv_user_info_name);
        tvAge = (TextView) findViewById(R.id.tv_user_info_age);
        tvSex = (TextView) findViewById(R.id.tv_user_info_sex);
        tvJob = (TextView) findViewById(R.id.tv_user_info_job);

        tvTel = (TextView) findViewById(R.id.tv_user_info_tel);
        tvWechat = (TextView) findViewById(R.id.tv_user_info_wechat);
        tvEmail = (TextView) findViewById(R.id.tv_user_info_email);
        tvRange = (TextView) findViewById(R.id.tv_user_info_range);
        tvIntergral = (TextView) findViewById(R.id.tv_user_info_intergral);

        ivAvatar.setOnClickListener(this);
        ivBg.setOnClickListener(this);
        tvName.setOnClickListener(this);
        tvJob.setOnClickListener(this);
        tvAddr.setOnClickListener(this);
        tvAge.setOnClickListener(this);
        tvSex.setOnClickListener(this);
        tvTel.setOnClickListener(this);
        tvWechat.setOnClickListener(this);
        tvEmail.setOnClickListener(this);
    }


    public void loadUserInfo() {
        avatar = ImageCropUtil.getAvatarFile();
        background = ImageCropUtil.getBackgroundFile();
        onRefreshAvatar();  //更新用户头像
        onRefresBackground();   //更新用户背景强

        //加载用户的个人资料信息
        JSONObject jsonObject = UserModel.getLocalUserProfile(this);

        try {
            tvName.setText(jsonObject.getString("username"));
            tvSex.setText(jsonObject.getString("sex"));
            tvAge.setText(jsonObject.getString("age"));
            tvTel.setText(jsonObject.getString("mobilePhoneNumber"));
            tvWechat.setText(jsonObject.getString("wechat"));
            tvAddr.setText(jsonObject.getString("address"));
            tvRange.setText(jsonObject.getString("range"));
            tvIntergral.setText(jsonObject.getString("intergral"));
            tvJob.setText(jsonObject.getString("job"));

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_user_info_avatar:
                ImageCropUtil.selectImage(this, Uri.fromFile(avatar), ImageCropUtil.SELECT_A_PICTURE_FOR_AVATAR);
                break;
            case R.id.iv_user_info_background:
                ImageCropUtil.selectImage(this, Uri.fromFile(background), ImageCropUtil.SELECT_A_PICTURE_FOR_BACKGROUND);
                break;
            case R.id.tv_user_info_name:
                NavigationUtil.navigateToEditUserInfoActivity(this, "username");
                break;
            case R.id.tv_user_info_sex:
                onSexClick();
                break;
            case R.id.tv_user_info_addr:
                NavigationUtil.navigateToEditUserInfoActivity(this, "address");
                break;
            case R.id.tv_user_info_age:
                NavigationUtil.navigateToEditUserInfoActivity(this, "age");
                break;
            case R.id.tv_user_info_tel:
                NavigationUtil.navigateToEditUserInfoActivity(this, "mobilePhoneNumber");
                break;
            case R.id.tv_user_info_email:
                NavigationUtil.navigateToEditUserInfoActivity(this,"email");
                break;
            case R.id.tv_user_info_job:
                NavigationUtil.navigateToEditUserInfoActivity(this, "job");
                break;
            case R.id.tv_user_info_wechat:
                NavigationUtil.navigateToEditUserInfoActivity(this, "wechat");
                break;
            default:
                break;
        }
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case ImageCropUtil.SELECT_A_PICTURE_FOR_AVATAR:
                    ImageCropUtil.cropImageUri(this, ImageCropUtil.getUri(this, data), 90, 90, ImageCropUtil.REFRESH_AVATAR);
                    break;
                case ImageCropUtil.REFRESH_AVATAR:
                    onRefreshAvatar();
                    break;
                case ImageCropUtil.SELECT_A_PICTURE_FOR_BACKGROUND:
                    ImageCropUtil.cropImageUri(this, ImageCropUtil.getUri(this, data), 350, 180, ImageCropUtil.REFRESH_BACKGROUND);
                    break;
                case ImageCropUtil.REFRESH_BACKGROUND:
                    onRefresBackground();
                    break;
                default:
                    break;
            }
        }
    }


    public void onRefreshAvatar() {
        final Bitmap bitmap = avatar != null ? ImageCropUtil.decodeUriAsBitmap(this, Uri.fromFile(avatar)) : null;
        if (bitmap != null) {
            ivAvatar.setImageBitmap(bitmap);
        } else {
            ivAvatar.setImageResource(R.drawable.header_big_default);
        }
    }


    public void onRefresBackground() {
        final Bitmap bitmap = background != null ? ImageCropUtil.decodeUriAsBitmap(this, Uri.fromFile(background)) : null;
        if (bitmap != null) {
            ivBg.setImageBitmap(bitmap);
        } else {
            ivBg.setImageResource(R.drawable.home_bg);
        }
    }


    public void onSexClick() {
        final String[] items = {"男", "女", "中"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, final int which) {
                JSONObject jsonObject = new JSONObject();
                try{
                    jsonObject.put("sex",items[which]);
                }catch (JSONException e){
                    e.printStackTrace();
                }
                UserModel.update(UserInfoActivity.this, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        tvSex.setText(items[which]);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });
                dialog.cancel();
            }
        });

        builder.create().show();
    }
}
