package com.muzhi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.muzhi.model.Result;
import com.muzhi.model.User;
import com.muzhi.service.FacilityInfoService;
import com.muzhi.service.UserService;
import com.muzhi.util.ResultUtil;

@Controller
@RequestMapping("/baseUrl")
public class FacilityInfoController {
	
	@Autowired
	private FacilityInfoService facilityInfoService;
	
	@Autowired
	private UserService userService;
	/**
	 * 设施升级信息展示接口
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/facilityUpgradeInfo", method = RequestMethod.GET)
	@ResponseBody
	public Result facilityUpgradeInfo(String token) {
		try {
			User user = userService.getUserByToken(token);
			return ResultUtil.success(facilityInfoService.facilityUpgradeInfo(user.getId()));
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(500, "系统错误");
		}
	}
	/**
	 * 设施升级
	 * @param message
	 * @return
	 */
	@RequestMapping(value = "/facilityUpgrade", method = RequestMethod.POST)
	@ResponseBody
	public Result facilityupgrade(@RequestBody String message) {
		try {
			JSONObject parseObject = JSON.parseObject(message);
			return facilityInfoService.facilityUpgrade(parseObject.getString("token"),parseObject.getInteger("id"));
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(500, "系统错误");
		}
	}
}
