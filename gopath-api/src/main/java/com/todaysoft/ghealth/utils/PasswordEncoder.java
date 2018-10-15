package com.todaysoft.ghealth.utils;

public interface PasswordEncoder
{
    String encode(CharSequence rawPassword);
    
    boolean matches(CharSequence rawPassword, String encodedPassword);
}