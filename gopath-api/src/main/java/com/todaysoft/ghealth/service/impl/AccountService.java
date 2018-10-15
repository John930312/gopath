package com.todaysoft.ghealth.service.impl;

import com.hsgene.restful.response.DataResponse;
import com.todaysoft.ghealth.exception.ErrorCode;
import com.todaysoft.ghealth.exception.ServiceException;
import com.todaysoft.ghealth.mybaties.mapper.UserMapper;
import com.todaysoft.ghealth.mybaties.mapper.UserTokenMapper;
import com.todaysoft.ghealth.mybaties.model.User;
import com.todaysoft.ghealth.mybaties.model.UserToken;
import com.todaysoft.ghealth.service.IAccountService;
import com.todaysoft.ghealth.utils.IdGen;
import com.todaysoft.ghealth.utils.PasswordEncoder;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.todaysoft.ghealth.DTO.AccountDTO;
import com.todaysoft.ghealth.DTO.LoginDTO;

import java.util.*;

/**
 * @Author: xjw
 * @Date: 2018/10/15 14:32
 */
@Service
public class AccountService implements IAccountService
{
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private UserTokenMapper userTokenMapper;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public DataResponse<AccountDTO> login(LoginDTO request)
    {
        AccountDTO account = getAccountDTO(request);
        
        if (null == account)
        {
            throw new ServiceException(ErrorCode.LOGIN_ACCOUNT_UNMATCHED, "用户名或密码错误");
        }
        
        return new DataResponse<>(account);
    }
    
    private AccountDTO getAccountDTO(LoginDTO request)
    {
        User user = userMapper.getUserByUserName(request.getUsername());
        
        if (null == user)
        {
            return null;
        }
        
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
        {
            throw new ServiceException(ErrorCode.LOGIN_ACCOUNT_UNMATCHED, "用户名或密码错误");
        }
        
        if (!user.getEnabled())
        {
            throw new ServiceException(ErrorCode.LOGIN_ACCOUNT_DISABLED, "账号被禁用");
        }
        
        // 登录成功，设置token
        Date timestamp = new Date();
        String token;
        
        UserToken userToken = userTokenMapper.getEntityByUserId(user.getId());
        
        if (null == userToken)
        {
            token = RandomStringUtils.randomAlphabetic(32);
            userToken = new UserToken();
            userToken.setId(IdGen.uuid());
            userToken.setToken(token);
            userToken.setAccountId(user.getId());
            userToken.setCreateTime(timestamp);
            userToken.setExpireTime(DateUtils.addDays(timestamp, 7));
            userTokenMapper.create(userToken);
        }
        else
        {
            token = userToken.getToken();
            userToken.setCreateTime(timestamp);
            userToken.setExpireTime(DateUtils.addDays(timestamp, 7));
            userTokenMapper.modify(userToken);
        }
        
        AccountDTO data = new AccountDTO();
        data.setId(user.getId());
        data.setToken(token);
        data.setName(user.getName());
        data.setUserName(user.getUsername());
        data.setBuiltin(user.getBuiltin());
        data.setAuthorizedResources(getUserAuthorizedResources(user));
        return data;
    }
    
    private Set<String> getUserAuthorizedResources(User user)
    {
        if (user.getBuiltin())
        {
            return null;
        }
        
        List<String> authorizedResources = userMapper.getUserAuthorizedResources(user.getId());
        
        if (CollectionUtils.isEmpty(authorizedResources))
        {
            return Collections.emptySet();
        }
        
        return new HashSet<String>(authorizedResources);
    }
}
