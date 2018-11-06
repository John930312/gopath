package com.todaysoft.ghealth.service.impl;

import com.hsgene.restful.response.DataResponse;
import com.hsgene.restful.util.CountRecords;
import com.todaysoft.ghealth.DTO.AgencyDTO;
import com.todaysoft.ghealth.mybatis.mapper.AgencyMapper;
import com.todaysoft.ghealth.mybatis.model.Agency;
import com.todaysoft.ghealth.mybatis.model.query.AgencyQuery;
import com.todaysoft.ghealth.request.AgencyMaintainRequest;
import com.todaysoft.ghealth.request.AgencyQueryRequest;
import com.todaysoft.ghealth.service.IAgencyService;
import com.todaysoft.ghealth.service.paser.AgencyQueryParser;
import com.todaysoft.ghealth.service.wrapper.AgencyWrapper;
import com.todaysoft.ghealth.utils.IdGen;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @Author: zyf
 * @Date: 2018/10/17 14:08
 */
@Service
public class AgencyService implements IAgencyService
{
    @Autowired
    private AgencyQueryParser agencyQueryParser;
    @Autowired
    private AgencyMapper agencyMapper;
    @Autowired
    private AgencyWrapper agencyWrapper;

    @Override
    public DataResponse<CountRecords<AgencyDTO>> query(AgencyQueryRequest request)
    {
        AgencyQuery query = agencyQueryParser.parse(request);
        CountRecords<AgencyDTO> data = new CountRecords<>();

        if (request.isCount())
        {
            long count = agencyMapper.count(query);
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
                int mod = (int) count % limit;

                if (0 == mod)
                {
                    offset = (((int) count / limit) - 1) * limit;
                }
                else
                {
                    offset = ((int) count / limit) * limit;
                }

                query.setOffset(offset);
            }
        }

        List<Agency> records = agencyMapper.query(query);
        data.setRecords(agencyWrapper.wrap(records));
        return new DataResponse<>(data);
    }

    @Override
    public DataResponse<AgencyDTO> get(String id)
    {
        return new DataResponse<>(agencyWrapper.wrap(agencyMapper.get(id)));
    }

    @Override
    @Transactional
    public void create(AgencyMaintainRequest request)
    {
        Agency agency = new Agency();
        agency.setId(IdGen.uuid());
        agency.setCode(request.getCode());
        agency.setName(request.getName());
        agency.setContactName(request.getContactName());
        agency.setPhone(request.getPhone());
        agency.setProvince(request.getProvince());
        agency.setCity(request.getCity());
        agency.setAddress(request.getAddress());
        agency.setCreateTime(new Date());
        agency.setCreatorName(request.getOperatorName());
        agency.setDeleted(false);
        agencyMapper.create(agency);
    }

    @Override
    @Transactional
    public void modify(AgencyMaintainRequest request)
    {
        Agency agency = agencyMapper.get(request.getId());
        agency.setCode(request.getCode());
        agency.setName(request.getName());
        agency.setContactName(request.getContactName());
        agency.setPhone(request.getPhone());
        agency.setProvince(request.getProvince());
        agency.setCity(request.getCity());
        agency.setAddress(request.getAddress());
        agencyMapper.modify(agency);
    }

    @Override
    public void delete(AgencyMaintainRequest request)
    {
        Agency agency = agencyMapper.get(request.getId());
        agency.setDeleted(true);
        agency.setDeletorName(request.getOperatorName());
        agency.setDeleteTime(new Date());
        agencyMapper.modify(agency);
    }

    @Override
    public DataResponse<Boolean> isCodeUnique(AgencyMaintainRequest request)
    {
        AgencyQuery query = new AgencyQuery();
        query.setCode(request.getCode());
        if (!StringUtils.isEmpty(request.getId()))
        {
            query.setExcludeKeys(Collections.singleton(request.getId()));
        }
        long count = agencyMapper.count(query);
        return new DataResponse<Boolean>(count == 0);
    }

}
