package com.todaysoft.ghealth.wechat.H5;

import com.todaysoft.ghealth.DTO.OrderDTO;
import com.todaysoft.ghealth.wechat.H5.WXPayConstants.SignType;
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
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xjw
 * @Date: 2018/10/19 10:34
 */
@Component
public class WXPay
{
    Logger log = LoggerFactory.getLogger(WXPay.class);
    
    public Map<String, String> unifiedorder(OrderDTO data)
    {
        try
        {
            String reqXML = unifiedorder(WXPayUtil.mapToXml(getUnifiedorderParams(data)), 6 * 1000, 8 * 1000);
            return this.processResponseXml(reqXML);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            return null;
        }
    }
    
    private Map<String, String> getUnifiedorderParams(OrderDTO data) throws Exception
    {
        Map<String, String> paramMap = new HashMap<String, String>();
        // 商品描述  *-*  商户-
        paramMap.put("body", "腾讯充值中心-QQ会员充值");
        //商户订单号
        paramMap.put("out_trade_no", data.getCode());
        // 交易金额  接口中参数支付金额单位为【分】，参数值不能带小数。对账单中的交易金额单位为【元】。
        paramMap.put("total_fee", data.getActualPrice().toString());
        // 终端IP APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
        paramMap.put("spbill_create_ip", "123.12.12.123");
        //通知地址 异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数
        paramMap.put("notify_url", "http://www.example.com/wxpay/notify");
        //交易类型 JSAPI 公众号支付  NATIVE 扫码支付 APP APP支付
        paramMap.put("trade_type", "JSAPI");
        //随机字符串
        paramMap.put("nonce_str", WXPayUtil.generateNonceStr());
        
        paramMap.put("appid", WXPayConstants.APPID);
        
        paramMap.put("mch_id", WXPayConstants.MCH_ID);
        
        paramMap.put("sign", WXPayUtil.generateSignature(paramMap, WXPayConstants.KEY));
        
        return paramMap;
    }
    
    /**
     * 作用：统一下单<br>
     * 场景：公共号支付
     * @param connectTimeoutMs 连接超时时间，单位是毫秒
     * @param readTimeoutMs 读超时时间，单位是毫秒
     * @return API返回数据
     * @throws Exception
     */
    private String unifiedorder(String reqBody, int connectTimeoutMs, int readTimeoutMs) throws IOException
    {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(WXPayConstants.UNIFIEDORDER_URL);
        
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(connectTimeoutMs).setConnectTimeout(readTimeoutMs).build();
        httpPost.setConfig(requestConfig);
        
        StringEntity postEntity = new StringEntity(reqBody, "UTF-8");
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.addHeader("User-Agent", WXPayConstants.USER_AGENT + " " + WXPayConstants.MCH_ID);
        httpPost.setEntity(postEntity);
        
        HttpResponse httpResponse = httpClient.execute(httpPost);
        HttpEntity httpEntity = httpResponse.getEntity();
        return EntityUtils.toString(httpEntity, "UTF-8");
    }
    
    public Map<String, String> getWXNeedData(String prepayId)
    {
        Map<String, String> payMap = new HashMap<String, String>();
        payMap.put("appId", WXPayConstants.APPID);
        payMap.put("timeStamp", System.currentTimeMillis() / 1000 + "");
        payMap.put("nonceStr", WXPayUtil.generateNonceStr());
        payMap.put("signType", "MD5");
        payMap.put("package", "prepay_id=" + prepayId);
        String paySign = null;
        try
        {
            paySign = WXPayUtil.generateSignature(payMap, WXPayConstants.KEY);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        payMap.put("paySign", paySign);
        return payMap;
    }
    
    public void backSuccessToWX(HttpServletResponse response)
    {
        Map<String, String> reqMap = Collections.emptyMap();
        reqMap.put("return_code", "SUCCESS");
        reqMap.put("return_msg", "OK");
        try
        {
            response.getWriter().write(WXPayUtil.mapToXml(reqMap));
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
        }
    }
    
    /**
     * 处理 HTTPS API返回数据，转换成Map对象。return_code为SUCCESS时，验证签名。
     * @param xmlStr API返回的XML格式数据
     * @return Map类型数据
     * @throws Exception
     */
    public Map<String, String> processResponseXml(String xmlStr) throws Exception
    {
        String RETURN_CODE = "return_code";
        String return_code;
        Map<String, String> respData = WXPayUtil.xmlToMap(xmlStr);
        if (respData.containsKey(RETURN_CODE))
        {
            return_code = respData.get(RETURN_CODE);
        }
        else
        {
            throw new Exception(String.format("No `return_code` in XML: %s", xmlStr));
        }
        
        if (return_code.equals(WXPayConstants.FAIL))
        {
            return respData;
        }
        else if (return_code.equals(WXPayConstants.SUCCESS))
        {
            if (WXPayUtil.isSignatureValid(respData, WXPayConstants.KEY, SignType.MD5))
            {
                return respData;
            }
            else
            {
                throw new Exception(String.format("Invalid sign value in XML: %s", xmlStr));
            }
        }
        else
        {
            throw new Exception(String.format("return_code value %s is invalid in XML: %s", return_code, xmlStr));
        }
    }
}