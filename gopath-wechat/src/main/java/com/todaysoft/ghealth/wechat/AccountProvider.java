package com.todaysoft.ghealth.wechat;


import com.todaysoft.ghealth.service.ITokenService;
import com.todaysoft.ghealth.wechat.dto.Account;
import com.todaysoft.ghealth.wechat.dto.SNSTokenDTO;
import com.todaysoft.ghealth.wechat.dto.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Component
public class AccountProvider
{
    private static Logger log = LoggerFactory.getLogger(AccountProvider.class);
    
    @Autowired
    private AccountMockConfig mockConfig;
    
    @Autowired
    private WechatService wechatService;
    
    @Autowired
    private ITokenService tokenService;
    
    public Account provide(HttpServletRequest request)
    {
        if (log.isDebugEnabled())
        {
            log.debug("Start to provide the account by com.todaysoft.ghealth.request.");
        }
        
        if (mockConfig.isEnabled())
        {
            if (log.isDebugEnabled())
            {
                log.debug("Account mock function is enabled, the mocked account to be provide.");
            }
            
            return mockConfig.getAccount();
        }
        
        String code = request.getParameter("code");
        
        if (StringUtils.isEmpty(code))
        {
            if (log.isDebugEnabled())
            {
                log.debug("Wechat code is not exists, should redirect the url to wechat.");
            }
            
            return null;
        }
        
        if (log.isDebugEnabled())
        {
            log.debug("Wechat code is {}, try to get openid by the api.", code);
        }
        
        SNSTokenDTO token = wechatService.getSNSToken(code);
        
        if (null == token)
        {
            log.error("The api responsed token by code {} is null.", code);
            throw new IllegalStateException();
        }
        
        String openid = token.getOpenid();
        
        if (log.isDebugEnabled())
        {
            log.debug("Obtain openid success, the openid is {}.", openid);
        }
        
        Account account = new Account();
        account.setOpenid(openid);
        
        // 根据openid获取微信绑定的客户账号的token，用于后续请求的身份认证
        Token accountToken = tokenService.getToken(openid);
        
        if (null != accountToken)
        {
            if (log.isDebugEnabled())
            {
                log.debug("Account is exists for openid {}, account name {}, token {}.", openid, accountToken.getToken());
            }
            
            account.setToken(accountToken.getToken());
        }
        else
        {
            if (log.isDebugEnabled())
            {
                log.debug("Account is not exists for openid {}.", openid);
            }
        }
        
        if (log.isDebugEnabled())
        {
            log.debug("Provided account openid {}, name {}, token {}.", account.getOpenid(), account.getToken());
        }
        
        return account;
    }
}
