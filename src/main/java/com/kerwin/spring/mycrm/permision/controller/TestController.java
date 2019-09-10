package com.kerwin.spring.mycrm.permision.controller;

import com.kerwin.spring.mycrm.permision.exception.PermissionException;
import com.kerwin.spring.mycrm.permision.util.BeanValidator;
import com.kerwin.spring.mycrm.permision.util.JsonData;
import com.kerwin.spring.mycrm.permision.vo.TestVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @className: TestController
 * @version: v1.0.0
 * @author: d.w
 * @date: 2019-09-04 17:19
 */
@Slf4j
@Controller
@RequestMapping("/test")
public class TestController
{
    /**
     * 功能描述:
     * 〈测试controller〉
     *
     * @param id 参数id
     * @return : java.lang.String
     * @author : d.w
     * @date : 2019/09/05 11:50
     */
    @ResponseBody
    @RequestMapping(value = "/hello.json",method = RequestMethod.GET)
    public JsonData hello(String id){
        log.info("hello");
        throw new PermissionException("test Exception");
//        return JsonData.success("hello.permission " + id);
    }

    @RequestMapping(value = "/hello.page",method = RequestMethod.GET)
    public String hello(){
        log.info("hello");
        throw new RuntimeException("test page exception");
    }

    @ResponseBody
    @RequestMapping(value = "/validate.json",method = RequestMethod.GET)
    public JsonData validate(TestVO vo){
        log.info("hello");
        try
        {
            Map<String, String> validate = BeanValidator.validate(vo);
            if (MapUtils.isNotEmpty(validate)){
                for (Map.Entry<String, String> entry : validate.entrySet())
                {
                    log.info("{}->{}",entry.getKey(),entry.getValue());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return JsonData.success("test validate");
    }
}
