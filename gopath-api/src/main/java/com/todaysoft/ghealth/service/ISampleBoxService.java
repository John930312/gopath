package com.todaysoft.ghealth.service;

import com.hsgene.restful.response.DataResponse;
import com.todaysoft.ghealth.DTO.OrderDTO;
import com.todaysoft.ghealth.request.MainSampleBoxRequest;
import com.todaysoft.ghealth.request.MaintainOrderRequest;

/**
 * @Author: xjw
 * @Date: 2018/10/17 17:28
 */
public interface ISampleBoxService
{
    void bind(MaintainOrderRequest request);

    DataResponse<OrderDTO> sampleBoxDetails(MainSampleBoxRequest request);

    DataResponse<OrderDTO> getOrderDTOBySampleBoxCode(MainSampleBoxRequest request);
}
