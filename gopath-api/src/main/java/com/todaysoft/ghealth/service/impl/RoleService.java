package com.todaysoft.ghealth.service.impl;

import com.hsgene.restful.response.DataResponse;
import com.hsgene.restful.util.CountRecords;
import com.todaysoft.ghealth.DTO.RoleDTO;
import com.todaysoft.ghealth.mybatis.mapper.RoleMapper;
import com.todaysoft.ghealth.mybatis.model.Role;
import com.todaysoft.ghealth.mybatis.model.query.RoleQuery;
import com.todaysoft.ghealth.mybatis.model.query.RoleQueryParser;
import com.todaysoft.ghealth.request.RoleQueryRequest;
import com.todaysoft.ghealth.service.IRoleService;
import com.todaysoft.ghealth.service.wrapper.RoleWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
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

}
