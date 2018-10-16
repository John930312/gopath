package com.todaysoft.ghealth.service.impl;

import com.hsgene.restful.response.DataResponse;
import com.todaysoft.ghealth.DTO.Dict;
import com.todaysoft.ghealth.mybatis.mapper.DictMapper;
import com.todaysoft.ghealth.mybatis.model.query.DictQuery;
import com.todaysoft.ghealth.request.DictQueryRequest;
import com.todaysoft.ghealth.service.IDictService;
import com.todaysoft.ghealth.service.paser.DictQueryParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: ljl
 * @Date: 2018/8/29 0029 9:08
 */
@Service
public class DictService implements IDictService
{
    @Autowired
    private DictMapper dictMapper;
    
    @Autowired
    private DictQueryParser dictQueryParser;
    
    @Override
    public DataResponse<List<Dict>> getDictsByCategory(DictQueryRequest request)
    {
        DictQuery query = dictQueryParser.parse(request);
        return new DataResponse<List<Dict>>(dictMapper.findList(query));
    }
    
    @Override
    public DataResponse<Dict> findByCategoryAndValue(DictQueryRequest request)
    {
        DictQuery query = dictQueryParser.parse(request);
        return new DataResponse<Dict>(dictMapper.findByCategoryAndValue(query));
    }
}
