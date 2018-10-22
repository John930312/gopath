package com.todaysoft.ghealth.mvc;


import com.todaysoft.ghealth.DTO.OrderDTO;
import com.todaysoft.ghealth.DTO.ProductDTO;
import com.todaysoft.ghealth.wechat.H5.WXPay;
import com.todaysoft.ghealth.wechat.H5.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @Author: xjw
 * @Date: 2018/10/19 15:13
 */
@Controller
@RequestMapping("/order")
public class OrderAction
{
    @Autowired
    private WXPay wxPay;
    
    @RequestMapping("/place.jsp")
    public String place(ProductDTO product, ModelMap model)
    {
        model.addAttribute("product", product);
        return "order/order_confirm";
    }
    
    @ResponseBody
    @RequestMapping("/pay.jsp")
    public Map<String, String> pay(OrderDTO data)
    {
        Map<String, String> unifiedorderMap = wxPay.unifiedorder(data);
        return wxPay.getWXNeedData(unifiedorderMap.get("prepay_id"));
    }
    
    @RequestMapping("/callBack.jsp")
    public void callBack(HttpServletRequest request, HttpServletResponse response)
    {
        //将微信发的xml转map
        Map<String, String> notifyMap = WXPayUtil.xmlToMap(WXPayUtil.getStreamData(request));
        
        //通知微信服务器收到信息 不要在调用回调action
        WXPayUtil.backSuccess(response);
    }
}
