package com.todaysoft.ghealth.config.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class GlobalAttributesInterceptor extends HandlerInterceptorAdapter
{
    @Autowired
    private GlobalAttributesProvider attributesProvider;
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
        throws Exception
    {
        if (null == modelAndView)
        {
            return;
        }
        
        ModelMap attributes = attributesProvider.provide(request);
        modelAndView.getModelMap().addAllAttributes(attributes);
    }
}
