package com.kerwin.spring.mycrm.permision.util;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Json数据处理类
 *
 * @className: JsonData
 * @version: v1.0.0
 * @author: d.w
 * @date: 2019-09-06 14:30
 */
@Setter
@Getter
public class JsonData
{
    /** 响应状态 */
    private boolean ret;

    /** 响应信息 */
    private String msg;

    /** 响应数据 */
    private Object data;

    public JsonData(boolean ret)
    {
        this.ret = ret;
    }

    /**
     * 功能描述:
     * 〈成功〉
     *
     * @param
     * @return : com.kerwin.spring.mycrm.permision.util.JsonData
     * @author : d.w
     * @date : 2019/09/06 14:37
     */
    public static JsonData success()
    {
        return new JsonData(true);
    }

    /**
     * 功能描述:
     * 〈带响应信息的成功〉
     *
     * @param msg 信息
     * @return : com.kerwin.spring.mycrm.permision.util.JsonData
     * @author : d.w
     * @date : 2019/09/06 14:37
     */
    public static JsonData success(String msg)
    {
        JsonData jsonData = new JsonData(true);
        jsonData.setMsg(msg);
        return jsonData;
    }

    /**
     * 功能描述:
     * 〈带响应信息和相应数据的成功〉
     *
     * @param msg 信息
     * @param obj 数据
     * @return : com.kerwin.spring.mycrm.permision.util.JsonData
     * @author : d.w
     * @date : 2019/09/06 14:37
     */
    public static JsonData success(String msg, Object obj)
    {
        JsonData jsonData = new JsonData(true);
        jsonData.setMsg(msg);
        jsonData.setData(obj);
        return jsonData;
    }

    /**
     * 功能描述:
     * 〈失败〉
     *
     * @param
     * @return : com.kerwin.spring.mycrm.permision.util.JsonData
     * @author : d.w
     * @date : 2019/09/06 14:38
     */
    public static JsonData failure()
    {
        return new JsonData(false);
    }

    /**
     * 功能描述:
     * 〈带响应信息的失败〉
     *
     * @param msg 信息
     * @return : com.kerwin.spring.mycrm.permision.util.JsonData
     * @author : d.w
     * @date : 2019/09/06 14:38
     */
    public static JsonData failure(String msg)
    {
        JsonData jsonData = new JsonData(false);
        jsonData.setMsg(msg);
        return jsonData;
    }

    /**
     * 功能描述:
     * 〈将jsonData转换为map〉
     *
     * @param
     * @return : java.util.Map<java.lang.String,java.lang.Object>
     * @author : d.w
     * @date : 2019/09/09 14:04
     */
    public Map<String, Object> toMap()
    {
        Map<String, Object> result = Maps.newHashMap();
        result.put("ret", ret);
        result.put("msg", msg);
        result.put("data", data);
        return result;
    }

}
