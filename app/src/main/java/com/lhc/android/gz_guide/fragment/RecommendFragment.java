package com.lhc.android.gz_guide.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.adapter.RecommendGoodsAdapter;
import com.lhc.android.gz_guide.model.RecommendGood;
import com.lhc.android.gz_guide.util.NavigationUtil;

import java.util.ArrayList;
import java.util.List;

public class RecommendFragment extends Fragment {

    private ListView listView;
    private List<RecommendGood> goods = new ArrayList<>();
    private RecommendGoodsAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);
        listView = (ListView) view.findViewById(R.id.lv_recommend);
        initData();
        adapter = new RecommendGoodsAdapter(getContext(),goods);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NavigationUtil.navigateToSpotDetailActivity(getContext(),position);
            }
        });
        return view;
    }

    public void initData(){
        RecommendGood good1 = new RecommendGood("广州塔是个好地方，广州塔塔身168米–334.4米处设有“蜘蛛侠栈道”，是世界最高最长的空中漫步云梯[7]  。塔身422.8米处设有旋转餐厅，是世界最高的旋转餐厅[8]  。塔身顶部450~454米处设有摩天轮，是世界最高摩天轮[9] ", R.drawable.gz, "", true, 3, "广州塔");
        RecommendGood good2 = new RecommendGood("陈家祠是个好地方，陈氏书院，俗称陈家祠，位于广州市中山七路。陈家祠是广东现存祠堂中最富有广东特色的艺术建筑群，布局严整，装饰精巧，富丽堂皇，是全国文物重点保护单位。", R.drawable.chenjiaci, "", false, 3, "陈家祠");
        for (int i = 0; i < 8; i++) {
            goods.add(good1);
            goods.add(good2);
        }
    }


}
