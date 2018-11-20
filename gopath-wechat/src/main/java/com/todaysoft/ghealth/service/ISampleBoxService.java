package com.todaysoft.ghealth.service;

import com.todaysoft.ghealth.DTO.OrderDTO;
import com.todaysoft.ghealth.DTO.SampleBoxDTO;
import com.todaysoft.ghealth.request.MaintainOrderRequest;

/**
 * @Author: xjw
 * @Date: 2018/10/17 16:02
 */
public interface ISampleBoxService
{
    void bind(OrderDTO data);

    OrderDTO detail(String id);

    SampleBoxDTO getOrderDTOBySampleBoxCode(String code);
}
