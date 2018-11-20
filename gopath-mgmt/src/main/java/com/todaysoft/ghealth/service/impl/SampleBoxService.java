package com.todaysoft.ghealth.service.impl;

import com.hsgene.restful.response.DataResponse;
import com.hsgene.restful.util.CountRecords;
import com.todaysoft.ghealth.DTO.SampleBoxDTO;
import com.todaysoft.ghealth.gateway.Gateway;
import com.todaysoft.ghealth.model.SampleBox;
import com.todaysoft.ghealth.model.searcher.SampleBoxSearcher;
import com.todaysoft.ghealth.request.SampleBoxMaintainRequest;
import com.todaysoft.ghealth.request.SampleBoxQueryRequest;
import com.todaysoft.ghealth.service.ISampleBoxService;
import com.todaysoft.ghealth.service.wrapper.SampleBoxWrapper;
import com.todaysoft.ghealth.support.Pager;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

/**
 * @Author: ljl
 * @Date: 2018/11/20 0020 9:33
 */
@Service
public class SampleBoxService implements ISampleBoxService
{
    @Autowired
    private Gateway gateway;
    
    @Autowired
    private SampleBoxWrapper sampleBoxWrapper;
    
    @Override
    public Pager<SampleBox> pager(SampleBoxSearcher searcher, int pageNo, int pageSize)
    {
        SampleBoxQueryRequest request = new SampleBoxQueryRequest();
        BeanUtils.copyProperties(searcher, request);
        request.setCount(true);
        request.setLimit(pageSize);
        request.setOffset((pageNo - 1) * pageSize);
        DataResponse<CountRecords<SampleBoxDTO>> response =
            gateway.post("/sampleBox/pager", request, new ParameterizedTypeReference<DataResponse<CountRecords<SampleBoxDTO>>>()
            {
            });
        if (null == response.getData() || null == response.getData().getCount())
        {
            throw new IllegalStateException();
        }
        Pager<SampleBox> pager = new Pager<>(pageNo, pageSize, response.getData().getCount().intValue());
        pager.setRecords(sampleBoxWrapper.wrap(response.getData().getRecords()));
        return pager;
    }
    
    @Override
    public void create(SampleBox data)
    {
        SampleBoxMaintainRequest request = new SampleBoxMaintainRequest();
        BeanUtils.copyProperties(data, request);
        gateway.post("/sampleBox/create", request);
    }
    
    @Override
    public Boolean isUniqueSampleBoxCode(String code)
    {
        DataResponse<Boolean> response =
                gateway.get("/sampleBox/isUniqueSampleBoxCode/{code}", new ParameterizedTypeReference<DataResponse<Boolean>>()
                {
                }, code);
        if (null == response.getData())
        {
            return false;
        }
        return response.getData();
    }
}
