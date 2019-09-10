package com.kerwin.spring.mycrm.permision.common;

import com.kerwin.spring.mycrm.permision.exception.PermissionException;
import com.kerwin.spring.mycrm.permision.util.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @className: MyCrmExceptionResolver
 * @version: v1.0.0
 * @author: d.w
 * @date: 2019-09-06 16:31
 */
@Slf4j
public class MyCrmExceptionResolver implements HandlerExceptionResolver
{
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
    {
        String url = request.getRequestURL().toString();
        ModelAndView mv;
        JsonData result;
        String defaultErrorMsg = "System error";
        String jsonErrorUrlPostfix = ".json";
        String pageErrorUrlPostfix = ".page";
        if (url.endsWith(jsonErrorUrlPostfix))
        {
            // 要求本项目所有json请求全部以json结尾
            if (ex instanceof PermissionException)
            {
                result = JsonData.failure(ex.getMessage());
                mv = new ModelAndView("jsonView", result.toMap());
            }
            else
            {
                log.error("unknown json Exception,url:"+url,ex);
                result = JsonData.failure(defaultErrorMsg);
                mv = new ModelAndView("jsonView", result.toMap());
            }
        }
        // 要求所有page请求全部以page结尾
        else if (url.endsWith(pageErrorUrlPostfix))
        {
            log.error("unknown page Exception,url:"+url,ex);
            result = JsonData.failure(defaultErrorMsg);
            mv = new ModelAndView("exception", result.toMap());
        }
        else
        {
            log.error("unknown Exception,url:"+url,ex);
            result = JsonData.failure(defaultErrorMsg);
            mv = new ModelAndView("jsonView", result.toMap());
        }
        return mv;
    }

}
