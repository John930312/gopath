package com.todaysoft.ghealth.controller.wechat;

import com.hsgene.restful.response.DataResponse;
import com.todaysoft.ghealth.DTO.ProductDTO;
import com.todaysoft.ghealth.request.ProductQueryRequest;
import com.todaysoft.ghealth.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: ljl
 * @Date: 2018/10/22 0022 13:54
 */
@RestController
@RequestMapping("/wechat/product")
public class WeChatProductController
{
    @Autowired
    private IProductService service;

    @RequestMapping("/indexList")
    public DataResponse<List<ProductDTO>> indexList(@RequestBody ProductQueryRequest request)
    {
        return service.indexList(request);
    }

    @RequestMapping("/list")
    public DataResponse<List<ProductDTO>> list(@RequestBody ProductQueryRequest request)
    {
        return service.list(request);
    }

    @RequestMapping("/get/{id}")
    public DataResponse<ProductDTO> get(@PathVariable String id)
    {
        return service.get(id);
    }
}
