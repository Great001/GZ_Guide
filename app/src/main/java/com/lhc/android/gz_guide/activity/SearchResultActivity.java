package com.lhc.android.gz_guide.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.widget.ListView;

import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.PoiAddrInfo;
import com.baidu.mapapi.search.poi.PoiResult;
import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.adapter.AddrSearchResultAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchResultActivity extends BaseActivity {

    public static final String POI_RESULT = "poi_result";
    private ListView listView;
    private AddrSearchResultAdapter adapter;

    private PoiResult poiResult;
    private List<PoiAddrInfo> addrInfos;
    private List<PoiInfo> poiInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        listView = (ListView) findViewById(R.id.lv_addr_search_result);
        adapter = new AddrSearchResultAdapter(this);
        poiResult = getIntent().getParcelableExtra(POI_RESULT);
        if(poiResult.isHasAddrInfo()) {
            addrInfos = poiResult.getAllAddr();
            adapter.setAddrInfos(addrInfos);
        }else{
            poiInfos = poiResult.getAllPoi();
            adapter.setPoiInfos(poiInfos);
        }
        listView.setAdapter(adapter);
    }

    @Override
    public int getTitleRes() {
        return R.string.search_result;
    }
}
