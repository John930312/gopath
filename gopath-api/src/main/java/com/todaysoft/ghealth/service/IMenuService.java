package com.todaysoft.ghealth.service;

import com.hsgene.restful.response.DataResponse;
import com.hsgene.restful.util.CountRecords;
import com.todaysoft.ghealth.DTO.MenuDTO;

public interface IMenuService
{
    DataResponse<CountRecords<MenuDTO>> getRootMenus();
}
