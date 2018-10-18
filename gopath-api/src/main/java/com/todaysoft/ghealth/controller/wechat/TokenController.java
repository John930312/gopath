package com.todaysoft.ghealth.controller.wechat;

import com.hsgene.restful.response.DataResponse;
import com.todaysoft.ghealth.DTO.TokenDTO;
import com.todaysoft.ghealth.service.ITokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xjw
 * @Date: 2018/10/17 16:26
 */
@RestController
@RequestMapping("/wechat/token")
public class TokenController
{
    @Autowired
    private ITokenService tokenService;
    
    @GetMapping("/{openid}")
    public DataResponse<TokenDTO> getToken(@PathVariable String openid)
    {
        return tokenService.getToken(openid);
    }
}
