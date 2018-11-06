package com.todaysoft.ghealth.service;

import com.hsgene.restful.response.DataResponse;
import com.hsgene.restful.util.CountRecords;
import com.todaysoft.ghealth.DTO.AgencyDTO;
import com.todaysoft.ghealth.request.AgencyMaintainRequest;
import com.todaysoft.ghealth.request.AgencyQueryRequest;

/**
 * @Author: zyf
 * @Date: 2018/10/17 14:00
 */

public interface IAgencyService
{
    DataResponse<CountRecords<AgencyDTO>> query(AgencyQueryRequest request);

    DataResponse<AgencyDTO> get(String id);

    void create(AgencyMaintainRequest request);

    void modify(AgencyMaintainRequest request);

    void delete(AgencyMaintainRequest request);

    DataResponse<Boolean> isCodeUnique(AgencyMaintainRequest request);
}
