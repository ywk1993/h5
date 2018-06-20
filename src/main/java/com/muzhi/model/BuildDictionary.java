package com.muzhi.model;
/**
 * 数据字典
 * @author yany
 *
 */
public enum BuildDictionary {
	//建筑的ID。1，餐厅；2，农场；3，果园；4，菜园；5，牧场；6，鱼塘；7，美食中心；8，订单中心；9，交易中心
	RESTAURANT(1, "餐厅", "com.muzhi.model.Restaurant"), FARM(2, "农场", "com.muzhi.model.Farm"), ORCHARD(3, "果园", "com.muzhi.model.Orchard"), 
	PASTURE(5, "菜园", "com.muzhi.model.Pasture"), VEGETABLEGARDEN(4, "牧场", "com.muzhi.model.Vegetablegarden"), 
	FISHPOND(6, "鱼", "com.muzhi.model.Fishpond"), FOODCENTER(7, "美食中心", "com.muzhi.model.Foodcenter"), 
	ORDERCENTER(8, "订单中心", "com.muzhi.model.OrderCenter");;
	Integer typeId;
	String desc;
	String clazz;
	
	private BuildDictionary(Integer typeId, String desc, String clazz) {
		this.typeId = typeId;
		this.desc = desc;
		this.clazz = clazz;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public static BuildDictionary find(Integer type) {
		if (BuildDictionary.RESTAURANT.getTypeId().equals(type)) {
			return BuildDictionary.RESTAURANT;
		} else if (BuildDictionary.FARM.getTypeId().equals(type)) {
			return BuildDictionary.FARM;
		} else if (BuildDictionary.ORCHARD.getTypeId().equals(type)) {
			return BuildDictionary.ORCHARD;
		} else if (BuildDictionary.PASTURE.getTypeId().equals(type)) {
			return BuildDictionary.PASTURE;
		} else if (BuildDictionary.VEGETABLEGARDEN.getTypeId().equals(type)) {
			return BuildDictionary.VEGETABLEGARDEN;
		} else if (BuildDictionary.FISHPOND.getTypeId().equals(type)) {
			return BuildDictionary.FISHPOND;
		} else if (BuildDictionary.FOODCENTER.getTypeId().equals(type)) {
			return BuildDictionary.FOODCENTER;
		} else {
			// 此处应定义异常
			System.out.println("字典无此类型！");
			return null;
		}
	}
	
}
