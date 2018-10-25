package com.todaysoft.ghealth.controller;

import com.hsgene.restful.response.DataResponse;
import com.hsgene.restful.util.CountRecords;
import com.todaysoft.ghealth.DTO.OrderDTO;
import com.todaysoft.ghealth.request.MainSampleBoxRequest;
import com.todaysoft.ghealth.request.MaintainOrderRequest;
import com.todaysoft.ghealth.request.OrderQueryRequest;
import com.todaysoft.ghealth.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    
    @RequestMapping("/isUniqueSampleBoxCode")
    public DataResponse<Boolean> isUniqueSampleBoxCode(@RequestBody MainSampleBoxRequest request)
    {
        return orderService.isUniqueSampleBoxCode(request);
    }

    @RequestMapping("/getMyOrder")
    public DataResponse<List<OrderDTO>> getMyOrder(@RequestBody OrderQueryRequest request)
    {
        return orderService.getByOpenid(request.getOpenId());
    }
    
    @PostMapping("/create")
    public DataResponse<String> create(@RequestBody MaintainOrderRequest request)
    {
        return orderService.create(request);
    }

    @PostMapping("/payed")
    public void payed(@RequestBody MaintainOrderRequest request)
    {
        orderService.payed(request);
    }
    
}
