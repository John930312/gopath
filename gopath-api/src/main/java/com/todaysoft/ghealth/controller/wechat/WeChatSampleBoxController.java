package com.todaysoft.ghealth.controller.wechat;

import com.hsgene.restful.response.DataResponse;
import com.todaysoft.ghealth.DTO.OrderDTO;
import com.todaysoft.ghealth.DTO.SampleBoxDTO;
import com.todaysoft.ghealth.request.MainSampleBoxRequest;
import com.todaysoft.ghealth.request.MaintainOrderRequest;
import com.todaysoft.ghealth.service.ISampleBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xjw
 * @Date: 2018/10/17 17:23
 */
@RestController
@RequestMapping("/wechat/sampleBox")
public class WeChatSampleBoxController
{
    @Autowired
    private ISampleBoxService sampleBoxService;
    
    @PostMapping("/bind")
    public void bind(@RequestBody MaintainOrderRequest request)
    {
        sampleBoxService.bind(request);
    }
    
    @PostMapping("/detail")
    public DataResponse<OrderDTO> sampleBoxDetails(@RequestBody MainSampleBoxRequest request)
    {
        return sampleBoxService.sampleBoxDetails(request);
    }

    @PostMapping("/getOrderDTOBySampleBoxCode")
    public DataResponse<SampleBoxDTO> getOrderDTOBySampleBoxCode(@RequestBody MainSampleBoxRequest request)
    {
        return sampleBoxService.getOrderDTOBySampleBoxCode(request);
    }
}
