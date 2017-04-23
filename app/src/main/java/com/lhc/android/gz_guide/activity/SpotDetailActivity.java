package com.lhc.android.gz_guide.activity;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
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
import com.bumptech.glide.Glide;
import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.model.RecommendAd;
import com.lhc.android.gz_guide.util.DimensionUtil;
import com.lhc.android.gz_guide.util.GeoCodeUtil;
import com.lhc.android.gz_guide.util.NavigationUtil;

public class SpotDetailActivity extends BaseActivity implements ViewSwitcher.ViewFactory,
        GestureDetector.OnGestureListener,
        View.OnClickListener{

    public static final String KEY_AD_DATA = "ad_data";

    private TextView tvAddr;
    private TextView tvOpenTime;
    private TextView tvIntro;
    private TextView tvPrice;
    private TextView tvRoute;

    private TextView tvNearTasty;
    private TextView tvNearHotel;
    private TextView tvNearFun;

    private ImageSwitcher imageSwitcher;
    private GestureDetector gestureDetector;
    private MapView mMapView;
    private BaiduMap mBmap;

    private RecommendAd ad;

    private String [] pics;
    private int[] images = {R.drawable.gztown1, R.drawable.gztown2, R.drawable.gztown3, R.drawable.gztown4, R.drawable.gztown5, R.drawable.gztown6, R.drawable.gztown7};
    private int currentImage = 0;
    private int switchHeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot_detail);
        ad = (RecommendAd) getIntent().getSerializableExtra(KEY_AD_DATA);
        if(ad != null) {
            getSupportActionBar().setTitle(ad.getTitle());
            pics = ad.getPics();
        }

        initView();
        switchHeight = DimensionUtil.dp2px(this, 180);

        GeoCodeUtil.init(ad.getAddress());   //初始化地理编码

    }

    @Override
    public int getTitleRes() {
        return R.string.spot_detail;
    }

    public void initView() {
        imageSwitcher = (ImageSwitcher) findViewById(R.id.is_spot_detail);
        tvAddr = (TextView) findViewById(R.id.tv_spot_location);
        tvIntro = (TextView) findViewById(R.id.tv_spot_brief_intro);
        tvOpenTime = (TextView) findViewById(R.id.tv_spot_openTime);
        tvPrice = (TextView) findViewById(R.id.tv_spot_price);
        tvRoute = (TextView) findViewById(R.id.tv_spot_route);

        SpannableString intro = new SpannableString("简介：" + ad.getDesc());
        intro.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.black)),0,2, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        tvIntro.setText(intro);
        SpannableString openTime = new SpannableString("开放时间：" + ad.getOpenTime());
        openTime.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.black)),0,4, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        tvOpenTime.setText(openTime);
        tvAddr.setText(ad.getAddress());
        SpannableString route = new SpannableString("如何到达：" + ad.getRoute());
        route.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.black)),0,4, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        tvRoute.setText(route);
        SpannableString price = new SpannableString("门票信息：" + ad.getPrice());
        price.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.black)),0,4, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        tvPrice.setText(price);


        tvNearFun = (TextView) findViewById(R.id.tv_nearby_fun);
        tvNearHotel = (TextView) findViewById(R.id.tv_nearby_hotel);
        tvNearTasty = (TextView) findViewById(R.id.tv_nearby_tasty);

        tvNearFun.setOnClickListener(this);
        tvNearTasty.setOnClickListener(this);
        tvNearHotel.setOnClickListener(this);

        imageSwitcher.setFactory(this);
        imageSwitcher.setLongClickable(true);
        gestureDetector = new GestureDetector(this, this);

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
                NavigationUtil.navigateToBmapActivity(SpotDetailActivity.this, GeoCodeUtil.getGeoResult(), ad.getAddress());
            }
        });

    }


    public void setUpMapView() {
        mBmap = mMapView.getMap();
//        mBmap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
//        mBmap.setTrafficEnabled(true);
        MapStatus status = new MapStatus.Builder().target(GeoCodeUtil.getGeoResult()).zoom(18).build();
        MapStatusUpdate update = MapStatusUpdateFactory.newMapStatus(status);
        mBmap.setMapStatus(update);
    }


    //实现ViewSwitch.Factory
    @Override
    public View makeView() {
        ImageView imageView = new ImageView(this);
        if(pics == null) {
            imageView.setImageResource(images[currentImage]);
        }else{
            Glide.with(this).load(pics[currentImage]).placeholder(R.drawable.loading).into(imageView);
        }
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
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
        } else {
            if (currentImage < images.length - 1) {
                currentImage++;
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
        if (Y < switchHeight) {
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

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_nearby_tasty:
                NavigationUtil.navigateToAboutTastyActivity(this);
                break;
            case R.id.tv_nearby_hotel:
                NavigationUtil.navigateToAboutHotelActivity(this);
                break;
            case R.id.tv_nearby_fun:
                NavigationUtil.navigateToAboutSpotActivity(this);
                break;
            default:
                break;
        }
    }
}
