package com.lhc.android.gz_guide.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.adapter.LocalGuiderAdapter;
import com.lhc.android.gz_guide.model.LocalGuide;
import com.lhc.android.gz_guide.util.NavigationUtil;

import java.util.ArrayList;
import java.util.List;

public class AboutLocalGuiderActivity extends BaseActivity {

    private ListView listView;
    private List<LocalGuide> localGuides = new ArrayList<>();
    private LocalGuiderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_local_guider);

        listView = (ListView) findViewById(R.id.lv_local_guides);
        initData();
        adapter = new LocalGuiderAdapter(this, localGuides);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NavigationUtil.navigateToLocalGuideDetailActivity(AboutLocalGuiderActivity.this);
            }
        });
    }

    @Override
    public int getTitleRes() {
        return R.string.get_guide;
    }

    public void initData() {
        LocalGuide guide1 = new LocalGuide();
        guide1.setName("张三");
        guide1.setJob("程序员");
        guide1.setRange(5);
        guide1.setSex(1);
        guide1.setTel("1563902943");
        guide1.setBriefInfo("自信开朗，积极阳光，乐于助人");
        guide1.setThumpResId(R.drawable.person_111);

        LocalGuide guide2 = new LocalGuide();
        guide2.setName("李四");
        guide2.setJob("导游");
        guide2.setRange(9);
        guide2.setSex(0);
        guide2.setTel("020-8392843");
        guide2.setBriefInfo("广州土生土长，熟悉老广州，非常愿意传播老广州文化");
        guide2.setThumpResId(R.drawable.person_136);

        for (int i = 0; i < 5; i++) {
            localGuides.add(guide1);
            localGuides.add(guide2);
        }
    }

}
