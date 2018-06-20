package com.muzhi.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.muzhi.model.OrderInfo;
import com.muzhi.model.Result;
import com.muzhi.model.User;
import com.muzhi.redis.service.UserRedisService;
import com.muzhi.service.OrderService;
import com.muzhi.util.ResultUtil;

@Controller
@RequestMapping("/baseUrl")
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserRedisService userRedisService;
	/**
	 * 收取奖励
	 * @param info
	 * @return
	 */
	@RequestMapping(value = "/receiveReward", method = RequestMethod.POST)
	@ResponseBody
	public Result receiveReward(@RequestBody String info) {
		try {
			JSONObject parseObject = JSON.parseObject(info);
			return orderService.receiveReward(parseObject.getString("token"), parseObject.getInteger("orderId"));
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(500, "系统错误");
		}
	}
	/**
	 * 获取订单
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/getOrders", method = RequestMethod.GET)
	@ResponseBody
	public Result getOrders(String token) {
		try {
			User user = userRedisService.getUser(token);
			List<OrderInfo> orderFoodList = orderService.getOrders(user.getId());
			return ResultUtil.success(orderFoodList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(500, "系统错误");
		}
	}
	/**
	 * 刷新
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/refreshReward", method = RequestMethod.GET)
	@ResponseBody
	public Result refreshReward(String token) {
		try {
			return orderService.refreshReward(token);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(500, "系统错误");
		}
	}
}
