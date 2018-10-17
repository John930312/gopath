package com.todaysoft.ghealth.service.wrapper;


import com.todaysoft.ghealth.DTO.SampleBoxDTO;
import com.todaysoft.ghealth.model.SampleBox;
import org.springframework.stereotype.Component;

/**
 * @Author: ljl
 * @Date: 2018/9/17 0017 10:30
 */
@Component
public class SampleBoxWrapper extends Wrapper<SampleBoxDTO,SampleBox>
{
    @Override
    protected String[] getCopyIgnoreProperties()
    {
        return new String[] {"createTime", "order"};
    }

    @Override
    protected void setCopyIgnoreProperties(SampleBoxDTO source, SampleBox target)
    {
        target.setCreateTime(parseDate(source.getCreateTime()));
    }
}
