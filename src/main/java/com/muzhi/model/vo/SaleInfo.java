package com.muzhi.model.vo;

/**
 * 
 * @author Yuwk
 *
 *         2018年1月30日
 */
public class SaleInfo {

	private Integer isDiscount;
	private Integer isRisePrice;
	private Integer isCompliment;
	private Integer discountNumber;
	private Integer risePriceNumber;
	private Integer[] complimentNumber;
	private Integer refusedNumber;
	private Integer saleNumber;
	private Integer tradeNumber;
	private FoodInfo foodInfo;
	private SellFoodInfo sellFoodInfo;
	private Integer loveNumber;
	private Integer foodQualify;
      
	
	public Integer getFoodQualify() {
		return foodQualify;
	}

	public void setFoodQualify(Integer foodQualify) {
		this.foodQualify = foodQualify;
	}

	public Integer getIsDiscount() {
		return isDiscount;
	}

	public void setIsDiscount(Integer isDiscount) {
		this.isDiscount = isDiscount;
	}

	public Integer getIsRisePrice() {
		return isRisePrice;
	}

	public void setIsRisePrice(Integer isRisePrice) {
		this.isRisePrice = isRisePrice;
	}

	public Integer getIsCompliment() {
		return isCompliment;
	}

	public void setIsCompliment(Integer isCompliment) {
		this.isCompliment = isCompliment;
	}

	public Integer getDiscountNumber() {
		return discountNumber;
	}

	public void setDiscountNumber(Integer discountNumber) {
		this.discountNumber = discountNumber;
	}

	public Integer getRisePriceNumber() {
		return risePriceNumber;
	}

	public void setRisePriceNumber(Integer risePriceNumber) {
		this.risePriceNumber = risePriceNumber;
	}

	public Integer[] getComplimentNumber() {
		return complimentNumber;
	}

	public void setComplimentNumber(Integer[] complimentNumber) {
		this.complimentNumber = complimentNumber;
	}

	public Integer getRefusedNumber() {
		return refusedNumber;
	}

	public void setRefusedNumber(Integer refusedNumber) {
		this.refusedNumber = refusedNumber;
	}

	public Integer getSaleNumber() {
		return saleNumber;
	}

	public void setSaleNumber(Integer saleNumber) {
		this.saleNumber = saleNumber;
	}

	public Integer getTradeNumber() {
		return tradeNumber;
	}

	public void setTradeNumber(Integer tradeNumber) {
		this.tradeNumber = tradeNumber;
	}

	public FoodInfo getFoodInfo() {
		return foodInfo;
	}

	public void setFoodInfo(FoodInfo foodInfo) {
		this.foodInfo = foodInfo;
	}

	public SellFoodInfo getSellFoodInfo() {
		return sellFoodInfo;
	}

	public void setSellFoodInfo(SellFoodInfo sellFoodInfo) {
		this.sellFoodInfo = sellFoodInfo;
	}

	public Integer getLoveNumber() {
		return loveNumber;
	}

	public void setLoveNumber(Integer loveNumber) {
		this.loveNumber = loveNumber;
	}

}
