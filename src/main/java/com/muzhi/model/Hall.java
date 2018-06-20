package com.muzhi.model;

import java.io.Serializable;
import java.util.List;

import com.muzhi.model.configbean.ConfigArticle;
import com.muzhi.model.vo.RestaurantIndexInfo;
import com.muzhi.model.vo.RoleVO;
import com.muzhi.model.vo.StaffAll;

/**
 * @author yany
 */
public class Hall implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private RestaurantIndexInfo restaurant;	
	private Inventory inventory;
	private CookBench cookBench;
	private Fridge fridge;
	private StaffAll staffAll;
	private List<RoleVO> role;
	private List<ConfigArticle> article;
	private Rank rank;
	
	
	public Rank getRank() {
		return rank;
	}
	public void setRank(Rank rank) {
		this.rank = rank;
	}
	public List<ConfigArticle> getArticle() {
		return article;
	}
	public void setArticle(List<ConfigArticle> article) {
		this.article = article;
	}
	public List<RoleVO> getRole() {
		return role;
	}
	public void setRole(List<RoleVO> role) {
		this.role = role;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public RestaurantIndexInfo getRestaurantIndexInfo() {
		return restaurant;
	}
	public void setRestaurant(RestaurantIndexInfo restaurant) {
		this.restaurant = restaurant;
	}
	public Inventory getInventory() {
		return inventory;
	}
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	public CookBench getCookBench() {
		return cookBench;
	}
	public void setCookBench(CookBench cookBench) {
		this.cookBench = cookBench;
	}
	public Fridge getFridge() {
		return fridge;
	}
	public void setFridge(Fridge fridge) {
		this.fridge = fridge;
	}
	public StaffAll getStaffAll() {
		return staffAll;
	}
	public void setStaffAll(StaffAll staffAll) {
		this.staffAll = staffAll;
	}
	
}