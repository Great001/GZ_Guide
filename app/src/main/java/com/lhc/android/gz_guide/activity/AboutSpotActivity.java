package com.lhc.android.gz_guide.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lhc.android.gz_guide.Interface.OnGetFunsListener;
import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.adapter.SpotsAdapter;
import com.lhc.android.gz_guide.model.RecommendModel;
import com.lhc.android.gz_guide.model.Spot;
import com.lhc.android.gz_guide.util.NavigationUtil;

import java.util.ArrayList;
import java.util.List;

public class AboutSpotActivity extends BaseActivity implements OnGetFunsListener {

    private ListView listView;
    private List<Spot> spotList = new ArrayList<>();
    private SpotsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_spot);
        listView = (ListView) findViewById(R.id.lv_spots);
        RecommendModel.getInstance().setOnGetFunsListener(this);
        adapter = new SpotsAdapter(this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NavigationUtil.navigateToSpotDetailActivity(AboutSpotActivity.this, position);
            }
        });

        RecommendModel.getInstance().getRecommendSpots(this);
    }

    @Override
    public int getTitleRes() {
        return R.string.spot_detail;
    }


    @Override
    public void onGetFuns(List<Spot> pots) {
        spotList = pots;
        adapter.setData(spotList);
        adapter.notifyDataSetChanged();

    }
}
