package com.muzhi.model.vo;

public class FacilityInfo {
	/**
	 * 设施id
	 */
	private Integer id;
	/**
	 * 玩家设施等级
	 */
	private Integer level;

	public FacilityInfo() {
		super();
	}

	public FacilityInfo(Integer id, Integer level) {
		super();
		this.id = id;
		this.level = level;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "FacilityInfo [id=" + id + ", level=" + level + "]";
	}
    
}
