package com.muzhi.model.vo;

import java.util.List;

public class Menu {
	private Integer foodType;
	private List<FoodN> foodList;
	
	public Menu(Integer foodType, List<FoodN> foodList) {
		super();
		this.foodType = foodType;
		this.foodList = foodList;
	}
	public Integer getFoodType() {
		return foodType;
	}
	public void setFoodType(Integer foodType) {
		this.foodType = foodType;
	}
	public List<FoodN> getFoodList() {
		return foodList;
	}
	public void setFoodList(List<FoodN> foodList) {
		this.foodList = foodList;
	}
	
	
}
