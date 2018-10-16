package com.todaysoft.ghealth.mvc;

import com.todaysoft.ghealth.form.FormSubmitHandler;
import com.todaysoft.ghealth.model.Role;
import com.todaysoft.ghealth.model.searcher.RoleSearcher;
import com.todaysoft.ghealth.service.IRoleService;
import com.todaysoft.ghealth.service.IUserService;
import com.todaysoft.ghealth.support.ModelResolver;
import com.todaysoft.ghealth.support.Pager;
import com.todaysoft.ghealth.support.PagerArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleAction
{
    @Autowired
    private IRoleService roleService;

    @RequestMapping("json_list.do")
    @ResponseBody
    public List<Role> jsonList(RoleSearcher searcher)
    {
        Pager<Role> pagination = roleService.pager(searcher, 1, 10);
        if (CollectionUtils.isEmpty(pagination.getRecords()))
        {
            return Collections.emptyList();
        }
        
        List<Role> roleList = pagination.getRecords();
        return roleList;
    }

}
