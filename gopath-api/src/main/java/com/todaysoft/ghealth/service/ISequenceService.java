package com.todaysoft.ghealth.service;


import com.todaysoft.ghealth.DTO.SequenceDTO;

public interface ISequenceService
{
    SequenceDTO get(String name);

    void  create(SequenceDTO data);
    
    void setDefaultCurrentValue(String name);
    
    int getSerialNumber(String name);
    
    void updateBatchDate(String name);
    
    void setCurrentVal(SequenceDTO data);


}
