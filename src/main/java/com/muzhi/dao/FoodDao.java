package com.muzhi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.muzhi.model.Food;

public interface FoodDao {
	public void addList(List<Food> foodList);
	public void getList(Food food);
	/**
	 * 获取已存储的数量
	 * @return
	 */
	Integer getTotalCount(@Param("id") Integer id);
	/**
	 * 跟新食物的数量
	 * @param food
	 */
	void updateFood(Food food);
	/**
	 * 根据userID foodID 品质等级获取唯一的数据
	 * @param id
	 * @param foodID
	 * @param foodQualify
	 * @return
	 */
	Food getOneFood(@Param("id") Integer id,@Param("foodID") Integer foodID,@Param("foodQualify") Integer foodQualify);
	/**
	 *  根据id选择用户库存数据
	 * @param id
	 * @return
	 */
	List<Food> getFood(@Param("id") Integer id);
	/**
	 * 获取可以推销的库存
	 * @param id
	 * @param foodID
	 * @param foodQualify
	 * @return
	 */
	List<Food> getSellFood(@Param("id") Integer id,@Param("foodID") Integer foodID,@Param("foodQualify") Integer foodQualify);
	/**
	 * 获取可以完成订单的库存
	 * @param id
	 * @param foodID
	 * @param foodQualify
	 * @return
	 */
	List<Food> getOrderFood(@Param("id") Integer id,@Param("foodID") Integer foodID,@Param("foodQualify") Integer foodQualify);
	/**
	 * 根据食物id获取食物
	 */
	List<Food> getFoodByFoodId(@Param("id") Integer id,@Param("foodID") Integer foodID);
}
