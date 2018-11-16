package com.todaysoft.ghealth.wechat;

import com.alibaba.fastjson.JSONObject;
import com.todaysoft.ghealth.wechat.dto.SNSTokenDTO;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.Collections;

@Service
public class WechatService
{
    private static Logger log = LoggerFactory.getLogger(WechatService.class);

    private static final String APPID = "wxd5300069594241a8";

    private static final String SECRET = "9cbdd0bb06fc3b9efc98a449aa81b631";

    // 永久二维码(字符串)
    public static final String QR_LIMIT_STR_SCENE = "QR_LIMIT_STR_SCENE";

    // 创建二维码URL
    public static final String CREATE_TICKET_PATH = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token={0}";

    // 通过ticket换取二维码
    public static final String SHOW_QRCODE_PATH = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket={0}";

    private RestTemplate template = new RestTemplate();

    public String getOauth2AuthorizeUrl(HttpServletRequest request)
    {
        try
        {
            String url = request.getRequestURL() + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
            String pattern = "https://open.weixin.qq.com/connect/oauth2/authorize?appid={0}&redirect_uri={1}&response_type=code&scope={2}&state={3}#wechat_redirect";
            return MessageFormat.format(pattern, APPID, URLEncoder.encode(url, "UTF-8"), "snsapi_base", "2");
        }
        catch (UnsupportedEncodingException e)
        {
            throw new IllegalStateException();
        }
    }

    public SNSTokenDTO getSNSToken(String code)
    {
        try
        {
            String url = "https://api.weixin.qq.com/sns/oauth2/access_token?grant_type=authorization_code&appid="+APPID+"&secret="+SECRET+"&code="+code;

            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet httpGet = new HttpGet(url);


            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(30000).build();
            httpGet.setConfig(requestConfig);

            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();

            JSONObject jsStr = JSONObject.parseObject(EntityUtils.toString( httpEntity, "UTF-8" ));

            SNSTokenDTO token = new SNSTokenDTO();
            token.setAccessToken(String.valueOf( jsStr.get("access_token")));
            token.setExpires(String.valueOf( jsStr.get("expires_in")));
            token.setRefreshToken(String.valueOf( jsStr.get("refresh_token")));
            token.setOpenid(String.valueOf( jsStr.get("openid")));
            token.setScope(String.valueOf( jsStr.get("scope")));

            if (null == token)
            {
                log.error("Get sns token error, response is null.");
                return null;
            }

            if (!token.isValid())
            {
                log.error("Get sns token error, error code {}, error com.todaysoft.ghealth.message {}", token.getErrorCode(), token.getErrorMessage());
                return null;
            }

            return token;
        }
        catch (Exception e)
        {
            log.error("Get sns token error.", e);
            return null;
        }
    }
}
