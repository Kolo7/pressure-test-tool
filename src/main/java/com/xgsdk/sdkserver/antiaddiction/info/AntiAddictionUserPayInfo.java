package com.xgsdk.sdkserver.antiaddiction.info;


import com.xgsdk.sdkserver.antiaddiction.info.enums.CertificationEnum;
import com.xgsdk.sdkserver.antiaddiction.info.enums.UserEnum;

public class AntiAddictionUserPayInfo extends AntiAddictionLoginInfo{
    private int paidAmount;

    public AntiAddictionUserPayInfo(String xgAppId, String day, String uid, long timestamp, String platform, String deviceId, UserEnum userEnum, CertificationEnum certificationEnum, int paidAmount) {
        super(xgAppId, day, uid, timestamp, platform, deviceId, userEnum, certificationEnum);
        this.paidAmount = paidAmount;
    }

    public int getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(int paidAmount) {
        this.paidAmount = paidAmount;
    }
}
