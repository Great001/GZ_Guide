package com.lhc.android.gz_guide.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/23.
 */
public class GzHistory {

    String title;
    String desc;
    String imgUrl;
    String contentLink;

    public String getContentLink() {
        return contentLink;
    }

    public void setContentLink(String contentLink) {
        this.contentLink = contentLink;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static  List<GzHistory> getHistoryList(JSONObject jsonObject) {
        List<GzHistory> gzHistoryList = new ArrayList<>();
        if (jsonObject != null) {
            try {
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                int len = jsonArray.length();
                for (int i = 0; i < len; i++) {
                    GzHistory history = new GzHistory();
                    JSONObject json = jsonArray.getJSONObject(i);
                    history.setTitle(json.optString("title"));
                    history.setDesc(json.optString("desc"));
                    history.setImgUrl(json.optString("imgUrl"));
                    history.setContentLink(json.optString("contentLink"));
                    gzHistoryList.add(history);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return gzHistoryList;
    }

}
