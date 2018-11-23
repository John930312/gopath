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
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.todaysoft.ghealth.wechat.H5.WXPayConstants.UNIFIEDORDER_URL;

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
            log.info( "发起支付返回参数:"+reqXML);
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
        paramMap.put("appid", WXPayConstants.APPID);//公众账号ID
        paramMap.put("mch_id", WXPayConstants.MCH_ID);//商户号
        paramMap.put("nonce_str", WXPayUtil.generateNonceStr());//随机字符串
        paramMap.put("body", "杰傲");//商品描述
        paramMap.put("out_trade_no", data.getCode());//商户订单号
        String s = data.getActualPrice().multiply( new BigDecimal( "100" ) ).toString();
        paramMap.put("total_fee", s.contains(".")?s.substring(0, s.indexOf( "." )):s );//标价金额 单位分
        paramMap.put("spbill_create_ip", "112.82.118.145");//终端IP 用户的ip
        paramMap.put("notify_url", "http://ja.jllhdzy.cn/callBack/notification");//通知地址
        paramMap.put("trade_type", "JSAPI");//交易类型
        paramMap.put("openid", data.getOpenId());
        paramMap.put("sign", WXPayUtil.generateSignature(paramMap, WXPayConstants.KEY));//签名
        log.info( "发起支付参数:"+paramMap.toString() );
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
        HttpPost httpPost = new HttpPost(UNIFIEDORDER_URL);
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(connectTimeoutMs).setConnectTimeout(readTimeoutMs).build();
        httpPost.setConfig(requestConfig);
        
        StringEntity postEntity = new StringEntity(reqBody, "UTF-8");
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.addHeader("User-Agent", WXPayConstants.USER_AGENT + " " + WXPayConstants.MCH_ID);
        log.info( "发起支付马上开始:"+postEntity.toString());


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