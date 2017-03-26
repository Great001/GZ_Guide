package com.lhc.android.gz_guide.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.adapter.TastyFoodAdapter;
import com.lhc.android.gz_guide.model.TastyFood;
import com.lhc.android.gz_guide.util.NavigationUtil;

import java.util.ArrayList;
import java.util.List;

public class AboutEatActivity extends AppCompatActivity {

    private ListView listView;
    private TastyFoodAdapter adapter;
    private List<TastyFood> foodList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_eat);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.eat);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        listView = (ListView) findViewById(R.id.lv_tasty_food);
        initData();
        adapter = new TastyFoodAdapter(this,foodList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NavigationUtil.navigateToTastyDetailActivity(AboutEatActivity.this);
            }
        });

    }

    public void initData() {

        TastyFood food1 = new TastyFood();
        food1.setName("特色广州卤味粉条");
        food1.setDesc("极具广府特色，味道鲜美");
        food1.setRating(Float.valueOf("4.2"));
        food1.setImgResId(R.drawable.tasty_noddle);
        food1.setPrice("avg 20元/份");

        TastyFood food2 = new TastyFood();
        food2.setName("广式早茶");
        food2.setDesc("广东尤其是广州本地人极具韵味的传统美食，物美价廉");
        food2.setRating(Float.valueOf("3.9"));
        food2.setPrice("avg 80元/位");
        food2.setImgResId(R.drawable.tasty_morning_tea);

        for (int i = 0; i < 8; i++) {
            foodList.add(food1);
            foodList.add(food2);
        }
    }

}
