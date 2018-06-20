package com.muzhi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.muzhi.model.FriendN;
import com.muzhi.model.LoginID;
import com.muzhi.model.Result;
import com.muzhi.model.state.MessageType;
import com.muzhi.service.FriendService;
import com.muzhi.service.MessageService;
import com.muzhi.service.UserService;
import com.muzhi.service.WeixinService;
import com.muzhi.util.ResultUtil;

/**
 * 微信
 * @author yany
 *
 */
@Controller
@RequestMapping(value="/baseUrl")
public class WeixinController {
	@Autowired
	WeixinService weixinService;
	@Autowired
	UserService userService;
	@Autowired
	MessageService messageService;
	
	@RequestMapping(value="/getId", method=RequestMethod.POST)
	@ResponseBody
	public Result getOpenId(@RequestBody String js_code) {	
		JSONObject parseObject = JSON.parseObject(js_code);
		String openId = weixinService.getOpenId(parseObject.getString("js_code"));
		if (openId == null) {return ResultUtil.error(1110, "js_code无效，未能从js_code获取到openId！");}
		String loginFromWeinxin = userService.loginFromWeinxin(openId);
		String friendid = parseObject.getString("friendid");
		if (null != friendid) {
			messageService.sendMessage(MessageType.ADD_FRIEND_AGRREE, loginFromWeinxin, new String[]{parseObject.getString("friendid")});
		}  
		LoginID loginID = new LoginID(loginFromWeinxin);
		return ResultUtil.success(loginID);
	}
	
}

