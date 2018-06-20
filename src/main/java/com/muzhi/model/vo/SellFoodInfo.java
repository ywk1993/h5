package com.muzhi.model.vo;

public class SellFoodInfo {
	private Integer foodId;
	private Integer heartnumber;
	private Integer foodQualify;
    
	
	public Integer getFoodQualify() {
		return foodQualify;
	}

	public void setFoodQualify(Integer foodQualify) {
		this.foodQualify = foodQualify;
	}

	public Integer getFoodId() {
		return foodId;
	}

	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}

	public Integer getHeartnumber() {
		return heartnumber;
	}

	public void setHeartnumber(Integer heartnumber) {
		this.heartnumber = heartnumber;
	}

	@Override
	public String toString() {
		return "sellFoodInfo [foodId=" + foodId + ", heartnumber=" + heartnumber + "]";
	}

}
