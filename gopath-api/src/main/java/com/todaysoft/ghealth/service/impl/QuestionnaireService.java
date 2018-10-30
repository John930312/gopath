package com.todaysoft.ghealth.service.impl;

import com.hsgene.restful.response.DataResponse;
import com.hsgene.restful.util.CountRecords;
import com.todaysoft.ghealth.DTO.Questionnaire;
import com.todaysoft.ghealth.mybatis.mapper.ProductQuestionnaireMapper;
import com.todaysoft.ghealth.mybatis.mapper.QuestionnaireMapper;
import com.todaysoft.ghealth.mybatis.mapper.SlideshowQuestionnaireMapper;
import com.todaysoft.ghealth.mybatis.model.query.QuestionnaireQuery;
import com.todaysoft.ghealth.mybatis.model.query.SlideshowQuestionnaireQuery;
import com.todaysoft.ghealth.request.QuestionnaireMaintainRequest;
import com.todaysoft.ghealth.request.QuestionnaireQueryRequest;
import com.todaysoft.ghealth.service.IQuestionnaireService;
import com.todaysoft.ghealth.utils.IdGen;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: ljl
 * @Date: 2018/10/18 0018 11:00
 */
@Service
public class QuestionnaireService implements IQuestionnaireService
{
    @Autowired
    private QuestionnaireMapper questionnaireMapper;
    
    @Autowired
    private ProductQuestionnaireMapper productQuestionnaireMapper;

    @Autowired
    private SlideshowQuestionnaireMapper slideshowQuestionnaireMapper;
    
    @Override
    public DataResponse<CountRecords<Questionnaire>> pager(QuestionnaireQueryRequest request)
    {
        QuestionnaireQuery query = new QuestionnaireQuery();
        BeanUtils.copyProperties(request, query);
        CountRecords<Questionnaire> data = new CountRecords<>();
        
        if (request.isCount())
        {
            long count = questionnaireMapper.count(query);
            data.setCount(count);
            
            if (0 == count)
            {
                data.setRecords(Collections.emptyList());
                return new DataResponse<>(data);
            }
            
            if (null != request.getLimit() && null != request.getOffset() && request.getOffset().intValue() >= count)
            {
                int offset;
                int limit = request.getLimit().intValue();
                int mod = (int)count % limit;
                
                if (0 == mod)
                {
                    offset = (((int)count / limit) - 1) * limit;
                }
                else
                {
                    offset = ((int)count / limit) * limit;
                }
                
                query.setOffset(offset);
            }
        }
        List<Questionnaire> records = questionnaireMapper.query(query);
        data.setRecords(records);
        return new DataResponse<>(data);
    }
    
    @Override
    @Transactional
    public void create(Questionnaire data)
    {
        data.setId(IdGen.uuid());
        data.setDeleted(false);
        questionnaireMapper.create(data);
    }
    
    @Override
    public DataResponse<Questionnaire> get(String id)
    {
        return new DataResponse<Questionnaire>(questionnaireMapper.get(id));
    }
    
    @Override
    @Transactional
    public void modify(Questionnaire data)
    {
        questionnaireMapper.modify(data);
    }
    
    @Override
    @Transactional
    public void delete(QuestionnaireMaintainRequest request)
    {
        Questionnaire data = new Questionnaire();
        BeanUtils.copyProperties(request, data);
        questionnaireMapper.modify(data);
        
        productQuestionnaireMapper.deleteByQuestionnaireId(request.getId());
    }
    
    @Override
    public List<Questionnaire> getQuestionnaires(SlideshowQuestionnaireQuery searcher)
    {
        
        return slideshowQuestionnaireMapper.getQuestionnaires(searcher);
    }

    @Override
    public DataResponse<List<Questionnaire>> list(String id)
    {
        List<Questionnaire> records = questionnaireMapper.getListBySlideshowId(id);
        return new DataResponse<>(records);
    }
}
