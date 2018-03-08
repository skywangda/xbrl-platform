package com.changhong.xbrl.sysmanage.model;

import java.util.ArrayList;
import java.util.List;

public class XbrlSmUserResp extends BaseRespEntity {

	private static final long serialVersionUID = -642628929254354478L;
	private String name;                         //姓名
	private String userCode;                     //工号
	private String avatarUrl;                    //头像
	private String token;						//令牌
	private boolean isManageAuth;
	private List<XbrlSmResourceResp> resources = new ArrayList<XbrlSmResourceResp>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public boolean isManageAuth() {
		return isManageAuth;
	}
	public void setManageAuth(boolean isManageAuth) {
		this.isManageAuth = isManageAuth;
	}
	public List<XbrlSmResourceResp> getResources() {
		return resources;
	}
	public void setResources(List<XbrlSmResourceResp> resources) {
		this.resources = resources;
	}
	
}
