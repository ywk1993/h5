package com.muzhi.model.vo;
/**
 * 厨力
 * @author yany
 *
 */
public class Strength {
	/**
	 * 厨力等级
	 */
	private Integer level;
	/**
	 * 厨力等级收益百分比
	 */
	private Integer income;
	/**
	 * 品质加成数
	 */
	private Integer incomeNum;
	/**
	 * 是否达成成就
	 */
	private Integer isAchieve;
	/**
	 * 需求厨力值
	 */
	private Integer requestStrength;
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getIncome() {
		return income;
	}
	public void setIncome(Integer income) {
		this.income = income;
	}
	public Integer getIncomeNum() {
		return incomeNum;
	}
	public void setIncomeNum(Integer incomeNum) {
		this.incomeNum = incomeNum;
	}
	public Integer getIsAchieve() {
		return isAchieve;
	}
	public void setIsAchieve(Integer isAchieve) {
		this.isAchieve = isAchieve;
	}
	public Integer getRequestStrength() {
		return requestStrength;
	}
	public void setRequestStrength(Integer requestStrength) {
		this.requestStrength = requestStrength;
	}
	
	
	
}
