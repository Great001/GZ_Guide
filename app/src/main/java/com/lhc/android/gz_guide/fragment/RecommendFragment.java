package com.lhc.android.gz_guide.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lhc.android.gz_guide.Interface.OnGetGoodsListener;
import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.adapter.RecommendGoodsAdapter;
import com.lhc.android.gz_guide.model.RecommendGood;
import com.lhc.android.gz_guide.model.RecommendModel;
import com.lhc.android.gz_guide.util.NavigationUtil;
import com.lhc.android.gz_guide.view.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class RecommendFragment extends Fragment implements OnGetGoodsListener {

    private PullToRefreshLayout mPrfLayout;
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
        mPrfLayout = (PullToRefreshLayout) view.findViewById(R.id.prl_recommend);
        listView = (ListView) view.findViewById(R.id.lv_recommend);
        setUpRefreshLayout();
        adapter = new RecommendGoodsAdapter(getContext());
        listView.setAdapter(adapter);
        RecommendModel.getInstance().addOnGetGoodsListener(this);
        initData();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NavigationUtil.navigateToSpotDetailActivity(getContext(),position);
            }
        });
        return view;
    }

    public void initData(){
        RecommendModel.getInstance().getRecommendGoods(getContext());
    }

    @Override
    public void onGetGoods(List<RecommendGood> goods) {
        this.goods = goods;
        if(adapter != null) {
            adapter.setGoods(goods);
            adapter.notifyDataSetChanged();
            if(mPrfLayout.getRefreshStatus() == PullToRefreshLayout.STATUS_REFRESHING) {
                mPrfLayout.refreshComplete();
            }
        }
    }

    @Override
    public void onGetFailed() {
        mPrfLayout.refreshError();
    }

    public void setUpRefreshLayout(){
        mPrfLayout.setOnRefreshLister(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                RecommendModel.getInstance().requestRecommendGoods(getContext());
            }
        });
    }
}
