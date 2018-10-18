package com.todaysoft.ghealth.exception;

import com.todaysoft.ghealth.security.AccountDetails;
import com.todaysoft.ghealth.service.IMessageService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
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
    private static Logger log = LoggerFactory.getLogger(ExceptionResolver.class);
    
    @Autowired
    private IMessageService messageService;
    
    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
    {
        ModelAndView mv = new ModelAndView("/error");
        ModelMap modelMap = mv.getModelMap();
        resolve(request, modelMap);
        
        if (ex instanceof ServiceException)
        {
            ServiceException exception = (ServiceException)ex;
            modelMap.addAttribute("error_code", exception.getErrorCode());
            
            String errorMessage = null;
            if (StringUtils.isEmpty(exception.getMessage()))
            {
                errorMessage = messageService.getMessage(exception.getErrorCode());
            }
            else
            {
                errorMessage = exception.getMessage();
            }
            modelMap.addAttribute("error_message", errorMessage);
        }
        
        modelMap.addAttribute("exception", ex);
        return mv;
    }
    
    @Override
    protected void logException(Exception ex, HttpServletRequest request)
    {
        if (ex instanceof ServiceException)
        {
            log.error("Error code " + ((ServiceException)ex).getErrorCode());
        }
        else
        {
            log.error(buildLogMessage(ex, request), ex);
        }
    }
    
    private void resolve(HttpServletRequest request, ModelMap model)
    {
        String base = request.getContextPath();
        model.addAttribute("base", base);
        model.addAttribute("plugins", base + "/static/plugins");
        model.addAttribute("system_js", base + "/static/system/js");
        model.addAttribute("system_css", base + "/static/system/css");
        model.addAttribute("system_images", base + "/static/system/images");
        
        // 当前登录账号
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (null != authentication)
        {
            Object principal = authentication.getPrincipal();
            
            if (principal instanceof AccountDetails)
            {
                AccountDetails account = (AccountDetails)principal;
                String name = org.springframework.util.StringUtils.isEmpty(account.getName()) ? account.getUsername() : account.getName();
                model.addAttribute("account_name", name);
            }
        }
    }
}
