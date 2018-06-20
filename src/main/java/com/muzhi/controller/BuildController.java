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
import com.muzhi.model.vo.Build;
import com.muzhi.service.BuildMaxLevelException;
import com.muzhi.service.BuildService;
import com.muzhi.service.BuildUpgradingException;
import com.muzhi.service.FoodcenterService;
import com.muzhi.service.UserService;
import com.muzhi.service.exception.DiamondNoEnoughException;
import com.muzhi.service.exception.GoldNoEnoughException;
import com.muzhi.service.exception.NoUpgradingException;
import com.muzhi.util.ResultUtil;

/**
 * 建筑模块 
 * @author yany
 *
 */
@Controller
@RequestMapping("/baseUrl")
public class BuildController {
	
	@Autowired
	BuildService buildService;
	@Autowired
	UserService	userService;
	@Autowired
	FoodcenterService	foodcenterService;
	/**
     * 获取所有建筑
     * @param info
     * @return
     */
    @RequestMapping(value="/getBuildAll",method=RequestMethod.GET)
    @ResponseBody
    public Result getBuildAll( String token ){
    	
    	try {
			User userByToken = userService.getUserByToken(token);
			buildService.getBuildAll(userByToken);//重复刷新
    		Result result= ResultUtil.success(buildService.getBuildAll(userByToken));
    		return result;
    	} catch (Exception e) {
    		e.printStackTrace();
    		return ResultUtil.error(500, "系统错误");
    	}   	
    }
    
    /**
     * 是否升级
     * @param info
     * @return
     */
    @SuppressWarnings("finally")
	@RequestMapping(value="/isAllowUpgrade",method=RequestMethod.POST)
    @ResponseBody
    public Result isAllowUpgrade (@RequestBody String str) {
    	Result result = null;
    	Build allowUpgrade = null;
    	try {
    		JSONObject parseObject = JSON.parseObject(str);
    		String token = parseObject.getString("token");
    		Integer type = parseObject.getInteger("type");
    		allowUpgrade = buildService.isAllowUpgrade(token, type);
    		result = ResultUtil.success(allowUpgrade, 0, "允许升级！");
    	} catch (GoldNoEnoughException e) {
    		result = ResultUtil.success(allowUpgrade, Integer.valueOf(e.getMessage()), "材料不足");
    	} catch (BuildUpgradingException e) {
    		result = ResultUtil.success(allowUpgrade, 4001, "无法升级，正在升级！");
    	} catch(BuildMaxLevelException e) {
    		result = ResultUtil.success(allowUpgrade,4003,"当前建筑已到达最高等级");
    	} finally {
    		return result; 
		}
    }
    
    /**
     * 加速升级
     * @param info
     * @return
     */
    @SuppressWarnings("finally")
	@RequestMapping(value="/accelerateAllowUpgrade",method=RequestMethod.POST)
    @ResponseBody
    public Result accelerateAllowUpgrade (@RequestBody String str) {
    	Result result = null;
    	Build allowUpgrade = null;
    	try {
    		JSONObject parseObject = JSON.parseObject(str);
    		String token = parseObject.getString("token");
    		Integer type = parseObject.getInteger("type");
    		User userByToken = userService.getUserByToken(token);
    		allowUpgrade = buildService.accelerateAllowUpgrade(userByToken, type);
    		result = ResultUtil.success(allowUpgrade, 0, "允许加速！");
    	} catch (DiamondNoEnoughException e) {
    		result = ResultUtil.success(allowUpgrade, 4005, "钻石不足！");
    	} catch (NoUpgradingException e) {
    		result = ResultUtil.success(allowUpgrade, 4006, "没有正在升级的建筑！");
    	} finally {
    		return result; 
		}
    }
    
    /**
     * 取消升级
     * @param info
     * @return
     */
    @SuppressWarnings("finally")
	@RequestMapping(value="/quitAllowUpgrade",method=RequestMethod.POST)
    @ResponseBody
    public Result quitAllowUpgrade(@RequestBody String str) {
    	Result result = null;
    	Build allowUpgrade = null;
    	try {
    		JSONObject parseObject = JSON.parseObject(str);
    		String token = parseObject.getString("token");
    		Integer type = parseObject.getInteger("type");
    		allowUpgrade = buildService.quitAllowUpgrade(token, type);
    		result = ResultUtil.success(allowUpgrade, 0, "允许取消");
    	} catch (NoUpgradingException e) {
    		result = ResultUtil.success(allowUpgrade, 4006, "没有正在升级的建筑！");
    	} finally {
    		return result; 
		} 
    }
    
    /**
     * 收取
     * @param info
     * @return
     */
    @RequestMapping(value="/collect",method=RequestMethod.POST)
    @ResponseBody
    public Result collect (@RequestBody String str) {
    	try {
    		JSONObject parseObject = JSON.parseObject(str);
    		String token = parseObject.getString("token");
    		Integer type = parseObject.getInteger("type");
    		User userByToken = userService.getUserByToken(token);
    		Result result= ResultUtil.success(buildService.collect(userByToken, type));
    		return result;
    	} catch (Exception e) {
    		e.printStackTrace();
    		return ResultUtil.error(500, "系统错误");
    	}   	
    }
    
    
    
    /**
     * 美食中心研究页面
     * @param info
     * @return
     */
    @RequestMapping(value="/getBookResearch",method=RequestMethod.GET)
    @ResponseBody
    public Result getBookResearch( String token ){
    	
    	try {
    		return  foodcenterService.getBookResearch(token);
    	} catch (Exception e) {
    		e.printStackTrace();
    		return ResultUtil.error(500, "系统错误");
    	}   	
    }
    /**
     * 研究美食
     * @param info
     * @return
     */
    @RequestMapping(value="/doBookResearch",method=RequestMethod.POST)
    @ResponseBody
    public Result doBookResearch( @RequestBody String info ){
    	
    	try {
    		JSONObject parseObject = JSON.parseObject(info);
    		String token = parseObject.getString("token");
    		Integer foodID = parseObject.getInteger("foodID");
    	    return foodcenterService.doBookResearch(token, foodID);
    	} catch (Exception e) {
    		e.printStackTrace();
    		return ResultUtil.error(500, "系统错误");
    	}   	
    }
    /**
     * 取消研究美食
     * @param info
     * @return
     */
    @RequestMapping(value="/cancerBookResearch",method=RequestMethod.POST)
    @ResponseBody
    public Result cancerBookResearch( @RequestBody String info  ){
    	
    	try {
    		JSONObject parseObject = JSON.parseObject(info);
    		String token = parseObject.getString("token");
    		Integer foodID = parseObject.getInteger("foodID");
    	    return foodcenterService.cancerBookResearch(token, foodID);
    	} catch (Exception e) {
    		e.printStackTrace();
    		return ResultUtil.error(500, "系统错误");
    	}   	
    }
    
    /**
     * 图鉴
     * @param info
     * @return
     */
    @RequestMapping(value="/drawing",method=RequestMethod.GET)
    @ResponseBody
    public Result getDraw( String token){
    	try {
    		return foodcenterService.getDraw(token);
    	} catch (Exception e) {
    		e.printStackTrace();
    		return ResultUtil.error(500, "系统错误");
    	}   	
    }
    /**
     * 研究加速
     * @param info
     * @return
     */
    @RequestMapping(value="/addSpeed",method=RequestMethod.GET)
    @ResponseBody
    public Result addSpeed( String token){
    	try {
    		return foodcenterService.addSpeed(token);
    	} catch (Exception e) {
    		e.printStackTrace();
    		return ResultUtil.error(500, "系统错误");
    	}   	
    }
    
}
