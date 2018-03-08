package com.changhong.xbrl.sysmanage.util;

import com.changhong.xbrl.sysmanage.domain.XbrlSmCompany;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class Util {

    /**
     * 获取请求body中的字符串
     * @param request
     * @author yubo.li@changhong.com
     * @date 2017/5/11 0011
     * @return
     */
    public static String readBodyJson(HttpServletRequest request) {
        String str = "";
        try {
            String inputLine;
            BufferedReader reader = request.getReader();
            while ((inputLine = reader.readLine()) != null) {
                str += inputLine;
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
        return str;
    }

    /**
     * request的ParameterMap中读取参数并按ASCII由小到大排序拼接成参数字符串
     * @param parameterMap 参数Map
     * @return 参数字符串
     */
    public static String buildQueryString(Map<String, String[]> parameterMap){
        String sign = "";
        StringBuffer sb = new StringBuffer();
        //有序参数Map
        SortedMap<String, String> map = new TreeMap<String, String>();
        try{
            //request中无序ParameterMap存入有序Map中
            Set<Map.Entry<String, String[]>> es = parameterMap.entrySet();
            Iterator<Map.Entry<String, String[]>> it = es.iterator();
            while(it.hasNext()) {
                Map.Entry<String, String[]> entry = it.next();
                String k = entry.getKey();
                String v = entry.getValue()[0];
                if(v==null){
                    v="";
                }
                if(!"sign".equals(k)){
                    map.put(k, v);
                }else{
                    sign = v;
                }
            }
            //拼接参数字符串
            Set<Map.Entry<String, String>> set = map.entrySet();
            Iterator<Map.Entry<String, String>> ite = set.iterator();
            while(ite.hasNext()) {
                Map.Entry<String, String> entry = ite.next();
                String k = entry.getKey();
                String v = entry.getValue();
                sb.append(k + "=" + v + "&");
            }

            sb.append("sign=");
            sb.append(sign);
        } catch (Exception e) {
            sb.append("参数解析失败, 原因："+e.getMessage());
        }
        return sb.toString();
    }


    public static class XbrlCompanyOrderComparator implements Comparator<XbrlSmCompany> {
        /**
         * 如果o1小于o2,返回一个负数;如果o1大于o2，返回一个正数;如果他们相等，则返回0;
         */
        @Override
        public int compare(XbrlSmCompany o1, XbrlSmCompany o2) {
            String order1 = o1.getCompanyName();
            String order2 = o2.getCompanyName();
            if(order1!=null&&order2!=null){
               int a = order1.compareTo(order2);
                return a;
            }
            return 0;

        }
    }
}
