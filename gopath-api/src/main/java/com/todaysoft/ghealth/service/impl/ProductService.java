package com.todaysoft.ghealth.service.impl;


import com.hsgene.restful.response.DataResponse;
import com.hsgene.restful.util.CountRecords;
import com.todaysoft.ghealth.DTO.ProductDTO;
import com.todaysoft.ghealth.mybatis.mapper.ProductMapper;
import com.todaysoft.ghealth.mybatis.model.Product;
import com.todaysoft.ghealth.mybatis.model.query.ProductQuery;
import com.todaysoft.ghealth.request.ProductMaintainRequest;
import com.todaysoft.ghealth.request.ProductQueryRequest;
import com.todaysoft.ghealth.service.IProductService;
import com.todaysoft.ghealth.service.wrapper.ProductWrapper;
import com.todaysoft.ghealth.utils.IdGen;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class ProductService implements IProductService
{
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductWrapper productWrapper;

    @Override
    public DataResponse<CountRecords<ProductDTO>> pager(ProductQueryRequest request)
    {
        ProductQuery query = new ProductQuery();
        BeanUtils.copyProperties(request,query);
        CountRecords<ProductDTO> data = new CountRecords<>();

        if (request.isCount())
        {
            long count = productMapper.count(query);
            data.setCount(count);

            if (0 == count)
            {
                data.setRecords(Collections.emptyList());
                return new DataResponse<>(data);
            }

            if (null != request.getLimit() && null != request.getOffset() && request.getOffset().intValue() >= count)
            {
                int offset;
                int limit = request.getLimit().intValue();
                int mod = (int) count % limit;

                if (0 == mod)
                {
                    offset = (((int) count / limit) - 1) * limit;
                }
                else
                {
                    offset = ((int) count / limit) * limit;
                }

                query.setOffset(offset);
            }
        }
        List<Product> records =new ArrayList<Product>();
        if (request.getEnabledMatch()){
             records = productMapper.weChatQuery(query);
        }else {
            records = productMapper.query(query);
        }
        data.setRecords(productWrapper.wrap(records));
        return new DataResponse<>(data);
    }

    @Override
    @Transactional
    public void create(ProductMaintainRequest request)
    {
        Product data = new Product();
        BeanUtils.copyProperties(request,data);
        data.setId(IdGen.uuid());
        data.setCreateTime(new Date());
        data.setDeleted(false);
        productMapper.create(data);
    }

    @Override
    public DataResponse<Boolean> isCodeUnique(ProductMaintainRequest request)
    {
        ProductQuery query = new ProductQuery();
        query.setCode(request.getCode());
        query.setCodeExactMatches(true);

        if (!StringUtils.isEmpty(request.getId()))
        {
            query.setExcludeKeys(Collections.singleton(request.getId()));
        }
        long count = productMapper.count(query);
        return new DataResponse<Boolean>(count == 0);
    }

    @Override
    public DataResponse<ProductDTO> get(String id)
    {
        return new DataResponse<ProductDTO>(productWrapper.wrap(productMapper.get(id)));
    }

    @Override
    @Transactional
    public void modify(ProductMaintainRequest request)
    {
        Product data = new Product();
        BeanUtils.copyProperties(request,data);
        data.setUpdateTime(new Date());
        productMapper.modify(data);
    }
}
