package com.muzhi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muzhi.dao.RestaurantDao;
import com.muzhi.model.Restaurant;
import com.muzhi.model.User;
import com.muzhi.model.configbean.ConfigRestaurant;
import com.muzhi.model.state.LEVEL_STATE;
import com.muzhi.service.RestaurantService;
import com.muzhi.service.config.InitConfig;

@Service
public class RestaurantServiceImpl implements RestaurantService{

	@Autowired
	RestaurantDao restaurantDao;

	@Override
	public Restaurant getRestaurant(Integer id) {
		return restaurantDao.selectByPrimaryKey(id);
	}

	@Override
	public void restaurantInit(User user) {
		Restaurant restaurant = new Restaurant();
		ConfigRestaurant configRestaurant = InitConfig.getInstance().getRestaurantMap().get( LEVEL_STATE.INIT_LEVEL.getNum());
		System.out.println(user.getId() +  LEVEL_STATE.INIT_LEVEL.getNum_description() +  LEVEL_STATE.INIT_LEVEL.getNum_description());
		restaurant.setId(user.getId());
		restaurant.setCustomNum(500);//数值来源值得商榷
		restaurant.setLevel(configRestaurant.getLevel());
		restaurant.setLoveLimit(configRestaurant.getLovelimit());
		restaurant.setMaxAsher(configRestaurant.getMaxchef());
		restaurant.setMaxChef(configRestaurant.getMaxchef());
		restaurant.setArea(configRestaurant.getArea());
		restaurantDao.insert(restaurant);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return restaurantDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Restaurant record) {
		return restaurantDao.insert(record);
	}

	@Override
	public int insertSelective(Restaurant record) {
		return restaurantDao.insertSelective(record);
	}

	@Override
	public Restaurant selectByPrimaryKey(Integer id) {
		return restaurantDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Restaurant record) {
		return restaurantDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Restaurant record) {
		return restaurantDao.updateByPrimaryKey(record);
	}

}
