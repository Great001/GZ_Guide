package com.lhc.android.gz_guide.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/25.
 */
public class Hotel {

    private int id;
    private String name;
    private String desc;
    private String leastPrice;
    private String rating;
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
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


    public static List<Hotel> getHotelList(JSONObject json){
        List<Hotel> list = new ArrayList<>();
        if(json != null){
            try {
                JSONArray jsonArray = json.getJSONArray("results");
                int len = jsonArray.length();
                for(int i = 0;i<len;i++){
                    Hotel data = new Hotel();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    data.setName(jsonObject.optString("name"));
                    data.setDesc(jsonObject.optString("desc"));
                    data.setAvailable(jsonObject.optBoolean("isAvailable"));
                    data.setRating(jsonObject.optString("rating"));
                    data.setLeastPrice(jsonObject.optString("leastPrice"));
                    data.setAddress(jsonObject.optString("address"));
                    data.setImgUrl(jsonObject.optString("imgUrl"));
                    data.setType(jsonObject.optString("type"));
                    list.add(data);
                }


            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return list;
    }

}
