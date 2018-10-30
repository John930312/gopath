package com.todaysoft.ghealth.service;

import com.todaysoft.ghealth.DTO.Questionnaire;

import java.util.List;

/**
 * @Author: ljl
 * @Date: 2018/10/19 0019 17:00
 */
public interface IQuestionnaireService
{
    List<Questionnaire> list(String id);
}
