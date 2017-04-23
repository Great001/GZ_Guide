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
import com.lhc.android.gz_guide.view.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class AboutStrategyActivity extends BaseActivity implements OnGetStrategiesListener,PullToRefreshLayout.OnRefreshListener{

    private PullToRefreshLayout prflStrategy;
    private ListView listView;
    private StrageriesAdapter adapter;
    private List<Strategy> strageryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_stragery);
        RecommendModel.getInstance().setOnGetStrategiesListener(this);
        prflStrategy = (PullToRefreshLayout) findViewById(R.id.prfl_strategy);
        listView = (ListView) findViewById(R.id.lv_stragery);
        adapter = new StrageriesAdapter(this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String contentLink = strageryList.get(position).getContentLink();
                String title = strageryList.get(position).getTitle();
                NavigationUtil.navigateToWebViewActivity(AboutStrategyActivity.this,contentLink,title);
            }
        });

        prflStrategy.setOnRefreshLister(this);
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
        if(prflStrategy.getRefreshStatus() == PullToRefreshLayout.STATUS_REFRESHING){
            prflStrategy.refreshComplete();
        }
    }

    @Override
    public void onRefresh() {
        RecommendModel.getInstance().requestRecommendStrategies(this);
    }
}
