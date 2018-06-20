package com.muzhi.model.vo;

import java.util.List;

import com.muzhi.model.CurrencyType;

public class Reward {
	/**
	 * 熟练度奖励图标
	 */
	private Integer id;
	/**
	 * 奖励类型
	 */
	private Integer rewardId;
	/**
	 * 熟练度奖励说明
	 */
	private String description;
	/**
	 * 奖励值
	 */
	private Integer rewardNum;
	/**
	 * 熟练度进度
	 */
	private Integer  process;
	/**
	 * 熟练度总数
	 */
	private Integer processTotal;
	/**
	 * 是否达成
	 */
	private Integer isAchieve;
	/**
	 * 材料减少图标 
	 */
	private List<CurrencyType> metarialList;
	
	public List<CurrencyType> getMetarialList() {
		return metarialList;
	}
	public void setMetarialList(List<CurrencyType> metarialList) {
		this.metarialList = metarialList;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRewardId() {
		return rewardId;
	}
	public void setRewardId(Integer rewardId) {
		this.rewardId = rewardId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getRewardNum() {
		return rewardNum;
	}
	public void setRewardNum(Integer rewardNum) {
		this.rewardNum = rewardNum;
	}
	public Integer getProcess() {
		return process;
	}
	public void setProcess(Integer process) {
		this.process = process;
	}
	public Integer getProcessTotal() {
		return processTotal;
	}
	public void setProcessTotal(Integer processTotal) {
		this.processTotal = processTotal;
	}
	public Integer getIsAchieve() {
		return isAchieve;
	}
	public void setIsAchieve(Integer isAchieve) {
		this.isAchieve = isAchieve;
	}
	
}
