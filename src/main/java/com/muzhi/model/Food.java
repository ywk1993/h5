package com.muzhi.model;
/**
 * 成品菜
 * @author yy
 *
 */
public class Food {
	/**
	 * 用户id
	 */
	private Integer id;
	/**
	 * 食物ID
	 */
	private Integer foodID;
	/**
	 * 食物品质
	 */
	private Integer foodQualify;
	/**
	 * 食物数量
	 */
	private Integer num;
	
	public Food() {
		super();
	}

	public Food(Integer id, Integer foodID, Integer foodQualify, Integer num) {
		super();
		this.id = id;
		this.foodID = foodID;
		this.foodQualify = foodQualify;
		this.num = num;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFoodID() {
		return foodID;
	}

	public void setFoodID(Integer foodID) {
		this.foodID = foodID;
	}

	public Integer getFoodQualify() {
		return foodQualify;
	}

	public void setFoodQualify(Integer foodQualify) {
		this.foodQualify = foodQualify;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
}
