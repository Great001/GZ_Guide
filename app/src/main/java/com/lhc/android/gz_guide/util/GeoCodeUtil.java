package com.lhc.android.gz_guide.util;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;

/**
 * Created by Administrator on 2017/3/28.
 */
public class GeoCodeUtil {

    private static volatile GeoCoder geoCoder;

    private static LatLng location;

    public static void init(String address) {
        if (geoCoder == null) {
            synchronized (GeoCodeUtil.class) {
                geoCoder = GeoCoder.newInstance();
                geoCoder.setOnGetGeoCodeResultListener(myListener);
            }
        }
        geoCoder.geocode(new GeoCodeOption()
                .city("广州")
                .address(address));
    }

    public static LatLng getGeoResult() {
        return location;
    }


    private static OnGetGeoCoderResultListener myListener = new OnGetGeoCoderResultListener() {
        @Override
        public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
            if (geoCodeResult == null || geoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
                //没有检索到结果
            }
            location = geoCodeResult.getLocation();

        }

        @Override
        public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {

        }
    };
}
