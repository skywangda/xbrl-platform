package com.changhong.xbrl.gateway.filter;

import com.changhong.xbrl.gateway.model.RequestInfo;
import com.changhong.xbrl.gateway.util.UtilDataTime;
import com.changhong.xbrl.gateway.util.UtilTools;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * Created by Liang on 2018/1/17.
 * 请求数据分析，是否存在id、token、sign等关键参数
 */
public class RequestAnalyseFilter extends ZuulFilter {

    protected static org.slf4j.Logger logger = LoggerFactory.getLogger(RequestDecryptFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        return (boolean) ctx.get("preFilterState");  //如果前一个过滤器的结果为true，则说明上一个过滤器成功了，需要进入当前的过滤，如果前一个过滤器的结果为false，则说明上一个过滤器没有成功，则无需进行下面的过滤动作了，直接跳过后面的所有过滤器并返回结果
    }

    @Override
    public Object run() {
        logger.info("RequestAnalyseFilter 请求数据合法性分析 拦截器");
        //请求合法性分析
        RequestInfo requestInfo = new RequestInfo();
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        Map<String, String[]> paramMap = null;
        String sid = null;
        String sign = null;
        String token = null;
        //判断 request 是一个文件上传的 request
        if(ServletFileUpload.isMultipartContent(request)) {
            sid = (String) request.getAttribute("sid");
            sign = (String) request.getAttribute("sign");
            token = (String) request.getAttribute("token");
        }else{
            paramMap = request.getParameterMap();
            sid = request.getParameter("sid");
            sign = request.getParameter("sign");
            token = (String) request.getParameter("token");
        }
        requestInfo.setRequestId(sid);
        requestInfo.setToken(token);
        requestInfo.setSign(sign);
        requestInfo.setRequestAddr(request.getRequestURI());
        if(paramMap == null){
            paramMap = (Map<String, String[]>) request.getAttribute("fields");
        }
        String parameterQuery = UtilTools.buildQueryString(paramMap);
        requestInfo.setRequestParameter(parameterQuery);
        requestInfo.setIpAddr(UtilTools.getIpAddress(request));
        requestInfo.setRequestTime(UtilDataTime.dateToStr(new Date(), null));

        System.out.println(requestInfo.toString());

        //设置下一个拦截器
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.set("preFilterState", true);
        ctx.set("requestInfo", requestInfo);
        ctx.setSendZuulResponse(true);
        return null;
    }
}
