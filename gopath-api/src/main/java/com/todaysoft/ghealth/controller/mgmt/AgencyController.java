package com.todaysoft.ghealth.controller.mgmt;

import com.hsgene.restful.response.DataResponse;
import com.hsgene.restful.util.CountRecords;
import com.todaysoft.ghealth.DTO.AgencyDTO;
import com.todaysoft.ghealth.request.AgencyMaintainRequest;
import com.todaysoft.ghealth.request.AgencyQueryRequest;
import com.todaysoft.ghealth.service.IAgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: zyf
 * @Date: 2018/10/17 13:43
 */
@RestController
@RequestMapping("/agency")
public class AgencyController
{
    @Autowired
    private IAgencyService agencyService;

    @PostMapping("/pager")
    public DataResponse<CountRecords<AgencyDTO>> pager(@RequestBody AgencyQueryRequest request)
    {
        return agencyService.query(request);
    }

    @RequestMapping("/get/{id}")
    public DataResponse<AgencyDTO> get(@PathVariable String id)
    {
        return agencyService.get(id);
    }

    @RequestMapping("/create")
    public void create(@RequestBody AgencyMaintainRequest request)
    {
        agencyService.create(request);
    }

    @RequestMapping("/modify")
    public void modify(@RequestBody AgencyMaintainRequest request)
    {
        agencyService.modify(request);
    }

    @RequestMapping("/delete")
    public void delete(@RequestBody AgencyMaintainRequest request)
    {
        agencyService.delete(request);
    }
}
