package com.todaysoft.ghealth.controller.mgmt;

import com.hsgene.restful.response.DataResponse;
import com.todaysoft.ghealth.DTO.Dict;
import com.todaysoft.ghealth.request.DictQueryRequest;
import com.todaysoft.ghealth.service.IDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: ljl
 * @Date: 2018/8/29 0029 9:04
 */
@RestController
@RequestMapping("/dict")
public class DictController
{
    @Autowired
    private IDictService dictService;
    
    @RequestMapping("/getDictsByCategory")
    public DataResponse<List<Dict>> getDictsByCategory(@RequestBody DictQueryRequest request)
    {
        return dictService.getDictsByCategory(request);
    }
    
    @RequestMapping("/getDictByCategoryAndValue")
    public DataResponse<Dict> getDictByCategoryAndValue(@RequestBody DictQueryRequest request)
    {
        return dictService.findByCategoryAndValue(request);
    }
}
