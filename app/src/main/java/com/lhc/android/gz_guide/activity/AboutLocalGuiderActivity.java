package com.lhc.android.gz_guide.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lhc.android.gz_guide.Interface.OnGetGuidesListener;
import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.adapter.LocalGuiderAdapter;
import com.lhc.android.gz_guide.model.LocalGuide;
import com.lhc.android.gz_guide.model.LocalModel;
import com.lhc.android.gz_guide.util.NavigationUtil;
import com.lhc.android.gz_guide.view.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class AboutLocalGuiderActivity extends BaseActivity implements OnGetGuidesListener{

    private PullToRefreshLayout prflGuides;
    private ListView listView;
    private List<LocalGuide> localGuides = new ArrayList<>();
    private LocalGuiderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_local_guider);
        prflGuides = (PullToRefreshLayout) findViewById(R.id.prfl_guides);
        listView = (ListView) findViewById(R.id.lv_local_guides);
        adapter = new LocalGuiderAdapter(this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                NavigationUtil.navigateToLocalGuideDetailActivity(AboutLocalGuiderActivity.this);
            }
        });

        prflGuides.setOnRefreshLister(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                LocalModel.getInstance().requestLocalGuides(AboutLocalGuiderActivity.this);
            }
        });
        LocalModel.getInstance().setOnGetGuidesListener(this);
        LocalModel.getInstance().getLocalGuides(this);
    }

    @Override
    public int getTitleRes() {
        return R.string.get_guide;
    }


    @Override
    public void onGetGuides(List<LocalGuide> guideList) {
        this.localGuides = guideList;
        adapter.setData(localGuides);
        adapter.notifyDataSetChanged();
        if(prflGuides.getRefreshStatus() == PullToRefreshLayout.STATUS_REFRESHING){
            prflGuides.refreshComplete();
        }
    }
}
