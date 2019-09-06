package com.kerwin.spring.mycrm.permision.common;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @className: MycrmExceptionResolver
 * @version: v1.0.0
 * @author: d.w
 * @date: 2019-09-06 16:31
 */
public class MycrmExceptionResolver implements HandlerExceptionResolver
{
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
            Object handler, Exception ex)
    {
        return null;
    }

}
