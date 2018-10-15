package com.todaysoft.ghealth.security;


import com.hsgene.restful.response.DataResponse;

import com.todaysoft.ghealth.gateway.Gateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import com.todaysoft.ghealth.DTO.AccountDTO;
import com.todaysoft.ghealth.DTO.LoginDTO;

@Component
public class AccountProvider extends AbstractUserDetailsAuthenticationProvider
{
    @Autowired
    private Gateway gateway;
    
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException
    {
        
    }
    
    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException
    {
        try
        {
            String password = (String)authentication.getCredentials();
            
            LoginDTO request = new LoginDTO();
            request.setUsername(username);
            request.setPassword(password);
            
            DataResponse<AccountDTO> response = gateway.post("/account/login", request, new ParameterizedTypeReference<DataResponse<AccountDTO>>()
            {
            });
            
            AccountDTO data = response.getData();
            AccountDetails account = new AccountDetails(data.getId(), data.getName(), data.getToken(), data.getUserName(), data.getBuiltin());
            account.setAuthorizedResources(data.getAuthorizedResources());
            return account;
        }
        catch (Exception e)
        {
            throw new BadCredentialsException(e.getMessage());
        }
    }
}
