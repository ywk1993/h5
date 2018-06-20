package com.muzhi.model;

import java.util.List;

import com.muzhi.util.StringUtil;

/**
 * 消耗道具
 * 
 * @author Yuwk 类型,id,数量;类型,id,数量 2018年5月28日
 */
public class Reused {
	private Integer type;
	private Integer id;
	private Integer number;
    
	public Reused() {
		super();
	}

	public Reused(String input) {
		List<Integer> splitHandle = StringUtil.splitHandle(input);
		this.type = splitHandle.get(0);
		this.id = splitHandle.get(1);
		this.number = splitHandle.get(2);
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

}
