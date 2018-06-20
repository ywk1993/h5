package com.muzhi.model.state;

/**
 * 等级状态
 * @author yany
 *
 */
public enum LEVEL_STATE {
	
	INIT_LEVEL(1, "初始化等级", 0, "初始化存量");
	Integer num;
	String num_description;
	Integer stockNum;
	String stockNum_description;
	
	private LEVEL_STATE(Integer num, String num_description, Integer stockNum, String stockNum_description) {
		this.num = num;
		this.num_description = num_description;
		this.stockNum = stockNum;
		this.stockNum_description = stockNum_description;
	}


	public String getNum_description() {
		return num_description;
	}


	public void setNum_description(String num_description) {
		this.num_description = num_description;
	}


	public String getStockNum_description() {
		return stockNum_description;
	}


	public void setStockNum_description(String stockNum_description) {
		this.stockNum_description = stockNum_description;
	}


	public Integer getStockNum() {
		return stockNum;
	}


	public void setStockNum(Integer stockNum) {
		this.stockNum = stockNum;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}


	public Integer getleftTime() {
		return 0;
	}
	
}
