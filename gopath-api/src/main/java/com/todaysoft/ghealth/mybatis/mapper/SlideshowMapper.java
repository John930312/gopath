package com.todaysoft.ghealth.mybatis.mapper;

import com.todaysoft.ghealth.mybatis.model.Slideshow;
import com.todaysoft.ghealth.mybatis.model.query.SlideshowQuery;

import java.util.List;

public interface SlideshowMapper
{
    long count(SlideshowQuery query);
    
    List<Slideshow> query(SlideshowQuery query);

    Slideshow get(String id);

    void create(Slideshow record);

    int modify(Slideshow record);
}
