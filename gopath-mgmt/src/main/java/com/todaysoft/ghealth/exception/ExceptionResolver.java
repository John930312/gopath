package com.todaysoft.ghealth.exception;

import com.todaysoft.ghealth.service.IMessageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: xjw
 * @Date: 2018/7/3 9:14
 */
@Component
public class ExceptionResolver extends SimpleMappingExceptionResolver
{
    @Autowired
    private IMessageService messageService;
    
    @Override
    public ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object object, Exception ex)
    {
        ModelAndView mv = new ModelAndView("error");
        if (ex instanceof ServiceException)
        {
            ServiceException exception = (ServiceException)ex;
            mv.getModelMap().addAttribute("error_code", exception.getErrorCode());

            String errorMessage;
            if (StringUtils.isEmpty(exception.getMessage()))
            {
                errorMessage = messageService.getMessage(exception.getErrorCode());
            }
            else
            {
                errorMessage = exception.getMessage();
            }
            mv.getModelMap().addAttribute("error_message", errorMessage);
        }
        return mv;
    }
}
