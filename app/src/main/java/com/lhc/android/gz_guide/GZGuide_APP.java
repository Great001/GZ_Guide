package com.lhc.android.gz_guide;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.lhc.android.gz_guide.model.Location;

/**
 * Created by Administrator on 2017/3/27.
 */
public class GZGuide_APP extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化百度地图SDK,使用全局的ApplicationContext
        SDKInitializer.initialize(getApplicationContext());
        //创建Location单例类的实例，实现定位，获取定位结果
        Location.newInstance(getApplicationContext());
    }
}
