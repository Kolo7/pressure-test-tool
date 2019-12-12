package com.xgsdk.sdkserver.antiaddiction.info.enums;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializable;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.util.TypeUtils;

import java.io.IOException;
import java.lang.reflect.Type;

public enum UserEnum{
    TOURIST("TOURIST"),USER("USER");
    public String userEnum;
    UserEnum(String userType){
        this.userEnum = userType;
    }

    public String getUserType() {
        return userEnum;
    }

    public void setUserType(String userType) {
        this.userEnum = userType;
    }
    public static UserEnum convert(String userEnum){
        UserEnum[] enums = UserEnum.values();
        for(UserEnum e : enums){
            if(e.userEnum.equals(userEnum))
                return e;
        }
        return null;
    }
}
