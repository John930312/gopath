package com.todaysoft.ghealth.service.wrapper;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.ParameterizedType;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public abstract class Wrapper<S, T>
{
    @SuppressWarnings("unchecked")
    public T wrap(S source)
    {
        if (null == source)
        {
            return null;
        }
        
        Class<T> targetClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        
        try
        {
            T target = targetClass.newInstance();
            String[] ignoreProperties = getCopyIgnoreProperties();
            
            if (null == ignoreProperties || 0 == ignoreProperties.length)
            {
                BeanUtils.copyProperties(source, target);
            }
            else
            {
                BeanUtils.copyProperties(source, target, ignoreProperties);
                setCopyIgnoreProperties(source, target);
            }
            
            return target;
        }
        catch (InstantiationException e)
        {
            throw new IllegalStateException();
        }
        catch (IllegalAccessException e)
        {
            throw new IllegalStateException();
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<T> wrap(List<S> sources)
    {
        if (CollectionUtils.isEmpty(sources))
        {
            return Collections.emptyList();
        }
        
        T target;
        String[] ignoreProperties;
        List<T> targets = new ArrayList<T>();
        Class<T> targetClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        
        for (S source : sources)
        {
            try
            {
                target = targetClass.newInstance();
                ignoreProperties = getCopyIgnoreProperties();
                
                if (null == ignoreProperties || 0 == ignoreProperties.length)
                {
                    BeanUtils.copyProperties(source, target);
                }
                else
                {
                    BeanUtils.copyProperties(source, target, ignoreProperties);
                    setCopyIgnoreProperties(source, target);
                }
            }
            catch (InstantiationException e)
            {
                throw new IllegalStateException();
            }
            catch (IllegalAccessException e)
            {
                throw new IllegalStateException();
            }
            
            targets.add(target);
        }
        
        return targets;
    }
    
    protected String[] getCopyIgnoreProperties()
    {
        return null;
    }
    
    protected void setCopyIgnoreProperties(S source, T target)
    {
        //
    }
    
    protected String formatDate(Date date)
    {
        return null == date ? null : DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
    }
    
    protected Date parseDate(String date)
    {
        if (StringUtils.isEmpty(date))
        {
            return null;
        }
        
        try
        {
            return DateUtils.parseDate(date, "yyyy-MM-dd HH:mm:ss");
        }
        catch (ParseException e)
        {
            throw new IllegalStateException();
        }
    }
}
