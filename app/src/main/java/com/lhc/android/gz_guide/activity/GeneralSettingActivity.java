package com.lhc.android.gz_guide.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lhc.android.gz_guide.GZGuide_APP;
import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.util.NavigationUtil;
import com.lhc.android.gz_guide.util.ToastUtil;

import java.util.Locale;

public class GeneralSettingActivity extends BaseActivity implements View.OnClickListener{

    private LinearLayout mLlLanguage;
    private TextView mTvLanSelected;

    private TextView mTvFontSize;
    private TextView mTvFlow;
    private TextView mTvCacheClear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_setting);

        initView();
    }

    @Override
    public int getTitleRes() {
        return R.string.general_setting;
    }

    public void initView(){
        mLlLanguage = (LinearLayout) findViewById(R.id.ll_language_setting);
        mTvLanSelected = (TextView) findViewById(R.id.tv_language_selected);
        mTvCacheClear = (TextView) findViewById(R.id.tv_cache_clear);
        mTvFlow = (TextView) findViewById(R.id.tv_flow_setting);
        mTvFontSize = (TextView) findViewById(R.id.tv_font_size_setting);

        SharedPreferences sp = getSharedPreferences(GZGuide_APP.SP_APP_CONFIG,MODE_PRIVATE);
        String language = sp.getString(GZGuide_APP.KEY_LANGUAGE,"NULL");
        if("zh".equals(language)){
            mTvLanSelected.setText(R.string.selected_language_zh);
        }else if("en".equals(language)){
            mTvLanSelected.setText(R.string.selected_language_en);
        }
        mLlLanguage.setOnClickListener(this);
        mTvFlow.setOnClickListener(this);
        mTvFontSize.setOnClickListener(this);
        mTvCacheClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_language_setting:
                showLanguageChoices();
                break;
            case R.id.tv_font_size_setting:
                ToastUtil.show(this,R.string.font_size_not_need_change);
                break;
            case R.id.tv_cache_clear:
                ToastUtil.show(this,R.string.cache_clear_finish);
                break;
            case R.id.tv_flow_setting:
                ToastUtil.show(this,R.string.current_flow);
                break;
            default:
                break;
        }

    }

    public void showLanguageChoices() {
        String[] languages = {getResources().getString(R.string.selected_language_zh),getResources().getString(R.string.selected_language_en)};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setSingleChoiceItems(languages, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch(which){
                    case 0:
                        changeLanguage(Locale.SIMPLIFIED_CHINESE);
                        mTvLanSelected.setText(R.string.selected_language_zh);
                        break;
                    case 1:
                        changeLanguage(Locale.ENGLISH);
                        mTvLanSelected.setText(R.string.selected_language_en);
                        break;
                    default:
                        break;
                }
                dialog.cancel();
                NavigationUtil.navigateToMainActivity(GeneralSettingActivity.this);
            }

        });
        builder.create().show();
    }


    public void changeLanguage(Locale locale){
        Resources  resources = getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        config.locale = locale;
        resources.updateConfiguration(config,dm);

        saveLanguageConfig(locale);
    }

    public void saveLanguageConfig(Locale locale){
        String language;
        if(locale.equals(Locale.SIMPLIFIED_CHINESE)){
            language = "zh";
        }else{
           language = "en";
        }
        SharedPreferences sp = getSharedPreferences(GZGuide_APP.SP_APP_CONFIG,MODE_PRIVATE);
        sp.edit().putString(GZGuide_APP.KEY_LANGUAGE,language).commit();
    }

}