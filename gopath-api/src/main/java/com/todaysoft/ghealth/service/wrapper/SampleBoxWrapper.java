package com.todaysoft.ghealth.service.wrapper;


import com.todaysoft.ghealth.DTO.SampleBoxDTO;
import com.todaysoft.ghealth.mybatis.model.SampleBox;
import com.todaysoft.ghealth.service.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: xjw
 * @Date: 2018/9/3 15:29
 */
@Component
public class SampleBoxWrapper extends Wrapper<SampleBox, SampleBoxDTO>
{
    @Autowired
    private IAreaService areaService;

    @Override
    public String[] getCopyIgnoreProperties()
    {
        return new String[] {"createTime"};
    }
    
    @Override
    public void setCopyIgnoreProperties(SampleBox source, SampleBoxDTO target)
    {
        target.setProvinceText(areaService.getDistrictName(source.getProvince()));
        target.setCityText(areaService.getDistrictName(source.getCity()));
        target.setCreateTime(formatDate(source.getCreateTime()));
    }
}
