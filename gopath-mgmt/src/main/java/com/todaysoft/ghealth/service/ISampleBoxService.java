package com.todaysoft.ghealth.service;

import com.todaysoft.ghealth.model.SampleBox;
import com.todaysoft.ghealth.model.searcher.SampleBoxSearcher;
import com.todaysoft.ghealth.support.Pager;

/**
 * @Author: ljl
 * @Date: 2018/11/20 0020 9:33
 */
public interface ISampleBoxService
{
    Pager<SampleBox> pager(SampleBoxSearcher searcher, int pageNo, int pageSize);

    void create(SampleBox data);

    Boolean isUniqueSampleBoxCode(String code);
}
