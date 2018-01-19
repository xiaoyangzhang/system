package com.yhyt.health.spring;  
  
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import com.google.common.collect.Lists;  
  
/** 
 * 替换为ResponseBody包装类 
 */  
public class ResponseBodyWrapFactoryBean implements InitializingBean {
	
    @Autowired  
    private RequestMappingHandlerAdapter adapter;  
  
	private String prefix;
	
	private String unPrefix;
	
    public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getUnPrefix() {
		return unPrefix;
	}

	public void setUnPrefix(String unPrefix) {
		this.unPrefix = unPrefix;
	}

	@Override  
    public void afterPropertiesSet() throws Exception {  
        List<HandlerMethodReturnValueHandler> returnValueHandlers = adapter.getReturnValueHandlers();  
        List<HandlerMethodReturnValueHandler> handlers = Lists.newArrayList(returnValueHandlers);  
        decorateHandlers(handlers);  
        adapter.setReturnValueHandlers(handlers);  
    }  
  
    private void decorateHandlers(List<HandlerMethodReturnValueHandler> handlers) {  
        for (HandlerMethodReturnValueHandler handler : handlers) {  
            if (handler instanceof RequestResponseBodyMethodProcessor) {  
                //用自己的ResponseBody包装类替换掉框架的，达到返回Result的效果  
                ResponseBodyWrapHandler decorator = new ResponseBodyWrapHandler(handler);
                decorator.setFormatIndexs(this.prefix);
                decorator.setFormatUnIndexs(this.unPrefix);
                int index = handlers.indexOf(handler);  
                handlers.set(index, decorator);  
                break;  
            }  
        }  
    }  
  
}  