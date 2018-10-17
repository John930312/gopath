package com.todaysoft.ghealth.service.wrapper;


import com.todaysoft.ghealth.DTO.SampleBoxDTO;
import com.todaysoft.ghealth.mybatis.model.SampleBox;
import org.springframework.stereotype.Component;

/**
 * @Author: xjw
 * @Date: 2018/9/3 15:29
 */
@Component
public class SampleBoxWrapper extends Wrapper<SampleBox, SampleBoxDTO>
{
    @Override
    public String[] getCopyIgnoreProperties()
    {
        return new String[] {"createTime"};
    }
    
    @Override
    public void setCopyIgnoreProperties(SampleBox source, SampleBoxDTO target)
    {
        target.setCreateTime(formatDate(source.getCreateTime()));
    }
}
