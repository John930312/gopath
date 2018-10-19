package com.todaysoft.ghealth.service.impl;


import com.hsgene.restful.response.DataResponse;
import com.todaysoft.ghealth.gateway.Gateway;
import com.todaysoft.ghealth.service.ITokenService;
import com.todaysoft.ghealth.wechat.dto.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import com.todaysoft.ghealth.DTO.TokenDTO;

import java.util.Objects;

@Service
public class TokenService implements ITokenService
{
    private static Logger log = LoggerFactory.getLogger(TokenService.class);
    
    @Autowired
    private Gateway gateway;
    
    @Override
    public Token getToken(String openid)
    {
        if (log.isDebugEnabled())
        {
            log.debug("Start to get token by openid {}.", openid);
        }
        
        DataResponse<TokenDTO> response = gateway.get("/wechat/token/{openid}", new ParameterizedTypeReference<DataResponse<TokenDTO>>()
        {
        }, openid);
        
        if (log.isDebugEnabled())
        {
            log.debug("Response status {}, com.todaysoft.ghealth.message {}, data {}.", response.getStatus(), response.getMessage(), response.getData());
        }
        
        if (Objects.isNull(response.getData()))
        {
            return null;
        }
        
        Token token = new Token();
        BeanUtils.copyProperties(response.getData(), token);
        return token;
    }
    

}
