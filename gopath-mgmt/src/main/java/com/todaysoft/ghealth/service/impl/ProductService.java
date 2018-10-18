package com.todaysoft.ghealth.service.impl;

import com.hsgene.restful.response.DataResponse;
import com.hsgene.restful.util.CountRecords;
import com.todaysoft.ghealth.DTO.ProductDTO;
import com.todaysoft.ghealth.config.core.AccountHolder;
import com.todaysoft.ghealth.gateway.Gateway;
import com.todaysoft.ghealth.model.Product;
import com.todaysoft.ghealth.model.searcher.ProductSearcher;
import com.todaysoft.ghealth.request.ProductMaintainRequest;
import com.todaysoft.ghealth.request.ProductQueryRequest;
import com.todaysoft.ghealth.service.IProductService;
import com.todaysoft.ghealth.service.wrapper.ProductWrapper;
import com.todaysoft.ghealth.support.Pager;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class ProductService implements IProductService
{
    @Autowired
    private Gateway gateway;
    
    @Autowired
    private ProductWrapper productWrapper;
    
    @Autowired
    private AccountHolder accountHolder;
    
    @Override
    public Pager<Product> pager(ProductSearcher searcher, int pageNo, int pageSize)
    {
        ProductQueryRequest request = new ProductQueryRequest();
        BeanUtils.copyProperties(searcher, request);
        request.setEnabledMatch(false);
        request.setCount(true);
        request.setLimit(pageSize);
        request.setOffset((pageNo - 1) * pageSize);
        DataResponse<CountRecords<ProductDTO>> response =
            gateway.post("/product/pager", request, new ParameterizedTypeReference<DataResponse<CountRecords<ProductDTO>>>()
            {
            });
        if (null == response.getData() || null == response.getData().getCount())
        {
            throw new IllegalStateException();
        }
        Pager<Product> pager = new Pager<>(pageNo, pageSize, response.getData().getCount().intValue());
        pager.setRecords(productWrapper.wrap(response.getData().getRecords()));
        return pager;
    }
    
    @Override
    public void create(Product data)
    {
        ProductMaintainRequest request = new ProductMaintainRequest();
        BeanUtils.copyProperties(data, request);
        request.setOperatorName(accountHolder.get().getName());
        gateway.post("/product/create", request);
    }
    
    @Override
    public boolean isCodeUnique(String id, String code)
    {
        ProductMaintainRequest request = new ProductMaintainRequest();
        request.setId(id);
        request.setCode(code);
        DataResponse<Boolean> response = gateway.post("/product/unique/code", request, new ParameterizedTypeReference<DataResponse<Boolean>>()
        {
        });
        
        if (null == response.getData())
        {
            return false;
        }
        
        return response.getData().booleanValue();
    }

    @Override
    public Product get(String id)
    {
        ProductMaintainRequest request = new ProductMaintainRequest();
        request.setId(id);
        DataResponse<ProductDTO> response = gateway.post("/product/details",
                request,
                new ParameterizedTypeReference<DataResponse<ProductDTO>>()
                {
                });
        return productWrapper.wrap(response.getData());
    }

    @Override
    public void modify(Product data)
    {
        ProductMaintainRequest request = new ProductMaintainRequest();
        BeanUtils.copyProperties(data, request);
        if (null != request.getEndTime())
        {
            Calendar c = Calendar.getInstance();
            c.setTime(request.getEndTime());
            c.add(Calendar.DAY_OF_MONTH, 1);
            c.add(Calendar.SECOND, -1);
            request.setEndTime(c.getTime());
        }
        request.setOperatorName(accountHolder.get().getName());
        gateway.post("/product/modify", request);
    }
}
