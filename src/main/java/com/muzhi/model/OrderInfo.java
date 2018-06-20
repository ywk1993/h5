package com.muzhi.model;

import java.util.Date;
import java.util.List;

public class OrderInfo {
	
    private Integer orderId;
	private Integer taskLevel;
	private Integer type;
	private Integer taskQualify;
    private List<OrderFood> orderFoods;//订单食物需求列表
	private List<CurrencyType> rewards;//订单奖励列表
	private Integer state;
    private Date startTime;
    private Date endTime;
	
    
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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
	public List<OrderFood> getOrderFoods() {
		return orderFoods;
	}
	public void setOrderFoods(List<OrderFood> orderFoods) {
		this.orderFoods = orderFoods;
	}
	public List<CurrencyType> getRewards() {
		return rewards;
	}
	public void setRewards(List<CurrencyType> rewards) {
		this.rewards = rewards;
	}
	@Override
	public String toString() {
		return "OrderInfo [tid=" + orderId + ", taskLevel=" + taskLevel + ", type=" + type + ", taskQualify=" + taskQualify
				+ ", orderFoods=" + orderFoods + ", rewards=" + rewards + "]";
	}
	
	
}
