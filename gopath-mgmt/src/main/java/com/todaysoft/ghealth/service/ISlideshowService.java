package com.todaysoft.ghealth.service;

import com.todaysoft.ghealth.model.Slideshow;
import com.todaysoft.ghealth.model.searcher.SlideshowSearcher;
import com.todaysoft.ghealth.support.Pager;

/**
 * @Author: zyf
 * @Date: 2018/10/23 13:41
 */

public interface ISlideshowService
{
    Pager<Slideshow> pager(SlideshowSearcher searcher, int pageNo, int pageSize);
    
    void create(Slideshow data);
    
    Slideshow get(String id);
    
    void modify(Slideshow data);
    
    void delete(Slideshow data);
}
