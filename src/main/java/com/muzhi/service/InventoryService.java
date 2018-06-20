package com.muzhi.service;

import com.muzhi.model.Inventory;
import com.muzhi.model.Result;
import com.muzhi.model.User;

public interface InventoryService {
	public Inventory getInventory(Integer id);
	public void updateInventory(Inventory inventory);
	public void inventoryInit(User userInit);
	public Inventory getInventoryWithFood(Integer id);
	public Inventory getInventoryWithOutFood(Integer id);
	/**
	 * 通过道具激活新的菜品
	 */
	public Result  activateCookbook(String token ,Integer foodId);
	/**
	 * 展示道具激活菜品的信息
	 */
	public Result getActivateInfo(String token,Integer propId);
}	
