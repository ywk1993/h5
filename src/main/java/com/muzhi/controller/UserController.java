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
import com.muzhi.service.UserService;
import com.muzhi.util.ResultUtil;
/**
 * 处理登录方面的控制
 * @author yany
 */
@Controller
@RequestMapping("/baseUrl")
public class UserController {
	
	@Autowired
	private UserService userService;
   /**
    * 用户登录
    * @param name
    * @param password
    * @return
    */
    @RequestMapping(value="/login",method=RequestMethod.POST)
    @ResponseBody
    public Result login(@RequestBody String user) {
    	try {
    		JSONObject parseObject = JSON.parseObject(user);
			Result result = userService.userLogin(parseObject.getString("userID"), parseObject.getString("password"), parseObject.getString("openid"), parseObject.getString("nickName"), parseObject.getString("avatarUrl"));
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(500, "系统错误");
		}
    }
    
    /**
     * 用户登录
     * @param name
     * @param password
     * @return
     */
     @RequestMapping(value="/refreshLogin",method=RequestMethod.POST)
     @ResponseBody
     public Result refreshLogin(@RequestBody String refreshToken) {
     	try {
     		JSONObject parseObject = JSON.parseObject(refreshToken);
 			Result result=userService.refreshLogin(parseObject.getString("refreshToken"));
 			return result;
 		} catch (Exception e) {
 			e.printStackTrace();
 			return ResultUtil.error(500, "系统错误");
 		} 
     } 

}
