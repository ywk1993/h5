package com.muzhi.model.vo;

import java.util.List;


import com.muzhi.model.Reused;

public class ResearchFood {

	private Integer researchStatus;
	private Integer foodID;
	private Integer foodLevel;
	private Integer foodQulify;
	private Integer researchLevel;
	private List<Reused> currencyList;
	private Integer requestStrength;
	
	public Integer getResearchStatus() {
		return researchStatus;
	}
	public void setResearchStatus(Integer researchStatus) {
		this.researchStatus = researchStatus;
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
	public Integer getFoodQulify() {
		return foodQulify;
	}
	public void setFoodQulify(Integer foodQulify) {
		this.foodQulify = foodQulify;
	}
	public Integer getResearchLevel() {
		return researchLevel;
	}
	public void setResearchLevel(Integer researchLevel) {
		this.researchLevel = researchLevel;
	}
	
	public List<Reused> getCurrencyList() {
		return currencyList;
	}
	public void setCurrencyList(List<Reused> currencyList) {
		this.currencyList = currencyList;
	}
	public Integer getRequestStrength() {
		return requestStrength;
	}
	public void setRequestStrength(Integer requestStrength) {
		this.requestStrength = requestStrength;
	}
	@Override
	public String toString() {
		return "ResearchFood [researchStatus=" + researchStatus + ", foodID=" + foodID + ", foodLevel=" + foodLevel
				+ ", foodQulify=" + foodQulify + ", researchLevel=" + researchLevel + ", currencyList=" + currencyList
				+ ", requestStrength=" + requestStrength + "]";
	}
    
}
