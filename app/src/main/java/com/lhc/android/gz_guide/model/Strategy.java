package com.lhc.android.gz_guide.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/26.
 */
public class Strategy {

    private String title;
    private String desc;
    private int strId;
    private String imgUrl;
    private int imgResId;
    private int readCount;
    private int commentCount;

    public int getCommentCount() {

        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
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

    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

    public int getStrId() {
        return strId;
    }

    public void setStrId(int strId) {
        this.strId = strId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }




    public static List<Strategy> getStrategyList(JSONObject json){
        List<Strategy> list = new ArrayList<>();
        if(json != null){
            try {
                JSONArray jsonArray = json.getJSONArray("results");
                int len = jsonArray.length();
                for(int i = 0;i<len;i++){
                    Strategy data = new Strategy();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    data.setTitle(jsonObject.optString("title"));
                    data.setDesc(jsonObject.optString("desc"));
                    data.setReadCount(jsonObject.optInt("readCount"));
                    data.setImgUrl(jsonObject.optString("imgUrl"));
                    data.setCommentCount(jsonObject.optInt("commentCount"));
                    list.add(data);
                }


            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return list;
    }

}
