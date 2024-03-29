package com.todaysoft.ghealth.config.core;


import com.todaysoft.ghealth.form.FormInputView;
import com.todaysoft.ghealth.form.FormSubmitHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.UUID;

@Component
public class GlobalAttributesInterceptor extends HandlerInterceptorAdapter
{
    private static final String TOKEN_NAMESPACE = "com.todaysoft.ghealth";
    
    private static final String TOKEN_NAME_FIELD = "REPEAT_SUBMIT_TOKEN";
    
    @Autowired
    private GlobalAttributesProvider attributesProvider;
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
    {
        if (null == modelAndView)
        {
            return;
        }
        
        ModelMap attributes = attributesProvider.provide(request);
        modelAndView.getModelMap().addAllAttributes(attributes);
    }
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        HandlerMethod method = (HandlerMethod)handler;
        
        if (isFormInputView(method))
        {
            String token = UUID.randomUUID().toString();
            String tokenName = TOKEN_NAMESPACE + "_" + token;
            request.setAttribute(TOKEN_NAME_FIELD, tokenName);
            request.setAttribute(tokenName, token);
            request.getSession().setAttribute(tokenName, token);
            return true;
        }
        else if (isFormSubmitHandler(method))
        {
            String tokenName = getFormSubmitTokenName(request);
            
            if (StringUtils.isEmpty(tokenName))
            {
                return false;
            }
            
            String token = request.getParameter(tokenName);
            String stoken = (String)request.getSession().getAttribute(tokenName);
            request.getSession().removeAttribute(tokenName);
            
            if (StringUtils.isEmpty(token) || StringUtils.isEmpty(stoken) || !token.equals(stoken))
            {
                return false;
            }
            
            return true;
        }
        else
        {
            return true;
        }
    }
    
    private boolean isFormInputView(HandlerMethod method)
    {
        return method.getMethodAnnotation(FormInputView.class) != null;
    }
    
    private boolean isFormSubmitHandler(HandlerMethod method)
    {
        return method.getMethodAnnotation(FormSubmitHandler.class) != null;
    }
    
    private String getFormSubmitTokenName(HttpServletRequest request)
    {
        Enumeration<String> names = request.getParameterNames();
        
        String name;
        
        while (names.hasMoreElements())
        {
            name = names.nextElement();
            
            if (name.startsWith(TOKEN_NAMESPACE))
            {
                return name;
            }
        }
        
        return null;
    }
}
