package com.lhc.android.gz_guide.model;

/**
 * Created by Administrator on 2017/3/25.
 */
public class Hotel {

    private int id;
    private String name;
    private String desc;
    private String leastPrice;
    private float rating;
    private boolean isAvailable;
    private String imgUrl;
    private int imgResId;
    private String type;
    private String address;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getLeastPrice() {
        return leastPrice;
    }

    public void setLeastPrice(String leastPrice) {
        this.leastPrice = leastPrice;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "address='" + address + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", leastPrice='" + leastPrice + '\'' +
                ", rating=" + rating +
                ", isAvailable=" + isAvailable +
                ", imgUrl='" + imgUrl + '\'' +
                ", imgResId='" + imgResId + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
