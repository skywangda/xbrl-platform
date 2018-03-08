package com.changhong.xbrl.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 *
 * @author hlc
 * @email 768353393@qq.com
 * @date 2018年3月8日
 */
public class RespEntity extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	
	public RespEntity() {
		put("code", 0);
		put("msg", "success");
	}
	
	public static RespEntity error() {
		return error(500, "未知异常，请联系管理员");
	}
	
	public static RespEntity error(String msg) {
		return error(500, msg);
	}
	
	public static RespEntity error(int code, String msg) {
		RespEntity re = new RespEntity();
		re.put("code", code);
		re.put("msg", msg);
		return re;
	}

	public static RespEntity ok(String msg) {
		RespEntity re = new RespEntity();
		re.put("msg", msg);
		return re;
	}
	
	public static RespEntity ok(Map<String, Object> map) {
		RespEntity re = new RespEntity();
		re.putAll(map);
		return re;
	}
	
	public static RespEntity ok() {
		return new RespEntity();
	}

	@Override
	public RespEntity put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
