package com.todaysoft.ghealth.config.core;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class StringTrimmerFormatter implements Formatter<String>
{
    @Override
    public String print(String object, Locale locale)
    {
        if (null == object)
        {
            return null;
        }
        
        return object.trim();
    }
    
    @Override
    public String parse(String text, Locale locale)
        throws ParseException
    {
        if (null == text)
        {
            return null;
        }
        
        return text.trim();
    }
}
