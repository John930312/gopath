package com.todaysoft.ghealth.controller.wechat;

import com.hsgene.restful.response.DataResponse;
import com.hsgene.restful.util.CountRecords;
import com.todaysoft.ghealth.DTO.SlideshowDTO;
import com.todaysoft.ghealth.service.ISlideshowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zyf
 * @Date: 2018/10/29 0022 13:54
 */

@RestController
@RequestMapping("/wechat/slideshow")
public class WeChatSlideshowController
{
    @Autowired
    private ISlideshowService slideshowService;
    
    @RequestMapping("/indexList")
    public DataResponse<CountRecords<SlideshowDTO>> indexList()
    {
        return slideshowService.indexList();
    }
}
