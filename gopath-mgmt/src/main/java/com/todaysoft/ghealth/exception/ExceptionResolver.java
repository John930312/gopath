package com.todaysoft.ghealth.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @Author: xjw
 * @Date: 2018/7/3 9:14
 */
@Component
public class ExceptionResolver extends SimpleMappingExceptionResolver
{
    @Autowired
    private LocaleResolver localeResolver;
    
    @Override
    public ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception)
    {
        // 区域解析器
        localeResolver.setLocale(request, response, Locale.CHINA);
        ModelAndView mv = new ModelAndView("/error");
        String base = request.getContextPath();
        mv.getModelMap().addAttribute("exception", exception);
        mv.getModelMap().addAttribute("base", base);
        mv.getModelMap().addAttribute("plugins", base + "/static/plugins");
        mv.getModelMap().addAttribute("system_js", base + "/static/system/js");
        mv.getModelMap().addAttribute("system_css", base + "/static/system/css");

        if (exception instanceof ServiceException)
        {
            ServiceException serviceException = (ServiceException)exception;
            // String message = messageService.getMessage(serviceException.getErrorCode());
            mv.getModelMap().addAttribute("error_code", serviceException.getErrorCode());
        }
        return mv;
    }
}
