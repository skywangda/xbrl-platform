package com.changhong.xbrl.gateway.model;

import java.io.Serializable;

/**
 * 
 * <p>Title: request请求对象</p>
 * <p>Description: 封装所有request有用的属性</p>
 * <p>Company: changhong </p>
 * @author liBo
 * @date 2015-3-10
 * @version 1.0
 */
public class RequestInfo implements Serializable {

	private static final long serialVersionUID = 335439305502841992L;

	private String requestId;		//请求ID（24位唯一字符串）
	private String sign;			//签名
	private String requestAddr;		//接口调用地址
	private String requestParameter;//接口调用参数
	private String ipAddr;			//请求IP
	private String requestTime;		//请求时间

	//业务参数
	private String token;			//用户token
	/**
	 * @return the requestId
	 */
	public String getRequestId() {
		return requestId;
	}
	/**
	 * @param requestId the requestId to set
	 */
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	/**
	 * @return the sign
	 */
	public String getSign() {
		return sign;
	}
	/**
	 * @param sign the sign to set
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}
	/**
	 * @return the requestAddr
	 */
	public String getRequestAddr() {
		return requestAddr;
	}
	/**
	 * @param requestAddr the requestAddr to set
	 */
	public void setRequestAddr(String requestAddr) {
		this.requestAddr = requestAddr;
	}
	/**
	 * @return the requestParameter
	 */
	public String getRequestParameter() {
		return requestParameter;
	}
	/**
	 * @param requestParameter the requestParameter to set
	 */
	public void setRequestParameter(String requestParameter) {
		this.requestParameter = requestParameter;
	}
	/**
	 * @return the ipAddr
	 */
	public String getIpAddr() {
		return ipAddr;
	}
	/**
	 * @param ipAddr the ipAddr to set
	 */
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	/**
	 * @return the requestTime
	 */
	public String getRequestTime() {
		return requestTime;
	}
	/**
	 * @param requestTime the requestTime to set
	 */
	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
