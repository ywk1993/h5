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
import com.muzhi.service.ArticleService;
import com.muzhi.util.ResultUtil;

@Controller
@RequestMapping("/baseUrl")
public class ArticleContrller {
   @Autowired
   private ArticleService articleService;
   
   /**
    * 获取装扮信息
    * @param token
    * @return
    */
	@RequestMapping(value = "/getArticleInfo", method = RequestMethod.GET)
	@ResponseBody
	public Result getArticleInfo(String token) {
		try {
			return ResultUtil.success(articleService.getArticleInfo(token));
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(500, "系统错误");
		}
	}
	/**
	   * 激活装扮
	   * @param token
	   * @param articleId
	   * @return
	   */
	@RequestMapping(value = "/activateArticle", method = RequestMethod.POST)
	@ResponseBody
	public Result activateArticle(@RequestBody String info) {
		try {
			JSONObject parseObject = JSON.parseObject(info);
			return articleService.activateArticle(parseObject.getString("token"),parseObject.getInteger("articleId"));
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(500, "系统错误");
		}
	}

	/**
	 * 装扮当前装扮
	 * 
	 * @param token
	 * @param articleId
	 * @return
	 */
	@RequestMapping(value = "/dressArticle", method = RequestMethod.POST)
	@ResponseBody
	public Result dressArticle(@RequestBody String info) {
		try {
			JSONObject parseObject = JSON.parseObject(info);
			return articleService.dressArticle(parseObject.getString("token"),parseObject.getInteger("articleId"));
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(500, "系统错误");
		}
	}
}
