package com.muzhi.model.vo;

import java.util.List;

import com.muzhi.model.Reused;
import com.muzhi.util.StringUtil;



/**
 * 库存
 * @author yany
 *
 */
public class InventoryVo {
	/**
	 * 食谱id
	 */
    private Integer foodId;
    /**
     * 食物类型
     */
    private Integer foodType;
    /**
     * 货币配方表
     */
    private List<Reused> typeList;
    /**
     * 食物等级
     */
    private Integer foodLevel;
    /**
     * 食物品质
     */
    private Integer foodQualify;
    /**
     * 食物奖励金币
     * 
     */
    private Integer gold;
    /**
     * 当前数练度
     * 
     */
    private Integer skill;
    /**
     * 升级所需要的熟练度
     * 
     */
    private Integer skillLimit;
	/**
	 * 食物数量
	 */
	private int foodNum;
   
	public Integer getFoodId() {
		return foodId;
	}

	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}

	public Integer getFoodType() {
		return foodType;
	}

	public void setFoodType(Integer foodType) {
		this.foodType = foodType;
	}

	public List<Reused> getTypeList() {
		return typeList;
	}

	public void setTypeList(String string) {
		List<Reused> list = StringUtil.getMetarial(string);
		this.typeList = list;
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

	public Integer getGold() {
		return gold;
	}

	public void setGold(Integer gold) {
		this.gold = gold;
	}

	public Integer getSkill() {
		return skill;
	}

	public void setSkill(Integer skill) {
		this.skill = skill;
	}

	public Integer getSkillLimit() {
		return skillLimit;
	}

	public void setSkillLimit(Integer skillLimit) {
		this.skillLimit = skillLimit;
	}

	public int getFoodNum() {
		return foodNum;
	}

	public void setFoodNum(int foodNum) {
		this.foodNum = foodNum;
	}

}
