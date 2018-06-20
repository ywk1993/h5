package com.muzhi.service.impl;

import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.muzhi.dao.CookBenchDao;
import com.muzhi.dao.LoginRecordDao;
import com.muzhi.dao.ProvinceDao;
import com.muzhi.dao.RefreshManagerDao;
import com.muzhi.dao.UserDao;
import com.muzhi.model.Fridge;
import com.muzhi.model.Inventory;
import com.muzhi.model.LoginResult;
import com.muzhi.model.Province;
import com.muzhi.model.RefreshManager;
import com.muzhi.model.Restaurant;
import com.muzhi.model.Result;
import com.muzhi.model.User;
import com.muzhi.model.vo.MenuAll;
import com.muzhi.model.vo.StaffAll;
import com.muzhi.redis.SpringRedisService;
import com.muzhi.service.AdminThread;
import com.muzhi.service.CookBenchService;
import com.muzhi.service.CookBookNService;
import com.muzhi.service.FoodService;
import com.muzhi.service.FridgeService;
import com.muzhi.service.InteractiveService;
import com.muzhi.service.InventoryService;
import com.muzhi.service.LoginService;
import com.muzhi.service.ManagerService;
import com.muzhi.service.RestaurantService;
import com.muzhi.service.UserService;
import com.muzhi.util.HttpUtils;
import com.muzhi.util.IP;
import com.muzhi.util.LogStrBuffer;


/**
 * 
 * @author Yuwk
 *
 *         2018年1月2日
 */
@Aspect
@Component
public class ResultAop{
	@Autowired
	private UserService userService;
	@Autowired
	private FridgeService fridgeService;
	@Autowired
	CookBenchDao cookBenchDao;
	@Autowired
	CookBenchService cookBenchService;
	@Autowired
	CookBookNService cookBookNService;
	@Autowired
	RestaurantService restaurantService;
	@Autowired
	InteractiveService interactiveService;
	@Autowired
	LoginService loginService;
	@Autowired
	UserDao userDao;
	@Autowired
	LoginRecordDao loginRecordDao;
	@Autowired
	SpringRedisService springRedis;
	@Autowired
	InventoryService inventoryService;
	@Autowired
	FoodService foodService;
	@Autowired
	ManagerService managerService;
	@Autowired
	private ProvinceDao provinceDao;
	
	@Pointcut("execution(* com.muzhi.controller.*.*(..))")
	private void pointCut() {
	}
	
	@Pointcut("execution(* com.muzhi.controller.MakeFoodController.makeFood(..))")
	private void pointCutMake() {
	}
	
	@Pointcut("execution(* com.muzhi.controller.MakeFoodController.isChefEnough(..))")
	private void pointCutIsChefEnough() {
	}
	
	@Pointcut("execution(* com.muzhi.controller.UserController.login(..))")
	private void userLogin() {
	}
	
	@Pointcut("execution(* com.muzhi.controller.OrderController.*(..))")
	private void pointCutOrder() {
	}
	
	
	/**
	 * 后置通知
	 */

	@AfterReturning(value = "pointCut()", returning = "val")
	public void afterMethod(JoinPoint joinPoint, Object val) {

		LogStrBuffer.addInforLog("调用aop代理方法==============================");
		
		Fridge fridge = null;
		Object[] args = joinPoint.getArgs();
		String token;
		try {
			JSONObject parseObject = JSON.parseObject(args[0].toString());
			token = parseObject.getString("token");
		} catch (JSONException e) {
			token = args[0].toString();
			//e.printStackTrace();
		}
		User user = userService.getUserByToken(token);
		
		if(null != user) {
		fridge = fridgeService.selectByPrimaryKey(user.getId());
		}
		if(null != fridge) {
			if (val instanceof Result) {
				((Result) val).setFridge(fridge);
				((Result) val).setUser(user);
			}
		}
	}
	
	/**
	 * 结果参数
	 * @param joinPoint
	 * @param val
	 */
	@AfterReturning(value = "pointCut()", returning = "val")
	public void afterMethodShowResult(JoinPoint joinPoint, Object val) {
		String methodName = joinPoint.getSignature().getName();
		LogStrBuffer.addInforLog("method:" + methodName + "的结果参数为" + val.toString());
		LogStrBuffer.flushLog();
	}
	
	/**
	 * 入口参数
	 * @param joinPoint
	 */
	@Before(value = "pointCut()")
    public void beforeMethod(JoinPoint joinPoint){
		String methodName = joinPoint.getSignature().getName();
        LogStrBuffer.startLogging(LogStrBuffer.logLevelInfor, LogStrBuffer.LogTypeFile);
		LogStrBuffer.addInforLog("method:" + methodName + "的入口参数为" + Arrays.asList(joinPoint.getArgs()));
	}
	
