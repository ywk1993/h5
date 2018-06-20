package com.muzhi.model;

import java.io.Serializable;

public class CurrencyInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 生产货币类型
	private Integer creatCurrency;
	
	// 资源回复速率
	private Integer createTime;
	
	// 建筑状态 
	private Integer state;
	
	// 满仓剩余时间
	private Integer currencyLeftTime;

	public Integer getCreatCurrency() {
		return creatCurrency;
	}

	public void setCreatCurrency(Integer creatCurrency) {
		this.creatCurrency = creatCurrency;
	}

	public Integer getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getCurrencyLeftTime() {
		return currencyLeftTime;
	}

	public void setCurrencyLeftTime(Integer currencyLeftTime) {
		this.currencyLeftTime = currencyLeftTime;
	}
	
}