package com.yhyt.health.spring;


public class AppResult {

	private Status status = new Status();
	private Object body;
	
	private String msg;
	
	public AppResult(){
		//TODO 不知何意
		super();
		if(status==null){
			status = new Status();
		}
	}
	
	public AppResult(String code,String message,Object body){
		if(status==null){
			status = new Status();
		}
		status.setCode(code);
		status.setMessage(message);
		this.body = body;
		this.msg = message;
	}
	
	public Status getStatus() {
		return status;
	}

	public Object getBody() {
		return body;
	}
	public void setBody(Object body) {
		this.body = body;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.getStatus().setMessage(msg);
		this.msg = msg;
	}

	public void setCode(String code) {
		this.getStatus().setCode(code);
	}

	public void setMessage(String message) {
		this.getStatus().setMessage(message);
	}
}
