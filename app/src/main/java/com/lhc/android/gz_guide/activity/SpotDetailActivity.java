package com.lhc.android.gz_guide.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.util.DimensionUtil;
import com.lhc.android.gz_guide.util.GeoCodeUtil;
import com.lhc.android.gz_guide.util.NavigationUtil;

public class SpotDetailActivity extends BaseActivity implements ViewSwitcher.ViewFactory, GestureDetector.OnGestureListener {

    public static final String SPOT_ID = "spotId";


    private TextView tvAddr;

    private ImageSwitcher imageSwitcher;
    private GestureDetector gestureDetector;
    private MapView mMapView;
    private BaiduMap mBmap;

    private int[] images = {R.drawable.gztown1, R.drawable.gztown2, R.drawable.gztown3, R.drawable.gztown4, R.drawable.gztown5, R.drawable.gztown6, R.drawable.gztown7};
    private int currentImage = 0;
    private int switchHeight;
    private String address = "广州市海珠区阅江西路22号";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_detail);
        initView();
        switchHeight = DimensionUtil.dp2px(this,180);
        GeoCodeUtil.init(address);

    }

    @Override
    public int getTitleRes() {
        return R.string.spot_detail;
    }

    public void initView() {
        imageSwitcher = (ImageSwitcher) findViewById(R.id.is_spot_detail);
        tvAddr = (TextView) findViewById(R.id.tv_spot_location);

        imageSwitcher.setFactory(this);
        imageSwitcher.setLongClickable(true);
        gestureDetector = new GestureDetector(this,this);

        mMapView = (MapView) findViewById(R.id.mv_spot);
        setUpMapView();
//        imageSwitcher.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return gestureDetector.onTouchEvent(event);
//            }
//        });

        tvAddr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NavigationUtil.navigateToBmapActivity(SpotDetailActivity.this,GeoCodeUtil.getGeoResult(),address);
            }
        });

    }


    public void setUpMapView(){
        mBmap= mMapView.getMap();
//        mBmap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
//        mBmap.setTrafficEnabled(true);
        MapStatus status= new MapStatus.Builder().target(GeoCodeUtil.getGeoResult()).zoom(18).build();
        MapStatusUpdate update= MapStatusUpdateFactory.newMapStatus(status);
        mBmap.setMapStatus(update);
    }



    @Override
    public View makeView() {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(images[currentImage]);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return imageView;
    }


    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (velocityX > 0) {
            if (currentImage > 0) {
                currentImage--;
                imageSwitcher.setImageResource(images[currentImage]);
            }
        }else{
            if(currentImage < images.length - 1){
                currentImage ++;
                imageSwitcher.setImageResource(images[currentImage]);
            }
        }
    return false;
}

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        float Y = ev.getRawY();
        if(Y < switchHeight){
            gestureDetector.onTouchEvent(ev);
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }
}
