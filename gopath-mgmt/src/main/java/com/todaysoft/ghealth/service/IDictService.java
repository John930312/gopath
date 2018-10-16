package com.todaysoft.ghealth.service;

import com.todaysoft.ghealth.DTO.Dict;

import java.util.List;

/**
 * @Author: ljl
 * @Date: 2018/8/28 0028 17:17
 */
public interface IDictService
{
    List<Dict> getDictsByCategory(String category);

    Dict getDictByCategoryAndValue(String category, String dictValue);
}
