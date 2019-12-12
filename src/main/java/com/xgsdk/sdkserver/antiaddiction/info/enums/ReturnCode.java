package com.xgsdk.sdkserver.antiaddiction.info.enums;

public enum ReturnCode {
    SUCCESS(0,"成功"),
    NON_REAL_NAME(-4,"用户未实名认证，请先完成实名认证"),
    END_OF_EXPERIENCE(-5," 游客1小时体验时间到"),
    NO_REPETITION(-6,"同一设备，15天内不得重复体验游客模式"),
    OUT_OF_RANGE_TIME(-7,"未成年游戏时间到，每日22时到次日8时不提供游戏服务"),
    DAILY_CUMULATIVE_TIMEOUT_HOLIDAY(-8,"未成年游戏时间到，法定节假日每日累计不到超过3小时"),
    DAILY_CUMULATIVE_TIMEOUT_WORKDAY(-8,"未成年游戏时间到，工作日每日累计不得超过1.5小时"),
    CHILD_NO_RECHARGE(-12,"未满8周岁用户不能充值"),
    UPPER_LIMIT_OF_SINGLE_RECHARGE_LOW(-13,"8周岁以上未满16周岁未成年单次充值不得超过50元人民币"),
    UPPER_LIMIT_OF_SINGLE_RECHARGE_HIGH(-13,"16周岁以上未满18周岁未成年单次充值不得超过100元人民币"),
    MAXIMUM_MONTHLY_RECHARGE_LOW(-14,"8周岁以上未满16周岁未成年每月充值累计金额不得超过200人民币"),
    MAXIMUM_MONTHLY_RECHARGE_HIGH(-14,"16周岁以上未满18周岁未成年每月充值累计金额不得超过400人民币"),
    TOURIST_NO_RECHARGE(-15,"游客不能充值"),
    INNER_EXCEPTION(-99,"程序内部异常"),
    ;
    private final int value;
    private final String reasonPhrase;
    ReturnCode(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }
    public int value() {
        return this.value;
    }

    public String stringValue() {
        return Integer.toString(this.value);
    }
    public String getReasonPhrase() {
        return reasonPhrase;
    }

    @Override
    public String toString() {
        return value + " - " + reasonPhrase;
    }
}
