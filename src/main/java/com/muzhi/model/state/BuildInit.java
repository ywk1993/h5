package com.muzhi.model.state;

import com.muzhi.model.Restaurant;
import com.muzhi.model.vo.Build;

public class BuildInit {
	Build build;
	Integer type;
	String description;
	
	public BuildInit() {
		super();
	}
	public BuildInit(Build build, Integer type, String description) {
		super();
		this.build = build;
		this.type = type;
		this.description = description;
		
		switch (type) {
		case 1: Restaurant restaurant = build.getRestaurant();
		}
	}
	public Build getBuild() {
		return build;
	}
	public void setBuild(Build build) {
		this.build = build;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
}
