package com.todaysoft.ghealth.service.wrapper;

import com.todaysoft.ghealth.DTO.AgencyDTO;
import com.todaysoft.ghealth.model.Agency;
import org.springframework.stereotype.Component;

/**
 * @Author: zyf
 * @Date: 2018/10/17 10:22
 */

@Component
public class AgencyWrapper extends Wrapper<AgencyDTO, Agency>
{

    @Override
    protected String[] getCopyIgnoreProperties() {
        return new String[]{"createTime"};
    }

    @Override
    protected void setCopyIgnoreProperties(AgencyDTO source, Agency target)
    {
        target.setCreateTime(parseDate(source.getCreateTime()));
    }

}
