package com.lhc.android.gz_guide;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.baidu.mapapi.SDKInitializer;
import com.lhc.android.gz_guide.model.Location;

import java.util.Locale;

/**
 * Created by Administrator on 2017/3/27.
 */
public class GZGuide_APP extends Application {

    public static final String SP_APP_CONFIG = "app_config";
    public static final String KEY_LANGUAGE = "language";

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化百度地图SDK,使用全局的ApplicationContext
        SDKInitializer.initialize(getApplicationContext());
        //创建Location单例类的实例，实现定位，获取定位结果
        Location.newInstance(getApplicationContext());

        initConfig();
    }

    //应用的初始化配置
    public void initConfig(){

        configLanguage();

    }

    //配置应用语言
    public void configLanguage(){
        Resources resources = getResources();
        Configuration configuration = resources.getConfiguration();
        DisplayMetrics dm = resources.getDisplayMetrics();

        SharedPreferences sp = getSharedPreferences(SP_APP_CONFIG,MODE_PRIVATE);
        String language = sp.getString(KEY_LANGUAGE,"NULL");
        //用户没有设置语言
        if("NULL".equals(language)){
            configuration.locale = Locale.getDefault();
        }else{
            switch (language){
                case "zh":
                    configuration.locale = Locale.SIMPLIFIED_CHINESE;
                    break;
                case "en":
                    configuration.locale = Locale.ENGLISH;
                    break;
                default:
                    break;
            }
        }
        resources.updateConfiguration(configuration,dm);
    }


}
