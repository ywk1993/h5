package com.muzhi.model.vo;

/**
 * 菜谱详情
 * @author yany
 *
 */
public class FoodDetail {
	Info info;
	/**熟练度*/
	Integer proficiency;
	/**厨力*/
	Integer strength;
	/*List<Proficiency> proficiency;
	List<Strength> strength;*/
	public Info getInfo() {
		return info;
	}
	public void setInfo(Info info) {
		this.info = info;
	}
	/*public List<Proficiency> getProficiency() {
		return proficiency;
	}
	public void setProficiency(List<Proficiency> proficiency) {
		this.proficiency = proficiency;
	}
	public List<Strength> getStrength() {
		return strength;
	}
	public void setStrength(List<Strength> strength) {
		this.strength = strength;
	}*/
	public Integer getProficiency() {
		return proficiency;
	}
	public void setProficiency(Integer proficiency) {
		this.proficiency = proficiency;
	}
	public Integer getStrength() {
		return strength;
	}
	public void setStrength(Integer strength) {
		this.strength = strength;
	}
}
