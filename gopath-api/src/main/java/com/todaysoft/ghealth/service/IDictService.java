package com.todaysoft.ghealth.service;

import com.hsgene.restful.response.DataResponse;
import com.todaysoft.ghealth.DTO.Dict;
import com.todaysoft.ghealth.request.DictQueryRequest;

import java.util.List;

/**
 * @Author: ljl
 * @Date: 2018/8/29 0029 9:06
 */
public interface IDictService
{
    DataResponse<List<Dict>> getDictsByCategory(DictQueryRequest request);

    DataResponse<Dict> findByCategoryAndValue(DictQueryRequest request);
}
