package com.todaysoft.ghealth.controller.mgmt;

import com.hsgene.restful.response.DataResponse;
import com.hsgene.restful.util.CountRecords;
import com.todaysoft.ghealth.DTO.SlideshowDTO;
import com.todaysoft.ghealth.request.SlideshowMaintainRequest;
import com.todaysoft.ghealth.request.SlideshowQueryRequest;
import com.todaysoft.ghealth.service.ISlideshowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: zyf
 * @Date: 2018/10/23 15:07
 */
@RestController
@RequestMapping("/slideshow")
public class SlideshowController
{
    @Autowired
    private ISlideshowService slideshowService;
    
    @PostMapping("/pager")
    public DataResponse<CountRecords<SlideshowDTO>> pager(@RequestBody SlideshowQueryRequest request)
    {
        return slideshowService.query(request);
    }
    
    @RequestMapping("/get/{id}")
    public DataResponse<SlideshowDTO> get(@PathVariable String id)
    {
        return slideshowService.get(id);
    }
    
    @RequestMapping("/create")
    public void create(@RequestBody SlideshowMaintainRequest request)
    {
        slideshowService.create(request);
    }
    
    @RequestMapping("/modify")
    public void modify(@RequestBody SlideshowMaintainRequest request)
    {
        slideshowService.modify(request);
    }
    
    @RequestMapping("/delete")
    public void delete(@RequestBody SlideshowMaintainRequest request)
    {
        slideshowService.delete(request);
    }
}
