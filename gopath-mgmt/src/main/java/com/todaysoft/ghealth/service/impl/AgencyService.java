package com.todaysoft.ghealth.service.impl;

import com.hsgene.restful.response.DataResponse;
import com.hsgene.restful.util.CountRecords;
import com.todaysoft.ghealth.DTO.AgencyDTO;
import com.todaysoft.ghealth.config.core.AccountHolder;
import com.todaysoft.ghealth.gateway.Gateway;
import com.todaysoft.ghealth.model.Agency;
import com.todaysoft.ghealth.model.searcher.AgencySearcher;
import com.todaysoft.ghealth.request.AgencyMaintainRequest;
import com.todaysoft.ghealth.request.AgencyQueryRequest;
import com.todaysoft.ghealth.service.IAgencyService;
import com.todaysoft.ghealth.service.wrapper.AgencyWrapper;
import com.todaysoft.ghealth.support.Pager;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @Author: zyf
 * @Date: 2018/10/17 10:04
 */

@Service
public class AgencyService implements IAgencyService
{

    @Autowired
    private Gateway gateway;
    @Autowired
    private AgencyWrapper agencyWrapper;
    @Autowired
    private AccountHolder accountHolder;

    @Override
    public Pager<Agency> pager(AgencySearcher searcher, int pageNo, int pageSize)
    {
        AgencyQueryRequest request = new AgencyQueryRequest();
        BeanUtils.copyProperties(searcher, request);
        request.setCount(true);
        request.setLimit(pageSize);
        request.setOffset((pageNo - 1) * pageSize);

        DataResponse<CountRecords<AgencyDTO>> response =
                gateway.post("/agency/pager", request, new ParameterizedTypeReference<DataResponse<CountRecords<AgencyDTO>>>()
                {
                });

        Pager<Agency> pager = new Pager<>(pageNo, pageSize, response.getData().getCount().intValue());
        pager.setRecords(agencyWrapper.wrap(response.getData().getRecords()));
        return pager;
    }

    @Override
    public Agency get(String id)
    {
        DataResponse<AgencyDTO> response = gateway.get("/agency/get/{id}", new ParameterizedTypeReference<DataResponse<AgencyDTO>>()
        {
        }, id);

        if (Objects.isNull(response.getData()))
        {
            return new Agency();
        }

        return agencyWrapper.wrap(response.getData());
    }

    @Override
    public void create(Agency data)
    {
        AgencyMaintainRequest request = new AgencyMaintainRequest();
        BeanUtils.copyProperties(data, request);
        request.setOperatorName(accountHolder.get().getName());
        gateway.post("/agency/create", request);
    }

    @Override
    public void modify(Agency data)
    {
        AgencyMaintainRequest request = new AgencyMaintainRequest();
        BeanUtils.copyProperties(data, request);
        gateway.post("/agency/modify", request);
    }

    @Override
    public void delete(Agency data)
    {
        AgencyMaintainRequest request = new AgencyMaintainRequest();
        request.setId(data.getId());
        request.setOperatorName(accountHolder.get().getName());
        gateway.post("/agency/delete", request);
    }
}
