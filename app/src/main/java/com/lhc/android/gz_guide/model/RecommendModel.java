package com.lhc.android.gz_guide.model;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lhc.android.gz_guide.ReftHttpClient;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/8.
 */
public class RecommendModel {

    public static final String URL_GET_GOODS = "https://api.bmob.cn/1/classes/RecommendGood";
    public static final String URL_GET_PAGER_DATA = "https://api.bmob.cn/1/classes/RecommendPagerData";

    private List<RecommendGood> goodList;
    private List<RecommendPagerData> pagerDataList;

    private  List<OnGetGoodsListener> getGoodsListeners = new ArrayList<>();
    private  OnGetPagerDataListener onGetPagerDataListener;


    private volatile static RecommendModel instance;

    public static RecommendModel getInstance(){
        if(instance == null){
            synchronized (RecommendModel.class){
                instance = new RecommendModel();
            }
        }
            return instance;
    }

    public  void getRecommendGoods(Context context){
        if (goodList != null) {
           for(OnGetGoodsListener listener:getGoodsListeners){
               if(listener != null){
                   listener.onGetGoods(goodList);
               }
           }
        }else {
            ReftHttpClient.getInstance(context).get(URL_GET_GOODS, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                        goodList = RecommendGood.getRecommendGoods(jsonObject);
                    for(OnGetGoodsListener listener:getGoodsListeners){
                        if(listener != null){
                            listener.onGetGoods(goodList);
                        }
                    }

                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {

                }
            });
        }
    }

    public void getRecommendPagerData(Context context){
        if(pagerDataList!= null){
            onGetPagerDataListener.onGetPagerData(pagerDataList);
        }else {
            ReftHttpClient.getInstance(context).get(URL_GET_PAGER_DATA, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    if (onGetPagerDataListener != null) {
                      pagerDataList = RecommendPagerData.getRecommendPageDataList(jsonObject);
                        onGetPagerDataListener.onGetPagerData(pagerDataList);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {

                }
            });
        }
    }


    public interface OnGetGoodsListener{
        void onGetGoods(List<RecommendGood> goods);
    }

    public void addOnGetGoodsListener(OnGetGoodsListener listener){
        getGoodsListeners.add(listener);
    }

    public interface OnGetPagerDataListener{
        void onGetPagerData(List<RecommendPagerData> datas);
    }

    public void setOnGetPagerDataListener(OnGetPagerDataListener listener){
        onGetPagerDataListener = listener;
    }

}
