package com.lhc.android.gz_guide.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.overlayutil.WalkingRouteOverlay;
import com.lhc.android.gz_guide.util.ToastUtil;
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
    private RoutePlanSearch mSearch;
    private LatLng location;
    private String address;

    private boolean isTraffic = false;
    private String startPlace;
    private String endPlace;
    private RouteLine route;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmap);

        location = getIntent().getParcelableExtra(LOCATION);
        address = getIntent().getStringExtra(ADDRESS);
        initView();
        initRouteSearch();
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

    public void initRouteSearch(){
        mSearch = RoutePlanSearch.newInstance();
        mSearch.setOnGetRoutePlanResultListener(new OnGetRoutePlanResultListener() {
            @Override
            public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {

                if (walkingRouteResult == null || walkingRouteResult.error != SearchResult.ERRORNO.NO_ERROR) {
                    Toast.makeText(BMapActivity.this, "抱歉，未找到结果", Toast.LENGTH_SHORT)
                            .show();
                }
                if (walkingRouteResult.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
                    // 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
                    walkingRouteResult.getSuggestAddrInfo();
                    return;
                }
                if (walkingRouteResult.error == SearchResult.ERRORNO.NO_ERROR) {
                    route = walkingRouteResult.getRouteLines().get(0);
                    WalkingRouteOverlay overlay = new MyWalkingRouteOverlay(baiduMap);
                    baiduMap.setOnMarkerClickListener(overlay);
//                    routeOverlay = overlay;
                    overlay.setData(walkingRouteResult.getRouteLines().get(0));
                    overlay.addToMap();
                    overlay.zoomToSpan();
                }
            }


            @Override
            public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

            }

            @Override
            public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

            }

            @Override
            public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {

            }

            @Override
            public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

            }

            @Override
            public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

            }
        });
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
                ToastUtil.show(this,R.string.recently_not_support_this);
                break;
            case R.id.btn_walk:
                showWalkRoute();
                break;
            case R.id.btn_drive:
                ToastUtil.show(this,R.string.recently_not_support_this);
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

    //步行路线规划
    public void showWalkRoute(){
        startPlace = mEtStartPlace.getText().toString().trim();
        endPlace = mEtEndPlace.getText().toString().trim();
        PlanNode startNode = PlanNode.withCityNameAndPlaceName("广州",startPlace);
        PlanNode endNode = PlanNode.withCityNameAndPlaceName("广州",endPlace);
        mSearch.walkingSearch(new WalkingRoutePlanOption().from(startNode).to(endNode));
    }

    class MyWalkingRouteOverlay extends WalkingRouteOverlay {
        public MyWalkingRouteOverlay(BaiduMap map) {
            super(map);
        }

        @Override
        public BitmapDescriptor getStartMarker() {
//            if (useDefaultIcon) {
            return BitmapDescriptorFactory.fromResource(R.drawable.icon_st);
//            }

        }

        @Override
        public BitmapDescriptor getTerminalMarker() {
//            if (useDefaultIcon) {
            return BitmapDescriptorFactory.fromResource(R.drawable.icon_en);
//            }
//            return null;
        }
    }

}
