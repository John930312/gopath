package com.todaysoft.ghealth.config;


import com.todaysoft.ghealth.gateway.GatewayConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan("com.todaysoft.ghealth")
@EnableAspectJAutoProxy
public class RootContext implements ApplicationContextAware
{
    private static ApplicationContext context;
    
    @Autowired
    private Environment environment;
    
    @Bean(name = "messageSource")
    public ReloadableResourceBundleMessageSource getMessageSource()
    {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("/WEB-INF/messages/errors");
        return messageSource;
    }
    
    @Bean
    public GatewayConfig getGatewayConfig()
    {
        GatewayConfig config = new GatewayConfig();
        config.setHost(environment.getRequiredProperty("gateway.host"));
        config.setPort(environment.getRequiredProperty("gateway.port"));
        config.setPath(environment.getRequiredProperty("gateway.path"));
        return config;
    }
    
    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException
    {
        RootContext.context = context;
    }
    
    public static <T> T getBean(Class<T> requiredType)
    {
        return context.getBean(requiredType);
    }
}
