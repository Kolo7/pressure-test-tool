package com.xgsdk.sdkserver.antiaddiction.info.enums;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.TypeUtils;

import java.lang.reflect.Type;

public class UserEnumDeserializer  implements ObjectDeserializer {
    @Override
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object o) {
        Object value = defaultJSONParser.parse();
        return value == null ? null:(T)UserEnum.convert(TypeUtils.castToString(value));
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }
}
