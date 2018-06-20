package com.muzhi.model;
/**
 * 灶台接口
 * @author yany
 *
 */
public class CookBench {
	/**
	 * 用户id
	 */
	private Integer id;
	/**
	 * 等级
	 */
	private Integer level;
	/**
	 * 加成 
	 */
	private Integer addition;
	
	public CookBench() {
		super();
	}

	public CookBench(Integer id, Integer level, Integer addition) {
		super();
		this.id = id;
		this.level = level;
		this.addition = addition;
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

	public Integer getAddition() {
		return addition;
	}

	public void setAddition(Integer addition) {
		this.addition = addition;
	}
	
	
}
