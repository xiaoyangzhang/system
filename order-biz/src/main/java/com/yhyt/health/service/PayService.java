package com.yhyt.health.service;

import com.yhyt.health.model.Order;
import com.yhyt.health.model.PayDetail;
import com.yhyt.health.spring.AppResult;

public interface PayService extends BaseService<PayDetail> {

//	public AppResult weixinRefund(PayDetail payDetail) throws Exception;
	
	/**
	 * id
	 * @param id
	 */
	void refund(Order order) throws Exception;
	
//	public AppResult alipayRefund(PayDetail payDetail);

	public AppResult updatePayDetail(PayDetail payDetail);
}
