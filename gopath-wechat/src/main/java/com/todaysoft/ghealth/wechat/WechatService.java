package com.todaysoft.ghealth.wechat;


import com.todaysoft.ghealth.wechat.dto.SNSTokenDTO;
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

    private static final String APPID = "wxbd6b5a4386023b26";

    private static final String SECRET = "8f345bbfab41394614adf3ea7182027e";

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
            String pattern =
                "https://open.weixin.qq.com/connect/oauth2/authorize?appid={0}&redirect_uri={1}&response_type=code&scope={2}&state={3}#wechat_redirect";
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
            String url = "https://api.weixin.qq.com/sns/oauth2/access_token?grant_type=authorization_code&appid={key}&secret={security}&code={code}";
            MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
            converter.setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_PLAIN));
            template.setMessageConverters(Collections.singletonList(converter));
            SNSTokenDTO token = template.getForObject(url, SNSTokenDTO.class, APPID, SECRET, code);

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
