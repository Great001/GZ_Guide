package com.lhc.android.gz_guide.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

import com.lhc.android.gz_guide.util.DimensionUtil;

/**
 * Created by Administrator on 2017/4/23.
 */
public class MyScrollView extends ScrollView {

    private OnMyScrollViewChangeListener onMyScrollViewChangeListener;

    public MyScrollView(Context context) {
        this(context,null);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        if(onMyScrollViewChangeListener != null){
            onMyScrollViewChangeListener.onScrollChanged(l,t,oldl,oldt);
        }
        super.onScrollChanged(l, t, oldl, oldt);
    }

    public interface OnMyScrollViewChangeListener{
        void onScrollChanged(int left,int top,int oldl,int oldt);
    }

    public void addMyScrollViewChangeListener(OnMyScrollViewChangeListener listener){
        this.onMyScrollViewChangeListener = listener;
    }

}
