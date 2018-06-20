package com.muzhi.service;

import com.muzhi.model.Restaurant;
import com.muzhi.model.User;

public interface RestaurantService {
	/**
	 * 获取的餐厅
	 * @param id
	 * @return 
	 */
	public Restaurant getRestaurant(Integer id);

	public void restaurantInit(User user);
	
	public int deleteByPrimaryKey(Integer id);

    public int insert(Restaurant record);

    public int insertSelective(Restaurant record);

    public Restaurant selectByPrimaryKey(Integer id);

    public int updateByPrimaryKeySelective(Restaurant record);

    public int updateByPrimaryKey(Restaurant record);
}
