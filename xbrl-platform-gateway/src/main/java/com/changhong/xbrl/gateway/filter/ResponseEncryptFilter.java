package com.changhong.xbrl.gateway.filter;

import com.changhong.xbrl.gateway.util.AesEncryptUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StreamUtils;

import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Created by Liang on 2018/1/16.
 * 响应数据加密
 */
public class ResponseEncryptFilter extends ZuulFilter {

    Logger logger = LoggerFactory.getLogger(ResponseEncryptFilter.class);
    public static final String backSlash_r_backSlash_n  = "\r\n";
    @Value("${decrypt.aes.key}")
    private String key;
    @Value("${decrypt.aes.iv}")
    private String iv;

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        //使用Jackson的ObjectMapper将Java对象转换成Json String
        RequestContext context = RequestContext.getCurrentContext();
        try {
            InputStream stream = context.getResponseDataStream();
            String body = StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
            String result = AesEncryptUtil.aesEncrypt(body, key, iv);
            String tmp = result.replaceAll(backSlash_r_backSlash_n, "");
            context.setSendZuulResponse(false);
            context.setResponseBody(tmp);
            context.setResponseStatusCode(200);
        }catch (Exception e){
            String defaultmsg="J933zDZzJLwl1sOSsGk2ukj0ajHOZp5ly6MstUqFDKdyRFM/zPSpPga3z4jn7xpx8m3/v1wGFru6";
            context.setResponseBody(defaultmsg);
            context.setResponseStatusCode(500);
        }
        return null;
    }

}
