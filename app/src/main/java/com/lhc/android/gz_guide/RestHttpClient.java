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
public class RestHttpClient {

    private volatile static RestHttpClient instance;

    private RequestQueue mQueue;

    private RestHttpClient(Context context) {
        mQueue = Volley.newRequestQueue(context);
    }


    public static RestHttpClient getInstance(Context context) {
        if (instance == null) {
            synchronized (RestHttpClient.class) {
                instance = new RestHttpClient(context);
            }
        }
        return instance;
    }


    public void post(String url, JSONObject jsonBody, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        BmobRequest postRequest = new BmobRequest(Request.Method.POST, url, jsonBody, listener, errorListener);
        mQueue.add(postRequest);
    }

    public void get(String url, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        BmobRequest getRequest = new BmobRequest(Request.Method.GET, url, null, listener, errorListener);
        mQueue.add(getRequest);
    }


    public void put(String token, String url, JSONObject body, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        BmobRequestWithToken putRequest = new BmobRequestWithToken(token, Request.Method.PUT, url, body, listener, errorListener);
        mQueue.add(putRequest);

    }


}
