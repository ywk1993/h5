package com.muzhi.model;

/**
 * 招牌
 * @author yany
 *
 */
public class Signboard {
	/**
	 * 用户id
	 */
	private Integer id;
	/**
	 * 等级
	 */
	private Integer level;
	/**
	 * 名气
	 */
	private Integer fame;

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

	public Integer getFame() {
		return fame;
	}

	public void setFame(Integer fame) {
		this.fame = fame;
	}

	public Signboard(Integer id, Integer level, Integer fame) {
		super();
		this.id = id;
		this.level = level;
		this.fame = fame;
	}

	public Signboard() {
		super();
	}

	@Override
	public String toString() {
		return "Signboard [id=" + id + ", level=" + level + ", fame=" + fame + "]";
	}

	
    
}
