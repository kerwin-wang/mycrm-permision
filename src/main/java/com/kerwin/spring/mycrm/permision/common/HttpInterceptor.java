package com.kerwin.spring.mycrm.permision.common;

import com.kerwin.spring.mycrm.permision.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @className: HttpInterceptor
 * @version: v1.0.0
 * @author: d.w
 * @date: 2019-09-10 11:04
 */
@Slf4j
public class HttpInterceptor extends HandlerInterceptorAdapter
{
    private static final String START_TIME = "startTime";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        String url = request.getRequestURL().toString();
        String clientAddr = request.getRemoteAddr();
        Map paramMap = request.getParameterMap();
        long startTime = System.currentTimeMillis();
        request.setAttribute(START_TIME,startTime);
        log.info("request start. url->{}, param->{},clientAddr->{}", url, JsonMapper.obj2String(paramMap),clientAddr);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
    {
        String url = request.getRequestURL().toString();
        Map paramMap = request.getParameterMap();
        long startTime = (long) request.getAttribute(START_TIME);
        long endTime = System.currentTimeMillis();
        log.info("request finished. url->{}, param->{}, time->{}", url, JsonMapper.obj2String(paramMap),endTime-startTime);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
    {
        String url = request.getRequestURL().toString();
        long startTime = (long) request.getAttribute(START_TIME);
        long endTime = System.currentTimeMillis();
        log.info("request complete. url->{}, time->{}", url,endTime-startTime);
    }

}
