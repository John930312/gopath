package com.todaysoft.ghealth.service;

import com.hsgene.restful.response.DataResponse;
import com.hsgene.restful.util.CountRecords;
import com.todaysoft.ghealth.DTO.OrderDTO;
import com.todaysoft.ghealth.request.MainSampleBoxRequest;
import com.todaysoft.ghealth.request.MaintainOrderRequest;
import com.todaysoft.ghealth.request.OrderQueryRequest;

import java.util.List;

/**
 * @Author: xjw
 * @Date: 2018/10/16 15:14
 */
public interface IOrderService
{
    DataResponse<CountRecords<OrderDTO>> pager(OrderQueryRequest request);
    
    DataResponse<List<OrderDTO>> list(OrderQueryRequest request);
    
    DataResponse<OrderDTO> get(String id);
    
    void modify(MaintainOrderRequest request);
    
    DataResponse<Boolean> isUniqueSampleBoxCode(MainSampleBoxRequest request);
}
