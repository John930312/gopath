package com.todaysoft.ghealth.controller.mgmt;

import com.hsgene.restful.response.DataResponse;
import com.hsgene.restful.util.CountRecords;
import com.todaysoft.ghealth.DTO.SampleBoxDTO;
import com.todaysoft.ghealth.request.SampleBoxMaintainRequest;
import com.todaysoft.ghealth.request.SampleBoxQueryRequest;
import com.todaysoft.ghealth.service.ISampleBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ljl
 * @Date: 2018/11/20 0020 9:40
 */
@RestController
@RequestMapping("/sampleBox")
public class SampleBoxController
{
    @Autowired
    private ISampleBoxService sampleBoxService;

    @RequestMapping("/pager")
    public DataResponse<CountRecords<SampleBoxDTO>> pager(@RequestBody SampleBoxQueryRequest request)
    {
        return sampleBoxService.pager(request);
    }

    @RequestMapping("/isUniqueSampleBoxCode/{code}")
    public DataResponse<Boolean> isUniqueSampleBoxCode(@PathVariable String code)
    {
        return sampleBoxService.isUniqueSampleBoxCode(code);
    }

    @RequestMapping("/create")
    public void create(@RequestBody SampleBoxMaintainRequest request)
    {
        sampleBoxService.create(request);
    }
}
