package com.lhc.android.gz_guide.model;

import com.baidu.mapapi.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/26.
 */
public class Spot {

    private String name;
    private String desc;
    private String address;
    private String ticketPrice;
    private int imgResId;
    private String imgUrl;
    private String rating;
    private String type;
    private LatLng location;
    private String contentLink;

    public Spot(){}



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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(String ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    public String getContentLink() {
        return contentLink;
    }

    public void setContentLink(String contentLink) {
        this.contentLink = contentLink;
    }

    public static List<Spot> getSpotList(JSONObject json){
        List<Spot> list = new ArrayList<>();
        if(json != null){
            try {
                JSONArray jsonArray = json.getJSONArray("results");
                int len = jsonArray.length();
                for(int i = 0;i<len;i++){
                    Spot data = new Spot();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    data.setName(jsonObject.optString("name"));
                    data.setDesc(jsonObject.optString("desc"));
                    data.setRating(jsonObject.optString("rating"));
                    data.setAddress(jsonObject.optString("address"));
                    data.setImgUrl(jsonObject.optString("imgUrl"));
                    data.setType(jsonObject.optString("type"));
                    data.setTicketPrice(jsonObject.optString("ticketPrice"));
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
