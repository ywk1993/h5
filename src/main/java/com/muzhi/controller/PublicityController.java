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
import com.muzhi.service.PublicityService;

/**
* @author ykw
* @version 创建时间：2018年6月6日 下午3:35:03
*/
@Controller
@RequestMapping("/baseUrl")
public class PublicityController {
	@Autowired
	private PublicityService publicityService;
	
	/**
	 * 获取宣传页面详情
	 * @param token
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getPublicityPage", method = RequestMethod.GET)
	public Object getPublicityPage(String token) {
		Object obj = null;
		try {
			obj = publicityService.getPublicityPage(token);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(500, "获取宣传页面失败", null, null, null);
		}
		return new Result(0, null, obj, null, null);
	}
	/**
	 * 宣传
	 * @param token
	 * @param type
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "publicity", method = RequestMethod.POST)
	public Object publicity(@RequestBody String str) {
		JSONObject jsonObject = JSON.parseObject(str);
		return publicityService.publicity(jsonObject.getString("token"), jsonObject.getInteger("type"));
	}
	/**
	 * 获取名气页面详情
	 * @param token
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getFameInfo", method = RequestMethod.GET)
	public Result getFameInfo(String token) {
		
		return publicityService.getFameInfo(token);
	}
}
