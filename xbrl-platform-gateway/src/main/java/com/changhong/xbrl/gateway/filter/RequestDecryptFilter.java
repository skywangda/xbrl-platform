package com.changhong.xbrl.gateway.filter;

import com.changhong.xbrl.gateway.model.RequestWrapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Liang on 2018/1/16.
 * 请求数据解密
 */
public class RequestDecryptFilter extends ZuulFilter {

    protected static Logger logger = LoggerFactory.getLogger(RequestDecryptFilter.class);

    @Value("${decrypt.aes.key}")
    private String key;
    @Value("${decrypt.aes.iv}")
    private String iv;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        logger.info("RequestDecryptFilter 请求数据解密 拦截器");
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        //请求数据解密
        try {
            RequestWrapper requestWrapper = new RequestWrapper(request, key, iv);
            requestWrapper.decryptdata();
            requestWrapper.setCharacterEncoding("UTF-8");
            ctx.setRequest(requestWrapper);
            ctx.set("preFilterState", true);
            ctx.setSendZuulResponse(true);
        }catch (Exception ex){
            ctx.setResponseStatusCode(401); // 返回错误码
            ctx.setResponseBody("{\"message\":\"参数解密失败!\"}"); // 返回错误内容
            ctx.set("preFilterState", false);
            ctx.setSendZuulResponse(true);
        }
        return null;
    }

}
