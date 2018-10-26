package com.todaysoft.ghealth.service.impl;

import com.hsgene.restful.response.DataResponse;
import com.hsgene.restful.util.CountRecords;
import com.todaysoft.ghealth.DTO.Questionnaire;
import com.todaysoft.ghealth.DTO.SlideshowDTO;
import com.todaysoft.ghealth.mybatis.mapper.SlideshowMapper;
import com.todaysoft.ghealth.mybatis.model.Slideshow;
import com.todaysoft.ghealth.mybatis.model.SlideshowQuestionnaire;
import com.todaysoft.ghealth.mybatis.model.query.SlideshowQuery;
import com.todaysoft.ghealth.request.SlideshowMaintainRequest;
import com.todaysoft.ghealth.request.SlideshowQueryRequest;
import com.todaysoft.ghealth.service.ISlideshowService;
import com.todaysoft.ghealth.service.paser.SlideshowQUeryParser;
import com.todaysoft.ghealth.service.wrapper.SlideshowWrapper;
import com.todaysoft.ghealth.utils.IdGen;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Author: zyf
 * @Date: 2018/10/23 15:44
 */

@Service
public class SlideshowService implements ISlideshowService
{
    @Autowired
    private SlideshowQUeryParser slideshowQUeryParser;
    
    @Autowired
    private SlideshowWrapper slideshowWrapper;
    
    @Autowired
    private SlideshowMapper slideshowMapper;
    
    @Autowired
    private SlideshowQuestionnaireService slideshowQuestionnaireService;
    
    @Override
    public DataResponse<CountRecords<SlideshowDTO>> query(SlideshowQueryRequest request)
    {
        SlideshowQuery query = slideshowQUeryParser.parse(request);
        CountRecords<SlideshowDTO> data = new CountRecords<>();
        
        if (request.isCount())
        {
            long count = slideshowMapper.count(query);
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
        
        List<Slideshow> records = slideshowMapper.query(query);
        data.setRecords(slideshowWrapper.wrap(records));
        return new DataResponse<>(data);
    }
    
    @Override
    public DataResponse<SlideshowDTO> get(String id)
    {
        return new DataResponse<>(slideshowWrapper.wrap(slideshowMapper.get(id)));
    }
    
    @Override
    @Transactional
    public void create(SlideshowMaintainRequest request)
    {
        Slideshow slideshow = new Slideshow();
        slideshow.setId(IdGen.uuid());
        slideshow.setName(request.getName());
        slideshow.setPictureUrl(request.getPictureUrl());
        slideshow.setCreateTime(new Date());
        slideshow.setDeleted(false);
        slideshowMapper.create(slideshow);
        

        List<SlideshowQuestionnaire> slideshowQuestionnaireList = new ArrayList<>();
        if (!StringUtils.isEmpty(request.getQuestionnairePlatForm()))
        {
            String a = request.getQuestionnairePlatForm();
            String[] questionnaires = a.split(",");
            for (int i = 0; i < questionnaires.length; i++)
            {
                SlideshowQuestionnaire slideshowQuestionnaire = new SlideshowQuestionnaire();
                Slideshow slideshow1 = new Slideshow();
                slideshow1.setId(slideshow.getId());
                slideshowQuestionnaire.setSlideshow(slideshow1);
                
                Questionnaire questionnaire = new Questionnaire();
                questionnaire.setId(questionnaires[i]);
                slideshowQuestionnaire.setQuestionnaire(questionnaire);
                
                slideshowQuestionnaireList.add(slideshowQuestionnaire);
            }
            slideshowQuestionnaireService.createSlideshowQuestionnaires(slideshowQuestionnaireList);
        }
        
    }
    
    @Override
    @Transactional
    public void modify(SlideshowMaintainRequest request)
    {
        Slideshow slideshow = slideshowMapper.get(request.getId());
        slideshow.setName(request.getName());
        slideshow.setPictureUrl(request.getPictureUrl());
        slideshowMapper.modify(slideshow);

        slideshowQuestionnaireService.deleteBySlideshowId(request.getId());

        List<SlideshowQuestionnaire> slideshowQuestionnaireList = new ArrayList<>();
        if (!StringUtils.isEmpty(request.getQuestionnairePlatForm()))
        {

            String a = request.getQuestionnairePlatForm();
            String[] questionnaires = a.split(",");
            for (int i = 0; i < questionnaires.length; i++)
            {
                SlideshowQuestionnaire slideshowQuestionnaire = new SlideshowQuestionnaire();
                Slideshow slideshow1 = new Slideshow();
                slideshow1.setId(slideshow.getId());
                slideshowQuestionnaire.setSlideshow(slideshow1);

                Questionnaire questionnaire = new Questionnaire();
                questionnaire.setId(questionnaires[i]);
                slideshowQuestionnaire.setQuestionnaire(questionnaire);

                slideshowQuestionnaireList.add(slideshowQuestionnaire);
            }
            slideshowQuestionnaireService.createSlideshowQuestionnaires(slideshowQuestionnaireList);
        }
    }
    
    @Override
    public void delete(SlideshowMaintainRequest request)
    {
        Slideshow slideshow = slideshowMapper.get(request.getId());
        slideshow.setDeleted(true);
        slideshowMapper.modify(slideshow);
    }
}
