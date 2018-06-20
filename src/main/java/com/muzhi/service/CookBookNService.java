package com.muzhi.service;

import com.muzhi.model.User;
import com.muzhi.model.vo.FoodDetail;
import com.muzhi.model.vo.MenuAll;

/**
 * 菜谱服务
 * @author yany
 *
 */
public interface CookBookNService {
	
	public MenuAll getCookBook(User user);
	
	public void setCookBook();
	
	public FoodDetail getFoodDetail(User user, Integer id);
	
}
