package com.lhc.android.gz_guide.model;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lhc.android.gz_guide.Interface.OnGetGzHistoryListener;
import com.lhc.android.gz_guide.RestHttpClient;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2017/4/23.
 */
public class HistoryModel {

    public static final String HISTORY_URL = "https://api.bmob.cn/1/classes/GzHistrory";

    private volatile  static HistoryModel instance;

    private OnGetGzHistoryListener onGetGzHistoryListener;
    private List<GzHistory> gzHistories;

    public static HistoryModel getInstance(){
        if(instance == null){
            synchronized (HistoryModel.class){
                instance = new HistoryModel();
            }
        }
        return instance;
    }


    public void getGzHistory(Context context){
            if (gzHistories != null) {
                if(onGetGzHistoryListener != null){
                    onGetGzHistoryListener.onGetHistories(gzHistories);
                }
            } else {
                queryGzHistory(context);
            }

    }


    public void queryGzHistory(Context context){
        RestHttpClient.getInstance(context).get(HISTORY_URL, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                gzHistories = GzHistory.getHistoryList(jsonObject);
                if(onGetGzHistoryListener != null){
                    onGetGzHistoryListener.onGetHistories(gzHistories);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
    }



    public void setOnGetGzHistoryListener(OnGetGzHistoryListener listener){
        onGetGzHistoryListener = listener;
    }

}
