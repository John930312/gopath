package com.todaysoft.ghealth.service;

import com.hsgene.restful.response.DataResponse;
import com.hsgene.restful.util.CountRecords;
import com.todaysoft.ghealth.DTO.OrderDTO;
import com.todaysoft.ghealth.DTO.SampleBoxDTO;
import com.todaysoft.ghealth.request.MainSampleBoxRequest;
import com.todaysoft.ghealth.request.MaintainOrderRequest;
import com.todaysoft.ghealth.request.SampleBoxMaintainRequest;
import com.todaysoft.ghealth.request.SampleBoxQueryRequest;

/**
 * @Author: xjw
 * @Date: 2018/10/17 17:28
 */
public interface ISampleBoxService
{
    void bind(MaintainOrderRequest request);

    void bindByCode(MaintainOrderRequest request);

    DataResponse<OrderDTO> sampleBoxDetails(MainSampleBoxRequest request);

    DataResponse<CountRecords<SampleBoxDTO>> pager(SampleBoxQueryRequest request);

    DataResponse<Boolean> isUniqueSampleBoxCode(String code);

    void create(SampleBoxMaintainRequest request);

    DataResponse<OrderDTO> getOrderDTOBySampleBoxCode(MainSampleBoxRequest request);

    DataResponse<SampleBoxDTO> getOrderDTOBySampleBoxCodeLocal(MainSampleBoxRequest request);


}
