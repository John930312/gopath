package com.todaysoft.ghealth.service.wrapper;

import com.todaysoft.ghealth.DTO.ProductDTO;
import com.todaysoft.ghealth.DTO.Questionnaire;
import com.todaysoft.ghealth.mybatis.mapper.ProductQuestionnaireMapper;
import com.todaysoft.ghealth.mybatis.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: ljl
 * @Date: 2018/8/29 0029 14:32
 */
@Component
public class ProductWrapper extends Wrapper<Product, ProductDTO>
{
    @Autowired
    private ProductQuestionnaireMapper productQuestionnaireMapper;

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
        target.setQuestionnaires(productQuestionnaireMapper.getQuestionnairesByProductId(source.getId()));
    }
}
