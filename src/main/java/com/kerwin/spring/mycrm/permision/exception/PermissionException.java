package com.kerwin.spring.mycrm.permision.exception;

/**
 * @className: PermissionException
 * @version: v1.0.0
 * @author: d.w
 * @date: 2019-09-09 13:47
 */
public class PermissionException extends RuntimeException
{
    public PermissionException()
    {
        super();
    }

    public PermissionException(String message)
    {
        super(message);
    }

    public PermissionException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public PermissionException(Throwable cause)
    {
        super(cause);
    }

    protected PermissionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
