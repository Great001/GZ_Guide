package com.lhc.android.gz_guide.model;

/**
 * Created by Administrator on 2017/3/26.
 */
public class Spot {

    private String name;
    private String desc;
    private String address;
    private float ticketPrice;
    private int imgResId;
    private String imgUrl;
    private float rating;
    private String type;

    public Spot(){}


    public Spot(String address, String desc, int imgResId, String imgUrl, String name, float rating, float ticketPrice, String type) {
        this.address = address;
        this.desc = desc;
        this.imgResId = imgResId;
        this.imgUrl = imgUrl;
        this.name = name;
        this.rating = rating;
        this.ticketPrice = ticketPrice;
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

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public float getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(float ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
