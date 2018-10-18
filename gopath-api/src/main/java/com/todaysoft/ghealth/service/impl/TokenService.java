package com.todaysoft.ghealth.service.impl;

import com.hsgene.restful.response.DataResponse;
import com.todaysoft.ghealth.DTO.TokenDTO;
import com.todaysoft.ghealth.exception.ServiceException;
import com.todaysoft.ghealth.mybatis.mapper.AccountTokenMapper;
import com.todaysoft.ghealth.mybatis.model.AccountToken;
import com.todaysoft.ghealth.service.ITokenService;
import com.todaysoft.ghealth.utils.IdGen;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

/**
 * @Author: xjw
 * @Date: 2018/10/17 16:28
 */
@Service
public class TokenService implements ITokenService
{
    @Autowired
    private AccountTokenMapper accountTokenMapper;
    
    @Override
    public DataResponse<TokenDTO> getToken(String openid)
    {
        TokenDTO dto = new TokenDTO();
        AccountToken accountToken = accountTokenMapper.getByOpenid(openid);
        
        Date now = new Date();
        if (Objects.isNull(accountToken))
        {
            String token = RandomStringUtils.randomAlphabetic(32);
            accountToken.setId(IdGen.uuid());
            accountToken.setCreateTime(now);
            accountToken.setOpenid(openid);
            accountToken.setExpireTime(DateUtils.addDays(now, 7));
            accountToken.setToken(token);
            accountTokenMapper.create(accountToken);
            
            dto.setToken(token);
            return new DataResponse<>(dto);
        }
        accountToken.setExpireTime(DateUtils.addDays(now, 7));
        accountTokenMapper.modify(accountToken);
        
        dto.setToken(accountToken.getToken());
        return new DataResponse<>(dto);
    }
}
