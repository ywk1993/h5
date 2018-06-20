package com.muzhi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muzhi.model.CookBook;
import com.muzhi.model.Hall;
import com.muzhi.model.MakeFoodInfo;
import com.muzhi.model.Restaurant;
import com.muzhi.model.User;
import com.muzhi.model.configbean.ConfigRestaurant;
import com.muzhi.model.vo.MakeFoodState;
import com.muzhi.model.vo.RestaurantIndexInfo;
import com.muzhi.redis.service.MakeFoodRedisService;
import com.muzhi.service.ArticleService;
import com.muzhi.service.CookBenchService;
import com.muzhi.service.CookBookSerivce;
import com.muzhi.service.FridgeService;
import com.muzhi.service.HallService;
import com.muzhi.service.InteractiveService;
import com.muzhi.service.InventoryService;
import com.muzhi.service.MakeFoodInfoService;
import com.muzhi.service.RankService;
import com.muzhi.service.RestaurantService;
import com.muzhi.service.RoleService;
import com.muzhi.service.UserService;
import com.muzhi.service.config.InitConfig;

@Service
public class HallServiceImpl implements HallService {

	@Autowired
	UserService userService;
	@Autowired
	RestaurantService restaurantService;
	@Autowired
	CookBenchService cookBenchService;
	@Autowired
	FridgeService fridgeService;
	@Autowired
	InteractiveService interactiveService;
	@Autowired
	InventoryService inventoryService;
	@Autowired
	MakeFoodInfoService makeFoodInfoService;
	@Autowired
	CookBookSerivce cookBookSerivce;
	@Autowired
	private MakeFoodRedisService makeFoodRedisService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private RankService rankService;

	@Override
	public Hall getHall(User user) {
		Hall hall = new Hall();
		hall.setUser(user);
		hall.setCookBench(cookBenchService.getCookBench(user.getId()));
		hall.setFridge(fridgeService.getFridge(user.getId()));
		hall.setInventory(inventoryService.getInventory(user.getId()));
		hall.setRestaurant(this.getRestaurant(user));
		hall.setStaffAll(interactiveService.getStaff(user));
		hall.setRole(roleService.getRoles(user));
		hall.setArticle(articleService.getUserArticel(user.getId()));
		hall.setRank(rankService.query(user.getId()));
		return hall;
	}

	@Override
	public RestaurantIndexInfo getRestaurant(User user) {
		RestaurantIndexInfo restaurantIndexInfo = new RestaurantIndexInfo();

		Restaurant restaurant = restaurantService.getRestaurant(user.getId());
		restaurantIndexInfo.setCustomNum(restaurant.getCustomNum());
		restaurantIndexInfo.setLevel(restaurant.getLevel());
		List<MakeFoodInfo> makeFoodInfoList = makeFoodInfoService.getMakeFoodInfo(user.getId());

		// 根据配置表获取用户等级
		ConfigRestaurant res = InitConfig.getInstance().getRestaurantMap().get(restaurant.getLevel());
		restaurantIndexInfo.setArea(res.getArea());
		restaurantIndexInfo.setLoveLimit(res.getLovelimit());

		List<MakeFoodState> list = new ArrayList<MakeFoodState>();
		for (MakeFoodInfo makeFoodInfo : makeFoodInfoList) {
			MakeFoodState makeFoodState = new MakeFoodState();
			makeFoodState.setFoodID(makeFoodInfo.getFoodId());
			makeFoodState.setIndex(makeFoodInfo.getIndex());
			Long lefttime = makeFoodRedisService.getExpire(makeFoodInfo.getIndex(), user.getId());
			if (lefttime == (-2) || lefttime == (-1)) {
				makeFoodState.setLeftTime(0);
			} else {
				makeFoodState.setLeftTime(lefttime.intValue());
			}
			CookBook cookBook = new CookBook();
			cookBook.setId(user.getId());
			cookBook.setFoodId(makeFoodInfo.getFoodId());
			CookBook oneCookbook = cookBookSerivce.getOneCookbook(user.getId(), makeFoodInfo.getFoodId());
			makeFoodState.setTotalTime(0);
			if (oneCookbook!=null) {
				makeFoodState.setTotalTime(oneCookbook.getMakeTime()*1000);
			}
			makeFoodState.setStatus(makeFoodInfo.getStatus());
			list.add(makeFoodState);
		}
		restaurantIndexInfo.setMakeFoodState(list);
		return restaurantIndexInfo;
	}

}
