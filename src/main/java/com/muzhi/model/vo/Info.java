package com.muzhi.model.vo;

import java.util.List;


import com.muzhi.model.Food;
import com.muzhi.model.Inventory;
import com.muzhi.model.Reused;

/**
 * 菜谱信息
 * @author yany
 *
 */
public class Info {
	/**
	 * 菜品id
	 */
	private Integer foodID;
	/**
	 * 菜品等级
	 */
	private Integer foodLevel;
//	private Integer foodName;
//	private Integer foodLogo;
	/**
	 * 菜品类型
	 */
	private Integer foodType;
	/**
	 * 菜品品质
	 */
	private Integer foodQulify;
	/**
	 * 已制作次数
	 */
	private Integer times;
	/**
	 * 存量
	 */
	private Integer stock;
	/**
	 * 传输需要的货币列表
	 */
	private List<Reused> currencyList;
	
	
	public List<Reused> getCurrencyList() {
		return currencyList;
	}
	public void setCurrencyList(List<Reused> currencyList) {
		this.currencyList = currencyList;
	}
	public Integer getFoodID() {
		return foodID;
	}
	public void setFoodID(Integer foodID) {
		this.foodID = foodID;
	}
	public Integer getFoodLevel() {
		return foodLevel;
	}
	public void setFoodLevel(Integer foodLevel) {
		this.foodLevel = foodLevel;
	}
	public Integer getFoodType() {
		return foodType;
	}
	public void setFoodType(Integer foodType) {
		this.foodType = foodType;
	}
	public Integer getFoodQulify() {
		return foodQulify;
	}
	public void setFoodQulify(Integer foodQulify) {
		this.foodQulify = foodQulify;
	}
	public Integer getTimes() {
		return times;
	}
	public void setTimes(Integer times) {
		this.times = times;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public void setStock(Inventory inventory) {
		int num = 0;
		List<Food> foodList = inventory.getFoodList();
		for (Food food : foodList) {
			if (this.getFoodID().equals(food.getFoodID())) {
				num += food.getNum();
			}
		}
		setStock(num);
	}
	
	
}
