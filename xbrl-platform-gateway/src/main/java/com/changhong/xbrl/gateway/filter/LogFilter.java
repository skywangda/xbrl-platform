package com.changhong.xbrl.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import org.slf4j.LoggerFactory;

/**
 * Created by Liang on 2018/1/16.
 * 日志收集拦截器
 */
public class LogFilter  extends ZuulFilter {

    protected static org.slf4j.Logger logger = LoggerFactory.getLogger(LogFilter.class);

    @Override
    public String filterType() {
        return "post";
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
        logger.info(" logger 日志记录 ");
        return  null;
    }
}
