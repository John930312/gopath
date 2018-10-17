package com.todaysoft.ghealth.service.wrapper;

import com.todaysoft.ghealth.DTO.ProductDTO;
import com.todaysoft.ghealth.mybatis.model.Product;
import org.springframework.stereotype.Component;

/**
 * @Author: ljl
 * @Date: 2018/8/29 0029 14:32
 */
@Component
public class ProductWrapper extends Wrapper<Product, ProductDTO>
{
    @Override
    protected String[] getCopyIgnoreProperties()
    {
        return new String[] {"startTime", "endTime", "createTime", "updateTime", "deleteTime"};
    }
    
    @Override
    protected void setCopyIgnoreProperties(Product source, ProductDTO target)
    {
        target.setStartTime(formatDate(source.getStartTime()));
        target.setEndTime(formatDate(source.getEndTime()));
        target.setCreateTime(formatDate(source.getCreateTime()));
        target.setUpdateTime(formatDate(source.getUpdateTime()));
        target.setDeleteTime(formatDate(source.getDeleteTime()));
    }
}
