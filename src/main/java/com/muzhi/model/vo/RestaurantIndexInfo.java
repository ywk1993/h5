package com.muzhi.model.vo;

import java.util.List;

public class RestaurantIndexInfo {
	/**
	 * 客源数目
	 */
	private Integer customNum;
	/**
	 * 等级
	 */
	private Integer level;
	/**
	 * 大厅面积
	 */
	private Integer area;
	/**
	 * 爱心值上限
	 */
	private Integer loveLimit;
	/**
	 * 位置制作状态
	 */
	private List<MakeFoodState> makeFoodState;
	
	public RestaurantIndexInfo() {
		super();
	}
	public RestaurantIndexInfo(Integer customNum, Integer level, List<MakeFoodState> makeFoodState) {
		super();
		this.customNum = customNum;
		this.level = level;
		this.makeFoodState = makeFoodState;
	}
	public Integer getCustomNum() {
		return customNum;
	}
	public void setCustomNum(Integer customNum) {
		this.customNum = customNum;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public List<MakeFoodState> getMakeFoodState() {
		return makeFoodState;
	}
	public void setMakeFoodState(List<MakeFoodState> makeFoodState) {
		this.makeFoodState = makeFoodState;
	}
	public Integer getArea() {
		return area;
	}
	public void setArea(Integer area) {
		this.area = area;
	}
	public Integer getLoveLimit() {
		return loveLimit;
	}
	public void setLoveLimit(Integer loveLimit) {
		this.loveLimit = loveLimit;
	}
	
}
