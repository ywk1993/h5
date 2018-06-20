package com.muzhi.model.vo;
/**
 * 员工
 * @author yany
 *
 */
public class Staff {
	// 员工id
	private Integer id;
	// 玩家头像
	private String img;
	// 雇佣倒计时
	private Integer leftTime;
	// 雇佣状态
	private Integer staffType;
	// 员工等级
	private Integer level;
	// 位置信息
	private Integer index;
	//解锁信息
	private Integer openLevel;
	//雇用金币
	private Integer gold;
	
	public Integer getOpenLevel() {
		return openLevel;
	}
	public void setOpenLevel(Integer openLevel) {
		this.openLevel = openLevel;
	}
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Integer getLeftTime() {
		return leftTime;
	}
	public void setLeftTime(Integer leftTime) {
		this.leftTime = leftTime;
	}
	public Integer getStaffType() {
		return staffType;
	}
	public void setStaffType(Integer staffType) {
		this.staffType = staffType;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getGold() {
		return gold;
	}
	public void setGold(Integer gold) {
		this.gold = gold;
	}

}
