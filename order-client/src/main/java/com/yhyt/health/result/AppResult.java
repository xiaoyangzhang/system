package com.yhyt.health.result;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * app返回结果类
 * @author gsh
 *
 */
public class AppResult{
	
	private static final long serialVersionUID = -5532491536749699416L;
	
	private ResultStatus status=new ResultStatus();
	
	private Map<String, Object> body=new HashMap<String, Object>();

	public ResultStatus getStatus() {
		return status;
	}

	public void setStatus(ResultStatus status) {
		this.status = status;
	}

	public Map<String, Object> getBody() {
		return body;
	}

	public void setBody(Map<String, Object> body) {
		this.body = body;
	}
}
