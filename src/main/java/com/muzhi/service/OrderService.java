package com.muzhi.service;

import java.util.List;

import com.muzhi.model.OrderInfo;
import com.muzhi.model.Result;

/**
 * 订单
 * @author Yuwk
 *
 * 2018年4月20日
 */
public interface OrderService {
	
	/**
	 * 刷新订单
	 * @param token
	 * @return
	 */
	public Result refreshReward(String token);
	/**
	 * 接收奖励
	 * @param token
	 * @param orderId
	 * @return
	 */
	public Result receiveReward(String token,Integer orderId);
	/**
	 * 获取订单列表
	 * @param uid
	 * @param level
	 * @return
	 */
	public List<OrderInfo> getOrders(Integer uid);
	
}
