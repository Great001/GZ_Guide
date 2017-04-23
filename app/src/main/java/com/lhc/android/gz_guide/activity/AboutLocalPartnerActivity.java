package com.lhc.android.gz_guide.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lhc.android.gz_guide.Interface.OnGetPartnersListener;
import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.adapter.LocalPartnersAdapter;
import com.lhc.android.gz_guide.model.LocalModel;
import com.lhc.android.gz_guide.model.LocalPartner;
import com.lhc.android.gz_guide.util.NavigationUtil;
import com.lhc.android.gz_guide.view.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class AboutLocalPartnerActivity extends BaseActivity implements OnGetPartnersListener {

    private PullToRefreshLayout prflPartners;
    private ListView listView;
    private LocalPartnersAdapter adapter;
    private List<LocalPartner> partners = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_local_partner);

        prflPartners = (PullToRefreshLayout) findViewById(R.id.prfl_partners);
        listView = (ListView) findViewById(R.id.lv_local_partners);
        adapter = new LocalPartnersAdapter(this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                NavigationUtil.navigateToPersonalDetailActivity(AboutLocalPartnerActivity.this);
            }
        });

        prflPartners.setOnRefreshLister(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                LocalModel.getInstance().requestLocalPartners(AboutLocalPartnerActivity.this);
            }
        });
        LocalModel.getInstance().setOnGetPartnersListener(this);
        LocalModel.getInstance().getLocalPartners(this);
    }

    @Override
    public int getTitleRes() {
        return R.string.get_partner;
    }

    @Override
    public void onGetPartners(List<LocalPartner> partnerList) {
       partners =  partnerList;
        adapter.setData(partnerList);
        adapter.notifyDataSetChanged();
        if(prflPartners.getRefreshStatus() == PullToRefreshLayout.STATUS_REFRESHING){
            prflPartners.refreshComplete();
        }
    }
}
