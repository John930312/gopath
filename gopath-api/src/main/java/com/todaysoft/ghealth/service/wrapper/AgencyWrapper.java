package com.todaysoft.ghealth.service.wrapper;

import com.todaysoft.ghealth.DTO.AgencyDTO;
import com.todaysoft.ghealth.mybatis.model.Agency;
import com.todaysoft.ghealth.service.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: zyf
 * @Date: 2018/10/17 13:43
 */

@Component
public class AgencyWrapper extends Wrapper<Agency, AgencyDTO>
{
    @Autowired
    private IAreaService areaService;
    
    @Override
    protected String[] getCopyIgnoreProperties()
    {
        return new String[] {"createTime"};
    }
    
    @Override
    protected void setCopyIgnoreProperties(Agency source, AgencyDTO target)
    {
        target.setProvinceText(areaService.getDistrictName(source.getProvince()));
        target.setCityText(areaService.getDistrictName(source.getCity()));
        target.setCreateTime(formatDate(source.getCreateTime()));
    }
}
