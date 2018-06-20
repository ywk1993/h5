package com.muzhi.model.vo;

import java.util.List;

import com.muzhi.model.Food;
import com.muzhi.model.Prop;

public class ResearchAll {
	private List<ResearchMenu> researchMenus;
	private int currentPower;
	private int maxPower;
	private List<Food> kuCun;
	private List<Prop> props;
	
	
	public List<Food> getKuCun() {
		return kuCun;
	}

	public void setKuCun(List<Food> kuCun) {
		this.kuCun = kuCun;
	}

	public List<Prop> getProps() {
		return props;
	}

	public void setProps(List<Prop> props) {
		this.props = props;
	}

	public ResearchAll() {
		super();
	}

	public ResearchAll(List<ResearchMenu> researchMenus, int currentPower, int maxPower,List<Food> kuCun,List<Prop> props) {
		super();
		this.researchMenus = researchMenus;
		this.currentPower = currentPower;
		this.maxPower = maxPower;
		this.kuCun =kuCun;
		this.props =props;
	}

	public List<ResearchMenu> getResearchMenus() {
		return researchMenus;
	}

	public void setResearchMenus(List<ResearchMenu> researchMenus) {
		this.researchMenus = researchMenus;
	}

	public int getCurrentPower() {
		return currentPower;
	}

	public void setCurrentPower(int currentPower) {
		this.currentPower = currentPower;
	}

	public int getMaxPower() {
		return maxPower;
	}

	public void setMaxPower(int maxPower) {
		this.maxPower = maxPower;
	}

}
