package com.muzhi.model.vo;
/**
 * 
 * @author Yuwk
 *
 * 2018年1月30日
 */
public class FoodInfo {
 
	private Integer foodId;
	private Integer price;
	private Integer isHave;
	public Integer getFoodId() {
		return foodId;
	}
	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getIsHave() {
		return isHave;
	}
	public void setIsHave(Integer isHave) {
		this.isHave = isHave;
	}
	@Override
	public String toString() {
		return "FoodInfo [foodId=" + foodId + ", price=" + price + ", isHave=" + isHave + "]";
	}
	
}
