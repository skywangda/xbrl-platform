package com.changhong.ssc.xbrldao.user.model;

import java.io.Serializable;

/**
 * 
 * <p>Title: MONGODB实体类——接入商令牌关系信息</p>
 * <p>Description: MONGODB实体类——接入商令牌关系信息</p>
 * <p>Company: changhong </p>
 * @author liBo
 * @date 2014-11-24
 * @version 1.0
 */
public class UserTokenRef implements Serializable {
	private static final long serialVersionUID = 2881446960402896803L;

	private String id;					//唯一主键
	private String name;				//登录名
	private String code;				//工号
	private String platform;			//平台（智能报告10;数据交换11）
	private String token;				//令牌
	
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}
	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * @return platform
	 */
	public String getPlatform() {
		return platform;
	}
	/**
	 * @param platform the platform to set
	 */
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	
	
}
