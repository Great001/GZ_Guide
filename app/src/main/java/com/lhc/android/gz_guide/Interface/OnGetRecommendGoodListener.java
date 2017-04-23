package com.lhc.android.gz_guide.Interface;

import com.lhc.android.gz_guide.model.RecommendGood;

import java.util.List;

/**
 * Created by Administrator on 2017/4/13.
 */
public interface OnGetRecommendGoodListener {
    void onGetGoods(List<RecommendGood> goods);

    void onGetFailed();

}
