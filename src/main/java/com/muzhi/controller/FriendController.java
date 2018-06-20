package com.muzhi.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 好友
 * @author yany
 *
 */
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.muzhi.model.FriendN;
import com.muzhi.model.Result;
import com.muzhi.model.User;
import com.muzhi.model.state.MessageType;
import com.muzhi.service.FriendService;
import com.muzhi.service.MessageService;
import com.muzhi.service.UserService;
import com.muzhi.util.ResultUtil;
@Controller
@RequestMapping(value="/baseUrl")
public class FriendController {
	@Autowired
	FriendService friendService;
	@Autowired
	UserService userService;
	
	@RequestMapping(method=RequestMethod.GET, value="/getFriend") 
	@ResponseBody
	public Result getFriend(String token) {
		User user = userService.getUserByToken(token);
		return ResultUtil.success(friendService.selectFriendsList(user.getId()));
	}
	
	/**
	 * 1.新朋友列表接口
	 * data:{
	 * uid:1
	 * friendList:{
	 * 		friendid:2,
	 * 		nickname:"",
	 * 		url:"",
	 * 		rank:""
	 * }
	 * 
	 * }
	 * 2.同意或忽略接口
	 * token, friendid
	 * 
	 * 3.添加接口
	 * token, friendid
	 * code:用户不存在
	 * 
	 * @param message
	 * @return 
	 */
	@RequestMapping(method=RequestMethod.POST, value="/addFriend")
	@ResponseBody
	public Result sendMessage(@RequestBody String message) {
		JSONObject object = JSON.parseObject(message);
		FriendN friend = new FriendN(object.getString("userid"), object.getString("friendid"));
		try {
			friendService.insertN(friend);
		} catch (Exception e) {
			return ResultUtil.error(6000, "好友不存在");
		}
		return ResultUtil.success();
	}
	
	@Autowired
	MessageService messageService;
	@RequestMapping(method=RequestMethod.POST, value="/sendMessage")
	@ResponseBody
	public Result addFriend(@RequestBody String message) {
		JSONObject object = JSON.parseObject(message);
		try {
			Result result = messageService.sendMessage(MessageType.getMesssageType(object.getInteger("code")), object.getString("self"), new String[]{object.getString("others")});
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(500, "系统错误");
		}
		
		/*if (sendMessage) {
			return ResultUtil.success();
		} else {
			return ResultUtil.error(6060, "无效操作");
		}*/
	}
	
	/**
	 * 获取好友大厅
	 * @param str
	 * @return
	 */
	@RequestMapping(value="getFriendHall",method=RequestMethod.POST)
	@ResponseBody
	public Result getFriendHall(@RequestBody String str) {
		JSONObject jsonObject = JSON.parseObject(str);
		try {
			Result result = friendService.getFriendHall(jsonObject.getString("token"), jsonObject.getInteger("friendId"));
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(500, "获取好友大厅失败");
		}
	}
	/**
	 * 获取好友庄园
	 * @param str
	 * @return
	 */
	@RequestMapping(value="getFriendBuildAll",method=RequestMethod.POST)
	@ResponseBody
	public Result getFriendBuildAll(@RequestBody String str) {
		JSONObject jsonObject = JSON.parseObject(str);
		try {
			Result result = friendService.getFriendBuildAll(jsonObject.getString("token"), jsonObject.getInteger("friendId"));
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(500, "获取好友庄园失败");
		}
	}

}
