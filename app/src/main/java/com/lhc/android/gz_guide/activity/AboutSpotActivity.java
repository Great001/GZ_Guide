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
import com.lhc.android.gz_guide.view.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class AboutSpotActivity extends BaseActivity implements OnGetFunsListener,PullToRefreshLayout.OnRefreshListener {

    private PullToRefreshLayout prflSpots;
    private ListView listView;
    private List<Spot> spotList = new ArrayList<>();
    private SpotsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_spot);
        prflSpots = (PullToRefreshLayout) findViewById(R.id.prfl_spots);
        listView = (ListView) findViewById(R.id.lv_spots);
        RecommendModel.getInstance().setOnGetFunsListener(this);
        adapter = new SpotsAdapter(this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String contentLink = spotList.get(position).getContentLink();
                String title = spotList.get(position).getName();
                NavigationUtil.navigateToWebViewActivity(AboutSpotActivity.this, contentLink,title);
            }
        });

        prflSpots.setOnRefreshLister(this);
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
        if(prflSpots.getRefreshStatus() == PullToRefreshLayout.STATUS_REFRESHING){
            prflSpots.refreshComplete();
        }

    }

    @Override
    public void onRefresh() {
        RecommendModel.getInstance().requestRecommendSpot(this);
    }
}
