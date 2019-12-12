package com.xgsdk.sdkserver.antiaddiction.info.enums;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializable;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.util.TypeUtils;

import java.io.IOException;
import java.lang.reflect.Type;

public enum CertificationEnum  {
    LESS_EIGHT("LESS_EIGHT"),
    EIGHT_SIXTEEN("EIGHT_SIXTEEN"),
    SIXTEEN_EIGHTEEN("SIXTEEN_EIGHTEEN"),
    LARGE_EIGHTEEN("LARGE_EIGHTEEN");
    public String certificationEnum;
    CertificationEnum(String ageGroup){
        this.certificationEnum = ageGroup;
    }

    public String getAgeGroup() {
        return certificationEnum;
    }

    public void setAgeGroup(String ageGroup) {
        this.certificationEnum = ageGroup;
    }

    public static CertificationEnum convert(String certificationEnum){
        CertificationEnum[] certificationEnums = CertificationEnum.values();
        for(CertificationEnum e : certificationEnums){
            if(e.certificationEnum.equals( certificationEnum))
                return e;
        }
        return null;
    }


}
