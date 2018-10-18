package com.todaysoft.ghealth.service;

import com.todaysoft.ghealth.model.Product;
import com.todaysoft.ghealth.model.searcher.ProductSearcher;
import com.todaysoft.ghealth.support.Pager;

public interface IProductService
{
    Pager<Product> pager(ProductSearcher searcher, int pageNo, int pageSize);
    
    void create(Product data);
    
    boolean isCodeUnique(String id, String code);

    Product get(String id);

    void modify(Product data);
}
