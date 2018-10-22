package com.todaysoft.ghealth.wechat.H5;

import org.apache.http.client.HttpClient;

/**
 * @Author: xjw
 * @Date: 2018/10/19 10:34
 */
public class WXPayConstants
{
    public enum SignType
    {
        MD5, HMACSHA256
    }
    
    public static final String APPID = ""; //公众账号ID
    
    public static final String MCH_ID = ""; //商户号
    
    public static final String KEY = "9e50f924d34c11e8beea408d5c9494ce";
    
    public static final String FAIL = "FAIL";
    
    public static final String SUCCESS = "SUCCESS";
    
    public static final String FIELD_SIGN = "sign";
    
    public static final String WXPAYSDK_VERSION = "WXPaySDK/3.0.9";
    
    public static final String UNIFIEDORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    
    public static final String USER_AGENT =
        WXPAYSDK_VERSION + " (" + System.getProperty("os.arch") + " " + System.getProperty("os.name") + " " + System.getProperty("os.version") + ") Java/"
            + System.getProperty("java.version") + " HttpClient/" + HttpClient.class.getPackage().getImplementationVersion();
    
}
