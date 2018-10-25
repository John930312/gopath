package com.todaysoft.ghealth.mvc;

import com.todaysoft.ghealth.DTO.OrderDTO;
import com.todaysoft.ghealth.DTO.ProductDTO;
import com.todaysoft.ghealth.service.IOrderService;
import com.todaysoft.ghealth.wechat.AccountContextHolder;
import com.todaysoft.ghealth.wechat.H5.WXPay;
import com.todaysoft.ghealth.wechat.H5.WXPayUtil;
import com.todaysoft.ghealth.wechat.dto.Account;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
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
    
    @Autowired
    private AccountContextHolder accountContextHolder;
    
    @Autowired
    private AccountContextHolder holder;
    
    @Autowired
    private IOrderService orderService;
    
    @RequestMapping("/place.jsp")
    public String place(ProductDTO product, ModelMap model)
    {
        ProductDTO p = new ProductDTO();
        p.setDiscount(true);
        p.setName("抑癌基因");
        p.setId("product_id_123");
        p.setDiscountPrice(new BigDecimal(1001));
        model.addAttribute("livePurchase", 1);
        model.addAttribute("product", p);
        BigDecimal price = p.getDiscount() ? p.getDiscountPrice() : p.getGuidingPrice();
        model.addAttribute("price", price);
        return "order/order_confirm";
    }
    
    @PostMapping("/payConfirm.jsp")
    public String payConfirm(OrderDTO data, ModelMap model)
    {
        if (StringUtils.isEmpty(data.getSampleBox().getName()))
        {
            data.setSampleBox(null);
        }
        BigDecimal actualPrice = data.getReportPrintRequired() == 1 ? data.getActualPrice().add(new BigDecimal("20")) : data.getActualPrice();
        data.setActualPrice(actualPrice);
        data.setCode(orderService.create(data));
        model.addAttribute("data", data);
        return "order/order_pay_confirm";
    }
    
    @ResponseBody
    @PostMapping("/pay.jsp")
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
    
    @RequestMapping("/list.jsp")
    public String list(ModelMap model, HttpSession session)
    {
        Account account = accountContextHolder.getAccount();
        List<OrderDTO> orders = orderService.getMyOrder(account.getOpenid());
        model.addAttribute("orders", orders);
        return "order/order_list";
    }
    @RequestMapping("/detail.jsp")
    public String detail(String id,ModelMap model, HttpSession session)
    {
        OrderDTO order = orderService.get(id);
        model.addAttribute("order", order);
        return "order/order_detail";
    }

}
