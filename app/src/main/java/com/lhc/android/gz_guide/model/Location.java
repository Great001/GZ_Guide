package com.lhc.android.gz_guide.model;

import android.content.Context;
import android.os.Looper;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.model.LatLng;
import com.lhc.android.gz_guide.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/27.
 */
public class Location {

    private volatile static Location instance;

    private LatLng location;
    private String address;
    private List<Poi> poiList;
    private LocationClient locationClient;
    private BDLocationListener listener;
    private Context context;

    private  Location(Context context) throws Exception{
        this.context = context;
        if(Thread.currentThread() == Looper.getMainLooper().getThread()){
            locationClient = new LocationClient(context);
            initLocation();
            listener = new MyLocationListener();
            locationClient.registerLocationListener(listener);
            locationClient.start();
        }else{
            throw new Exception("should create Location in main thread!");
        }
    }


    public static Location newInstance(Context context){
        if(instance == null){
            synchronized (Location.class) {
                try {
                    instance = new Location(context);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
        return instance;
    }

    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
//        option.setCoorType("bd0911");
        option.setScanSpan(10000);
        option.setIsNeedAddress(true);
        option.setIsNeedLocationDescribe(true);
        option.setOpenGps(true);
        option.setIsNeedLocationPoiList(true);
        option.setIgnoreKillProcess(false);
        option.SetIgnoreCacheException(false);
        option.setEnableSimulateGps(false);
        locationClient.setLocOption(option);
    }

    public LatLng getLocation(){
        return location;
    }

    public void setLocation(LatLng location){
        this.location = location;
    }

    public String getLocalAddress(){
        return address;
    }

    public void setLocalAddress(String address){
        this.address = address;
    }


    public void requestLocation(){
        locationClient.requestLocation();
    }

    public List<Poi> getPoiList(){
        if(poiList == null){
            poiList = new ArrayList<>();
        }
        return poiList;
    }

    public void setPoiList(List<Poi> list){
        poiList = list;
    }


    class MyLocationListener implements BDLocationListener{
        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            int type = bdLocation.getLocType();
            if(type == 61 || type == 161) {
                address = bdLocation.getAddrStr();
                location = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
                poiList = bdLocation.getPoiList();
            }else{
                ToastUtil.show(context,"网络存在异常，请检查网络设置");
                location = new LatLng(23.117055306224895,113.2759952545166);
                address = "广州市";
            }
        }
    }
}
