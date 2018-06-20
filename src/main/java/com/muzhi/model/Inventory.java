package com.muzhi.model;

import java.util.List;
/**
 * 货柜
 * @author yany
 *
 */
public class Inventory {
	/**
	 * 用户id
	 */
	private Integer id;
	/**
	 * 位置数目
	 */
	private Integer indexNum;
	/**
	 * 等级
	 */
	private Integer level;
	/**
	 * 食物列表
	 */
	private List<Food> foodList;
	
	public Inventory() {
		super();
	}

	public Inventory(Integer id, Integer indexNum, Integer level, List<Food> foodList) {
		super();
		this.id = id;
		this.indexNum = indexNum;
		this.level = level;
		this.foodList = foodList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIndexNum() {
		return indexNum;
	}

	public void setIndexNum(Integer indexNum) {
		this.indexNum = indexNum;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public List<Food> getFoodList() {
		return foodList;
	}

	public void setFoodList(List<Food> foodList) {
		this.foodList = foodList;
	}
	
}
