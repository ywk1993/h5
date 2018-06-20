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
import com.muzhi.service.CookBookNService;
import com.muzhi.service.FoodService;
import com.muzhi.service.HallService;
import com.muzhi.service.UserService;
import com.muzhi.util.ResultUtil;


/**
 * 制作食物模块  = 菜谱 + 
 * @author yany
 * 
 */
@Controller
@RequestMapping("/baseUrl")
public class MakeFoodController {
	@Autowired
	private FoodService foodService;
	
	@Autowired
	private HallService hallService;
	@Autowired
	private UserService userService;
	@Autowired
	private CookBookNService cookBookNService;
	
   /**
    * 大厅接口
    * @param name
    * @param password
    * @return
    */
    @RequestMapping(value="/getHall",method=RequestMethod.GET)
    @ResponseBody
    public Result getHall( String token) {
    	try {
			User userByToken = userService.getUserByToken(token);
			Result result= ResultUtil.success(hallService.getHall(userByToken));
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(500, "系统错误");
		}
    }
	
	 /**
     * 食物收取
     * @param stockFoodInfo
     * @return
     */
    @RequestMapping(value="/stockFood",method=RequestMethod.POST)
    @ResponseBody
    public Result stockFood( @RequestBody String stockFoodInfo){
    	JSONObject parseObject = JSON.parseObject(stockFoodInfo);
    	try {
    		Result result=foodService.stockFood(parseObject.getString("token"),parseObject.getInteger("foodID"),parseObject.getInteger("index"));
    		return result;
    	} catch (Exception e) {
    		e.printStackTrace();
    		return ResultUtil.error(500, "系统错误");
    	}   	
    }
    
    /**
     * 制作
     * @param makeFoodInfo
     * @return
     */
    @RequestMapping(value="/makeFood",method=RequestMethod.POST)
    @ResponseBody
   // @TaskMarker(method=TaskMethod.MAKE_Y_FOOD)
//    @TaskMarker(method=TaskMethod.MAKE_X_Y, xValue="foodID")
    //@TaskMarker(method=TaskMethod.MAKE_X_QUALIFY_Y)
//    @TaskMarker(method=TaskMethod)
    public Result makeFood( @RequestBody String makeFoodInfo){
    	JSONObject parseObject = JSON.parseObject(makeFoodInfo);
    	try {
    		Result result=foodService.makeFood(parseObject.getString("token"),parseObject.getInteger("foodID"),parseObject.getInteger("index"));
    		return result;
    	} catch (Exception e) {
    		e.printStackTrace();
    		return ResultUtil.error(500, "系统错误");
    	}   	
    }
    /**
     * 厨师位是否足够
     * @param token
     * @return
     */
    @RequestMapping(value="/isChefEnough",method=RequestMethod.GET)
    @ResponseBody
    public Result isChefEnough( String token){
    	try {
    		Result result=foodService.isChefEnough(token);
    		return result;
    	} catch (Exception e) {
    		e.printStackTrace();
    		return ResultUtil.error(500, "系统错误");
    	}   	
    }
    /**
     * 获取菜谱接口
     * @param token
     * @return
     */
    @RequestMapping(value="/getCookBook",method=RequestMethod.GET)
    @ResponseBody

    public Result getCookBook(String token){
    	try {
    		User user = userService.getUserByToken(token);
    		Result result= ResultUtil.success(cookBookNService.getCookBook(user));
    		return result;
    	} catch (Exception e) {
    		e.printStackTrace();
    		return ResultUtil.error(500, "系统错误");
    	} 	
    }
    /**
     * 菜谱详情
     * @param info
     * @return
     */
    @RequestMapping(value="/getFoodDetail",method=RequestMethod.GET)
    @ResponseBody
    public Result getFoodDetail( String token, Integer foodID ){
    	
    	try {
    		User userByToken = userService.getUserByToken(token);
    		Result result= ResultUtil.success(cookBookNService.getFoodDetail(userByToken, foodID));
    		return result;
    	} catch (Exception e) {
    		e.printStackTrace();
    		return ResultUtil.error(500, "系统错误");
    	}   	
    }
}
