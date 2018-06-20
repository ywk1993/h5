package com.muzhi.model.vo;

import com.muzhi.model.Farm;
import com.muzhi.model.Fishpond;
import com.muzhi.model.Foodcenter;
import com.muzhi.model.Manor;
import com.muzhi.model.Orchard;
import com.muzhi.model.OrderCenter;
import com.muzhi.model.Pasture;
import com.muzhi.model.Restaurant;
import com.muzhi.model.VegetableGarden;

public class Build {
	/**
	 * 获取农场
	 */
	Farm farm;
	/**
	 * 获取鱼塘
	 */
	Fishpond fishpond;
	/**
	 * 获取牧场
	 */
	Pasture pasture;
	/**
	 * 获取果园
	 */
	Orchard orchard;
	/**
	 * 获取菜园 
	 */
	VegetableGarden vegetableGarden;
	/**
	 * 美食中心
	 */
	Foodcenter foodcenter;
	/**
	 * 餐厅
	 */
	Restaurant restaurant;
	/**
	 * 
	 */
	OrderCenter orderCenter;
	
	/**
	 * 升级信息
	 */
	Manor manorFarm;
	/**
	 * 升级信息
	 */
	Manor manorFishpond;
	/**
	 * 升级信息
	 */
	Manor manorPasture;
	/**
	 * 升级信息
	 */
	Manor manorOrchard;
	/**
	 * 升级信息
	 */
	Manor manorVegetableGarden;
	/**
	 * 升级信息
	 */
	Manor manorResearch;
	
	Manor manorRestaurant;
	
	Manor manorOrderCenter;
	
	Integer foodID;
	
	/**
     * 距离上一次收取的时间_粮食（大米）
     */
    private Long riceStartTime;

    /**
     * 水果（香蕉）
     */

    private Long bananaStartTime;
    /**
     * 蔬菜
     */
    private Long vegetableStartTime;
    /**
     * 肉类
     */
    private Long meatStartTime;
    /**
     * 鱼类
     */
    private Long fishStartTime;
	
	public Long getRiceStartTime() {
		return riceStartTime;
	}

	public void setRiceStartTime(Long riceStartTime) {
		this.riceStartTime = riceStartTime;
	}

	public Long getBananaStartTime() {
		return bananaStartTime;
	}

	public void setBananaStartTime(Long bananaStartTime) {
		this.bananaStartTime = bananaStartTime;
	}

	public Long getVegetableStartTime() {
		return vegetableStartTime;
	}

	public void setVegetableStartTime(Long vegetableStartTime) {
		this.vegetableStartTime = vegetableStartTime;
	}

	public Long getMeatStartTime() {
		return meatStartTime;
	}

	public void setMeatStartTime(Long meatStartTime) {
		this.meatStartTime = meatStartTime;
	}

	public Long getFishStartTime() {
		return fishStartTime;
	}

	public void setFishStartTime(Long fishStartTime) {
		this.fishStartTime = fishStartTime;
	}

	public Integer getFoodID() {
		return foodID;
	}

	public void setFoodID(Integer foodID) {
		this.foodID = foodID;
	}

	public Farm getFarm() {
		return farm;
	}

	public void setFarm(Farm farm) {
		this.farm = farm;
	}

	public Fishpond getFishpond() {
		return fishpond;
	}

	public void setFishpond(Fishpond fishpond) {
		this.fishpond = fishpond;
	}

	public Pasture getPasture() {
		return pasture;
	}

	public void setPasture(Pasture pasture) {
		this.pasture = pasture;
	}

	public Orchard getOrchard() {
		return orchard;
	}

	public void setOrchard(Orchard orchard) {
		this.orchard = orchard;
	}

	public VegetableGarden getVegetableGarden() {
		return vegetableGarden;
	}

	public void setVegetableGarden(VegetableGarden vegetableGarden) {
		this.vegetableGarden = vegetableGarden;
	}


	public Foodcenter getFoodcenter() {
		return foodcenter;
	}

	public void setFoodcenter(Foodcenter foodcenter) {
		this.foodcenter = foodcenter;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Manor getManorFarm() {
		return manorFarm;
	}

	public void setManorFarm(Manor manorFarm) {
		this.manorFarm = manorFarm;
	}

	public Manor getManorFishpond() {
		return manorFishpond;
	}

	public void setManorFishpond(Manor manorFishpond) {
		this.manorFishpond = manorFishpond;
	}

	public Manor getManorPasture() {
		return manorPasture;
	}

	public void setManorPasture(Manor manorPasture) {
		this.manorPasture = manorPasture;
	}

	public Manor getManorOrchard() {
		return manorOrchard;
	}

	public void setManorOrchard(Manor manorOrchard) {
		this.manorOrchard = manorOrchard;
	}

	public Manor getManorVegetableGarden() {
		return manorVegetableGarden;
	}

	public void setManorVegetableGarden(Manor manorVegetableGarden) {
		this.manorVegetableGarden = manorVegetableGarden;
	}

	public Manor getManorResearch() {
		return manorResearch;
	}

	public void setManorResearch(Manor manorResearch) {
		this.manorResearch = manorResearch;
	}

	public Manor getManorRestaurant() {
		return manorRestaurant;
	}

	public void setManorRestaurant(Manor manorRestaurant) {
		this.manorRestaurant = manorRestaurant;
	}

	public OrderCenter getOrderCenter() {
		return orderCenter;
	}

	public void setOrderCenter(OrderCenter orderCenter) {
		this.orderCenter = orderCenter;
	}

	public Manor getManorOrderCenter() {
		return manorOrderCenter;
	}

	public void setManorOrderCenter(Manor manorOrderCenter) {
		this.manorOrderCenter = manorOrderCenter;
	}

}
