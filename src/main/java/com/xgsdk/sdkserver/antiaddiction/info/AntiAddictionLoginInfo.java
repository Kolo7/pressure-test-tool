package com.xgsdk.sdkserver.antiaddiction.info;

import com.alibaba.fastjson.annotation.JSONField;
import com.xgsdk.sdkserver.antiaddiction.info.enums.CertificationDeserializer;
import com.xgsdk.sdkserver.antiaddiction.info.enums.CertificationEnum;
import com.xgsdk.sdkserver.antiaddiction.info.enums.UserEnum;
import com.xgsdk.sdkserver.antiaddiction.info.enums.UserEnumDeserializer;

public class AntiAddictionLoginInfo extends AntiAddictionLogoutInfo{
    private String platform;
    private String deviceId;
    @JSONField(deserializeUsing = UserEnumDeserializer.class)
    private UserEnum userEnum;
    @JSONField( deserializeUsing = CertificationDeserializer.class)
    private CertificationEnum certificationEnum;

    public AntiAddictionLoginInfo(String xgAppId, String day, String uid, long timestamp, String platform, String deviceId, UserEnum userEnum, CertificationEnum certificationEnum) {
        super(xgAppId, day, uid, timestamp);
        this.platform = platform;
        this.deviceId = deviceId;
        this.userEnum = userEnum;
        this.certificationEnum = certificationEnum;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public UserEnum getUserEnum() {
        return userEnum;
    }

    public void setUserEnum(UserEnum userEnum) {
        this.userEnum = userEnum;
    }

    public CertificationEnum getCertificationEnum() {
        return certificationEnum;
    }

    public void setCertificationEnum(CertificationEnum certificationEnum) {
        this.certificationEnum = certificationEnum;
    }
}
