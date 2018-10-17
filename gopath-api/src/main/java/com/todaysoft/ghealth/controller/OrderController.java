package com.todaysoft.ghealth.controller;

import com.hsgene.restful.response.DataResponse;
import com.hsgene.restful.util.CountRecords;
import com.todaysoft.ghealth.DTO.OrderDTO;
import com.todaysoft.ghealth.request.MaintainOrderRequest;
import com.todaysoft.ghealth.request.OrderQueryRequest;
import com.todaysoft.ghealth.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: xjw
 * @Date: 2018/10/16 15:11
 */
@RestController
@RequestMapping("/order")
public class OrderController
{
    @Autowired
    private IOrderService orderService;

    @RequestMapping("/get/{id}")
    public DataResponse<OrderDTO> get(@PathVariable String id)
    {
        return orderService.get(id);
    }

    @RequestMapping("/pager")
    public DataResponse<CountRecords<OrderDTO>> pager(@RequestBody OrderQueryRequest request)
    {
        return orderService.pager(request);
    }

    @RequestMapping("/list")
    public DataResponse<List<OrderDTO>> list(@RequestBody OrderQueryRequest request)
    {
        return orderService.list(request);
    }

    @RequestMapping(value = "/modify")
    public void modify(@RequestBody MaintainOrderRequest request)
    {
        orderService.modify(request);
    }

    @RequestMapping("/isUniqueCode/{code}")
    public DataResponse<Boolean> isUniqueCode(@PathVariable String code)
    {
        return orderService.isUniqueCode(code);
    }

}
