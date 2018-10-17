package com.todaysoft.ghealth.service.paser;


import com.todaysoft.ghealth.mybatis.model.query.OrderQuery;
import com.todaysoft.ghealth.request.OrderQueryRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: ljl
 * @Date: 2018/9/17 0017 16:33
 */
@Component
public class OrderQueryParser extends QueryParser<OrderQueryRequest,OrderQuery>
{
    @Override
    protected String[] getCopyIgnoreProperties()
    {
        return new String[] {"startTime","endTime"};
    }

    @Override
    protected void setCopyIgnoreProperties(OrderQueryRequest source, OrderQuery target)
    {
        if (StringUtils.isNotEmpty(source.getStartTime()))
        {
            target.setStartTime(transferLongToDate("yyyy-MM-dd 00:00:00", Long.valueOf(source.getStartTime())));
        }
        if (StringUtils.isNotEmpty(source.getEndTime()))
        {
            target.setEndTime(transferLongToDate("yyyy-MM-dd 23:59:59", Long.valueOf(source.getEndTime())));
        }
    }

    private String transferLongToDate(String dateFormat, Long millSec)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = new Date(millSec);
        return sdf.format(date);
    }
}
