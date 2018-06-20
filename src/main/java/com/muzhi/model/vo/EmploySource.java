package com.muzhi.model.vo;

import com.muzhi.model.User;

public class EmploySource {
	// 用户信息
	private User user;
	// 雇佣金币
	private Integer gold;
	// 好友折扣
	private Integer discount;
	// 是否是新员工
	private Integer isNew;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getGold() {
		return gold;
	}
	public void setGold(Integer gold) {
		this.gold = gold;
	}
	public Integer getDiscount() {
		return discount;
	}
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	public Integer getIsNew() {
		return isNew;
	}
	public void setIsNew(Integer isNew) {
		this.isNew = isNew;
	}
	
}
