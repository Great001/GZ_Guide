package com.lhc.android.gz_guide.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.Response;
import com.lhc.android.gz_guide.RestHttpClient;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/3/30.
 */
public class UserModel {

    public static final String SP_USER_PROFILE = "user_profile";

    public static final String USER_URL = "https://api.bmob.cn/1/users";
    public static final String USER_LOGIN_URL = "https://api.bmob.cn/1/login?username=%s&password=%s";
    public static final String USER_UPDATE_URL = "https://api.bmob.cn/1/users/%s";
    public static final String USER_RESET_PASSWORD = "https://api.bmob.cn/1/requestEmailVerify";


    private volatile static UserModel instance;

    public static UserModel getInstance() {
        if (instance == null) {
            synchronized (UserModel.class) {
                instance = new UserModel();
            }
        }
        return instance;
    }

    private boolean loginStatus = false;

    public void register(Context context, JSONObject jsonUser, Response.Listener listener, Response.ErrorListener errorListener) {
        RestHttpClient.getInstance(context).post(USER_URL, jsonUser, listener, errorListener);
    }

    public void login(Context context, String username, String password, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        String url = String.format(USER_LOGIN_URL, username, password);
        RestHttpClient.getInstance(context).get(url, listener, errorListener);
    }

    public void update(Context context, JSONObject body, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        String objectId = getUserProperty(context, "objectId");
        String sessionToken = getUserProperty(context, "sessionToken");
        String url = String.format(USER_UPDATE_URL, objectId);

        RestHttpClient.getInstance(context).put(sessionToken, url, body, listener, errorListener);
    }

    public void resetPassword(Context context, JSONObject body, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        RestHttpClient.getInstance(context).post(USER_RESET_PASSWORD, body, listener, errorListener);
    }



    public void saveUserProfile(Context context, JSONObject jsonObject) {
        SharedPreferences sp = context.getSharedPreferences(SP_USER_PROFILE, Context.MODE_PRIVATE);
        if (jsonObject != null) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("objectId", jsonObject.optString("objectId"));
            editor.putString("username", jsonObject.optString("username"));
            editor.putString("password", jsonObject.optString("password"));
            editor.putString("sessionToken", jsonObject.optString("sessionToken"));
            editor.putString("sex", jsonObject.optString("sex"));
            editor.putString("job", jsonObject.optString("job"));
            editor.putString("range", jsonObject.optString("range"));
            editor.putString("intergral", jsonObject.optString("intergral"));
            editor.putString("wechat", jsonObject.optString("wechat"));
            editor.putString("signature", jsonObject.optString("signature"));
            editor.putString("age", jsonObject.optString("age"));
            editor.putString("email", jsonObject.optString("email"));
            editor.putString("mobilePhoneNumber", jsonObject.optString("mobilePhoneNumber"));
            editor.putString("address", jsonObject.optString("address"));
            editor.putBoolean("loginstate",true);
            editor.commit();
        }
    }


    public JSONObject getLocalUserProfile(Context context) {
        JSONObject jsonObject = null;
        SharedPreferences sp = context.getSharedPreferences(SP_USER_PROFILE, Context.MODE_PRIVATE);
        try {
            jsonObject = new JSONObject();
            jsonObject.put("objectId", sp.getString("objectId", ""));
            jsonObject.put("username", sp.getString("username", "张三"));
            jsonObject.put("sessionToken", sp.getString("sessionToken", ""));

            jsonObject.put("mobilePhoneNumber", sp.getString("mobilePhoneNumber", "15612345678"));
            jsonObject.put("email", sp.getString("email", "abc@123.com"));
            jsonObject.put("wechat", sp.getString("wechat", "wechat001"));
            jsonObject.put("sex", sp.getString("sex", "男"));
            jsonObject.put("age", sp.getString("age", "0"));
            jsonObject.put("job", sp.getString("job", "程序员"));
            jsonObject.put("range", sp.getString("range", "0"));
            jsonObject.put("intergral", sp.getString("intergral", "0"));
            jsonObject.put("address", sp.getString("address", "广州"));
            jsonObject.put("signature", sp.getString("signature", "海阔凭鱼跃，天高任鸟飞"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;

    }


    public String getUserProperty(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(SP_USER_PROFILE, Context.MODE_PRIVATE);
        return sp.getString(key, null);
    }

    public boolean updateUserProperty(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(SP_USER_PROFILE, Context.MODE_PRIVATE);
        return sp.edit().putString(key, value).commit();
    }

    public boolean getLoginStatus(){
        return loginStatus;
    }

    public void setLoginStatus(boolean loginStatus){
        this.loginStatus = loginStatus;
    }


    public void saveLoginState(Context context, boolean state) {
        SharedPreferences sp = context.getSharedPreferences(SP_USER_PROFILE, Context.MODE_PRIVATE);
        sp.edit().putBoolean("loginstate", state).commit();
    }

    public static boolean getLoginState(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SP_USER_PROFILE, Context.MODE_PRIVATE);
        return sp.getBoolean("loginstate", false);
    }


}
