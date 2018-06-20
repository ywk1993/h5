package com.muzhi.model.vo;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import com.muzhi.model.Food;
import com.muzhi.model.Inventory;
import com.muzhi.model.Reused;
import com.muzhi.model.configbean.ConfigSkill;

public class FoodN {
	private static final Integer NEWFOOD = 3;
	/**
	 * 用户id
	 */
	private Integer uid;
	/**
	 * 菜品状态
	 */
	private Integer foodStatus;
	/**
	 * 菜品ID
	 */
	private Integer foodID;
	/**
	 * 菜品等级
	 */
	private Integer foodLevel;
//	private String foodName;
//	private Integer foodLogo;
	/**
	 * 菜品品质
	 */
	private Integer foodQualify;
	/**
	 * 熟练度进度
	 */
	private Integer process;
	/**
	 * 熟练度总数
	 */
	private Integer processTotal;
	/**
	 * 工艺状态即是否显示精通
	 */
	private Integer skillStatus;
	
	private List<Reused> currencyList;
//	/**
//	 * 蔬菜
//	 */
//	private Integer vegetable;
//	/**
//	 * 香蕉
//	 */
//	private Integer banana;
//	/**
//	 * 肉
//	 */
//	private Integer meat;
//	/**
//	 * 鱼
//	 */
//	private Integer fish;
	/**
	 * 存量
	 */
	private Integer stock;
	/**
	 * 需要的厨力
	 */
	private Integer requestStrength;
	/**
	 * 需要再制作的个数
	 */
	private Integer needNum;
	/**
	 * 菜品类型
	 */
	private Integer foodType;
	/**
	 * 图纸状态 1获取；0未获取
	 */
	private Integer status;
	/**
	 * 奖励来源食物id
	 */
	private Integer resourceFoodId;
	
	
	public Integer getResourceFoodId() {
		return resourceFoodId;
	}
	public void setResourceFoodId(Integer resourceFoodId) {
		this.resourceFoodId = resourceFoodId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getFoodStatus() {
		return foodStatus;
	}
	public void setFoodStatus(Integer foodStatus) {
		this.foodStatus = foodStatus;
	}
	public Integer getFoodID() {
		return foodID;
	}
	public void setFoodID(Integer foodID) {
		this.foodID = foodID;
	}
	public Integer getFoodLevel() {
		return foodLevel;
	}
	public void setFoodLevel(Integer foodLevel) {
		this.foodLevel = foodLevel;
	}
	public Integer getFoodQualify() {
		return foodQualify;
	}
	public void setFoodQualify(Integer foodQualify) {
		this.foodQualify = foodQualify;
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
	public Integer getSkillStatus() {
		return skillStatus;
	}
	public void setSkillStatus(Integer skillStatus) {
		this.skillStatus = skillStatus;
	}
	
	public List<Reused> getCurrencyList() {
		return currencyList;
	}
	public void setCurrencyList(List<Reused> currencyList) {
		this.currencyList = currencyList;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Integer getRequestStrength() {
		return requestStrength;
	}
	public void setRequestStrength(Integer requestStrength) {
		this.requestStrength = requestStrength;
	}
	public Integer getNeedNum() {
		return needNum;
	}
	public void setNeedNum(Integer needNum) {
		this.needNum = needNum;
	}
	public Integer getFoodType() {
		return foodType;
	}
	public void setFoodType(Integer foodType) {
		this.foodType = foodType;
	}
	public void setStock(Inventory inventory) {
		int num = 0;
		List<Food> foodList = inventory.getFoodList();
		for (Food food : foodList) {
			if (this.getFoodID().equals(food.getFoodID())) {
				num +=food.getNum();
			}
		}
		setStock(num);
	}
	public void setNeedNum(Map<Integer, ConfigSkill> configSkillMap) {
		if (this.getFoodStatus() == 3 || this.getFoodStatus() == 4) {
			Set<Entry<Integer, ConfigSkill>> entrySet = configSkillMap.entrySet();
			
			for (Entry<Integer, ConfigSkill> entry : entrySet) {
				// 获取新菜品的奖励
				if (entry.getValue().getRewardtype() == NEWFOOD) {
					// 选取最近的一个熟练度
					if (this.getProcess() < entry.getValue().getRequest()) {
						this.setNeedNum(entry.getValue().getRequest() - this.getProcess());
						break;
					}
				} 
			}
			setNeedNum(needNum);
		} else {
			setNeedNum(0);
		}
		
			
	}
	
}
