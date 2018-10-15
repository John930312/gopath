package com.todaysoft.ghealth.config.core;


import com.todaysoft.ghealth.security.AccountDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;

@Component
public class GlobalAttributesProvider
{
    public ModelMap provide(HttpServletRequest request)
    {
        ModelMap model = new ModelMap();
        String base = request.getContextPath();
        model.addAttribute("base", base);
        model.addAttribute("plugins", base + "/static/plugins");
        model.addAttribute("system_js", base + "/static/system/js");
        model.addAttribute("system_css", base + "/static/system/css");
        model.addAttribute("system_images", base + "/static/system/images");
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (null != authentication)
        {
            Object principal = authentication.getPrincipal();
            
            if (principal instanceof AccountDetails)
            {
                AccountDetails details = (AccountDetails)principal;
                model.addAttribute("account_details", details);
            }
        }
        
        return model;
    }
}
