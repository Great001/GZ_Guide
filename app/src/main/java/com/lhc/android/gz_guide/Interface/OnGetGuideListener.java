package com.lhc.android.gz_guide.Interface;

import com.lhc.android.gz_guide.model.LocalGuide;

import java.util.List;

/**
 * Created by Administrator on 2017/4/14.
 */
public interface OnGetGuideListener {

    void onGetGuides(List<LocalGuide> list);
}
