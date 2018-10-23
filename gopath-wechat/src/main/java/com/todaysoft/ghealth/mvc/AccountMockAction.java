package com.todaysoft.ghealth.mvc;

import com.todaysoft.ghealth.exception.ServiceException;
import com.todaysoft.ghealth.wechat.AccountMockConfig;
import com.todaysoft.ghealth.wechat.dto.Account;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/mock/account")
public class AccountMockAction
{
    @Autowired
    private AccountMockConfig config;
    
    @RequestMapping("/{enabled}/{openid}")
    public Map<String, Object> mock(@PathVariable boolean enabled, @PathVariable String openid)
    {
        Map<String, Object> rsp = new HashMap<String, Object>();
        
        try
        {
            config.setEnabled(enabled);
            rsp.put("success", true);
            rsp.put("enabled", enabled);
            
            if (enabled)
            {
                if (StringUtils.isEmpty(openid))
                {
                    throw new ServiceException("MOCK_ERROR", "Mock启用时Openid不能为空");
                }
                
                Account account = new Account();
                account.setOpenid(openid);
                account.setToken(null);
                config.setAccount(account);
                rsp.put("account", account);
            }
        }
        catch (Exception e)
        {
            rsp.put("success", false);
            rsp.put("error", e.getMessage());
        }
        
        return rsp;
    }

    @RequestMapping("/{enabled}/{openid}/agencyId/{agencyId}")
    public Map<String, Object> agencyId(@PathVariable boolean enabled, @PathVariable String openid,@PathVariable String agencyId)
    {
        Map<String, Object> rsp = new HashMap<String, Object>();

        try
        {
            config.setEnabled(enabled);
            rsp.put("success", true);
            rsp.put("enabled", enabled);
            rsp.put("agencyId", agencyId);

            if (enabled)
            {
                if (StringUtils.isEmpty(openid))
                {
                    throw new ServiceException("MOCK_ERROR", "Mock启用时Openid不能为空");
                }

                Account account = new Account();
                account.setOpenid(openid);
                account.setAgencyId(agencyId);
                account.setToken(null);
                config.setAccount(account);
                rsp.put("account", account);
            }
        }
        catch (Exception e)
        {
            rsp.put("success", false);
            rsp.put("error", e.getMessage());
        }

        return rsp;
    }
    
    @RequestMapping("/{enabled}/{openid}/{token}")
    public Map<String, Object> mock(@PathVariable boolean enabled, @PathVariable String openid, @PathVariable String token)
    {
        Map<String, Object> rsp = new HashMap<String, Object>();
        
        try
        {
            config.setEnabled(enabled);
            rsp.put("success", true);
            rsp.put("enabled", enabled);
            rsp.put("token", token);
            
            if (enabled)
            {
                if (StringUtils.isEmpty(openid))
                {
                    throw new ServiceException("MOCK_ERROR", "Mock启用时Openid不能为空");
                }
                
                Account account = new Account();
                account.setOpenid(openid);
                account.setToken(token);
                config.setAccount(account);
                rsp.put("account", account);
            }
        }
        catch (Exception e)
        {
            rsp.put("success", false);
            rsp.put("error", e.getMessage());
        }
        
        return rsp;
    }
}
