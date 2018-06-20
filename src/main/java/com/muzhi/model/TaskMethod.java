package com.muzhi.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 任务执行的方法
 * @author yany
 * "


11：在订单中心完成X份订单："
"1：成功售卖菜品达到X：填写数量。
2：制作菜品达到X：填写数量。
3：制作X品质菜品达到X：填写品质，数量。
4：制作X菜品达到Y：填写菜品ID，数量。
5：研究力达到X：填写研究力。
6：豪华度达到X：填写豪华度值。
7：收集菜谱数量达到X：填写菜谱数量。
8：X建筑等级达到X：填写建筑ID，等级。
9：雇佣X次数达到X：填写职位ID（1为厨师，2为迎宾），次数。
10:制作X等级的菜品达到Y：填写等级，数量
11：在订单中心完成X份订单：填写数量"
 * 
 */
public enum TaskMethod {
	/**
	 * 1：成功售卖菜品达到X
	 */
	SALE_Y_FOOD(1, "1：成功售卖菜品达到X", "salefood"),
	/**
	 * 2：制作菜品达到X：
	 */
	MAKE_Y_FOOD(2, "2：制作菜品达到X：", "makeFood"),
	/**
	 * 3：制作X品质菜品达到X：
	 */
	MAKE_X_QUALIFY_Y(3, "3：制作X品质菜品达到X：", "doBookResearch"),
	/**
	 * 4：制作X菜品达到Y：
	 */
	MAKE_X_Y(4, "4：制作X菜品达到Y：", "makeFoodById"),
	/**
	 * 5：研究力达到X：
	 */
	RESEARCH_Y(5, "5：研究力达到X：", "getBookResearch"),
	/**
	 * 6：豪华度达到X：
	 */
	DELUXE_X(6, "6：豪华度达到X：", "getArticleInfo"),
	/**
	 * 7：收集菜谱数量达到X：
	 */
	COLLECT_Y(7, "7：收集菜谱数量达到X：", "getCookBook"),
	/**
	 * 8：X建筑等级达到X：
	 */
	X_BUILD_Y_LEVEL(8, "8：X建筑等级达到X：", "getBuildAll"), 
	/**
	 * 9：雇佣X次数达到X：
	 */
	EMPLOY_X_Y_TIME(9, "9：雇佣X次数达到X：", "employ"), 
	/**
	 * 10:制作X等级的菜品达到Y：
	 */
	MAKEFOOD_X_LEVEL_Y_NUM(10, "10:制作X等级的菜品达到Y：", "makeFood"),
	/**
	 * 11：在订单中心完成X份订单：填写数量
	 */
	ORDER_X_(11, "11：在订单中心完成X份订单：填写数量", "receiveReward");
	
	
	
	
	// SaleController.class.getAnnotationsByType(TaskMarker.class)
	public final Integer taskCondition;
	public final String description;
	public final String method;
	
	private TaskMethod(Integer taskCondition, String description, String method) {
		this.taskCondition = taskCondition; 
		this.description = description;
		this.method = method;
	}

	public Integer getTaskCondition() {
		return taskCondition;
	}

	public String getDescription() {
		return description;
	}

	public String getMethod() {
		return method;
	}
	
}
