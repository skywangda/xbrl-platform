package com.changhong.xbrl.gateway.util;

import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.Map.Entry;

/**
 * 
 * <p>Title: 公用工具类</p>
 * <p>Description: 用于定义项目所需公共静态方法</p>
 * <p>Company: changhong </p>
 * @author liBo
 * @date 2015-3-10
 * @version 1.0
 */
public class UtilTools {

	protected static org.slf4j.Logger logger = LoggerFactory.getLogger(UtilTools.class);

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
			Set<Entry<String, String[]>> es = parameterMap.entrySet();
			Iterator<Entry<String, String[]>> it = es.iterator();
			while(it.hasNext()) {
				Entry<String, String[]> entry = it.next();
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
			Set<Entry<String, String>> set = map.entrySet();
			Iterator<Entry<String, String>> ite = set.iterator();
			while(ite.hasNext()) {
				Entry<String, String> entry = ite.next();
				String k = entry.getKey();
				String v = entry.getValue();
				sb.append(k + "=" + v + "&");
			}
			
			sb.append("sign=");
			sb.append(sign); 
		} catch (Exception e) {
			logger.error("buildQueryString(Map<String, String[]> parameterMap,String key)拼接请求参数符串失败，原因：" + e.getMessage());
		}
		return sb.toString();
	}
	
	/**
	 * 获取请求IP地址
	 * @param request HTTP请求对象
	 * @return IP地址
	 */
	public static String getIpAddress(HttpServletRequest request){
		//Nginx转发报文头中获取
		String ip = request.getHeader("X-real-ip");
		
		if(null == ip || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
			ip = request.getRemoteAddr();
		}

		return ip;
	}
	
	/**
	 * MD5加密
	 * @param src 原串
	 * @param charset 编码规则
	 * @return 加密串
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String MD5Encode(String src, String charset) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md5 = null;
		md5 = MessageDigest.getInstance("MD5");

		byte[] byteArray = null;
		byteArray = src.getBytes(charset);
	
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer(32);
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

}
