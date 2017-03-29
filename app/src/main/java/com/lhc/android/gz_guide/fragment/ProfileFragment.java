package com.lhc.android.gz_guide.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.util.ImageCropUtil;
import com.lhc.android.gz_guide.util.NavigationUtil;
import com.lhc.android.gz_guide.util.ToastUtil;
import com.lhc.android.gz_guide.view.CircleImageView;

import java.io.File;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    private TextView tvMyFriend;
    private TextView tvMyDiscover;
    private TextView tvMyStargery;
    private TextView tvMySetting;
    private Button btnLogOut;

    private RelativeLayout rlUserInfo;
    private TextView tvUserName;
    private TextView tvUserJob;
    private TextView tvUserRange;
    private TextView tvUserSignature;
    private TextView tvLogin;
    private CircleImageView ivUserAvatar;
    private ImageView ivUserSex;
    private ImageView ivUserBackground;

    private File avatar;
    private File background;

    private int avatarSourceType;
    private static final int AVATAR_FROM_RESOURCE = 1;
    private static final int AVATAR_FROM_FILE = 2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        initView(view);
//        loadUserInfo();
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        loadUserInfo();//加载用户信息,放在此处的目的是及时更新
    }

    public void initView(View view) {
        tvMyDiscover = (TextView) view.findViewById(R.id.tv_user_discover);
        tvMyFriend = (TextView) view.findViewById(R.id.tv_user_friends);
        tvMySetting = (TextView) view.findViewById(R.id.tv_user_setting);
        tvMyStargery = (TextView) view.findViewById(R.id.tv_user_stragery);
        btnLogOut = (Button) view.findViewById(R.id.btn_user_log_out);

        tvUserJob = (TextView) view.findViewById(R.id.tv_user_job);
        tvUserName = (TextView) view.findViewById(R.id.tv_user_name);
        tvUserRange = (TextView) view.findViewById(R.id.tv_user_range);
        tvUserSignature = (TextView) view.findViewById(R.id.tv_user_signature);
        tvLogin = (TextView) view.findViewById(R.id.tv_login_register);
        ivUserAvatar = (CircleImageView) view.findViewById(R.id.iv_user_avatar);
        ivUserSex = (ImageView) view.findViewById(R.id.iv_user_sex);
        ivUserBackground = (ImageView) view.findViewById(R.id.iv_user_background);
        rlUserInfo = (RelativeLayout) view.findViewById(R.id.rl_personal_info);

        tvMyStargery.setOnClickListener(this);
        tvMyDiscover.setOnClickListener(this);
        tvMySetting.setOnClickListener(this);
        tvMyFriend.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
        btnLogOut.setOnClickListener(this);

        ivUserAvatar.setOnClickListener(this);
        rlUserInfo.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_user_friends:
                NavigationUtil.navigateToUserFriendActivity(getContext());
                break;
            case R.id.tv_user_stragery:
                NavigationUtil.navigateToUserStrageryActivity(getContext());
                break;
            case R.id.tv_user_discover:
                NavigationUtil.navigateToUserDiscoverActivity(getContext());
                break;
            case R.id.tv_user_setting:
                NavigationUtil.navigateToUserSettingActivity(getContext());
                break;
            case R.id.tv_login_register:
                NavigationUtil.navigateToLoginActivity(getContext());
                break;
            case R.id.btn_user_log_out:
                onLogOutClick();
                break;
            case R.id.iv_user_avatar:
                onAvatarClick();
                break;
            case R.id.rl_personal_info:
                NavigationUtil.navagateToUserInfoActivity(getContext());
                break;
            default:
                break;
        }
    }

    public void loadUserInfo() {
        avatar = ImageCropUtil.getAvatarFile();
        background = ImageCropUtil.getBackgroundFile();
        onRefreshAvatar();
        onRefresBackground();
    }

    public void onLogOutClick() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("退出当前账号？");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ToastUtil.show(getContext(), "已退出当前账号");
                onUserLogout();
                dialog.cancel();
            }
        });
        builder.create().show();

    }


    public void onUserLogout() {
        tvUserName.setText("未登录");
        tvUserRange.setVisibility(View.GONE);
        tvUserJob.setVisibility(View.GONE);
        tvUserSignature.setVisibility(View.GONE);
        ivUserAvatar.setImageResource(R.drawable.avatar_default);
        ivUserAvatar.setClickable(false);
        tvLogin.setVisibility(View.VISIBLE);
        ivUserSex.setVisibility(View.GONE);
    }

    public void onUserLogin() {
        tvUserName.setText("张三");
        tvUserRange.setVisibility(View.VISIBLE);
        tvUserJob.setVisibility(View.VISIBLE);
        tvUserSignature.setVisibility(View.VISIBLE);
        ivUserAvatar.setImageResource(R.drawable.header_boy);
        ivUserAvatar.setClickable(true);
        tvLogin.setVisibility(View.GONE);
        ivUserSex.setVisibility(View.VISIBLE);
    }

    public void onAvatarClick() {
        if(avatarSourceType ==  AVATAR_FROM_FILE) {
            ImageDialogFragment.newInstance(avatar).show(getActivity().getSupportFragmentManager(), "user_profile");
        }else{
            ImageDialogFragment.newInstance(R.drawable.header_big_default).show(getActivity().getSupportFragmentManager(), "user_profile");
        }
    }

    public void onRefreshAvatar() {
        final Bitmap bitmap = avatar != null ? ImageCropUtil.decodeUriAsBitmap(getContext(), Uri.fromFile(avatar)) : null;
        if (bitmap != null) {
            ivUserAvatar.setImageBitmap(bitmap);
            avatarSourceType = AVATAR_FROM_FILE;
        } else {
            ivUserAvatar.setImageResource(R.drawable.header_big_default);
            avatarSourceType = AVATAR_FROM_RESOURCE;
        }
    }


    public void onRefresBackground() {
        final Bitmap bitmap = background != null ? ImageCropUtil.decodeUriAsBitmap(getContext(), Uri.fromFile(background)) : null;
        if (bitmap != null) {
            ivUserBackground.setImageBitmap(bitmap);
        } else {
            ivUserBackground.setImageResource(R.drawable.home_bg);
        }
    }
}


