package com.changhong.xbrl.sysmanage.model;

import java.io.Serializable;

/**
 * 
 * <p>Title:返回实体基类 </p>
 * <p>Description:所有接口返回对象的父类，提供公用的属性 </p>
 * <p>Company: changhong </p>
 * @author liBo
 * @date 2015-3-10
 * @version 1.0
 */
public class BaseRespEntity implements Serializable {

	private static final long serialVersionUID = -8181387663165225153L;

	private String rspCode;			//返回码
	private String rspDesc;			//返回描述
	private String id;				//流水号
	
	/**
	 * @return the rspCode
	 */
	public String getRspCode() {
		return rspCode;
	}
	/**
	 * @param rspCode the rspCode to set
	 */
	public void setRspCode(String rspCode) {
		this.rspCode = rspCode;
	}
	/**
	 * @return the rspDesc
	 */
	public String getRspDesc() {
		return rspDesc;
	}
	/**
	 * @param rspDesc the rspDesc to set
	 */
	public void setRspDesc(String rspDesc) {
		this.rspDesc = rspDesc;
	}
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
	
	
}

