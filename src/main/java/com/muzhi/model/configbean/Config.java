package com.muzhi.model.configbean;

import java.util.List;


/**
 * 给前端转json用的
 */
public class Config {
    //命名约定 去掉config取小写字母
	private List<ConfigFacilities> facilities;
	private List<ConfigFood> food;
	private List<ConfigSkill> skill;
	private List<ConfigStrength> strength;
	
	private List<ConfigResearch> research;
	private List<ConfigRestaurant> restaurant;
	private List<ConfigCookcenter> cookcenter;
	private List<ConfigManor> manor;
	private List<ConfigResources> resources;
	private List<ConfigAdvancedProp> advancedprop;
	private List<ConfigRole> role;
	private List<ConfigDialog> dialog;
	
	
	public List<ConfigDialog> getDialog() {
		return dialog;
	}
	public void setDialog(List<ConfigDialog> dialog) {
		this.dialog = dialog;
	}
	public List<ConfigRole> getRole() {
		return role;
	}
	public void setRole(List<ConfigRole> role) {
		this.role = role;
	}
	public List<ConfigResearch> getResearch() {
		return research;
	}
	public void setResearch(List<ConfigResearch> research) {
		this.research = research;
	}
	public List<ConfigRestaurant> getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(List<ConfigRestaurant> restaurant) {
		this.restaurant = restaurant;
	}
	public List<ConfigCookcenter> getCookcenter() {
		return cookcenter;
	}
	public void setCookcenter(List<ConfigCookcenter> cookcenter) {
		this.cookcenter = cookcenter;
	}
	public List<ConfigManor> getManor() {
		return manor;
	}
	public void setManor(List<ConfigManor> manor) {
		this.manor = manor;
	}
	public List<ConfigResources> getResources() {
		return resources;
	}
	public void setResources(List<ConfigResources> resources) {
		this.resources = resources;
	}
	public List<ConfigFacilities> getFacilities() {
		return facilities;
	}
	public void setFacilities(List<ConfigFacilities> facilities) {
		this.facilities = facilities;
	}
	public List<ConfigFood> getFood() {
		return food;
	}
	public void setFood(List<ConfigFood> food) {
		this.food = food;
	}
	public List<ConfigSkill> getSkill() {
		return skill;
	}
	public void setSkill(List<ConfigSkill> skill) {
		this.skill = skill;
	}
	public List<ConfigStrength> getStrength() {
		return strength;
	}
	public void setStrength(List<ConfigStrength> strength) {
		this.strength = strength;
	}
	public List<ConfigAdvancedProp> getAdvancedprop() {
		return advancedprop;
	}
	public void setAdvancedprop(List<ConfigAdvancedProp> advancedprop) {
		this.advancedprop = advancedprop;
	}

	
}
