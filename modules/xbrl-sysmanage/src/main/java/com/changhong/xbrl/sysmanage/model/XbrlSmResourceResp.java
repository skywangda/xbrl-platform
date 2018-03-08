package com.changhong.xbrl.sysmanage.model;

import java.io.Serializable;
import java.util.List;

public class XbrlSmResourceResp implements Serializable {
	private String name;                         //资源名称
	private String id;                           //资源URL
	private String menuid;
	private List<XbrlSmResourceResp> buttons;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<XbrlSmResourceResp> getButtons() {
		return buttons;
	}
	public void setButtons(List<XbrlSmResourceResp> buttons) {
		this.buttons = buttons;
	}
	public String getMenuid() {
		return menuid;
	}
	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}
	
	
}
