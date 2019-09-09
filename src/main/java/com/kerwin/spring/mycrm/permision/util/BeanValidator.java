package com.kerwin.spring.mycrm.permision.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kerwin.spring.mycrm.permision.exception.ParamException;
import org.apache.commons.collections.MapUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

/**
 * 自定义参数校验工具
 *
 * @className: BeanValidator
 * @version: v1.0.0
 * @author: d.w
 * @date: 2019-09-09 15:09
 */
public class BeanValidator
{
    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    @SuppressWarnings("unchecked")
    private static <T> Map<String, String> validate(T t, Class... group)
    {
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<T>> validates = validator.validate(t, group);
        if (validates.isEmpty())
        {
            return Collections.emptyMap();
        }
        else
        {
            LinkedHashMap errors = Maps.newLinkedHashMap();
            for (ConstraintViolation<T> violation : validates)
            {
                errors.put(violation.getPropertyPath().toString(), violation.getMessage());
            }
            return errors;
        }
    }

    @SuppressWarnings("unchecked")
    private static Map<String, String> validate(Collection<?> collection)
    {
        Preconditions.checkNotNull(collection);
        Iterator<?> iterator = collection.iterator();
        Map errors;
        do
        {
            if (!iterator.hasNext())
            {
                return Collections.emptyMap();
            }
            else
            {
                Object o = iterator.next();
                errors = validate(o, new Class[0]);
            }
        }
        while (errors.isEmpty());
        return errors;
    }

    /**
     * 功能描述:
     * 〈参数校验〉
     *
     * @param first   第一个对象
     * @param objects 其他对象
     * @return : java.util.Map<java.lang.String,java.lang.String>
     * @author : d.w
     * @date : 2019/09/09 15:59
     */
    public static Map<String, String> validate(Object first, Object... objects)
    {
        if (first != null && objects.length > 0)
        {
            return validate(Lists.asList(first, objects));
        }
        else
        {
            return validate(first, new Class[0]);
        }
    }

    public static void check(Object o) throws ParamException
    {
        Map<String, String> validate = BeanValidator.validate(o);
        if (MapUtils.isNotEmpty(validate))
        {
            throw new ParamException(validate.toString());
        }
    }

}
