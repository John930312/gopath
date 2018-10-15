package com.todaysoft.ghealth.utils;

import com.todaysoft.ghealth.service.ISequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.todaysoft.ghealth.DTO.SequenceDTO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Component
public class SerialNumber
{
    @Autowired
    private ISequenceService sequenceService;
    
    private Integer INCREMENT = 1;
    
    public String getCode(String name)
    {
        String result = null;
        SequenceDTO sequence = sequenceService.get(name);
        String prefixCode = new SimpleDateFormat("yyyyMMdd").format(new Date());
        if (Objects.isNull(sequence))
        {
            Integer currentValue = 1;
            SequenceDTO data = new SequenceDTO();
            data.setName(name);
            data.setId(IdGen.uuid());
            data.setIncrement(INCREMENT);
            data.setCurrentValue(currentValue);
            sequenceService.create(data);
            result = prefixCode + String.format("%03d", currentValue);
        }
        else
        {
            if (!Objects.isNull(sequence.getBatchDate()))
            {
                double distanceOfTwoDate = getDistanceOfTwoDate(sequence.getBatchDate(), new Date());
                if (distanceOfTwoDate > 0)
                {
                    sequenceService.setDefaultCurrentValue(name);
                    sequenceService.updateBatchDate(name);
                }
            }
            SequenceDTO newSequence = sequenceService.get(name);
            Integer code = newSequence.getCurrentValue() + newSequence.getIncrement();
            SequenceDTO data = new SequenceDTO();
            data.setCurrentValue(code);
            data.setName(name);
            sequenceService.setCurrentVal(data);
            result = prefixCode + String.format("%03d", code);
        }
        return result;
    }
    
    public double getDistanceOfTwoDate(Date before, Date after)
    {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
    }
    
}
