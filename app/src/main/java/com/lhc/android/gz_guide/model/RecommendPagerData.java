package com.lhc.android.gz_guide.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/9.
 */
public class RecommendPagerData {

    private String imgUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public static List<RecommendPagerData> getRecommendPageDataList(JSONObject json){
        List<RecommendPagerData> list = new ArrayList<>();
        if(json != null){
            try {
                JSONArray jsonArray = json.getJSONArray("results");
                int len = jsonArray.length();
                for(int i = 0;i<len;i++){
                    RecommendPagerData data = new RecommendPagerData();
                    data.setImgUrl(jsonArray.getJSONObject(i).optString("imgUrl"));
                    list.add(data);
                }


            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return list;
    }

}
