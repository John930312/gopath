package com.todaysoft.ghealth.controller;


import com.hsgene.restful.response.DataResponse;
import com.todaysoft.ghealth.DTO.AreaDTO;
import com.todaysoft.ghealth.request.AreaQueryRequest;
import com.todaysoft.ghealth.service.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: xjw
 * @Date: 2018/10/14 9:11
 */
@RestController
@RequestMapping("/area")
public class AreaController
{
    @Autowired
    private IAreaService areaService;

    @RequestMapping("/findProvince")
    private DataResponse<List<AreaDTO>> findProvince(@RequestBody AreaQueryRequest request)
    {
        return areaService.list(request);
    }

    @RequestMapping("/findByParentId")
    private DataResponse<List<AreaDTO>> findByParentId(@RequestBody AreaQueryRequest request)
    {
        return areaService.list(request);
    }
}
