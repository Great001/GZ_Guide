package com.lhc.android.gz_guide.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.model.UserModel;

import org.json.JSONObject;

public class UserPreferActivity extends BaseActivity implements View.OnClickListener{

    private LinearLayout mLlPreferZome;
    private LinearLayout mLlPreferFun;
    private LinearLayout mLlPreferHotel;
    private LinearLayout mLlPreferTasty;
    private LinearLayout mLlPreferTraffic;

    private TextView mTvPreferZome;
    private TextView mTvPreferHotel;
    private TextView mTvPreferFun;
    private TextView mTvPreferTasty;
    private TextView mTvPreferTraffic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_prefer);
        initView();
    }

    @Override
    public int getTitleRes() {
        return R.string.prefer_setting;
    }


    public void initView(){
        mLlPreferFun = (LinearLayout) findViewById(R.id.ll_prefer_fun);
        mLlPreferHotel = (LinearLayout) findViewById(R.id.ll_prefer_hotel);
        mLlPreferTasty = (LinearLayout) findViewById(R.id.ll_prefer_tasty);
        mLlPreferZome = (LinearLayout) findViewById(R.id.ll_prefer_zome);
        mLlPreferTraffic = (LinearLayout) findViewById(R.id.ll_prefer_traffic);

        mTvPreferFun = (TextView) findViewById(R.id.tv_prefer_fun);
        mTvPreferHotel = (TextView) findViewById(R.id.tv_prefer_hotel);
        mTvPreferTasty = (TextView) findViewById(R.id.tv_prefer_tasty);
        mTvPreferTraffic = (TextView) findViewById(R.id.tv_prefer_traffic);
        mTvPreferZome = (TextView) findViewById(R.id.tv_prefer_zome);

        mLlPreferHotel.setOnClickListener(this);
        mLlPreferFun.setOnClickListener(this);
        mLlPreferTraffic.setOnClickListener(this);
        mLlPreferTasty.setOnClickListener(this);
        mLlPreferZome.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_prefer_zome:
                onPreferZomeClick();
                break;
            case R.id.ll_prefer_fun:
                onPreferFunClick();
                break;
            case R.id.ll_prefer_hotel:
                onPreferHotelClick();
                break;
            case R.id.ll_prefer_traffic:
                onPreferTrafficClick();
                break;
            case R.id.ll_prefer_tasty:
                onPreferTastyClick();
                break;
            default:
                break;
        }
    }

    public void onPreferZomeClick(){
        final String [] zomes = {getString(R.string.not_limit),getString(R.string.tianhe),getString(R.string.yuexiu),getString(R.string.liwan),getString(R.string.haizhu),
                getString(R.string.baiyun),getString(R.string.panyu),getString(R.string.conghua),getString(R.string.luogang)};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setSingleChoiceItems(zomes, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mTvPreferZome.setText(zomes[which]);
                //提交偏好设置到后台
                UserModel.getInstance().updateUserProperty(UserPreferActivity.this,"preferZomes",zomes[which]);
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    public void onPreferHotelClick(){
        final String [] hotelTypes = {getString(R.string.not_limit),getString(R.string.type_economic),getString(R.string.type_splendid)};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setSingleChoiceItems(hotelTypes, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mTvPreferHotel.setText(hotelTypes[which]);
                //提交偏好设置到后台
                UserModel.getInstance().updateUserProperty(UserPreferActivity.this,"preferHotel",hotelTypes[which]);
                dialog.cancel();
            }
        });
        builder.create().show();

    }

    public void onPreferFunClick(){
        final String [] funTypes = {getString(R.string.not_limit),getString(R.string.type_historical),getString(R.string.type_modern)};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setSingleChoiceItems(funTypes, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mTvPreferFun.setText(funTypes[which]);
                //提交偏好设置到后台
                UserModel.getInstance().updateUserProperty(UserPreferActivity.this,"preferFun",funTypes[which]);
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    public void onPreferTrafficClick(){
        final String [] trafficTypes = {getString(R.string.not_limit),getString(R.string.type_walk),getString(R.string.type_bike),
        getString(R.string.type_subway),getString(R.string.type_bus),getString(R.string.type_drive)};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setSingleChoiceItems(trafficTypes, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mTvPreferTraffic.setText(trafficTypes[which]);
                //提交偏好设置到后台
                UserModel.getInstance().updateUserProperty(UserPreferActivity.this,"preferTraffic",trafficTypes[which]);
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    public void onPreferTastyClick(){
        final String tastyTypes[] = {getString(R.string.not_limit),getString(R.string.type_gz),getString(R.string.type_other)};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setSingleChoiceItems(tastyTypes, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mTvPreferTasty.setText(tastyTypes[which]);
                //提交偏好设置到后台
                UserModel.getInstance().updateUserProperty(UserPreferActivity.this,"preferTasty",tastyTypes[which]);
                dialog.cancel();
            }
        });
        builder.create().show();
    }


}
