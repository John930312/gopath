package com.todaysoft.ghealth.service;


import com.hsgene.restful.response.DataResponse;
import com.todaysoft.ghealth.DTO.AreaDTO;
import com.todaysoft.ghealth.request.AreaQueryRequest;

import java.util.List;

/**
 * @Author: xjw
 * @Date: 2018/10/13 0003 14:58
 */
public interface IAreaService
{
    String getDistrictName(String id);

    DataResponse<List<AreaDTO>> list(AreaQueryRequest request);
}
