package com.todaysoft.ghealth.service;

import com.todaysoft.ghealth.DTO.ProductDTO;
import com.todaysoft.ghealth.model.searcher.ProductSearcher;

import java.util.List;

/**
 * @Author: ljl
 * @Date: 2018/10/20 0020 15:39
 */
public interface IProductService
{

    List<ProductDTO> indexList(ProductSearcher searcher);

    List<ProductDTO> list(ProductSearcher searcher);

    ProductDTO get(String id);
}
