package com.lhc.android.gz_guide.model;

/**
 * Created by Administrator on 2017/3/26.
 */
public class LocalGuide {

    private int id;
    private String name;
    private int sex;
    private int age;
    private int orderCount;
    private String signature;
    private String job;
    private int range;
    private int thumpResId;
    private String thumpUrl;
    private String briefInfo;
    private String tel;
    private String weChat;
    private String email;
    private boolean isVerified;

    public int getAge() {

        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

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

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public int getThumpResId() {
        return thumpResId;
    }

    public void setThumpResId(int thumpResId) {
        this.thumpResId = thumpResId;
    }

    public String getThumpUrl() {
        return thumpUrl;
    }

    public void setThumpUrl(String thumpUrl) {
        this.thumpUrl = thumpUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getWeChat() {
        return weChat;
    }

    public void setWeChat(String weChat) {
        this.weChat = weChat;
    }

    public String getBriefInfo() {
        return briefInfo;
    }

    public void setBriefInfo(String briefInfo) {
        this.briefInfo = briefInfo;
    }
}
