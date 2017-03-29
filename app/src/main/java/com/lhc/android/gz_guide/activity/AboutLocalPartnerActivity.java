package com.lhc.android.gz_guide.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.adapter.LocalPartnersAdapter;
import com.lhc.android.gz_guide.model.LocalPartner;
import com.lhc.android.gz_guide.util.NavigationUtil;

import java.util.ArrayList;
import java.util.List;

public class AboutLocalPartnerActivity extends BaseActivity {

    private ListView listView;
    private LocalPartnersAdapter adapter;
    private List<LocalPartner> partners = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_local_partner);

        listView = (ListView) findViewById(R.id.lv_local_partners);
        initData();
        adapter = new LocalPartnersAdapter(this, partners);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NavigationUtil.navigateToPersonalDetailActivity(AboutLocalPartnerActivity.this);
            }
        });
    }

    @Override
    public int getTitleRes() {
        return R.string.get_partner;
    }

    public void initData() {
        LocalPartner partner1 = new LocalPartner();
        partner1.setName("张三");
        partner1.setSex(0);
        partner1.setJob("销售");
        partner1.setTel("1560304839");
        partner1.setRequirment("准时守时，善良开朗，最好后摄影");
        partner1.setAvatarResId(R.drawable.person_136);

        LocalPartner partner2 = new LocalPartner();
        partner2.setName("王五");
        partner2.setSex(1);
        partner2.setJob("警察");
        partner2.setTel("020-110");
        partner2.setRequirment("正直，热爱广府文化");
        partner2.setAvatarResId(R.drawable.person_111);

        for (int i = 0; i < 5; i++) {
            partners.add(partner1);
            partners.add(partner2);
        }
    }

}
