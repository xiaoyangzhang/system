package com.yhyt.health.service;

import com.yhyt.health.model.Order;
import com.yhyt.health.model.OrderDepartmentDetail;
import com.yhyt.health.model.OrderVo;
import com.yhyt.health.result.AppResult;

public interface OrderService {

	AppResult getOrder(Order der);
	AppResult getOrderRemainCount(Order der);
	AppResult placeOrder(OrderVo orderVo);
	AppResult giveOrder(OrderVo orderVo);
	AppResult updateOrderState(Order order);

	AppResult addOrderDepartmentDetail(OrderDepartmentDetail orderDepartmentDetail);
	Order getOrderById(Long id);

	/**
	 * 支付前修改支付方式
	 * @param orderId
	 * @return
	 */
	AppResult cancelWXPay(Long orderId);
}