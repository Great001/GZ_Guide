package com.lhc.android.gz_guide.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/24.
 */
public class RecommendGood {

    private String title;
    private String desc;
    private String imgUrl;
    private int imgRes;
    private String rating;
    private boolean isPhiase;

    private String contentLink;

    public RecommendGood(){
        super();
    }

    public RecommendGood(String desc, int imgRes, String imgUrl, boolean isPhiase, String rating, String title) {
        this.desc = desc;
        this.imgRes = imgRes;
        this.imgUrl = imgUrl;
        this.isPhiase = isPhiase;
        this.rating = rating;
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public boolean isPhiase() {
        return isPhiase;
    }

    public void setPhiase(boolean phiase) {
        isPhiase = phiase;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }

    public String getContentLink() {
        return contentLink;
    }

    public void setContentLink(String contentLink) {
        this.contentLink = contentLink;
    }

    public static List<RecommendGood> getRecommendGoods(JSONObject jsonObject){
        List<RecommendGood> goods = new ArrayList<>();
        try {
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            int len = jsonArray.length();
            for(int i = 0;i < len ;i++){
                JSONObject json = jsonArray.getJSONObject(i);
                RecommendGood good = new RecommendGood();
                good.setPhiase(json.optBoolean("isPhraise"));
                good.setTitle(json.optString("title"));
                good.setDesc(json.optString("desc"));
                good.setRating(json.optString("rating"));
                good.setImgUrl(json.optString("imgUrl"));
                good.setContentLink(json.optString("contentLink"));
                goods.add(good);
            }

        }catch(JSONException e){
            e.printStackTrace();
        }

        return goods;
    }

}
