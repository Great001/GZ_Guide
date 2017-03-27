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
        SDKInitializer.initialize(getApplicationContext());
        Location.newInstance(getApplicationContext());
    }
}
