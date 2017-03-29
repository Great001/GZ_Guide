package com.lhc.android.gz_guide.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.adapter.SpotsAdapter;
import com.lhc.android.gz_guide.model.Spot;
import com.lhc.android.gz_guide.util.NavigationUtil;

import java.util.ArrayList;
import java.util.List;

public class AboutSpotActivity extends BaseActivity {

    private ListView listView;
    private List<Spot> spotList = new ArrayList<>();
    private SpotsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_spot);
        listView = (ListView) findViewById(R.id.lv_spots);
        initData();
        adapter = new SpotsAdapter(this, spotList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NavigationUtil.navigateToSpotDetailActivity(AboutSpotActivity.this, position);
            }
        });
    }

    @Override
    public int getTitleRes() {
        return R.string.spot_detail;
    }

    public void initData() {
        Spot spot1 = new Spot();
        spot1.setName("广州塔");
        spot1.setDesc("一塔倾城，新广州、新地标");
        spot1.setAddress("广州市海珠区");
        spot1.setTicketPrice(120);
        spot1.setRating(Float.valueOf("4.1"));
        spot1.setImgResId(R.drawable.gz);

        Spot spot2 = new Spot();
        spot2.setName("西关");
        spot2.setDesc("荔湾区就因旧时西关内有“一湾青水绿，两岸荔枝红”美誉的“荔枝湾”而得名。\n" +
                "同时，老西关也是今日广州著名的美食标志，素有“食在广州，味在西关”的说法。");
        spot2.setAddress("广州市荔湾区");
        spot2.setTicketPrice(0);
        spot2.setRating(Float.valueOf("3.9"));
        spot2.setImgResId(R.drawable.gz_xg);

        for (int i = 0; i < 6; i++) {
            spotList.add(spot1);
            spotList.add(spot2);
        }


    }

}
