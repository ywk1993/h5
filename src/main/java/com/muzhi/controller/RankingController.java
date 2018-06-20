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
import com.muzhi.model.vo.RankVO;
import com.muzhi.service.RankingService;
import com.muzhi.util.ResultUtil;

/**
* @author ykw
* @version 创建时间：2018年6月14日 下午3:44:34
*/
@Controller
@RequestMapping("/baseUrl")
public class RankingController {
	@Autowired
	private RankingService rankingService;
	
	@RequestMapping(value="getRanking",method = RequestMethod.POST)
	@ResponseBody
	public Result getRanking(@RequestBody String str) {
		JSONObject json = JSON.parseObject(str);
		try {
			RankVO rankVO = rankingService.getRanking(json.getString("token"), json.getInteger("page"), json.getInteger("pageNum"));
			return ResultUtil.success(rankVO, 0);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(500, "获取排行榜失败");
		}
	}
}
