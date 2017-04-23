package com.lhc.android.gz_guide.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/25.
 */
public class TastyFood {

    private String name;
    private String price;
    private int imgResId;
    private String imgUrl;
    private String desc;
    private String rating;
    private String contentLink;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getImgResId() {
        return imgResId;
    }

    public void setImgResId(int imgResId) {
        this.imgResId = imgResId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getContentLink() {
        return contentLink;
    }

    public void setContentLink(String contentLink) {
        this.contentLink = contentLink;
    }

    public static List<TastyFood> getTastyList(JSONObject json){
        List<TastyFood> list = new ArrayList<>();
        if(json != null){
            try {
                JSONArray jsonArray = json.getJSONArray("results");
                int len = jsonArray.length();
                for(int i = 0;i<len;i++){
                    TastyFood data = new TastyFood();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    data.setName(jsonObject.optString("name"));
                    data.setDesc(jsonObject.optString("desc"));
                    data.setRating(jsonObject.optString("rating"));
                    data.setImgUrl(jsonObject.optString("imgUrl"));
                    data.setPrice(jsonObject.optString("price"));
                    data.setContentLink(jsonObject.optString("contentLink"));
                    list.add(data);
                }


            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return list;
    }


}
