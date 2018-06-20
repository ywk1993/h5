package com.muzhi.model.vo;

public class ResearchState {
	private Integer leftTime;
	private Integer totalTime;
	private Integer foodID;
	public Integer getLeftTime() {
		return leftTime;
	}
	public void setLeftTime(Integer leftTime) {
		this.leftTime = leftTime;
	}
	public Integer getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(Integer totalTime) {
		this.totalTime = totalTime;
	}
	public Integer getFoodID() {
		return foodID;
	}
	public void setFoodID(Integer foodID) {
		this.foodID = foodID;
	}
	public ResearchState(Integer leftTime, Integer totalTime, Integer foodID) {
		super();
		this.leftTime = leftTime;
		this.totalTime = totalTime;
		this.foodID = foodID;
	}
	public ResearchState() {
		super();
	}
    
}
