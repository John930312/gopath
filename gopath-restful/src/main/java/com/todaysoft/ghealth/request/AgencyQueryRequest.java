package com.todaysoft.ghealth.request;

import com.hsgene.restful.request.QueryRequest;

/**
 * @Author: zyf
 * @Date: 2018/10/17 10:07
 */

public class AgencyQueryRequest extends QueryRequest
{

    private String code;

    private String name;

    private String contactName;

    private String phone;

    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getContactName() { return contactName; }

    public void setContactName(String contactName) { this.contactName = contactName; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }
}
