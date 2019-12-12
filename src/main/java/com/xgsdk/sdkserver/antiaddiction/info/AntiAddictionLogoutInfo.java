package com.xgsdk.sdkserver.antiaddiction.info;

public class AntiAddictionLogoutInfo {
    private String xgAppId;
    private String day;
    private String uid;
    private long timestamp;

    public AntiAddictionLogoutInfo(String xgAppId, String day, String uid, long timestamp) {
        this.xgAppId = xgAppId;
        this.day = day;
        this.uid = uid;
        this.timestamp = timestamp;
    }

    public String getXgAppId() {
        return xgAppId;
    }

    public void setXgAppId(String xgAppId) {
        this.xgAppId = xgAppId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
