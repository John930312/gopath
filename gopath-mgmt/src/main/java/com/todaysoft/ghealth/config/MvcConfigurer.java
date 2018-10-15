package com.todaysoft.ghealth.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.todaysoft.ghealth.config.core.AccountInterceptor;
import com.todaysoft.ghealth.config.core.GlobalAttributesInterceptor;
import com.todaysoft.ghealth.config.core.StringTrimmerFormatter;
import com.todaysoft.ghealth.exception.ExceptionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.CacheControl;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.MappedInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.ServletContext;
import java.util.List;

@Configuration
public class MvcConfigurer implements WebMvcConfigurer
{
    @Autowired
    private ExceptionResolver exceptionResolver;
    
    @Autowired
    private AccountInterceptor threadLocalInterceptor;
    
    @Autowired
    private GlobalAttributesInterceptor globalAttributesInterceptor;

    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:templates/static/").setCacheControl(CacheControl.empty());
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(threadLocalInterceptor);
        registry.addInterceptor(new MappedInterceptor(new String[] {"/", "/**/*.jsp"}, globalAttributesInterceptor));
    }
    
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers)
    {
        exceptionResolvers.add(exceptionResolver);
    }
    
    @Override
    public void addFormatters(FormatterRegistry registry)
    {
        registry.addFormatter(new DateFormatter());
        registry.addFormatter(new StringTrimmerFormatter());
    }
    
    private CharacterEncodingFilter getCharacterEncodingFilter()
    {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setForceEncoding(true);
        filter.setEncoding("UTF-8");
        return filter;
    }
    
    @Bean
    public FilterRegistrationBean filterRegistrationBean()
    {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(getCharacterEncodingFilter());
        return registrationBean;
    }
    
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters)
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(mapper);
        converters.add(converter);
    }
    
    @Bean(name = "localeResolver")
    public LocaleResolver localeResolverBean()
    {
        return new SessionLocaleResolver();
    }
    
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver getCommonsMultipartResolver(ServletContext servletContext)
    {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver(servletContext);
        resolver.setMaxUploadSize(104857600);
        resolver.setMaxInMemorySize(104857600);
        return resolver;
    }
}
