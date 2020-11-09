package com.example.demo.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author XiaoshanDu
 * @date 2019/2/10
 */
public class JsonUtils {

    public static Boolean isJsonString(String jsonStr) {
        if (StringUtils.isEmpty(jsonStr)) {
            return false;
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.readValue(jsonStr, Object.class);
        } catch (Exception e) {
            return false;
        }
        return true;

    }

    public static String toJsonString(Object obj) {
        String jsonStr;
        if (obj == null) {
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        try {
            jsonStr = mapper.writeValueAsString(obj);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        return jsonStr;
    }

    public static String toNonNullJsonString(Object obj) {
        String jsonStr;
        if (obj == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        try {
            jsonStr = mapper.writeValueAsString(obj);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        return jsonStr;
    }

    public static <T> T toJavaObject(String jsonStr, Class<T> clazz) {
        T obj;
        if (StringUtils.isEmpty(jsonStr)) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            obj = mapper.readValue(jsonStr, clazz);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        return obj;
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> convertJsonArrayToList(String jsonStr,Class clazz) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<T> list = new ArrayList<>();
        if(StringUtils.isEmpty(jsonStr)){
            return list;
        }
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, clazz);
            list =  mapper.readValue(jsonStr, javaType);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        return list;
    }


}
