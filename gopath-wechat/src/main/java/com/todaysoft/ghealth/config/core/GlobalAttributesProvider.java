package com.todaysoft.ghealth.config.core;


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
        model.addAttribute("plugins", base + "/templates/static/plugins");
        model.addAttribute("system_js", base + "/templates/static/system/js");
        model.addAttribute("system_css", base + "/templates/static/system/css");
        model.addAttribute("system_images", base + "/templates/static/system/images");

        return model;
    }
}
