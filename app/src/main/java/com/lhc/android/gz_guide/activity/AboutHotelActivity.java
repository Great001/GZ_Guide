package com.lhc.android.gz_guide.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.adapter.HotelsAdapter;
import com.lhc.android.gz_guide.model.Hotel;
import com.lhc.android.gz_guide.util.NavigationUtil;

import java.util.ArrayList;
import java.util.List;

public class AboutHotelActivity extends BaseActivity {

    private ListView lvHotels;
    private HotelsAdapter adapter;
    private List<Hotel> hotels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_hotel);
        lvHotels = (ListView) findViewById(R.id.lv_hotels);
        initData();
        adapter = new HotelsAdapter(this, hotels);
        lvHotels.setAdapter(adapter);

        lvHotels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NavigationUtil.navigateToHotelDetailActivity(AboutHotelActivity.this);
            }
        });

    }

    @Override
    public int getTitleRes() {
        return R.string.hotel;
    }

    public void initData() {
        Hotel hotel1 = new Hotel();
        hotel1.setRating(Float.valueOf("4.6"));
        hotel1.setName("华师旅馆");
        hotel1.setType("经济型");
        hotel1.setAddress("天河区中山大道西55号华南师范大学");
        hotel1.setAvailable(true);
        hotel1.setLeastPrice("40元起");
        hotel1.setImgResId(R.drawable.hotel_scnu);

        Hotel hotel2 = new Hotel();
        hotel2.setImgResId(R.drawable.gz_bte);
        hotel2.setName("白天鹅酒店");
        hotel2.setType("豪华型");
        hotel2.setAvailable(false);
        hotel2.setAddress("广州市越秀区人民大道44号");
        hotel2.setLeastPrice("150元起");
        hotel2.setRating(Float.valueOf("4.8"));

        for (int i = 0; i < 7; i++) {
            hotels.add(hotel1);
            hotels.add(hotel2);
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
}
