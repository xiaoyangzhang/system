package com.yhyt.health.service.impl;

import com.yhyt.health.util.SMSutil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/** 
* localadmin 作者: 
* @version 创建时间：2017年8月17日 下午3:03:27 
* 类说明 
*/
@Service
public class SMSService { 
	
	//发送短信采用异步的方式
	@Async
	public void executeAsyncTaskPlus(String username, String randCode, String expire) {
		System.out.println();
		SMSutil.sendMessage(username,randCode,expire); 
	}
}
