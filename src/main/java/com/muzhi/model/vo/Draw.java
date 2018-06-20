package com.muzhi.model.vo;

public class Draw implements Comparable<Draw>{
	private Integer foodID;
	private Integer state;
	private Integer level;

	public Integer getFoodID() {
		return foodID;
	}

	public void setFoodID(Integer foodID) {
		this.foodID = foodID;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Override
	public int compareTo(Draw o) {
		
		return this.level-o.level;
	}
	

}
