package com.muzhi.model;

import java.util.List;

import com.muzhi.util.StringUtil;

/**
 * @author 
 */
public class OrderFood {

    /**
     * 菜品ID
     */
    private Integer foodid;

    /**
     * 菜品品质
     */
    private Integer foodqualify;
    /**
     * 需求数量
     */
    private Integer needNumber;
    
    
    
	public OrderFood() {
		super();
	}
	public OrderFood(String string) {
		List<Integer> splitHandle = StringUtil.splitHandle(string);
		this.foodid = splitHandle.get(0);
		this.foodqualify = splitHandle.get(2);
		this.needNumber = splitHandle.get(1);
	}
	public Integer getFoodid() {
		return foodid;
	}
	public void setFoodid(Integer foodid) {
		this.foodid = foodid;
	}
	public Integer getFoodqualify() {
		return foodqualify;
	}
	public void setFoodqualify(Integer foodqualify) {
		this.foodqualify = foodqualify;
	}
	public Integer getNeedNumber() {
		return needNumber;
	}
	public void setNeedNumber(Integer needNumber) {
		this.needNumber = needNumber;
	}
	@Override
	public String toString() {
		return "OrderFood [foodid=" + foodid + ", foodqualify=" + foodqualify + ", needNumber=" + needNumber + "]";
	}
    
    
    
}