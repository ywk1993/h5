//package com.muzhi.service;
//
//import java.lang.reflect.Method;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Vector;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.stereotype.Component;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONException;
//import com.alibaba.fastjson.JSONObject;
//import com.muzhi.model.Fridge;
//import com.muzhi.model.MakeFoodInfo;
//import com.muzhi.model.Rank;
//import com.muzhi.model.Result;
//import com.muzhi.model.Task;
//import com.muzhi.model.TaskMarker;
//import com.muzhi.model.TaskMethod;
//import com.muzhi.model.User;
//import com.muzhi.util.LogStrBuffer;
//import com.muzhi.util.ResultFormatUtil;
//
//
///**
// * 处理评级任务相关的业务, 由于任务与最终的Result耦合，故该切面放在最后处理。
// * @author yany
// *
// */
//@Aspect
//@Component
//public class RankAop {
//	@Autowired
//	UserService userService;
//	@Autowired
//	TaskService taskService;
//	@Autowired
//	BuildService buildService;
//	@Autowired
//	CookBookNService cookBookNSerivce;
//	@Autowired
//	ArticleService articleService;
//	@Autowired
//	FoodcenterService foodcenterService;
//	@Autowired
//	RankService rankService;
//	
//	@Pointcut("execution(* com.muzhi.controller.SaleController.inproveRank(..))")
//	private void controllerTask() {
//	}
//	
//	/**
//	 * "1：成功售卖菜品达到X
//		2：制作菜品达到X：
//		3：制作X品质菜品达到X：
//		4：制作X菜品达到Y：
//		5：研究力达到X：
//		6：豪华度达到X：
//		7：收集菜谱数量达到X：
//		8：X建筑等级达到X：
//		9：雇佣X次数达到X：
//		10:制作X等级的菜品达到Y：
//		11：在订单中心完成X份订单："
//	 * @param joinPoint
//	 * @param val
//	 */
//	public static String route() {
//		
//		return null;
//	}
//	
//	@SuppressWarnings("static-access")
//	@AfterReturning(value = "controllerTask()", returning = "val")
//	public void controllerTask(JoinPoint joinPoint, Object val) {
//		LogStrBuffer.addInforLog("调用aop代理方法==============================");
//		Object[] args = joinPoint.getArgs();
//		String token;
//		try {
//			JSONObject parseObject = JSON.parseObject(args[0].toString());
//			token = parseObject.getString("token");
//		} catch (JSONException e) {
//			token = args[0].toString();
//			//e.printStackTrace();
//		}
//		User user = userService.getUserByToken(token);
//		// 刷新 5,6,7,8,
//		buildService.getBuildAll(user);
//		articleService.getArticleInfo(token);
//		foodcenterService.getBookResearch(token);
//		cookBookNSerivce.getCookBook(user);
//		Rank query = rankService.query(user.getId());
//		val = ResultFormatUtil.success(query, query.getClass().getSimpleName());
//	}
//
//}
//	
