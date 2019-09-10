package com.kerwin.spring.mycrm.permision.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Jackson转换工具
 *
 * @className: JsonMapper
 * @version: v1.0.0
 * @author: d.w
 * @date: 2019-09-10 9:46
 */
@Slf4j
public class JsonMapper
{
    private static ObjectMapper objectMapper = new ObjectMapper();

    static
    {
        // 反序列化的时候如果多了其他属性,不抛出异常
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 如果是空对象的时候,不抛异常
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // 序列化的时候序列对象的所有非空属性
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    }

    /**
     * 功能描述:
     * 〈将json对象转换为string〉
     *
     * @param t 带转换的对象
     * @return : java.lang.String
     * @author : d.w
     * @date : 2019/09/10 10:07
     */
    public static <T> String obj2String(T t)
    {
        if (t == null)
        {
            return null;
        }
        try
        {
            return t instanceof String ? (String) t : objectMapper.writeValueAsString(t);
        }
        catch (JsonProcessingException e)
        {
            log.warn("json object parse to string error : error->{}", e);
            return null;
        }
    }

    /**
     * 功能描述:
     * 〈将string转换为json对象〉
     *
     * @param s         带转换字符串
     * @param reference 目标类型
     * @return : T
     * @author : d.w
     * @date : 2019/09/10 10:07
     */
    @SuppressWarnings("unchecked")
    public static <T> T string2Obj(String s, TypeReference<T> reference)
    {
        if (s == null)
        {
            return null;
        }
        try
        {
            return (T) (reference.getType().equals(String.class) ? s : objectMapper.readValue(s, reference));
        }
        catch (IOException e)
        {
            log.warn("parse string to object error, string->{},typeReference->{},error->{}", s, reference.getType(), e);
            return null;
        }
    }

}
