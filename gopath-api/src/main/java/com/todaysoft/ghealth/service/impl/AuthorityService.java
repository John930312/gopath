package com.todaysoft.ghealth.service.impl;

import com.hsgene.restful.response.DataResponse;
import com.todaysoft.ghealth.DTO.Authority;
import com.todaysoft.ghealth.DTO.AuthorityNode;
import com.todaysoft.ghealth.mybatis.mapper.AuthorityMapper;
import com.todaysoft.ghealth.service.IAuthorityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ljl
 * @Date: 2018/8/28 0028 10:35
 */
@Service
public class AuthorityService implements IAuthorityService
{
    @Autowired
    private AuthorityMapper mapper;

    @Override
    public DataResponse<List<AuthorityNode>> getAuthorityNodes()
    {
        //所有的父节点
        List<Authority> authorities = mapper.getParentAuthorities();
        List<AuthorityNode> AuthorityNodes = new ArrayList<AuthorityNode>();
        if (!CollectionUtils.isEmpty(authorities))
        {
            for (Authority authority : authorities)
            {
                AuthorityNodes.add(warp(authority));
            }
        }
        return new DataResponse<List<AuthorityNode>>(AuthorityNodes);
    }

    private AuthorityNode warp(Authority data)
    {
        if (null != data)
        {
            List<Authority> sons = mapper.getAuthoritiesByParentId(data.getId());
            List<AuthorityNode> sonNodes = new ArrayList<AuthorityNode>();
            AuthorityNode authorityNode = new AuthorityNode();
            BeanUtils.copyProperties(data, authorityNode);
            if (!CollectionUtils.isEmpty(sons))
            {
                for (Authority authority : sons)
                {
                    AuthorityNode childNode = new AuthorityNode();
                    BeanUtils.copyProperties(authority, childNode);
                    sonNodes.add(childNode);
                    List<Authority> grandsons = mapper.getAuthoritiesByParentId(authority.getId());
                    List<AuthorityNode> grandsonNodes = new ArrayList<AuthorityNode>();
                    if (!CollectionUtils.isEmpty(grandsons))
                    {
                        for (Authority a : grandsons)
                        {
                            AuthorityNode grandsonNode = new AuthorityNode();
                            BeanUtils.copyProperties(a, grandsonNode);
                            grandsonNodes.add(grandsonNode);
                            warp(a);
                        }
                    }
                    childNode.setChild(grandsonNodes);
                }
            }
            authorityNode.setChild(sonNodes);
            return authorityNode;
        }
        return null;
    }
}
