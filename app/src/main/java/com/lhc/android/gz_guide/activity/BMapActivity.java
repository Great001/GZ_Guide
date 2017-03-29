package com.lhc.android.gz_guide.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;
import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.view.FloatingButton;

public class BMapActivity extends BaseActivity implements FloatingButton.OnButtonClickListener, View.OnClickListener {

    public static final String LOCATION = "location";
    public static final String ADDRESS = "address";

    private FloatingButton floatingButton;
    private TextView tvTraffic;
    private LinearLayout llNavigation;
    private EditText mEtStartPlace;
    private EditText mEtEndPlace;
    private Button mBtnWalk, mBtnBus, mBtnDrive;

    private MapView mapView;
    private BaiduMap baiduMap;
    private LatLng location;
    private String address;

    private boolean isTraffic = false;
    private String startPlace;
    private String endPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmap);

        location = getIntent().getParcelableExtra(LOCATION);
        address = getIntent().getStringExtra(ADDRESS);
        initView();
    }

    @Override
    public int getTitleRes() {
        return R.string.baidu_map;
    }

    public void initView() {
        floatingButton = (FloatingButton) findViewById(R.id.fbtn_open_navigation);
        llNavigation = (LinearLayout) findViewById(R.id.ll_navigation);
        mEtStartPlace = (EditText) findViewById(R.id.et_start_place);
        mEtEndPlace = (EditText) findViewById(R.id.et_end_place);
        mBtnBus = (Button) findViewById(R.id.btn_bus);
        mBtnWalk = (Button) findViewById(R.id.btn_walk);
        mBtnDrive = (Button) findViewById(R.id.btn_drive);

        tvTraffic = (TextView) findViewById(R.id.tv_traffic);
        mapView = (MapView) findViewById(R.id.mv_address_detail);
        baiduMap = mapView.getMap();
        MapStatus status = new MapStatus.Builder().target(location).zoom(18).build();
        MapStatusUpdate update = MapStatusUpdateFactory.newMapStatus(status);
        baiduMap.setMapStatus(update);

        final BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.location);
        baiduMap.addOverlay(new MarkerOptions()
                .position(location)
                .draggable(true)
                .icon(bitmap));

        baiduMap.addOverlay(new TextOptions()
                .position(location)
                .text(address)
                .fontSize(26)
                .fontColor(0xFFFF4081));

        tvTraffic.setOnClickListener(this);
        floatingButton.setOnButtonClickListener(this);
        mBtnDrive.setOnClickListener(this);
        mBtnWalk.setOnClickListener(this);
        mBtnBus.setOnClickListener(this);

    }

    @Override
    public void onButtonClick() {
        if (llNavigation.getVisibility() == View.VISIBLE) {
            llNavigation.setVisibility(View.GONE);
        } else {
            llNavigation.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_bus:
                break;
            case R.id.btn_walk:
                break;
            case R.id.btn_drive:
                break;
            case R.id.tv_traffic:
                if (isTraffic) {
                    isTraffic = false;
                    baiduMap.setTrafficEnabled(isTraffic);
                } else {
                    isTraffic = true;
                    baiduMap.setTrafficEnabled(isTraffic);
                }
                break;
            default:
                break;
        }
    }
}
