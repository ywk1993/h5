package com.muzhi.model;
/**
 * 材料字典
 * @author yany
 *
 */
public enum MetarialDictionary {
	RICE(1, "大米", 0), BANANA(2, "香蕉", 0), VEGETABLE(3, "蔬菜", 0), MEAT(4, "肉类", 0), FISH(5, "鱼类", 0);
	
	/**
	 * 类型
	 */
	private Integer type;
	/**
	 * 描述
	 */
	private String desc;
	/**
	 * 数值
	 */
	private Integer num;
	
	private MetarialDictionary(Integer type, String desc, Integer num) {
		this.type = type;
		this.desc = desc;
		this.num = num;
	}
	
	
	public Integer getNum() {
		return num;
	}


	public void setNum(Integer num) {
		this.num = num;
	}


	public Integer getType() {
		return type;
	}


	public Integer getTypeId() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	// RICE(1, "大米"), BANANA(2, "香蕉"), VEGETABLE(3, "蔬菜"), MEAT(4, "肉类"), FISH(5, "鱼类");
	public static MetarialDictionary find(Integer type) {
		if (MetarialDictionary.RICE.getTypeId().equals(type)) {
			return MetarialDictionary.RICE;
		} else if (MetarialDictionary.BANANA.getTypeId().equals(type)) {
			return MetarialDictionary.BANANA;
		} else if (MetarialDictionary.VEGETABLE.getTypeId().equals(type)) {
			return MetarialDictionary.VEGETABLE;
		} else if (MetarialDictionary.MEAT.getTypeId().equals(type)) {
			return MetarialDictionary.MEAT;
		} else if (MetarialDictionary.FISH.getTypeId().equals(type)) {
			return MetarialDictionary.FISH;
		} else {
			// 此处应定义异常
			System.out.println("字典无此类型！");
			return null;
		}
	}
	
}
