package com.todaysoft.ghealth.controller.mgmt;

import com.hsgene.restful.response.DataResponse;
import com.todaysoft.ghealth.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.todaysoft.ghealth.DTO.AccountDTO;
import com.todaysoft.ghealth.DTO.LoginDTO;

/**
 * @Author: xjw
 * @Date: 2018/10/15 14:29
 */
@RestController
@RequestMapping("/account")
public class AccountController
{
    @Autowired
    private IAccountService accountService;

    @PostMapping("/login")
    public DataResponse<AccountDTO> login(@RequestBody LoginDTO request)
    {
        return accountService.login(request);
    }
}
