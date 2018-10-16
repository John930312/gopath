package com.todaysoft.ghealth.service.impl;

import com.hsgene.restful.response.DataResponse;
import com.todaysoft.ghealth.DTO.Dict;
import com.todaysoft.ghealth.gateway.Gateway;
import com.todaysoft.ghealth.request.DictQueryRequest;
import com.todaysoft.ghealth.service.IDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * @Author: ljl
 * @Date: 2018/8/28 0028 17:18
 */
@Service
public class DictService implements IDictService
{
    @Autowired
    private Gateway gateway;
    
    @Override
    public List<Dict> getDictsByCategory(String category)
    {
        DictQueryRequest request = new DictQueryRequest();
        request.setCategory(category);
        DataResponse<List<Dict>> response = gateway.post("/dict/getDictsByCategory", request, new ParameterizedTypeReference<DataResponse<List<Dict>>>()
        {
        });
        if (CollectionUtils.isEmpty(response.getData()))
        {
            return Collections.emptyList();
        }
        return response.getData();
    }
    
    @Override
    public Dict getDictByCategoryAndValue(String category, String dictValue)
    {
        DictQueryRequest request = new DictQueryRequest();
        request.setCategory(category);
        request.setDictValue(dictValue);
        DataResponse<Dict> response = gateway.post("/dict/getDictByCategoryAndValue", request, new ParameterizedTypeReference<DataResponse<Dict>>()
        {
        });
        if (null == response.getData())
        {
            return new Dict();
        }
        return response.getData();
    }
}
