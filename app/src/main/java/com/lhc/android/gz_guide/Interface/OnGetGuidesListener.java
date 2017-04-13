package com.lhc.android.gz_guide.Interface;

import com.lhc.android.gz_guide.model.LocalGuide;

import java.util.List;

/**
 * Created by Administrator on 2017/4/13.
 */
public interface OnGetGuidesListener {
    void onGetGuides(List<LocalGuide> guideList);
}
