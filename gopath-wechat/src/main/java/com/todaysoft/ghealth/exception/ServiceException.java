package com.todaysoft.ghealth.exception;

/**
 * @Author: xjw
 * @Date: 2018/7/3 9:02
 */
public class ServiceException extends RuntimeException
{
    private String errorCode;
    
    public ServiceException(String errorCode)
    {
        this.errorCode = errorCode;
    }

    public ServiceException(String error, String message)
    {
        super(message);
        this.errorCode = error;
    }
    
    public String getErrorCode()
    {
        return errorCode;
    }
    
    public void setErrorCode(String errorCode)
    {
        this.errorCode = errorCode;
    }
}
