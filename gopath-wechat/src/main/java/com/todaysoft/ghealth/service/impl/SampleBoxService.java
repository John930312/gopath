package com.todaysoft.ghealth.service.impl;

import com.hsgene.restful.response.DataResponse;
import com.todaysoft.ghealth.DTO.OrderDTO;
import com.todaysoft.ghealth.DTO.SampleBoxDTO;
import com.todaysoft.ghealth.gateway.Gateway;
import com.todaysoft.ghealth.request.MainSampleBoxRequest;
import com.todaysoft.ghealth.request.MaintainOrderRequest;
import com.todaysoft.ghealth.service.ISampleBoxService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @Author: xjw
 * @Date: 2018/10/17 16:03
 */
@Service
public class SampleBoxService implements ISampleBoxService
{
    @Autowired
    private Gateway gateway;
    
    @Override
    public void bind(OrderDTO data)
    {
        MaintainOrderRequest request = new MaintainOrderRequest();
        BeanUtils.copyProperties(data, request);
        gateway.post("/wechat/sampleBox/bind", request);
    }
    
    @Override
    public OrderDTO detail(String id)
    {
        MainSampleBoxRequest request = new MainSampleBoxRequest();
        request.setId(id);
        
        DataResponse<OrderDTO> response = gateway.post("/wechat/sampleBox/detail", request, new ParameterizedTypeReference<DataResponse<OrderDTO>>()
        {
        });
        
        return Objects.isNull(response.getData()) ? null : response.getData();
    }

    @Override
    public SampleBoxDTO getOrderDTOBySampleBoxCode(String code)
    {
        MainSampleBoxRequest request = new MainSampleBoxRequest();
        request.setCode(code);

        DataResponse<SampleBoxDTO> response = gateway.post("/wechat/sampleBox/getOrderDTOBySampleBoxCode", request, new ParameterizedTypeReference<DataResponse<SampleBoxDTO>>()
        {
        });
        return Objects.isNull(response.getData()) ? null : response.getData();
    }
}
