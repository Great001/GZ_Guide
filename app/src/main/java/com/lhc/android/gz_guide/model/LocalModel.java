package com.lhc.android.gz_guide.model;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lhc.android.gz_guide.Interface.OnGetGuidesListener;
import com.lhc.android.gz_guide.Interface.OnGetPartnersListener;
import com.lhc.android.gz_guide.ReftHttpClient;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2017/4/14.
 */
public class LocalModel {

    public static final String URL_GET_GUIDE = "https://api.bmob.cn/1/classes/guide";
    public static final String URL_GET_PARTNER = "https://api.bmob.cn/1/classes/partner";

    private OnGetGuidesListener onGetGuidesListener;
    private OnGetPartnersListener onGetPartnersListener;

    private List<LocalGuide> guideList;
    private List<LocalPartner> partnerList;

    private volatile static LocalModel instance;

    public static LocalModel getInstance(){
        if(instance == null){
            synchronized (LocalModel.class){
                instance = new LocalModel();
            }
        }
        return instance;
    }

    public void getLocalGuides(Context context){
        if(onGetGuidesListener == null){
            return;
        }

        if(guideList != null){
            onGetGuidesListener.onGetGuides(guideList);
        }else{
          requestLocalGuides(context);
        }
    }

    public void requestLocalGuides(Context context){
        ReftHttpClient.getInstance(context).get(URL_GET_GUIDE, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

                guideList = LocalGuide.getLocalGuideList(jsonObject);
                onGetGuidesListener.onGetGuides(guideList);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
    }


    public void getLocalPartners(Context context){
        if(onGetPartnersListener == null){
            return;
        }
        if(partnerList != null){
            onGetPartnersListener.onGetPartners(partnerList);
        }else{
          requestLocalPartners(context);
        }
    }

    public void requestLocalPartners(Context context){
        ReftHttpClient.getInstance(context).get(URL_GET_PARTNER, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

                partnerList = LocalPartner.getLocalPartnerList(jsonObject);
                onGetPartnersListener.onGetPartners(partnerList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
    }


    public void setOnGetGuidesListener(OnGetGuidesListener listener){
        onGetGuidesListener = listener;
    }

    public void setOnGetPartnersListener(OnGetPartnersListener listener){
        onGetPartnersListener = listener;
    }


}
