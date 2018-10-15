package com.todaysoft.ghealth.config.core;


import com.todaysoft.ghealth.security.AccountDetails;
import org.springframework.stereotype.Component;

@Component
public class AccountHolder extends ThreadLocal<AccountDetails>
{
    
}
