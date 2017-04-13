package com.lhc.android.gz_guide.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lhc.android.gz_guide.Interface.OnGetTastyFoodsListener;
import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.adapter.TastyFoodAdapter;
import com.lhc.android.gz_guide.model.RecommendModel;
import com.lhc.android.gz_guide.model.TastyFood;
import com.lhc.android.gz_guide.util.NavigationUtil;

import java.util.ArrayList;
import java.util.List;

public class AboutTastyActivity extends BaseActivity implements OnGetTastyFoodsListener {

    private ListView listView;
    private TastyFoodAdapter adapter;
    private List<TastyFood> foodList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_eat);
        RecommendModel.getInstance().setOnGetTastyFoodsListener(this);
        listView = (ListView) findViewById(R.id.lv_tasty_food);
        adapter = new TastyFoodAdapter(this);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NavigationUtil.navigateToTastyDetailActivity(AboutTastyActivity.this);
            }
        });

        RecommendModel.getInstance().getRecommendTasties(this);
    }


    @Override
    public int getTitleRes() {
        return R.string.eat;
    }


    @Override
    public void onGetTasties(List<TastyFood> tastyFoods) {

        foodList = tastyFoods;
        adapter.setData(foodList);
        adapter.notifyDataSetChanged();
    }
}