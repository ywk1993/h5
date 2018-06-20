package com.muzhi.model.configbean;

public class ConfigLove {
	private Integer foodLevel;//彩品等级
	private Integer number;//爱心数量

	public Integer getFoodLevel() {
		return foodLevel;
	}

	public void setFoodLevel(Integer foodLevel) {
		this.foodLevel = foodLevel;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "ConfigLove [foodLevel=" + foodLevel + ", number=" + number + "]";
	}
    
}
