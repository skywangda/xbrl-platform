package com.changhong.xbrl.gateway.filter;

import com.changhong.xbrl.gateway.model.*;
import com.changhong.xbrl.gateway.util.RedisUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Liang on 2018/1/16.
 */
public class AuthorityFilter extends ZuulFilter {

    public static final Long TOKEN_INVALIDE_TIME = Long.valueOf(7200);

    @Autowired
    private RedisUtil redisUtil;

    /*
    PRE：这种过滤器在请求被路由之前调用。我们可利用这种过滤器实现身份验证、在集群中选择请求的微服务、记录调试信息等。
    ROUTING：这种过滤器将请求路由到微服务。这种过滤器用于构建发送给微服务的请求，并使用Apache HttpClient或Netfilx Ribbon请求微服务。
    POST：这种过滤器在路由到微服务以后执行。这种过滤器可用来为响应添加标准的HTTP Header、收集统计信息和指标、将响应从微服务发送给客户端等。
    ERROR：在其他阶段发生错误时执行该过滤器。
    除了默认的过滤器类型，Zuul还允许我们创建自定义的过滤器类型。例如，我们可以定制一种STATIC类型的过滤器，直接在Zuul中生成响应，而不将请求转发到后端的微服务
     */
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 3;   //数字越大越靠后
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        boolean isloginAuth =ctx.getRequest().getRequestURI().contains("xbrl-user-es/rest/user/json/loginAuth");
        return (boolean) ctx.get("preFilterState") && !isloginAuth;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        //token 校验
        RequestInfo requestInfo =(RequestInfo)ctx.get("requestInfo");
        if(StringUtils.isNotBlank(requestInfo.getToken())) {
            Object obj = redisUtil.get("token", requestInfo.getToken());
            if (obj == null) {
                ctx.setResponseStatusCode(200);
                ctx.setResponseBody("{\"message\":\"token无效!\"}");
                ctx.set("preFilterState", false);
                ctx.setSendZuulResponse(false);
            }else{
                //空闲时间为2小时,用户的每次接口调用重置接口token的失效时间
                redisUtil.set("token",requestInfo.getToken(),obj, TOKEN_INVALIDE_TIME);
                //设置下一个拦截器
                ctx.set("preFilterState", true);
                ctx.setSendZuulResponse(true);
            }
        }else{
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody("{\"message\":\"缺少必要参数token!\"}");
            ctx.set("preFilterState", false);
            ctx.setSendZuulResponse(false);
        }
        return null;
    }
}
