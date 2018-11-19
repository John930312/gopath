package com.todaysoft.ghealth.mvc;

import com.todaysoft.ghealth.DTO.OrderDTO;
import com.todaysoft.ghealth.service.IOrderService;
import com.todaysoft.ghealth.wechat.H5.WXPayConstants;
import com.todaysoft.ghealth.wechat.H5.WXPayUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <微信支付 查询 回调>
 * @author kai.xu
 * @date 2018年11月14日
 */
@Controller
@RequestMapping("/callBack")
public class CallBackAction {

    private static Logger log = LoggerFactory.getLogger(CallBackAction.class);

    @Autowired
    private IOrderService orderService;

    /**
     * <功能详细描述>
     * 查询已经支付的订单,此操作适合用户完成支付后跳转
     * @author kai.xu
     * @date 2018年11月14日
     * @param request
     * @return void
     */
    @RequestMapping("/getPayOrder")
    @ResponseBody
    public void getPayOrder(HttpServletRequest request) {
        try {

            String code = request.getParameter("code");
            if(StringUtils.isEmpty(code)||"null".equals(code)||StringUtils.isEmpty(code)||"null".equals(code)){
                log.info("code 参数为空");
                return;
            }

            //step1:发起请求查询
            Map<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("appid", WXPayConstants.APPID);//公众账号ID
            paramMap.put("mch_id", WXPayConstants.MCH_ID);//商户号
            paramMap.put("out_trade_no", code);//商户订单号
            paramMap.put("nonce_str", WXPayUtil.generateNonceStr());//随机字符串
            paramMap.put("sign", WXPayUtil.generateSignature(paramMap, WXPayConstants.KEY));//签名

            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost(WXPayConstants.ORDERQUERY_URL);

            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(6000).setConnectTimeout(8000).build();
            httpPost.setConfig(requestConfig);

            StringEntity postEntity = new StringEntity(WXPayUtil.mapToXml(paramMap),"UTF-8");
            httpPost.addHeader("Content-Type", "text/xml");
            httpPost.addHeader("User-Agent", WXPayConstants.USER_AGENT + " " + WXPayConstants.MCH_ID);
            httpPost.setEntity(postEntity);

            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();

            Map<String, Object> paynotifyMap = doXMLParse(EntityUtils.toString(httpEntity, "UTF-8"));

            if(!paynotifyMap.get("trade_state_desc").toString().equals("订单未支付")){
                //step2:更新订单
                OrderDTO order = new OrderDTO();
                order.setCode(code);
                order.setStatus(0);
                orderService.updateByCode(order);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <功能详细描述>
     * 微信支付主动回调,查询更新订单
     * @author kai.xu
     * @date 2018年11月14日
     * @param request
     * @return String
     */
    @RequestMapping("/notification")
    @ResponseBody
    public String notification(HttpServletRequest request,HttpServletResponse response) {
        try{
           response.getWriter();
           String notityXml = parseRequst(request);
           Map<String, Object> paynotifyMap = doXMLParse(notityXml);
            if (paynotifyMap.get("result_code").toString().equalsIgnoreCase("SUCCESS")){
                String orderCode = paynotifyMap.get("out_trade_no").toString();

                //更新订单
                OrderDTO order = new OrderDTO();
                order.setCode(orderCode);
                order.setStatus(0);
                orderService.updateByCode(order);

                return setXML("SUCCESS","OK");
            }
            return setXML("FAIL","NO");
        }catch (Exception e) {
            e.printStackTrace();
            return setXML("FAIL","NO");
        }
    }

    public static String setXML(String return_code, String return_msg) {
        return "<xml><return_code><![CDATA[" + return_code + "]]></return_code><return_msg><![CDATA[" + return_msg + "]]></return_msg></xml>";
    }

    public static String parseRequst(HttpServletRequest request){
        String body = "";
        try {
            request.setCharacterEncoding("utf-8");
            ServletInputStream inputStream = request.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8")); // 设置编码格式“utf-8”否则获取中文为乱码
            while (true) {
                String info = br.readLine();
                if (info == null) {
                    break;
                }
                if (body == null || "".equals(body)) {
                    body = info;
                } else {
                    body += info;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return body;
    }

    // xml解析
    public static Map doXMLParse(String strxml) throws Exception {
        strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");

        if (null == strxml || "".equals(strxml)) {
            return null;
        }
        Map m = new HashMap();
        InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(in);
        Element root = doc.getRootElement();
        List list = root.getChildren();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Element e = (Element) it.next();
            String k = e.getName();
            String v = "";
            List children = e.getChildren();
            if (children.isEmpty()) {
                v = e.getTextNormalize();
            } else {
                v = getChildrenText(children);
            }

            m.put(k, v);
        }
        // 关闭流
        in.close();
        return m;
    }

    public static String getChildrenText(List children) {
        StringBuffer sb = new StringBuffer();
        if (!children.isEmpty()) {
            Iterator it = children.iterator();
            while (it.hasNext()) {
                Element e = (Element) it.next();
                String name = e.getName();
                String value = e.getTextNormalize();
                List list = e.getChildren();
                sb.append("<" + name + ">");
                if (!list.isEmpty()) {
                    sb.append(getChildrenText(list));
                }
                sb.append(value);
                sb.append("</" + name + ">");
            }
        }
        return sb.toString();
    }

}
