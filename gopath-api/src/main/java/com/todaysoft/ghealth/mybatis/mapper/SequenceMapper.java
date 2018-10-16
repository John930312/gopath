package com.todaysoft.ghealth.mybatis.mapper;

import com.todaysoft.ghealth.DTO.SequenceDTO;

public interface SequenceMapper
{
    void create(SequenceDTO sequence);
    
    SequenceDTO get(String name);
    
    void setDefaultCurrentValue(String name);
    
    int getSerialNumber(String name);
    
    void updateBatchDate(String name);
    
    void setCurrentVal(SequenceDTO sequence);
}