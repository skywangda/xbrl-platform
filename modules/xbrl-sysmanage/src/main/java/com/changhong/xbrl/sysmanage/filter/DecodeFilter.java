package com.changhong.xbrl.sysmanage.filter;

import com.changhong.xbrl.sysmanage.util.RequestWrapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Liang on 2018/1/25.
 */
@Component
@WebFilter(filterName="myFilter",urlPatterns="/api/*")
public class DecodeFilter implements Filter {

    protected static org.slf4j.Logger logger = LoggerFactory.getLogger(DecodeFilter.class);

    @Value("${decrypt.aes.key}")
    private String aeskey;

    @Value("${decrypt.aes.iv}")
    private String aesiv;

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        System.out.println("DecodeFilter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httprequest = (HttpServletRequest) request;
        RequestWrapper httpRequest = new RequestWrapper(httprequest,aeskey,aesiv);
        chain.doFilter(httpRequest, response);
    }

    @Override
    public void destroy() {
        logger.info("DecodeFilter destroy");
    }
}
