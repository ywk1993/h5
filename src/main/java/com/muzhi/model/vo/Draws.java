package com.muzhi.model.vo;

import java.util.List;

public class Draws {
	private Integer type;
	private List<Draw> list;
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public List<Draw> getList() {
		return list;
	}
	public void setList(List<Draw> list) {
		this.list = list;
	}
	public Draws(Integer type, List<Draw> list) {
		super();
		this.type = type;
		this.list = list;
	}
	public Draws() {
		super();
	}
	
}
