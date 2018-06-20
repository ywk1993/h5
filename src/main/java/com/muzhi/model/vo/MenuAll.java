package com.muzhi.model.vo;

import java.util.List;

import com.muzhi.model.Food;
import com.muzhi.model.Prop;

public class MenuAll {
	private List<Food> kuCun;
	private List<Prop> props;
	private List<Menu> menuList;
	private Integer currentStrength;
	
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
	public MenuAll(List<Menu> menuList, Integer currentStrength,List<Food> kuCun,List<Prop> props) {
		super();
		this.menuList = menuList;
		this.currentStrength = currentStrength;
		this.kuCun=kuCun;
		this.props =props;
	}
	public List<Menu> getMenuList() {
		return menuList;
	}
	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}
	public Integer getCurrentStrength() {
		return currentStrength;
	}
	public void setCurrentStrength(Integer currentStrength) {
		this.currentStrength = currentStrength;
	}
	
}
