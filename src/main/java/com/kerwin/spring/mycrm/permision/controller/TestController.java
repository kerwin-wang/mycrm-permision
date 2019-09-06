package com.kerwin.spring.mycrm.permision.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(String id){
        log.info("hello");
        return "hello.permission " + id;
    }
}
