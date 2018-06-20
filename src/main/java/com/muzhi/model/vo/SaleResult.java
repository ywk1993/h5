package com.muzhi.model.vo;

public class SaleResult {
    private int heartNumber;
	private int heartVariable;
	private int goldNumber;
	private int goldVariable;
	private RoleVO nextRole;
	public int getHeartNumber() {
		return heartNumber;
	}
	public void setHeartNumber(int heartNumber) {
		this.heartNumber = heartNumber;
	}
	public int getHeartVariable() {
		return heartVariable;
	}
	public void setHeartVariable(int heartVariable) {
		this.heartVariable = heartVariable;
	}
	public int getGoldNumber() {
		return goldNumber;
	}
	public void setGoldNumber(int goldNumber) {
		this.goldNumber = goldNumber;
	}
	public int getGoldVariable() {
		return goldVariable;
	}
	public void setGoldVariable(int goldVariable) {
		this.goldVariable = goldVariable;
	}
	public RoleVO getNextRole() {
		return nextRole;
	}
	public void setNextRole(RoleVO nextRole) {
		this.nextRole = nextRole;
	}
	
}
