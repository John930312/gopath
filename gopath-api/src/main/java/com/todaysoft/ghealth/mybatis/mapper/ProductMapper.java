package com.todaysoft.ghealth.mybatis.mapper;

import com.todaysoft.ghealth.mybatis.model.Product;
import com.todaysoft.ghealth.mybatis.model.query.ProductQuery;

import java.util.List;

public interface ProductMapper
{
    long count(ProductQuery query);

    List<Product> query(ProductQuery query);

    void create(Product data);

    Product get(String id);

    void modify(Product data);

    List<Product> weChatQuery(ProductQuery query);

    List<Product> list(ProductQuery query);
}
