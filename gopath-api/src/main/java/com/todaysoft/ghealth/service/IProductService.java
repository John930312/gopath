package com.todaysoft.ghealth.service;

import com.hsgene.restful.response.DataResponse;
import com.hsgene.restful.util.CountRecords;
import com.todaysoft.ghealth.DTO.ProductDTO;
import com.todaysoft.ghealth.request.ProductMaintainRequest;
import com.todaysoft.ghealth.request.ProductQueryRequest;

import java.util.List;

public interface IProductService
{
    DataResponse<CountRecords<ProductDTO>> pager(ProductQueryRequest request);

    void create(ProductMaintainRequest request);

    DataResponse<Boolean> isCodeUnique(ProductMaintainRequest request);

    DataResponse<ProductDTO> get(String id);

    void modify(ProductMaintainRequest request);

    DataResponse<List<ProductDTO>> list(ProductQueryRequest request);

    DataResponse<List<ProductDTO>> indexList(ProductQueryRequest request);
}
