package com.muzhi.model.configbean;

public class ConfigOrder {

	private Integer id;
	private Integer taskLevel;
	private Integer type;
	private Integer taskQualify;
	private String mainReward;
	private Integer goldReward;
	private Integer zuanshiReward;
	private Integer requestNumber;
	private String qualifyRound;
	private String foodRound;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTaskLevel() {
		return taskLevel;
	}
	public void setTaskLevel(Integer taskLevel) {
		this.taskLevel = taskLevel;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getTaskQualify() {
		return taskQualify;
	}
	public void setTaskQualify(Integer taskQualify) {
		this.taskQualify = taskQualify;
	}
	public String getMainReward() {
		return mainReward;
	}
	public void setMainReward(String mainReward) {
		this.mainReward = mainReward;
	}
	public Integer getGoldReward() {
		return goldReward;
	}
	public void setGoldReward(Integer goldReward) {
		this.goldReward = goldReward;
	}
	
	public Integer getZuanshiReward() {
		return zuanshiReward;
	}
	public void setZuanshiReward(Integer zuanshiReward) {
		this.zuanshiReward = zuanshiReward;
	}
	public Integer getRequestNumber() {
		return requestNumber;
	}
	public void setRequestNumber(Integer requestNumber) {
		this.requestNumber = requestNumber;
	}
	public String getQualifyRound() {
		return qualifyRound;
	}
	public void setQualifyRound(String qualifyRound) {
		this.qualifyRound = qualifyRound;
	}
	public String getFoodRound() {
		return foodRound;
	}
	public void setFoodRound(String foodRound) {
		this.foodRound = foodRound;
	}
	@Override
	public String toString() {
		return "ConfigOrder [id=" + id + ", taskLevel=" + taskLevel + ", type=" + type + ", taskQualify=" + taskQualify
				+ ", mainReward=" + mainReward + ", goldReward=" + goldReward + ", zuanshiReward=" + zuanshiReward
				+ ", requestNumber=" + requestNumber + ", qualifyRound=" + qualifyRound + ", foodRound=" + foodRound
				+ "]";
	}
	
	

}
