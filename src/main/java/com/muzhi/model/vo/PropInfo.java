package com.muzhi.model.vo;

import java.util.List;

import com.muzhi.model.Prop;

public class PropInfo {
	
	private Integer type;
	private List<Prop> Prop;
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public List<Prop> getProp() {
		return Prop;
	}
	public void setProp(List<Prop> prop) {
		Prop = prop;
	}
	public PropInfo(Integer type, List<com.muzhi.model.Prop> prop) {
		super();
		this.type = type;
		Prop = prop;
	}
	public PropInfo() {
		super();
	}
	
	
}
