package com.todaysoft.ghealth.service.impl;

import com.hsgene.restful.response.DataResponse;
import com.hsgene.restful.util.CountRecords;
import com.todaysoft.ghealth.DTO.UserDTO;
import com.todaysoft.ghealth.mybaties.mapper.UserMapper;
import com.todaysoft.ghealth.mybaties.model.User;
import com.todaysoft.ghealth.mybaties.model.query.UserQuery;
import com.todaysoft.ghealth.request.UserQueryRequest;
import com.todaysoft.ghealth.service.IUserService;
import com.todaysoft.ghealth.service.paser.UserQueryParser;
import com.todaysoft.ghealth.service.wrapper.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
}
