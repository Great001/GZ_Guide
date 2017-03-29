package com.lhc.android.gz_guide.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.baidu.location.Poi;
import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.adapter.LocationAdapter;
import com.lhc.android.gz_guide.model.Location;

import java.util.List;

public class LocateResultActivity extends BaseActivity {

    private ListView listView;
    private List<Poi> poiList;
    private LocationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locate_result);

        listView = (ListView) findViewById(R.id.lv_location);
        poiList = Location.newInstance(getApplicationContext()).getPoiList();
        adapter = new LocationAdapter(this, poiList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Location.newInstance(getApplicationContext()).setLocalAddress(poiList.get(position).getName());
                finish();
            }
        });
    }

    @Override
    public int getTitleRes() {
        return R.string.locate_result;
    }
}
