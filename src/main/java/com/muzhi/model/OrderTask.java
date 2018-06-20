package com.muzhi.model;

import java.util.Date;

public class OrderTask {
	private Integer orderId;//订单id
	private Integer tid;//配值表的id
	private Integer id;//用户的id
	private String orderFood;//需求食物列表
	private Date startTime;//订单开始的时间
	private Date endTime;//订单结束时间
	private Integer state;//奖励领取标志（订单是否完成）1完成，0未完成
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrderFood() {
		return orderFood;
	}
	public void setOrderFood(String orderFood) {
		this.orderFood = orderFood;
	}
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
	@Override
	public String toString() {
		return "OrderTask [tid=" + tid + ", id=" + id + ", orderFood=" + orderFood + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", state=" + state + "]";
	}
	
}
