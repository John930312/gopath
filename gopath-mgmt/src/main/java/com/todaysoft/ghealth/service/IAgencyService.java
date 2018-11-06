package com.todaysoft.ghealth.service;

import com.todaysoft.ghealth.model.Agency;
import com.todaysoft.ghealth.model.searcher.AgencySearcher;
import com.todaysoft.ghealth.support.Pager;

/**
 * @Author: zyf
 * @Date: 2018/10/17 9:32
 */

public interface IAgencyService {
    Pager<Agency> pager(AgencySearcher searcher, int pageNo, int pageSize);

    Agency get(String id);

    void create(Agency data);

    void modify(Agency data);

    void delete(Agency data);

    boolean isCodeUnique(String code, String id);

}
