package com.muzhi.model.vo;

import java.util.List;

/**
* @author ykw
* @version 创建时间：2018年3月22日 下午4:41:22
*/
public class StaffAll {
	List<Staff> chefList;//厨师列表
	
	List<Staff> asherList;//迎宾列表
	
	Integer restaurantLevel;//餐厅等级
	
	public StaffAll(List<Staff> chefList,List<Staff> asherList,Integer restaurantLevel) {
		this.asherList = asherList;
		this.chefList = chefList;
		this.restaurantLevel = restaurantLevel;
	}

	public List<Staff> getChefList() {
		return chefList;
	}

	public void setChefList(List<Staff> chefList) {
		this.chefList = chefList;
	}

	public List<Staff> getAsherList() {
		return asherList;
	}

	public void setAsherList(List<Staff> asherList) {
		this.asherList = asherList;
	}

	public Integer getRestaurantLevel() {
		return restaurantLevel;
	}

	public void setRestaurantLevel(Integer restaurantLevel) {
		this.restaurantLevel = restaurantLevel;
	}
	
}
