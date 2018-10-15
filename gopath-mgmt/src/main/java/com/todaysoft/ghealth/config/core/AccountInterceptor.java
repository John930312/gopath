package com.todaysoft.ghealth.config.core;


import com.todaysoft.ghealth.security.AccountDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AccountInterceptor extends HandlerInterceptorAdapter
{
    @Autowired
    private AccountHolder accountHolder;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (null != authentication)
        {
            Object principal = authentication.getPrincipal();
            
            if (principal instanceof AccountDetails)
            {
                AccountDetails details = (AccountDetails)principal;
                accountHolder.set(details);
            }
        }
        
        return super.preHandle(request, response, handler);
    }
}
