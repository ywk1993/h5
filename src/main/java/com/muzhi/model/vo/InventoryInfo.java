package com.muzhi.model.vo;

import java.util.List;
/**
 * 库存信息
 * @author yany
 *
 */
public class InventoryInfo {
	/**
	 * 配方列表
	 */
	private List<InventoryVo> inventoryList;
	/**
	 * 库存当前总数
	 */
	private Integer currentNum;
	
	/**
	 * 库存上限
	 */
	private Integer totalNum;

	public InventoryInfo(List<InventoryVo> inventoryList, Integer currentNum, Integer totalNum) {
		super();
		this.inventoryList = inventoryList;
		this.currentNum = currentNum;
		this.totalNum = totalNum;
	}

	public List<InventoryVo> getInventoryList() {
		return inventoryList;
	}

	public void setInventoryList(List<InventoryVo> inventoryList) {
		this.inventoryList = inventoryList;
	}

	public Integer getCurrentNum() {
		return currentNum;
	}

	public void setCurrentNum(Integer currentNum) {
		this.currentNum = currentNum;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	
}