package com.muzhi.model.vo;


public class MakeFoodState {
	/**
	 * 厨师位状态0空1正在制作2已完成
	 */
	private int status;
	/**
	 * 厨师位位置
	 */
	private int index;
	/**
	 * 制作完成剩余时间
	 */
	private Integer leftTime;
	/**
	 * 制作需要的总时间
	 */
	private Integer totalTime;
	/**
	 * 制作菜品的id
	 */
	private Integer foodID;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

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

}
