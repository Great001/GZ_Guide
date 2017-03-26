package com.lhc.android.gz_guide.model;

/**
 * Created by Administrator on 2017/3/24.
 */
public class RecommendGood {

    private String title;
    private String desc;
    private String imgUrl;
    private int imgRes;
    private float rating;
    private boolean isPhiase;

    public RecommendGood(String desc, int imgRes, String imgUrl, boolean isPhiase, float rating, String title) {
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

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
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
}
