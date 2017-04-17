package com.lhc.android.gz_guide.model;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lhc.android.gz_guide.Interface.OnGetFunsListener;
import com.lhc.android.gz_guide.Interface.OnGetGoodsListener;
import com.lhc.android.gz_guide.Interface.OnGetHotelsListener;
import com.lhc.android.gz_guide.Interface.OnGetPagerDataListener;
import com.lhc.android.gz_guide.Interface.OnGetStrategiesListener;
import com.lhc.android.gz_guide.Interface.OnGetTastyFoodsListener;
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
    public static final String URL_GET_HOTELS = "https://api.bmob.cn/1/classes/hotel";
    public static final String URL_GET_TASTIES = "https://api.bmob.cn/1/classes/tasty";
    public static final String URL_GET_SPOTS = "https://api.bmob.cn/1/classes/spot";
    public static final String URL_GET_STARTEGIES = "https://api.bmob.cn/1/classes/strategy";

    private List<RecommendGood> goodList;
    private List<RecommendPagerData> pagerDataList;
    private List<Hotel> hotelList;
    private List<TastyFood> tastyFoodList;
    private List<Strategy> strageryList;
    private List<Spot> spotList;

    private List<OnGetGoodsListener> getGoodsListeners = new ArrayList<>();
    private OnGetPagerDataListener onGetPagerDataListener;

    private OnGetFunsListener onGetFunsListener;
    private OnGetHotelsListener onGetHotelsListener;
    private OnGetStrategiesListener onGetStrateriesListener;
    private OnGetTastyFoodsListener onGetTastyFoodsListener;


    private volatile static RecommendModel instance;

    public static RecommendModel getInstance() {
        if (instance == null) {
            synchronized (RecommendModel.class) {
                instance = new RecommendModel();
            }
        }
        return instance;
    }

    public void getRecommendGoods(Context context) {
        if (goodList != null) {
            for (OnGetGoodsListener listener : getGoodsListeners) {
                if (listener != null) {
                    listener.onGetGoods(goodList);
                }
            }
        } else {
            requestRecommendGoods(context);
        }
    }

    public void requestRecommendGoods(Context context) {
        ReftHttpClient.getInstance(context).get(URL_GET_GOODS, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                goodList = RecommendGood.getRecommendGoods(jsonObject);
                for (OnGetGoodsListener listener : getGoodsListeners) {
                    if (listener != null) {
                        listener.onGetGoods(goodList);
                    }
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                for (OnGetGoodsListener listener : getGoodsListeners) {
                    if (listener != null) {
                        listener.onGetFailed();
                    }
                }
            }
        });
    }


    public void getRecommendPagerData(Context context) {
        if (pagerDataList != null) {
            onGetPagerDataListener.onGetPagerData(pagerDataList);
        } else {
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


    public void getRecommendHotels(Context context) {
        if (onGetHotelsListener == null) {
            return;
        }
        if (hotelList != null) {
            onGetHotelsListener.onGetHotels(hotelList);
        } else {
            requestRecommendHotels(context);
        }

    }

    public void requestRecommendHotels(Context context) {
        ReftHttpClient.getInstance(context).get(URL_GET_HOTELS, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                hotelList = Hotel.getHotelList(jsonObject);
                onGetHotelsListener.onGetHotels(hotelList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
    }


    public void getRecommendSpots(Context context) {
        if (onGetFunsListener == null) {
            return;
        }
        if (spotList != null) {
            onGetFunsListener.onGetFuns(spotList);
        } else {
           requestRecommendSpot(context);
        }
    }

    public void requestRecommendSpot(Context context){
        ReftHttpClient.getInstance(context).get(URL_GET_SPOTS, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                spotList = Spot.getSpotList(jsonObject);
                onGetFunsListener.onGetFuns(spotList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
    }

    public void getRecommendStrategies(Context context) {
        if (onGetStrateriesListener == null) {
            return;
        }
        if (strageryList != null) {
            onGetStrateriesListener.onGetStrateries(strageryList);
        } else {
            requestRecommendStrategies(context);
        }
    }


    public void requestRecommendStrategies(Context context) {
        ReftHttpClient.getInstance(context).get(URL_GET_STARTEGIES, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                strageryList = Strategy.getStrategyList(jsonObject);
                onGetStrateriesListener.onGetStrateries(strageryList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
    }


    public void getRecommendTasties(Context context) {
        if (onGetTastyFoodsListener == null) {
            return;
        }
        if (tastyFoodList != null) {
            onGetTastyFoodsListener.onGetTasties(tastyFoodList);
        } else {
            requestRecommendTasties(context);
        }

    }

    public void requestRecommendTasties(Context context) {
        ReftHttpClient.getInstance(context).get(URL_GET_TASTIES, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

                tastyFoodList = TastyFood.getTastyList(jsonObject);
                onGetTastyFoodsListener.onGetTasties(tastyFoodList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

    }


    public void addOnGetGoodsListener(OnGetGoodsListener listener) {
        getGoodsListeners.add(listener);
    }

    public void setOnGetPagerDataListener(OnGetPagerDataListener listener) {
        onGetPagerDataListener = listener;
    }

    public void setOnGetHotelsListener(OnGetHotelsListener listener) {
        onGetHotelsListener = listener;
    }

    public void setOnGetFunsListener(OnGetFunsListener listener) {
        onGetFunsListener = listener;
    }


    public void setOnGetTastyFoodsListener(OnGetTastyFoodsListener listener) {
        onGetTastyFoodsListener = listener;
    }

    public void setOnGetStrategiesListener(OnGetStrategiesListener listener) {
        onGetStrateriesListener = listener;
    }


}
