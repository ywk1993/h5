package com.muzhi.model;

import java.util.List;

import com.muzhi.model.CurrencyType;

/**
 * 冰箱实体
 * @author yany
 *
 */
public class Fridge {
	/**
	 * 用户id
	 */
	private Integer id;
	/**
	 * 冰箱等级
	 */
	private Integer level;
	/**
	 * 冰箱存储容量
	 */
	private Integer indexNum;
	
	private Integer rice;
	
	private Integer banana;
	
	private Integer vegetable;
	
	private Integer meat;
	
	private Integer fish;
	
	public Fridge() {
		super();
	}
	
	public Fridge(Integer id, Integer level, Integer indexNum, Integer rice, Integer banana, Integer vegetable,
			Integer meat, Integer fish) {
		super();
		this.id = id;
		this.level = level;
		this.indexNum = indexNum;
		this.rice = rice;
		this.banana = banana;
		this.vegetable = vegetable;
		this.meat = meat;
		this.fish = fish;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getIndexNum() {
		return indexNum;
	}

	public void setIndexNum(Integer indexNum) {
		this.indexNum = indexNum;
	}

	public Integer getRice() {
		return rice;
	}

	public void setRice(Integer rice) {
		this.rice = rice;
	}

	public Integer getBanana() {
		return banana;
	}

	public void setBanana(Integer banana) {
		this.banana = banana;
	}

	public Integer getVegetable() {
		return vegetable;
	}

	public void setVegetable(Integer vegetable) {
		this.vegetable = vegetable;
	}

	public Integer getMeat() {
		return meat;
	}

	public void setMeat(Integer meat) {
		this.meat = meat;
	}

	public Integer getFish() {
		return fish;
	}

	public void setFish(Integer fish) {
		this.fish = fish;
	}
	
}
