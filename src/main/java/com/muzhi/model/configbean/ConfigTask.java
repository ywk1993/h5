package com.muzhi.model.configbean;

public class ConfigTask {
	private Integer id;
	private String describe;
	private Integer condition;
	private String parament;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public Integer getCondition() {
		return condition;
	}
	public void setCondition(Integer condition) {
		this.condition = condition;
	}
	public String getParament() {
		return parament;
	}
	public void setParament(String parament) {
		this.parament = parament;
	}
	@Override
	public String toString() {
		return "ConfigTask [id=" + id + ", describe=" + describe + ", condition=" + condition + ", parament=" + parament
				+ "]";
	}
    
}
