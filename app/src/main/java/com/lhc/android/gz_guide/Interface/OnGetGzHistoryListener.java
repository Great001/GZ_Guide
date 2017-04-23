package com.lhc.android.gz_guide.Interface;

import com.lhc.android.gz_guide.model.GzHistory;

import java.util.List;

/**
 * Created by Administrator on 2017/4/23.
 */
public interface OnGetGzHistoryListener {

    void onGetHistories(List<GzHistory> historiyList);
}
