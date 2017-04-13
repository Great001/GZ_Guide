package com.lhc.android.gz_guide.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lhc.android.gz_guide.Interface.OnGetStrategiesListener;
import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.adapter.StrageriesAdapter;
import com.lhc.android.gz_guide.model.RecommendModel;
import com.lhc.android.gz_guide.model.Strategy;
import com.lhc.android.gz_guide.util.NavigationUtil;

import java.util.ArrayList;
import java.util.List;

public class AboutStrageryActivity extends BaseActivity implements OnGetStrategiesListener{

    private ListView listView;
    private StrageriesAdapter adapter;
    private List<Strategy> strageryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_stragery);
        RecommendModel.getInstance().setOnGetStrategiesListener(this);
        listView = (ListView) findViewById(R.id.lv_stragery);
//        initData();
        adapter = new StrageriesAdapter(this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NavigationUtil.navigateToStrageryDetailActivity(AboutStrageryActivity.this);
            }
        });

        RecommendModel.getInstance().getRecommendStrategies(this);
    }

    @Override
    public int getTitleRes() {
        return R.string.stratery;
    }

    @Override
    public void onGetStrateries(List<Strategy> strageryList) {
        this.strageryList = strageryList;
        adapter.setData(strageryList);
        adapter.notifyDataSetChanged();
    }

    public void initData() {

        Strategy stragery1 = new Strategy();
        stragery1.setTitle("广州老城一日游");
        stragery1.setDesc("一天带你领略老广州的风韵");
        stragery1.setReadCount(10000);
        stragery1.setCommentCount(490);
        stragery1.setImgResId(R.drawable.gz_sx);

        Strategy stragery2 = new Strategy();
        stragery2.setTitle("广州全程三日游");
        stragery2.setDesc("三天带你全面游玩这座有着悠久历史和现代化气息的城市，让你看到广州的魅力");
        stragery2.setImgResId(R.drawable.gz_sm);
        stragery2.setReadCount(8922);
        stragery2.setCommentCount(290);

        Strategy stragery3 = new Strategy();
        stragery3.setTitle("吃货来广州必看");
        stragery3.setDesc("来广州，当然不能错过广州的美食，带你一起尝遍广州的各色美食，绝对满足你的味蕾");
        stragery3.setReadCount(8320);
        stragery3.setCommentCount(870);
        stragery3.setImgResId(R.drawable.gz_msj);

        for (int i = 0; i < 5; i++) {
            strageryList.add(stragery1);
            strageryList.add(stragery2);
            strageryList.add(stragery3);
        }


    }

}
