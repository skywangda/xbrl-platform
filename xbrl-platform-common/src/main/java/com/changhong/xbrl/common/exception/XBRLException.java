package com.changhong.xbrl.common.exception;

/**
 * 自定义异常
 * 
 * @author hlc
 * @email 768353393@qq.com
 * @date 2018年3月8日
 */
public class XBRLException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
    private String msg;
    private int code = 500;
    
    public XBRLException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	public XBRLException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}
	
	public XBRLException(String msg, int code) {
		super(msg);
		this.msg = msg;
		this.code = code;
	}
	
	public XBRLException(String msg, int code, Throwable e) {
		super(msg, e);
		this.msg = msg;
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	
}
