package com.kerwin.spring.mycrm.permision.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 获取上下文的bean
 *
 * @className: ApplicationContextHelper
 * @version: v1.0.0
 * @author: d.w
 * @date: 2019-09-10 10:15
 */
public class ApplicationContextHelper implements ApplicationContextAware
{
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException
    {
        applicationContext = context;
    }

    /**
     * 功能描述:
     * 〈通过类型获取bean〉
     *
     * @param clazz 类型
     * @return : T
     * @author : d.w
     * @date : 2019/09/10 10:20
     */
    public static <T> T popBean(Class<T> clazz)
    {
        if (applicationContext == null)
        {
            return null;
        }
        return applicationContext.getBean(clazz);
    }

    /**
     * 功能描述:
     * 〈通过名称和类型获取bean〉
     *
     * @param name  bean name
     * @param clazz bean class
     * @return : T
     * @author : d.w
     * @date : 2019/09/10 10:22
     */
    public static <T> T popBean(String name, Class<T> clazz)
    {
        if (applicationContext == null)
        {
            return null;
        }
        return applicationContext.getBean(name, clazz);
    }

}
