package com.yhyt.health.util;

import java.io.Serializable;

public class BusinessException extends RuntimeException implements Serializable {

	private String code;
	private String msg;
	
	public BusinessException(String code,String msg){
		super(msg);
		this.code = code;
		this.msg = msg;
	}
	
	public BusinessException(Throwable e){
		super(e);
		if(e instanceof NullPointerException){
			this.msg = "空异常";
		}else{
			this.msg = e.getMessage();
		}
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
