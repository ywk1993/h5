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
import com.muzhi.model.Result;
import com.muzhi.model.User;
import com.muzhi.model.vo.EmployManager;
import com.muzhi.model.vo.StaffAll;
import com.muzhi.service.InteractiveService;
import com.muzhi.service.UserService;
import com.muzhi.util.ResultUtil;

/**
 * 交互模块，如模块有细分，作为交互调度控制
 * @author yany
 *
 */
@Controller
@RequestMapping("/baseUrl")
public class InteractiveController {
	
	@Autowired
	InteractiveService interactiveService;
	@Autowired
	UserService userService;
	/**
	 * 雇佣来源接口
	 * @param str
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/employSource", method = RequestMethod.GET)
	public Result employSource(String token) {
		User userByToken = userService.getUserByToken(token);
		
		List<EmployManager> employSource = interactiveService.employSource(userByToken);
		return ResultUtil.success(employSource);
	}
	/**
	 * 雇佣接口
	 * @param str
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/employ", method = RequestMethod.POST)
	public Result employ(@RequestBody String str) {
		JSONObject parseObject = JSON.parseObject(str);
		String token = parseObject.getString("token");
		User userByToken = userService.getUserByToken(token);
		Integer id = parseObject.getInteger("id");
		Integer type = parseObject.getInteger("type");
		Integer index = parseObject.getInteger("index");
		
		return interactiveService.employ(userByToken, id, type,index);
	}
	/**
	 * 雇佣接口
	 * @param str
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/employAgain", method = RequestMethod.POST)
	public Result employAgain(@RequestBody String str) {
		JSONObject parseObject = JSON.parseObject(str);
		String token = parseObject.getString("token");
		User userByToken = userService.getUserByToken(token);
		Integer id = parseObject.getInteger("id");
		Integer type = parseObject.getInteger("type");
		Integer index = parseObject.getInteger("index");
		
		return interactiveService.employAgain(userByToken, id, type,index);
	}
	/**
	 * 获取员工接口
	 * @param str
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getStaff", method = RequestMethod.GET)
	public Result getStaff( String token) {
		User userByToken = userService.getUserByToken(token);
		
		StaffAll staffAll = interactiveService.getStaff(userByToken);
		return ResultUtil.success(staffAll);
	}
	/**
	 * 解雇员工接口
	 * @param str
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/dismiss",method = RequestMethod.POST)
	public Result dismiss(@RequestBody String str) {
		JSONObject parseObject = JSON.parseObject(str);
		String token = parseObject.getString("token");
		User user = userService.getUserByToken(token);
		Integer id = parseObject.getInteger("id");
		Integer type = parseObject.getInteger("type");
		return interactiveService.dismiss(user, id, type);
	}
}
