package com.todaysoft.ghealth.freemarker;

import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Properties;

/**
 * @Author: xjw
 * @Date: 2018/8/16 15:51
 */

@Configuration
public class FreemarkerConfig
{
    @Autowired
    private freemarker.template.Configuration configuration;

    @Autowired
    private FindDictsByCategory findDictsByCategory;

    @Autowired
    private FindDictByCategoryAndValue findDictByCategoryAndValue;

    @Autowired
    private SecurityResourceDirectiveModel securityResourceDirectiveModel;

    @PostConstruct
    public void setSharedVariable() throws TemplateException
    {
        Properties properties = new Properties();
        properties.setProperty("number_format", "0.##");
        configuration.setSettings(properties);
//        configuration.setSharedVariable("dict_entries", dictEntriesDirectiveModel);
        configuration.setSharedVariable("security_resource", securityResourceDirectiveModel);
        configuration.setSharedVariable("dict_category", findDictsByCategory);
        configuration.setSharedVariable("dict_category_value", findDictByCategoryAndValue);

    }
}