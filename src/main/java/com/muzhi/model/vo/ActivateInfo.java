package com.muzhi.model.vo;
/**
 * 激活信息
 * @author Yuwk
 *
 * 2018年5月31日
 */
public class ActivateInfo {
	private Integer foodId;//激活食物id
	private Integer propId;//道具id
	private Integer propType;//道具类型
	private Integer propLevel;//道具等级
	private Integer propQualify;//品质
	private String describe;//描述
	private Integer needNumber;//需求的数量
	private Integer haveNumber;//已有的数量
	private Integer isActivate;//0没激活1激活了
	public Integer getFoodId() {
		return foodId;
	}
	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}
	public Integer getPropId() {
		return propId;
	}
	public void setPropId(Integer propId) {
		this.propId = propId;
	}
	public Integer getPropType() {
		return propType;
	}
	public void setPropType(Integer propType) {
		this.propType = propType;
	}
	public Integer getPropLevel() {
		return propLevel;
	}
	public void setPropLevel(Integer propLevel) {
		this.propLevel = propLevel;
	}
	public Integer getPropQualify() {
		return propQualify;
	}
	public void setPropQualify(Integer propQualify) {
		this.propQualify = propQualify;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public Integer getNeedNumber() {
		return needNumber;
	}
	public void setNeedNumber(Integer needNumber) {
		this.needNumber = needNumber;
	}
	public Integer getHaveNumber() {
		return haveNumber;
	}
	public void setHaveNumber(Integer haveNumber) {
		this.haveNumber = haveNumber;
	}
	public Integer getIsActivate() {
		return isActivate;
	}
	public void setIsActivate(Integer isActivate) {
		this.isActivate = isActivate;
	}
	
}
