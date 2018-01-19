package com.yhyt.health.spring;

import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.alibaba.fastjson.JSONObject;

public class ResponseBodyWrapHandler implements HandlerMethodReturnValueHandler {
	private final HandlerMethodReturnValueHandler delegate;

	private static Logger logger = LoggerFactory.getLogger(ResponseBodyWrapHandler.class);

	public ResponseBodyWrapHandler(HandlerMethodReturnValueHandler delegate) {
		this.delegate = delegate;
	}

	private String formatIndexs;
	
	private String formatUnIndexs;
	
	public String getFormatIndexs() {
		return formatIndexs;
	}

	public void setFormatIndexs(String formatIndexs) {
		this.formatIndexs = formatIndexs;
	}

	public String getFormatUnIndexs() {
		return formatUnIndexs;
	}

	public void setFormatUnIndexs(String formatUnIndexs) {
		this.formatUnIndexs = formatUnIndexs;
	}

	@Override
	public boolean supportsReturnType(MethodParameter returnType) {
		return delegate.supportsReturnType(returnType);
	}

	@Override
	public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest) throws Exception {
		String userAgent = webRequest.getHeader("user-agent");
		String uri = ((ServletWebRequest)webRequest).getRequest().getServletPath();
		String prefix = "";
		if("/".equals(uri)||uri.split("/").length==1){
			prefix = uri.split("/")[0]+"/";
		}else{
			prefix = uri.split("/")[1]+"/";
		}
		if(StringUtils.isBlank(formatUnIndexs)||(StringUtils.isNotBlank(formatUnIndexs)&&!(formatUnIndexs.indexOf(prefix)>0))){
			if(StringUtils.isBlank(formatIndexs)||(StringUtils.isNotBlank(formatIndexs)&&(formatIndexs.indexOf(prefix)>0))){
				if (userAgent.matches(".*Android.*") || userAgent.matches(".*iPhone.*") || userAgent.matches(".*iPad.*")) {
					try {
						logger.info(JSONObject.toJSONString(returnValue));
						logger.info(userAgent);
						JXPathContext context = JXPathContext.newContext(returnValue);
						Object result1 = context.selectSingleNode("status");
						context = JXPathContext.newContext(result1);
						if (context.selectSingleNode("code") != null || context.selectSingleNode("message") != null) {
							delegate.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
						} else {
							AppResult result = new AppResult();
							result.setBody(returnValue);
							delegate.handleReturnValue(result, returnType, mavContainer, webRequest);
						}
						return ;
					} catch (Exception e) {
						AppResult result = new AppResult();
						result.setBody(returnValue);
						delegate.handleReturnValue(result, returnType, mavContainer, webRequest);
						return ;
					}
				} 
			}
		}
		delegate.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
	}

	public static void main(String... strings) {
		AppResult result = new AppResult();
		JXPathContext context = JXPathContext.newContext(result);
		Object result1 = context.selectSingleNode("status");
		context = JXPathContext.newContext(result1);
		if (context.selectSingleNode("code") != null || context.selectSingleNode("message") != null) {
			System.out.println(true);
		} else {
			System.out.println(false);
		}
	}
}