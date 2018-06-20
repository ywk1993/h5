package com.muzhi.model.vo;

import java.util.List;

public class ResearchMenu {
	private int foodType;
	private List<ResearchFood> foodList;
    
	public ResearchMenu() {
		super();
	}

	public ResearchMenu(int foodType, List<ResearchFood> foodList) {
		super();
		this.foodType = foodType;
		this.foodList = foodList;
	}

	public int getFoodType() {
		return foodType;
	}

	public void setFoodType(int foodType) {
		this.foodType = foodType;
	}

	public List<ResearchFood> getFoodList() {
		return foodList;
	}

	public void setFoodList(List<ResearchFood> foodList) {
		this.foodList = foodList;
	}

}
