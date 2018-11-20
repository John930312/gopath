package com.todaysoft.ghealth.request;

import java.util.List;

/**
 * @Author: ljl
 * @Date: 2018/11/20 0020 14:10
 */
public class SampleBoxMaintainRequest extends MaintainRequest
{
    private String agencyId;
    
    private Integer type;//采样盒新增类型：0-系统下单；1-后台新增；
    
    private List<String> sampleBoxCode;
    
    public String getAgencyId()
    {
        return agencyId;
    }
    
    public void setAgencyId(String agencyId)
    {
        this.agencyId = agencyId;
    }
    
    public Integer getType()
    {
        return type;
    }
    
    public void setType(Integer type)
    {
        this.type = type;
    }
    
    public List<String> getSampleBoxCode()
    {
        return sampleBoxCode;
    }
    
    public void setSampleBoxCode(List<String> sampleBoxCode)
    {
        this.sampleBoxCode = sampleBoxCode;
    }
}
