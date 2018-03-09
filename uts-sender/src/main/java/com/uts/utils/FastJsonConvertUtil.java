package com.uts.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;

/**
 * Created by Administrator on 2018/3/6 0006.
 */
public class FastJsonConvertUtil {
    private static final SerializerFeature[] featuresWithNullValue = { SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullBooleanAsFalse,
            SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullStringAsEmpty };


    public <T> T jsonToObject(String data, Class<T> clazz){
      T t=  JSON.parseObject(data ,clazz);
        return  t;
    }

    public <T> T jsonObjectToObject(JSONObject data ,Class<T> clazz){
     T t=   JSON.toJavaObject(data,clazz);
        return t;
    }
    public <T> List<T> jsontToList(String data,Class<T> clazz){
       List<T> list= JSON.parseArray(data,clazz);

        return list;
    }

    public static String ObjectToJson(Object o){
        String text = JSON.toJSONString(o);
        return text;
    }

    public static String convertObjectToJSONWithNullValue(Object o){
       String text= JSON.toJSONString(o,featuresWithNullValue);
        return text;
    }
}
