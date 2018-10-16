package com.todaysoft.ghealth.service.impl;

import com.hsgene.restful.response.DataResponse;
import com.hsgene.restful.util.CountRecords;
import com.todaysoft.ghealth.DTO.Authority;
import com.todaysoft.ghealth.DTO.RoleDTO;
import com.todaysoft.ghealth.mybatis.mapper.RoleAuthorityMapper;
import com.todaysoft.ghealth.mybatis.mapper.RoleMapper;
import com.todaysoft.ghealth.mybatis.mapper.UserRoleMapper;
import com.todaysoft.ghealth.mybatis.model.Role;
import com.todaysoft.ghealth.mybatis.model.RoleAuthority;
import com.todaysoft.ghealth.mybatis.model.User;
import com.todaysoft.ghealth.mybatis.model.query.RoleAuthorityQuery;
import com.todaysoft.ghealth.mybatis.model.query.RoleQuery;
import com.todaysoft.ghealth.mybatis.model.query.RoleQueryParser;
import com.todaysoft.ghealth.mybatis.model.query.UserRoleQuery;
import com.todaysoft.ghealth.request.RoleMaintainRequest;
import com.todaysoft.ghealth.request.RoleQueryRequest;
import com.todaysoft.ghealth.service.IRoleService;
import com.todaysoft.ghealth.service.wrapper.RoleWrapper;
import com.todaysoft.ghealth.utils.IdGen;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @Author: ljl
 * @Date: 2018/8/28 0028 9:18
 */
@Service
public class RoleService implements IRoleService
{
    @Autowired
    private RoleMapper roleMapper;
    
    @Autowired
    private RoleQueryParser roleQueryParser;
    
    @Autowired
    private RoleWrapper roleWrapper;

    @Autowired
    private RoleAuthorityMapper roleAuthorityMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public DataResponse<CountRecords<RoleDTO>> pager(RoleQueryRequest request)
    {
        RoleQuery query = roleQueryParser.parse(request);
        CountRecords<RoleDTO> data = new CountRecords<>();
        
        if (request.isCount())
        {
            long count = roleMapper.count(query);
            data.setCount(count);
            
            if (0 == count)
            {
                data.setRecords(Collections.emptyList());
                return new DataResponse<>(data);
            }
            
            if (null != request.getLimit() && null != request.getOffset() && request.getOffset().intValue() >= count)
            {
                int offset;
                int limit = request.getLimit().intValue();
                int mod = (int)count % limit;
                
                if (0 == mod)
                {
                    offset = (((int)count / limit) - 1) * limit;
                }
                else
                {
                    offset = ((int)count / limit) * limit;
                }
                
                query.setOffset(offset);
            }
        }
        
        List<Role> records = roleMapper.query(query);
        data.setRecords(roleWrapper.wrap(records));
        return new DataResponse<>(data);
    }

    @Override
    @Transactional
    public void create(RoleMaintainRequest request) {
        Role data = new Role();
        BeanUtils.copyProperties(request, data);
        data.setId(IdGen.uuid());
        data.setCreatorName(request.getOperatorName());
        data.setCreateTime(new Date());
        data.setDeleted(false);
        roleMapper.insert(data);
        insertRoleAuthoirties(request.getAuthorityIds(), data);
    }

    private void insertRoleAuthoirties(String authorityIds, Role data) {
        if (StringUtils.isNotEmpty(authorityIds)) {
            for (String authorityId : authorityIds.split(",")) {
                RoleAuthority roleAuthority = new RoleAuthority();
                Authority authority = new Authority();
                authority.setId(authorityId);
                roleAuthority.setRole(data);
                roleAuthority.setAuthority(authority);
                insertRoleAuthority(roleAuthority);
            }
        }
    }

    @Transactional
    public void insertRoleAuthority(RoleAuthority data) {
        roleAuthorityMapper.insert(data);
    }

    @Override
    @Transactional
    public void modify(RoleMaintainRequest request) {
        Role data = new Role();
        BeanUtils.copyProperties(request, data);
        data.setUpdateTime(new Date());
        data.setUpdatorName(request.getOperatorName());
        roleMapper.update(data);
        roleAuthorityMapper.deleteRoleAuthority(data.getId());
        insertRoleAuthoirties(request.getAuthorityIds(), data);
    }

    @Override
    @Transactional
    public DataResponse<Boolean> delete(RoleMaintainRequest request) {
        int count = userRoleMapper.countByRoleId(request.getId());
        if (count == 0) {
            Role data = new Role();
            BeanUtils.copyProperties(request, data);
            data.setDeleted(true);
            data.setDeletorName(request.getOperatorName());
            data.setDeleteTime(new Date());
            roleMapper.update(data);
            roleAuthorityMapper.deleteRoleAuthority(data.getId());
            userRoleMapper.deleteUserRoleByRoleId(data.getId());
            return new DataResponse<Boolean>(true);
        } else {
            return new DataResponse<Boolean>(false);
        }
    }

    @Override
    public DataResponse<RoleDTO> get(RoleMaintainRequest request) {
        return new DataResponse<RoleDTO>(roleWrapper.wrap(roleMapper.get(request.getId())));
    }

    @Override
    public DataResponse<Boolean> isNameUnique(RoleMaintainRequest request) {
        RoleQuery searcher = new RoleQuery();
        searcher.setName(request.getName());
        searcher.setNameExactMatches(true);

        if (!StringUtils.isEmpty(request.getId())) {
            searcher.setExcludeKeys(Collections.singleton(request.getId()));
        }
        int count = roleMapper.count(searcher);
        return new DataResponse<Boolean>(count == 0);
    }

    @Override
    public List<Authority> getRoleAuthorities(RoleAuthorityQuery searcher) {
        return roleAuthorityMapper.search(searcher);
    }

    @Override
    public List<User> getUsers(UserRoleQuery searcher) {
        return userRoleMapper.getUsers(searcher);
    }

    @Override
    public List<Role> getRoles(UserRoleQuery searcher) {
        return userRoleMapper.getRoles(searcher);
    }

}
