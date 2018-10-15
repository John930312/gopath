package com.todaysoft.ghealth.service.impl;

import com.todaysoft.ghealth.mybaties.mapper.SequenceMapper;
import com.todaysoft.ghealth.service.ISequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.todaysoft.ghealth.DTO.SequenceDTO;

@Service
public class SequenceService implements ISequenceService
{
    @Autowired
    private SequenceMapper sequenceMapper;
    
    @Override
    public SequenceDTO get(String name)
    {
        return sequenceMapper.get(name);
    }
    
    @Override
    public void create(SequenceDTO data)
    {
        sequenceMapper.create(data);
    }
    
    @Override
    public void setDefaultCurrentValue(String name)
    {
        sequenceMapper.setDefaultCurrentValue(name);
    }
    
    @Override
    public int getSerialNumber(String name)
    {
        return sequenceMapper.getSerialNumber(name);
    }
    
    @Override
    public void updateBatchDate(String name)
    {
        sequenceMapper.updateBatchDate(name);
    }
    
    @Override
    public void setCurrentVal(SequenceDTO data)
    {
        sequenceMapper.setCurrentVal(data);
    }
}
