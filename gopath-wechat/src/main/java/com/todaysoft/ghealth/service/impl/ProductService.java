package com.todaysoft.ghealth.service.impl;

import com.hsgene.restful.response.DataResponse;
import com.todaysoft.ghealth.DTO.ProductDTO;
import com.todaysoft.ghealth.gateway.Gateway;
import com.todaysoft.ghealth.model.searcher.ProductSearcher;
import com.todaysoft.ghealth.request.ProductQueryRequest;
import com.todaysoft.ghealth.service.IProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @Author: ljl
 * @Date: 2018/10/20 0020 15:39
 */
@Service
public class ProductService implements IProductService
{
    @Autowired
    private Gateway gateway;

    @Override
    public List<ProductDTO> indexList(ProductSearcher searcher)
    {
        ProductQueryRequest request = new ProductQueryRequest();
        BeanUtils.copyProperties(searcher, request);
        DataResponse<List<ProductDTO>> response = gateway.post("/wechat/product/indexList", request, new ParameterizedTypeReference<DataResponse<List<ProductDTO>>>()
        {
        });
        return response.getData();
    }

    @Override
    public List<ProductDTO> list(ProductSearcher searcher)
    {
        ProductQueryRequest request = new ProductQueryRequest();
        BeanUtils.copyProperties(searcher, request);
        DataResponse<List<ProductDTO>> response = gateway.post("/wechat/product/list", request, new ParameterizedTypeReference<DataResponse<List<ProductDTO>>>()
        {
        });
        return response.getData();
    }

    @Override
    public ProductDTO get(String id)
    {
        DataResponse<ProductDTO> response = gateway.get("/wechat/product/get/{id}", new ParameterizedTypeReference<DataResponse<ProductDTO>>()
        {
        }, id);

        if (Objects.isNull(response.getData()))
        {
            return new ProductDTO();
        }

        return response.getData();
    }
}
