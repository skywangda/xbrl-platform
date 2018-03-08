package com.changhong.xbrl.gateway.config;

import com.changhong.xbrl.gateway.filter.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Liang on 2018/1/17.
 */
@Configuration
public class CustomZuulFilterConfiguration {

    @Bean
    public RequestDecryptFilter getRequestDecryptFilter() {
        return new RequestDecryptFilter();
    }

    @Bean
    public RequestAnalyseFilter getRequestAnalyseFilter() {
        return new RequestAnalyseFilter();
    }

    @Bean
    public AuthorityFilter getAuthorityFilter() {
        return new AuthorityFilter();
    }

    @Bean
    public LogFilter getLogFilter() {
        return new LogFilter();
    }

    @Bean
    public ResponseEncryptFilter getResponseEncryptFilter() {
        return new ResponseEncryptFilter();
    }

}
