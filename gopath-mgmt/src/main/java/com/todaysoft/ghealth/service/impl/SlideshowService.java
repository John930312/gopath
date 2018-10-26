package com.todaysoft.ghealth.service.impl;

import com.hsgene.restful.response.DataResponse;
import com.hsgene.restful.util.CountRecords;
import com.todaysoft.ghealth.DTO.SlideshowDTO;
import com.todaysoft.ghealth.gateway.Gateway;
import com.todaysoft.ghealth.model.Slideshow;
import com.todaysoft.ghealth.model.searcher.SlideshowSearcher;
import com.todaysoft.ghealth.request.SlideshowMaintainRequest;
import com.todaysoft.ghealth.request.SlideshowQueryRequest;
import com.todaysoft.ghealth.service.ISlideshowService;
import com.todaysoft.ghealth.service.wrapper.SlideshowWrapper;
import com.todaysoft.ghealth.support.Pager;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @Author: zyf
 * @Date: 2018/10/23 14:23
 */

@Service
public class SlideshowService implements ISlideshowService
{
    @Autowired
    private Gateway gateway;
    
    @Autowired
    private SlideshowWrapper slideshowWrapper;
    
    @Override
    public Pager<Slideshow> pager(SlideshowSearcher searcher, int pageNo, int pageSize)
    {
        SlideshowQueryRequest request = new SlideshowQueryRequest();
        BeanUtils.copyProperties(searcher, request);
        request.setCount(true);
        request.setLimit(pageSize);
        request.setOffset((pageNo - 1) * pageSize);
        
        DataResponse<CountRecords<SlideshowDTO>> response =
            gateway.post("/slideshow/pager", request, new ParameterizedTypeReference<DataResponse<CountRecords<SlideshowDTO>>>()
            {
            });
        
        Pager<Slideshow> pager = new Pager<>(pageNo, pageSize, response.getData().getCount().intValue());
        pager.setRecords(slideshowWrapper.wrap(response.getData().getRecords()));
        return pager;
    }
    
    public void create(Slideshow data)
    {
        SlideshowMaintainRequest request = new SlideshowMaintainRequest();
        BeanUtils.copyProperties(data, request);
        gateway.post("/slideshow/create", request);
    }
    
    @Override
    public Slideshow get(String id)
    {
        DataResponse<SlideshowDTO> response = gateway.get("/slideshow/get/{id}", new ParameterizedTypeReference<DataResponse<SlideshowDTO>>()
        {
        }, id);
        
        if (Objects.isNull(response.getData()))
        {
            return new Slideshow();
        }
        
        return slideshowWrapper.wrap(response.getData());
    }
    
    @Override
    public void modify(Slideshow data)
    {
        SlideshowMaintainRequest request = new SlideshowMaintainRequest();
        BeanUtils.copyProperties(data, request);
        gateway.post("/slideshow/modify", request);
    }
    
    @Override
    public void delete(Slideshow data)
    {
        SlideshowMaintainRequest request = new SlideshowMaintainRequest();
        request.setId(data.getId());
        gateway.post("/slideshow/delete", request);
    }
}
