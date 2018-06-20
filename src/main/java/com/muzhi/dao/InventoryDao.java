package com.muzhi.dao;

import com.muzhi.model.Inventory;

public interface InventoryDao {
	void updateInventory(Inventory inventory);
	void insert(Inventory inventory);
	Inventory getInventoryWithFood(Integer id);
	Inventory getInventoryWithOutFood(Integer id);
}
