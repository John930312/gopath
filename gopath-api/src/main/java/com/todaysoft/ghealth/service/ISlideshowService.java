package com.todaysoft.ghealth.service;

import com.hsgene.restful.response.DataResponse;
import com.hsgene.restful.util.CountRecords;
import com.todaysoft.ghealth.DTO.SlideshowDTO;
import com.todaysoft.ghealth.request.SlideshowMaintainRequest;
import com.todaysoft.ghealth.request.SlideshowQueryRequest;

/**
 * @Author: zyf
 * @Date: 2018/10/23 15:15
 */

public interface ISlideshowService
{
    DataResponse<CountRecords<SlideshowDTO>> query(SlideshowQueryRequest request);

    DataResponse<SlideshowDTO> get(String id);

    void create(SlideshowMaintainRequest request);

    void modify(SlideshowMaintainRequest request);

    void delete(SlideshowMaintainRequest request);
}
