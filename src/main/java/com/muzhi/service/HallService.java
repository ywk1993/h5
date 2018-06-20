package com.muzhi.service;

import com.muzhi.model.Hall;
import com.muzhi.model.User;
import com.muzhi.model.vo.RestaurantIndexInfo;

public interface HallService {
	public Hall getHall(User user);
	public RestaurantIndexInfo getRestaurant(User user);
}
