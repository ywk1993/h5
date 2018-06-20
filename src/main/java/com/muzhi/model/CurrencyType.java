package com.muzhi.model;

/**
 * 货币信息
 * 
 * @author Yuwk
 *
 *         2017年11月23日
 */
public class CurrencyType {
	/**
	 * 货币类型
	 */
	private Integer currencyType;
	/**
	 * 货币数量
	 */
	private Integer currencyNum;

	public CurrencyType(Integer currencyType, Integer currencyNum) {
		super();
		this.currencyType = currencyType;
		this.currencyNum = currencyNum;
	}

	public Integer getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(Integer currencyType) {
		this.currencyType = currencyType;
	}

	public Integer getCurrencyNum() {
		return currencyNum;
	}

	public void setCurrencyNum(Integer currencyNum) {
		this.currencyNum = currencyNum;
	}

	@Override
	public String toString() {
		return "CurrencyType [currencyType=" + currencyType + ", currencyNum=" + currencyNum + "]";
	}

}
