package com.todaysoft.ghealth.mvc;

import com.todaysoft.ghealth.DTO.OrderDTO;
import com.todaysoft.ghealth.DTO.ProductDTO;
import com.todaysoft.ghealth.service.IOrderService;
import com.todaysoft.ghealth.service.IProductService;
import com.todaysoft.ghealth.wechat.AccountContextHolder;
import com.todaysoft.ghealth.wechat.H5.WXPay;
import com.todaysoft.ghealth.wechat.H5.WXPayUtil;
import com.todaysoft.ghealth.wechat.dto.Account;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
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
    private static Logger log = LoggerFactory.getLogger(OrderAction.class);

    @Autowired
    private WXPay wxPay;
    
    @Autowired
    private AccountContextHolder accountContextHolder;
    
    @Autowired
    private AccountContextHolder holder;
    
    @Autowired
    private IOrderService orderService;

    @Autowired
    private IProductService productService;

    @RequestMapping("/place.jsp")
    public String place(String id, ModelMap model)
    {
        model.addAttribute("livePurchase", 1);
        ProductDTO product = productService.get(id);
        model.addAttribute("product", product);
        BigDecimal price = product.getDiscount() ? product.getDiscountPrice() : product.getGuidingPrice();
        model.addAttribute("price", price);
        model.addAttribute("openId", holder.getAccount().getOpenid());
        return "order/order_confirm";
    }

    @RequestMapping("/payConfirm.jsp")
    public String payConfirm(OrderDTO data,String getWay, ModelMap model)
    {
        if (StringUtils.isNotEmpty(data.getId())) {
            OrderDTO order = orderService.get(data.getId());
            if(org.springframework.util.StringUtils.isEmpty(order)&&org.springframework.util.StringUtils.isEmpty(order.getSampleBox())&& org.springframework.util.StringUtils.isEmpty(order.getSampleBox().getAddress())){
                getWay = "0";
            }else{
                getWay = "1";
            }
            model.addAttribute("getWay", getWay);
            model.addAttribute("openId",holder.getAccount().getOpenid());
            model.addAttribute("data", order);
            return "order/order_pay_confirm";
        }
        if (StringUtils.isEmpty(data.getSampleBox().getName()))
        {
            data.setSampleBox(null);
        }
        BigDecimal actualPrice = data.getReportPrintRequired() == 1 ? data.getActualPrice().add(new BigDecimal("20")) : data.getActualPrice();
        data.setActualPrice(actualPrice);
        data.setCode(orderService.create(data));
        model.addAttribute("data", data);
        model.addAttribute("openId", holder.getAccount().getOpenid());
        model.addAttribute("getWay", getWay);
        return "order/order_pay_confirm";
    }
    
    @ResponseBody
    @PostMapping("/pay.jsp")
    public Map<String, String> pay(OrderDTO data)
    {
        data.setOpenId(holder.getAccount().getOpenid());
        log.info("openId"+ holder.getAccount().getOpenid());
        Map<String, String> unifiedorderMap = wxPay.unifiedorder(data);
        return wxPay.getWXNeedData(unifiedorderMap.get("prepay_id"));
    }
    
    @RequestMapping("/callBack.jsp")
    public void callBack(HttpServletRequest request, HttpServletResponse response)
    {
        //将微信发的xml转map
        Map<String, String> notifyMap = WXPayUtil.xmlToMap(WXPayUtil.getStreamData(request));
        orderService.payed(notifyMap.get("out_trade_no"));
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

    /*@RequestMapping(value = "/getPDF", produces = MediaType.APPLICATION_PDF_VALUE)
    @ResponseBody
    public byte[] getPDF(@RequestParam(value = "path", required = false) String path) throws IOException
    {
        if (path != null)
        {
            File file = new File(path);
            FileInputStream inputStream = new FileInputStream(file);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, inputStream.available());
            return bytes;
        }
        else
        {
            return null;
        }
    }*/


    @ResponseBody
    @GetMapping("/getPDF")
    public void getPDF(HttpServletRequest request, HttpServletResponse response)
    {

        String path = request.getParameter("path");
        File file = new File(path);
        if (file.exists()) {
            //打开后,强制下载
            //response.setContentType("application/force-download");// 设置强制下载不打开
            //response.addHeader("Content-Disposition","attachment;fileName=" + fileName);// 设置文件名
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
