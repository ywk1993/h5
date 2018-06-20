package com.muzhi.model;

import com.muzhi.model.configbean.Configfame;

public class FameInfo {
	private Integer fameLevel;
	private Integer totalFame;
	private Configfame thisLevelFame;
	private Configfame nextLevelFame;
	private Integer xuanChuang;
	private Integer yuanGong;
	private Integer sheShi;

	public Integer getFameLevel() {
		return fameLevel;
	}

	public void setFameLevel(Integer fameLevel) {
		this.fameLevel = fameLevel;
	}

	public Integer getTotalFame() {
		return totalFame;
	}

	public void setTotalFame(Integer totalFame) {
		this.totalFame = totalFame;
	}

	public Configfame getThisLevelFame() {
		return thisLevelFame;
	}

	public void setThisLevelFame(Configfame thisLevelFame) {
		this.thisLevelFame = thisLevelFame;
	}

	public Configfame getNextLevelFame() {
		return nextLevelFame;
	}

	public void setNextLevelFame(Configfame nextLevelFame) {
		this.nextLevelFame = nextLevelFame;
	}

	public Integer getXuanChuang() {
		return xuanChuang;
	}

	public void setXuanChuang(Integer xuanChuang) {
		this.xuanChuang = xuanChuang;
	}

	public Integer getYuanGong() {
		return yuanGong;
	}

	public void setYuanGong(Integer yuanGong) {
		this.yuanGong = yuanGong;
	}

	public Integer getSheShi() {
		return sheShi;
	}

	public void setSheShi(Integer sheShi) {
		this.sheShi = sheShi;
	}

	
    
}
