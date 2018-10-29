package com.todaysoft.ghealth.service.impl;

import com.hsgene.restful.response.DataResponse;
import com.hsgene.restful.util.CountRecords;
import com.todaysoft.ghealth.DTO.SlideshowDTO;
import com.todaysoft.ghealth.gateway.Gateway;
import com.todaysoft.ghealth.service.ISlideshowService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zyf
 * @Date: 2018/10/29 10:54
 */


@Service
public class SlideshowService implements ISlideshowService {
    @Autowired
    private Gateway gateway;

    public List<SlideshowDTO> indexList(){
        DataResponse<CountRecords<SlideshowDTO>> response =
                gateway.post("/wechat/slideshow/indexList", null, new ParameterizedTypeReference<DataResponse<CountRecords<SlideshowDTO>>>()
                {
                });
        return response.getData().getRecords();
    }
}
