package com.muzhi.controller;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.muzhi.model.Rank;
import com.muzhi.model.Result;
import com.muzhi.model.TaskMarker;
import com.muzhi.model.TaskMethod;
import com.muzhi.model.User;
import com.muzhi.model.vo.InventoryInfo;
import com.muzhi.model.vo.PropInfo;
import com.muzhi.service.BuildMaxLevelException;
import com.muzhi.service.CookBenchService;
import com.muzhi.service.FridgeService;
import com.muzhi.service.InventoryService;
import com.muzhi.service.RankService;
import com.muzhi.service.RestaurantService;
import com.muzhi.service.SaleFoodlService;
import com.muzhi.service.UserService;
import com.muzhi.service.impl.TaskNotAllFinishException;
import com.muzhi.util.ResultFormatUtil;
import com.muzhi.util.ResultUtil;

/**
 * 售卖模块 = 玩家 + 餐馆 + 普通客源 + 货柜 + 美食家  + 富豪  + 美食商人
 * @author yany
 *
 */
@Controller
@RequestMapping("/baseUrl")
public class SaleController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private InventoryService inventoryService;
	@Autowired
	private CookBenchService cookBenchService;
	@Autowired
	private FridgeService fridgeService;
	@Autowired
	private SaleFoodlService saleFoodlService;
	@Autowired
	private RankService rankService;
	
	/**
	 * 获取玩家信息
	 * @return
	 */
	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	@ResponseBody
	public Result getUser(String token) {
		try {
			User userByToken = userService.getUserByToken(token);
			Result result = ResultUtil.success(userByToken);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(500, "系统错误");
		}
	}
	
	/**
	 * 获取餐馆信息
	 * @return
	 */
	@RequestMapping(value = "/getRestaurant", method = RequestMethod.GET)
	@ResponseBody
	public Result getRestaurant( String token) {
		try {
			User userByToken = userService.getUserByToken(token);
			Result result = ResultUtil.success(restaurantService.getRestaurant(userByToken.getId()));
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(500, "系统错误");
		}
	}
	
	/**
	 * 获取货柜接口
	 * @return
	 */
	@RequestMapping(value = "/getInventory", method = RequestMethod.GET)
	@ResponseBody
	public Result getInventory( String token) {
		try {
			
			User userByToken = userService.getUserByToken(token);
			Result result = ResultUtil.success(inventoryService.getInventory(userByToken.getId()));
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(500, "系统错误");
		}
	}
	
	/**
	 * 获取灶台
	 * @return
	 */
	@RequestMapping(value = "/getCookBench", method = RequestMethod.GET)
	@ResponseBody
	public Result getCookBench( String token) {
		try {
			User userByToken = userService.getUserByToken(token);
			Result result = ResultUtil.success(cookBenchService.getCookBench(userByToken.getId()));
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(500, "系统错误");
		}
	}

	/**
	 * 获取冰箱信息 
	 * @return
	 */
	@RequestMapping(value = "/getFridge", method = RequestMethod.GET)
	@ResponseBody
	public Result getFridge(String token) {
		try {
			User userByToken = userService.getUserByToken(token);
			Result result = ResultUtil.success(fridgeService.getFridge(userByToken.getId()));
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error(500, "系统错误");
		}
	}
	
    /**
     * 拒绝客人请求
     * @param token
     * @param foodId
     * @param roleId
     * @param barId
     * @return
     */
    @RequestMapping(value="/refuseCustomer",method=RequestMethod.POST)
    @ResponseBody
    public Result refuseCustomer(@RequestBody String makeFoodInfo){
    	JSONObject parseObject = JSON.parseObject(makeFoodInfo);
    	try {
    		Result result=saleFoodlService.refuseCustomer(parseObject.getString("token"),parseObject.getInteger("foodId"),parseObject.getInteger("roleId"),parseObject.getInteger("index"));
    		return result;
    	} catch (Exception e) {
    		e.printStackTrace();
    		return ResultUtil.error(500, "系统错误");
    	}   	
    }
    
    /**
     * 售卖信息
     * @param makeFoodInfo
     * @return
     */
    @RequestMapping(value="/saleInfo",method=RequestMethod.POST)
    @ResponseBody
    public Result saleInfo( @RequestBody String makeFoodInfo){
    	JSONObject parseObject = JSON.parseObject(makeFoodInfo);
    	try {
    		Result result=saleFoodlService.saleInfo(parseObject.getString("token"),parseObject.getInteger("index"));
    		return result;
    	} catch (Exception e) {
    		e.printStackTrace();
    		return ResultUtil.error(500, "系统错误");
    	}   	
    }
    /**
     * 促销
     * @param makeFoodInfo
     * @return
     */
    @RequestMapping(value="/sellSale",method=RequestMethod.POST)
    @ResponseBody
    public Result sellSale( @RequestBody String makeFoodInfo){
    	JSONObject parseObject = JSON.parseObject(makeFoodInfo);
    	try {
    		Result result=saleFoodlService.sellSale(parseObject.getString("token"),parseObject.getInteger("index"),parseObject.getInteger("foodId"),parseObject.getInteger("cookbookLevel"));
    		return result;
    	} catch (Exception e) {
    		e.printStackTrace();
    		return ResultUtil.error(500, "系统错误");
    	}   	
    }
    /**
     * 打折
     * @param makeFoodInfo
     * @return
     */
    @RequestMapping(value="/discountSale",method=RequestMethod.POST)
    @ResponseBody
    public Result discountSale( @RequestBody String makeFoodInfo){
    	JSONObject parseObject = JSON.parseObject(makeFoodInfo);
    	try {
    		Result result=saleFoodlService.discountSale(parseObject.getString("token"),parseObject.getInteger("index"));
    		return result;
    	} catch (Exception e) {
    		e.printStackTrace();
    		return ResultUtil.error(500, "系统错误");
    	}   	
    }
    /**
     * 涨价
     * @param makeFoodInfo
     * @return
     */
    @RequestMapping(value="/risePriceSale",method=RequestMethod.POST)
    @ResponseBody
    public Result risePriceSale( @RequestBody String makeFoodInfo){
    	JSONObject parseObject = JSON.parseObject(makeFoodInfo);
    	try {
    		Result result=saleFoodlService.risePriceSale(parseObject.getString("token"),parseObject.getInteger("index"));
    		return result;
    	} catch (Exception e) {
    		e.printStackTrace();
    		return ResultUtil.error(500, "系统错误");
    	}   	
    }
    /**
     * 恭维
     * @param makeFoodInfo
     * @return
     */
    @RequestMapping(value="/compliment",method=RequestMethod.POST)
    @ResponseBody
    public Result complimentSale( @RequestBody String makeFoodInfo){
    	JSONObject parseObject = JSON.parseObject(makeFoodInfo);
    	try {
    		Result result=saleFoodlService.complimentSale(parseObject.getString("token"),parseObject.getInteger("index"));
    		return result;
    	} catch (Exception e) {
    		e.printStackTrace();
    		return ResultUtil.error(500, "系统错误");
    	}   	
    }
    /**
     * 卖
     * @param makeFoodInfo
     * @return
     */
    @RequestMapping(value="/saleFood",method=RequestMethod.POST)
    @ResponseBody
    @TaskMarker(method=TaskMethod.SALE_Y_FOOD)
    public Result saleFood( @RequestBody String makeFoodInfo){
    	JSONObject parseObject = JSON.parseObject(makeFoodInfo);
    	try {
    		Result result=saleFoodlService.saleFood(parseObject.getString("token"),parseObject.getInteger("foodId"),parseObject.getInteger("roleId"),parseObject.getInteger("index"));
    		return result;
    	} catch (Exception e) {
    		e.printStackTrace();
    		return ResultUtil.error(500, "系统错误");
    	}   	
    }
    
    public static void main(String[] args) {
    	try {
    		Method[] methods = SaleController.class.getMethods();
    		Vector<Method> vectorMethod = new Vector<Method>(Arrays.asList(methods));
    		for (Method method : vectorMethod) {
    			Annotation[] annotations = method.getAnnotations();
    			Vector<Annotation> vectorAnnotation = new Vector<Annotation>(Arrays.asList(annotations));
    			for (Annotation annotation : vectorAnnotation) {
    				if (annotation instanceof TaskMarker) {
    					System.out.println(true);
    				} 
    				
    			}
    		}
		} catch(Exception e) {
			e.printStackTrace();
		}
//		getAnnotationsByType(TaskMarker.class);
		
//		for (int i = 0; i < annotationsByType.length; i++) {
//			System.out.println(annotationsByType[i].method());
//			System.out.println(1);
//		}
	}
	/**
	 * 库存接口
	 * @param token
	 * @return
	 */
	@RequestMapping(value="/inventory", method=RequestMethod.GET)
	@ResponseBody
	public Result inventory(String token){
		
		User user = userService.getUserByToken(token);
		InventoryInfo allInventory = saleFoodlService.getAllInventory(user);
		return ResultUtil.success(allInventory);
		
	}
	/**
	 * 高级食材接口
	 * @param token
	 * @return
	 */
	@RequestMapping(value="/prop", method=RequestMethod.GET)
	@ResponseBody
	public Result prop(String token){
		
		User user = userService.getUserByToken(token);
		List<PropInfo> propInfo = saleFoodlService.getPropInfo(user);
		return ResultUtil.success(propInfo);
		
	}
	/**
	 * 获取评级系统
	 * @param token
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getRankSystem", method=RequestMethod.GET)
	public Result getRank(String token) {
		Rank query = rankService.query(userService.getUserByToken(token).getId());
		return ResultFormatUtil.success(query, query.getClass().getSimpleName());
	}
	/**
	 * 提高评级
	 * @param token
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/inproveRank", method=RequestMethod.GET)
	public Result inproveRank(String token) {
		Result result = null;
		try {
			Integer id = userService.getUserByToken(token).getId();
			rankService.update(id);
			Rank query = rankService.refresh(token);
			result = ResultFormatUtil.success(query, query.getClass().getSimpleName());
		} catch (BuildMaxLevelException e) {
			e.printStackTrace();
			result = ResultFormatUtil.error(1101, "已经达到最高评级！");
		} catch (TaskNotAllFinishException e) {
			e.printStackTrace();
			result = ResultFormatUtil.error(1102, "任务未全部完成！");
		} 
		return result;
		
	}
	/**
	 * 菜品激活信息
	 * @param token
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getActivateInfo", method=RequestMethod.POST)
	public Result getActivateInfo(@RequestBody String makeFoodInfo) {
		JSONObject parseObject = JSON.parseObject(makeFoodInfo);
		try {
			return inventoryService.getActivateInfo(parseObject.getString("token"),parseObject.getInteger("propId"));
    	} catch (Exception e) {
    		e.printStackTrace();
    		return ResultUtil.error(500, "系统错误");
    	}   	
		
	}
	/**
	 * 激活
	 * @param token
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/activateCookbook", method=RequestMethod.POST)
	public Result activateCookbook(@RequestBody String makeFoodInfo) {
		JSONObject parseObject = JSON.parseObject(makeFoodInfo);
		try {
			return inventoryService.activateCookbook(parseObject.getString("token"),parseObject.getInteger("foodId"));
    	} catch (Exception e) {
    		e.printStackTrace();
    		return ResultUtil.error(500, "系统错误");
    	}   	
		
	}
}
