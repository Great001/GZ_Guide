package com.lhc.android.gz_guide.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/26.
 */
public class LocalPartner {

    private int id;
    private String name;
    private String job;
    private int sex;
    private String tel;
    private String requirment;

    private int avatarResId;
    private String avatarUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRequirment() {
        return requirment;
    }

    public void setRequirment(String requirment) {
        this.requirment = requirment;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getAvatarResId() {
        return avatarResId;
    }

    public void setAvatarResId(int avatarResId) {
        this.avatarResId = avatarResId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public static List<LocalPartner> getLocalPartnerList(JSONObject json){
        List<LocalPartner> list = new ArrayList<>();
        if(json != null){
            try {
                JSONArray jsonArray = json.getJSONArray("results");
                int len = jsonArray.length();
                for(int i = 0;i<len;i++){
                    LocalPartner data = new LocalPartner();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    data.setName(jsonObject.optString("name"));
                    data.setTel(jsonObject.optString("tel"));
                    data.setAvatarUrl(jsonObject.optString("avatarUrl"));
                    data.setSex(jsonObject.optInt("sex"));
                    data.setJob(jsonObject.optString("job"));
                    data.setRequirment(jsonObject.optString("requirment"));
                    list.add(data);
                }


            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return list;
    }


}
