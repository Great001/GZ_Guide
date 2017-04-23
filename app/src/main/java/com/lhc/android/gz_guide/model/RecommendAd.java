package com.lhc.android.gz_guide.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/9.
 */
public class RecommendAd implements Serializable{

    private String imgUrl;
    private String title;
    private String desc;
    private String openTime;
    private String price;
    private String address;
    private String route;
    private String [] pics;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getPics() {
        return pics;
    }

    public void setPics(String[] pics) {
        this.pics = pics;
    }

    public static List<RecommendAd> getRecommendAdList(JSONObject json){
        List<RecommendAd> list = new ArrayList<>();
        if(json != null){
            try {
                JSONArray jsonArray = json.getJSONArray("results");
                int len = jsonArray.length();
                for(int i = 0;i<len;i++){
                    RecommendAd data = new RecommendAd();
                    JSONObject jsonObject =jsonArray.getJSONObject(i);
                    data.setImgUrl(jsonObject.optString("imgUrl"));
                    data.setTitle(jsonObject.optString("title"));
                    data.setDesc(jsonObject.optString("desc"));
                    data.setOpenTime(jsonObject.optString("openTime"));
                    data.setAddress(jsonObject.optString("address"));
                    data.setRoute(jsonObject.optString("route"));
                    data.setPrice(jsonObject.optString("price"));

                    JSONArray picArray = jsonObject.optJSONArray("pics");
                    if(picArray != null) {
                        int picLen = picArray.length();
                        String[] str = new String[picLen];
                        for (int j = 0; j < picLen;j++){
                            str[j] = jsonArray.getString(j);
                        }
                        data.setPics(str);
                    }
                    list.add(data);
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return list;
    }

}
