package com.todaysoft.ghealth.service.impl;

import com.hsgene.restful.response.DataResponse;
import com.hsgene.restful.util.CountRecords;
import com.todaysoft.ghealth.DTO.UserDTO;
import com.todaysoft.ghealth.mybaties.mapper.UserMapper;
import com.todaysoft.ghealth.mybaties.model.User;
import com.todaysoft.ghealth.mybaties.model.query.UserQuery;
import com.todaysoft.ghealth.request.UserMaintainRequest;
import com.todaysoft.ghealth.request.UserQueryRequest;
import com.todaysoft.ghealth.service.IUserService;
import com.todaysoft.ghealth.service.paser.UserQueryParser;
import com.todaysoft.ghealth.service.wrapper.UserWrapper;
import com.todaysoft.ghealth.utils.IdGen;
import com.todaysoft.ghealth.utils.PasswordEncoder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @Author: xjw
 * @Date: 2018/10/15 16:11
 */
@Service
public class UserService implements IUserService
{
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private UserQueryParser userQueryParser;
    
    @Autowired
    private UserWrapper userWrapper;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public DataResponse<CountRecords<UserDTO>> query(UserQueryRequest request)
    {
        UserQuery query = userQueryParser.parse(request);
        query.setBuiltin(false);
        CountRecords<UserDTO> data = new CountRecords<>();
        
        if (request.isCount())
        {
            long count = userMapper.count(query);
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
        
        List<User> records = userMapper.query(query);
        data.setRecords(userWrapper.wrap(records));
        return new DataResponse<>(data);
    }


    @Override
    public DataResponse<UserDTO> get(String id)
    {
        return new DataResponse<UserDTO>(userWrapper.wrap(userMapper.get(id)));
    }

    @Override
    public DataResponse<Boolean> isUsernameUnique(UserMaintainRequest request)
    {
        UserQuery query = new UserQuery();
        query.setName(request.getUsername());
        query.setUsernameExactMatches(true);
        if (!StringUtils.isEmpty(request.getId()))
        {
            query.setExcludeKeys(Collections.singleton(request.getId()));
        }
        long count = userMapper.count(query);
        return new DataResponse<Boolean>(count == 0);
    }

    @Override
    @Transactional
    public void create(UserMaintainRequest request)
    {
        User user = new User();
        user.setId(IdGen.uuid());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());
        user.setPhone(request.getPhone());
        user.setEmail(request.getEmail());
        user.setBuiltin(false);
        user.setEnabled(true);
        user.setDeleted(false);
        user.setCreatorName(request.getOperatorName());
        user.setCreateTime(new Date());
        userMapper.create(user);

    }

    @Override
    @Transactional
    public void modify(UserMaintainRequest request)
    {
        User user = userMapper.get(request.getId());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setName(request.getName());
        user.setUpdatorName(request.getOperatorName());
        user.setUpdateTime(new Date());
        if (!StringUtils.isEmpty(request.getPassword())){
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        userMapper.modify(user);


    }

    @Override
    public void delete(UserMaintainRequest request)
    {
        User user = userMapper.get(request.getId());
        user.setDeleted(true);
        user.setDeletorName(request.getOperatorName());
        user.setDeleteTime(new Date());
        userMapper.modify(user);
    }

    @Override
    public void change(UserMaintainRequest request)
    {
        User user = userMapper.get(request.getId());
        user.setEnabled(request.getEnabled());
        user.setUpdateTime(new Date());
        user.setUpdatorName(request.getOperatorName());
        userMapper.modify(user);
    }
}
