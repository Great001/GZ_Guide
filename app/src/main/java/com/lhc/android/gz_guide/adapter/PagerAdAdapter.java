package com.lhc.android.gz_guide.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lhc.android.gz_guide.R;
import com.lhc.android.gz_guide.model.RecommendAd;
import com.lhc.android.gz_guide.util.NavigationUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/22.
 */
public class PagerAdAdapter extends PagerAdapter {

    private Context context;
    private List<RecommendAd> pagerDataList = new ArrayList<>();

    public PagerAdAdapter(Context context){
        this.context = context;
    }

    public void setData(List<RecommendAd> list){
        pagerDataList = list;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView imageView = new ImageView(context);
        imageView.setClickable(true);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationUtil.navigateToSpotDetailActivity(context,pagerDataList.get(position));
            }
        });
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewPager.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(params);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        RecommendAd data = pagerDataList.get(position);
        Glide.with(context).load(data.getImgUrl()).placeholder(R.drawable.loading).into(imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if(object==container.getChildAt(position)) {
            container.removeView((View)object);
        }
    }

    @Override
    public int getCount() {
        return pagerDataList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}