	/**
	 * 入口参数
	 * @param joinPoint
	 */
	@AfterReturning(value = "pointCutMake()", returning = "val")
    public void makeFoodAfterMethod(JoinPoint joinPoint, Object val){
		
		String methodName = joinPoint.getSignature().getName();
		LogStrBuffer.addInforLog("method:" + methodName + "的结果参数为" + val.toString());
		
		Object[] args = joinPoint.getArgs();
		String token;
		try {
			JSONObject parseObject = JSON.parseObject(args[0].toString());
			token = parseObject.getString("token");
		} catch (JSONException e) {
			token = args[0].toString();
			//e.printStackTrace();
		}
		User user = userService.getUserByToken(token);
		MenuAll cookBook = cookBookNService.getCookBook(user);
		Restaurant restaurant = restaurantService.getRestaurant(user.getId());
		StaffAll staff = interactiveService.getStaff(user);
		
		((Result) val).setMenuAll(cookBook);
		((Result) val).setRestaurant(restaurant);
		((Result) val).setStaff(staff);
	}
	
	/**
	 * 入口参数
	 * @param joinPoint
	 */
	@Autowired
	RefreshManagerDao refreshManagerDao;
	@AfterReturning(value = "pointCutOrder()", returning = "val")
    public void orderAfterMethod(JoinPoint joinPoint, Object val){
		
		String methodName = joinPoint.getSignature().getName();
		LogStrBuffer.addInforLog("method:" + methodName + "的结果参数为" + val.toString());
		
		Object[] args = joinPoint.getArgs();
		String token;
		try {
			JSONObject parseObject = JSON.parseObject(args[0].toString());
			token = parseObject.getString("token");
		} catch (JSONException e) {
			token = args[0].toString();
			//e.printStackTrace();
		}
		User user = userService.getUserByToken(token);
		RefreshManager refreshManager = refreshManagerDao.selectByPrimaryKey(user.getId());
		Inventory inventory = inventoryService.getInventory(user.getId());
		inventory.setFoodList(foodService.getFood(user.getId()));
		refreshManager.setCurrencyId(5);
		refreshManager.setCurrencyNum(refreshManager.getTotleNum() * 20);
		((Result) val).setRefreshManager(refreshManager);
		((Result) val).setInventory(inventory);
	}
	
	/**
	 * 入口参数
	 * @param joinPoint
	 */
	@AfterReturning(value = "pointCutIsChefEnough()", returning = "val")
    public void isEnoughAfterMethod(JoinPoint joinPoint, Object val){
		
		Object[] args = joinPoint.getArgs();
		String token;
		try {
			JSONObject parseObject = JSON.parseObject(args[0].toString());
			token = parseObject.getString("token");
		} catch (JSONException e) {
			token = args[0].toString();
			//e.printStackTrace();
		}
		User user = userService.getUserByToken(token);
		StaffAll staff = interactiveService.getStaff(user);
		
		((Result) val).setStaff(staff);
	}
	
	/**
	 * 登录后置通知
	 * @param joinPoint
	 * @throws Exception
	 */
	private BlockingQueue<Runnable> blockingQueue =new LinkedBlockingQueue<>();
	private ThreadPoolExecutor threadPoolExecutor =new ThreadPoolExecutor(10, 20, 1000, TimeUnit.MILLISECONDS, blockingQueue);
	@AfterReturning(value = "userLogin()", returning = "val")
	public void afterUserLogin(JoinPoint joinPoint ,Object val) throws Exception {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		
		Object parament =((Result) val).getData();
		String token =((LoginResult) parament).getToken();
		User user = userService.getUserByToken(token);
		String loginIp = HttpUtils.getIpAddr(request);
		String province = IP.getProvinceByIP(loginIp);
		if(null==province) {
			province = "广东省";
		}
		
		provinceDao.updateByPrimaryKey(new Province(user.getId(), province));
		
		//调用游戏后台接口
		threadPoolExecutor.execute(new AdminThread(joinPoint,managerService,val));
		/*Thread t = new AdminThread(joinPoint,managerService,val);
		t.start();*/
			
		System.out.println("方法执行完成");
		
	/*	try {
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			Object[] args = joinPoint.getArgs();
			JSONObject parseObject = JSON.parseObject(args[0].toString());
			String userName = parseObject.getString("userID");
			String password = parseObject.getString("password");
			Object parament =((Result) val).getData();
			//String token =((LoginResult) parament).getToken();
			String ua = request.getHeader("User-Agent");
			UserAgent userAgent = UserAgent.parseUserAgentString(ua);  
			Browser browser = userAgent.getBrowser();  
			
			Login selectOne = loginService.selectOne(userName, password);
			
			User user = userDao.getUser(selectOne.getId());
			//登录ip
			String loginIp = request.getRemoteAddr();
			LoginRecord loginRecord = new LoginRecord();
			String id =UUID.randomUUID().toString();
			loginRecord.setId(id);
			loginRecord.setUserId(user.getId());
			loginRecord.setRegisterTime(selectOne.getRegisterTime());
			loginRecord.setLoginTime(new Date());
			loginRecord.setLoginIp(loginIp);
			loginRecord.setPhoneModel(browser.toString());
			loginRecord.setLogoutTime(null);//暂定
			loginRecord.setEmpower(null);//暂定
			loginRecord.setForum(null);//暂定
			loginRecordDao.insert(loginRecord);
			//springRedis.set(token, id);
		} catch (Exception e) {
			System.out.println("游戏后台错误");
			e.printStackTrace();
		}*/
	}
	
}
