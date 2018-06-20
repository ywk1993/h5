package com.muzhi.service;

import java.util.List;
import com.muzhi.model.Food;
import com.muzhi.model.Result;
import com.muzhi.model.User;


public interface FoodService {
	public void addList(List<Food> foodList);
	public void getList(Food food);
	/**
	 * 食物收取
	 * @param token
	 * @param foodId
	 * @param index
	 * @return
	 */
	public Result stockFood(String token,Integer foodId, Integer index);
	/**
	 * 食物制作
	 * @param token
	 * @param foodId
	 * @param index
	 * @return
	 */
	public Result makeFood(String token,Integer foodId, Integer index);
	/**
	 * 获取已存储的数量
	 * @return
	 */
	public int getTotalCount(Integer id);
	/**
	 * 跟新食物的数量
	 * @param food
	 */
	public void updateFood(Food food);
	/**
	 * 根据userID foodID 品质等级获取唯一的数据
	 * @param id
	 * @param foodID
	 * @param foodQualify
	 * @return
	 */
	public Food getOneFood(Integer id,Integer foodID,Integer foodQualify);
	/**
	 * 根据id选择用户库存数据
	 * @param id
	 * @return
	 */
	public List<Food> getFood(Integer id);
	/**
	 * 看具体某个等级的食物是否存在库存 0没有  1有
	 * @param id
	 * @param foodID
	 * @param foodQualify
	 * @return
	 */
	public int isExist(Integer id,Integer foodID,Integer foodQualify);
	/**
	 * 厨师位是否足够并返回index最小值
	 * @param token
	 * @return
	 */
	public Result isChefEnough(String token);
	/**
	 * 获取可以推销的库存
	 * @param id
	 * @param foodID
	 * @param foodQualify
	 * @return
	 */
	public List<Food> getSellFood(Integer id, Integer foodID, Integer foodQualify);
	/**
	 * 获取可以完成订单的库存
	 * @param id
	 * @param foodID
	 * @param foodQualify
	 * @return
	 */
	public List<Food> getOrderFood(Integer id, Integer foodID, Integer foodQualify);
	/**
	 * 根据id获取食物
	 * @param id
	 * @param foodID
	 * @return
	 */
	public List<Food> getFoodByFoodId(Integer id, Integer foodID);
	
	public void foodInit(User userInit);
}
