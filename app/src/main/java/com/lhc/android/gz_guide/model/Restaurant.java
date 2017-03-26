package com.lhc.android.gz_guide.model;

/**
 * Created by Administrator on 2017/3/25.
 */
public class Restaurant {

    private String name;
    private String desc;
    private String type;
    private String state;
    private float rating;
    private String imgUrl;
    private String imgResId;
    private String tel;
    private String address;

    public Restaurant(String address, String desc, String imgResId, String imgUrl, String name, float rating, String state, String tel, String type) {
        this.address = address;
        this.desc = desc;
        this.imgResId = imgResId;
        this.imgUrl = imgUrl;
        this.name = name;
        this.rating = rating;
        this.state = state;
        this.tel = tel;
        this.type = type;
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

    public String getImgResId() {
        return imgResId;
    }

    public void setImgResId(String imgResId) {
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

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
