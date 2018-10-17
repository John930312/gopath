package com.todaysoft.ghealth.service.impl;


import com.hsgene.restful.response.DataResponse;

import com.todaysoft.ghealth.DTO.AreaDTO;
import com.todaysoft.ghealth.mybatis.mapper.AreaMapper;
import com.todaysoft.ghealth.request.AreaQueryRequest;
import com.todaysoft.ghealth.service.IAreaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: ljl
 * @Date: 2018/9/3 0003 14:58
 */
@Service
public class AreaService implements IAreaService
{
    @Autowired
    private AreaMapper areaMapper;

    private Map<String, AreaDTO> cache = new HashMap<String, AreaDTO>();

    @Override
    public String getDistrictName(String id)
    {
        if (StringUtils.isEmpty(id))
        {
            return "";
        }

        AreaDTO district = cache.get(id);

        if (null == district)
        {
            district = areaMapper.get(id);

            if (null != district)
            {
                cache.put(id, district);
            }
        }

        return null == district ? "" : district.getName();
    }

    @Override
    public DataResponse<List<AreaDTO>> list(AreaQueryRequest request)
    {
        List<AreaDTO> datas;

        if (null != request.getParentId())
        {
            datas = areaMapper.findByParentId(request.getParentId());
        }
        else
        {
            datas = areaMapper.findProvince();
        }

        List<AreaDTO> areas = new ArrayList<>();
        AreaDTO area;

        if (null != datas && datas.size() > 0)
        {
            for (AreaDTO data : datas)
            {
                area = new AreaDTO();
                BeanUtils.copyProperties(data, area);
                areas.add(area);
            }
        }
        return new DataResponse<>(areas);
    }
}
