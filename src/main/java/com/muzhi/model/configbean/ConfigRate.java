package com.muzhi.model.configbean;

public class ConfigRate {
	private Integer rateLevel;
	private String name;
	private String logo;
	private Integer startLevel;
	private String task;
	private Integer needGold;
	public Integer getRateLevel() {
		return rateLevel;
	}
	public void setRateLevel(Integer rateLevel) {
		this.rateLevel = rateLevel;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public Integer getStartLevel() {
		return startLevel;
	}
	public void setStartLevel(Integer startLevel) {
		this.startLevel = startLevel;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public Integer getNeedGold() {
		return needGold;
	}
	public void setNeedGold(Integer needGold) {
		this.needGold = needGold;
	}
	@Override
	public String toString() {
		return "ConfigRate [rateLevel=" + rateLevel + ", name=" + name + ", logo=" + logo + ", startLevel=" + startLevel
				+ ", task=" + task + ", needGold=" + needGold + "]";
	}
    
}
