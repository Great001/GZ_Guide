package com.lhc.android.gz_guide;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/30.
 */
public class  BmobRequest extends JsonObjectRequest {

    public BmobRequest(int method, String url,JSONObject body, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener){
        super(method,url,body,listener,errorListener);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String,String> headerMap = new HashMap<>();
        headerMap.put("X-Bmob-Application-Id","ca610bdbddf67fd7e4e28dc901781826");
        headerMap.put("X-Bmob-REST-API-Key","2089821127326283a4875780d0820bd2");
        return headerMap;
    }
}
