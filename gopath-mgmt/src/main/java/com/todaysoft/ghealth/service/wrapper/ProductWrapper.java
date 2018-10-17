package com.todaysoft.ghealth.service.wrapper;

import com.todaysoft.ghealth.DTO.ProductDTO;
import com.todaysoft.ghealth.model.Product;
import org.springframework.stereotype.Component;

/**
 * @Author: ljl
 * @Date: 2018/8/29 0029 14:05
 */
@Component
public class ProductWrapper extends Wrapper<ProductDTO, Product>
{
    @Override
    protected String[] getCopyIgnoreProperties()
    {
        return new String[] {"startTime", "endTime", "createTime", "updateTime", "deleteTime"};
    }
    
    @Override
    protected void setCopyIgnoreProperties(ProductDTO source, Product target)
    {
        target.setStartTime(parseDate(source.getStartTime()));
        target.setEndTime(parseDate(source.getEndTime()));
        target.setCreateTime(parseDate(source.getCreateTime()));
        target.setUpdateTime(parseDate(source.getUpdateTime()));
        target.setDeleteTime(parseDate(source.getDeleteTime()));
    }
}
