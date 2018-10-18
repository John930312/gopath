package com.todaysoft.ghealth.controller.mgmt;

import com.hsgene.restful.response.DataResponse;
import com.hsgene.restful.util.CountRecords;
import com.todaysoft.ghealth.DTO.ProductDTO;
import com.todaysoft.ghealth.request.ProductMaintainRequest;
import com.todaysoft.ghealth.request.ProductQueryRequest;
import com.todaysoft.ghealth.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController
{
    @Autowired
    private IProductService service;

    @RequestMapping("/pager")
    public DataResponse<CountRecords<ProductDTO>> pager(@RequestBody ProductQueryRequest request)
    {
        return service.pager(request);
    }

    @RequestMapping("/create")
    public void create(@RequestBody ProductMaintainRequest request)
    {
        service.create(request);
    }

    @RequestMapping("/unique/code")
    public DataResponse<Boolean> isCodeUnique(@RequestBody ProductMaintainRequest request)
    {
        return service.isCodeUnique(request);
    }

    @RequestMapping(value = "/details")
    public DataResponse<ProductDTO> getDetails(@RequestBody ProductMaintainRequest request)
    {
        return service.get(request.getId());
    }

    @RequestMapping(value = "/modify")
    public void modify(@RequestBody ProductMaintainRequest request)
    {
       service.modify(request);
    }
}
