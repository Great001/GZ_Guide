package com.lhc.android.gz_guide;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * Created by Administrator on 2017/3/30.
 */
public class ReftHttpClient {

    private volatile  static ReftHttpClient instance;
    private  RequestQueue mQueue;

    private ReftHttpClient(Context context){
       mQueue = Volley.newRequestQueue(context);
    }


    public static ReftHttpClient getInstance(Context context){
       if(instance == null){
           synchronized (ReftHttpClient.class) {
               instance = new ReftHttpClient(context);
           }
       }
        return instance;
    }


    public void post(String url, JSONObject jsonBody, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener){
        BmobRequest postRequest = new BmobRequest(Request.Method.POST, url, jsonBody, listener, errorListener);
        mQueue.add(postRequest);
    }

    public void get(String url, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener){
        BmobRequest getRequest = new BmobRequest(Request.Method.GET,url,null,listener,errorListener);
        mQueue.add(getRequest);
    }


    public void put(String token,String url,JSONObject body,Response.Listener<JSONObject> listener, Response.ErrorListener errorListener){
        BmobRequestWithToken putRequest = new BmobRequestWithToken(token, Request.Method.PUT,url,body,listener,errorListener);
        mQueue.add(putRequest);

    }



}
