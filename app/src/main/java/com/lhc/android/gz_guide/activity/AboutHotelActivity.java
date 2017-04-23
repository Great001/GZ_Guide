package com.lhc.android.gz_guide.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lhc.android.gz_guide.Interface.OnGetHotelsListener;
import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.adapter.HotelsAdapter;
import com.lhc.android.gz_guide.model.Hotel;
import com.lhc.android.gz_guide.model.RecommendModel;
import com.lhc.android.gz_guide.util.NavigationUtil;
import com.lhc.android.gz_guide.view.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class AboutHotelActivity extends BaseActivity implements OnGetHotelsListener,PullToRefreshLayout.OnRefreshListener{

    private PullToRefreshLayout prflHotels;
    private ListView lvHotels;
    private HotelsAdapter adapter;
    private List<Hotel> hotels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_hotel);
        prflHotels = (PullToRefreshLayout) findViewById(R.id.prfl_hotels);
        lvHotels = (ListView) findViewById(R.id.lv_hotels);
        RecommendModel.getInstance().setOnGetHotelsListener(this);
        adapter = new HotelsAdapter(this);
        lvHotels.setAdapter(adapter);

        lvHotels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String contentLink = hotels.get(position).getContentLink();
                String title = hotels.get(position).getName();
                NavigationUtil.navigateToWebViewActivity(AboutHotelActivity.this,contentLink,title);
            }
        });

        prflHotels.setOnRefreshLister(this);

        RecommendModel.getInstance().getRecommendHotels(this);

    }

    @Override
    public int getTitleRes() {
        return R.string.hotel;
    }



    @Override
    public void onGetHotels(List<Hotel> hotels) {
        this.hotels  = hotels;
        adapter.setData(hotels);
        adapter.notifyDataSetChanged();
        if(prflHotels.getRefreshStatus() == PullToRefreshLayout.STATUS_REFRESHING) {
            prflHotels.refreshComplete();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {
        RecommendModel.getInstance().requestRecommendHotels(this);
    }
}
