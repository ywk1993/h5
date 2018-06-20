package com.muzhi.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muzhi.model.CookBench;
import com.muzhi.model.Fridge;
import com.muzhi.model.Inventory;
import com.muzhi.model.Restaurant;
import com.muzhi.model.Result;
import com.muzhi.model.Signboard;
import com.muzhi.model.User;
import com.muzhi.model.configbean.ConfigFacilities;
import com.muzhi.model.vo.FacilityInfo;
import com.muzhi.redis.service.UserRedisService;
import com.muzhi.service.CookBenchService;
import com.muzhi.service.FacilityInfoService;
import com.muzhi.service.FridgeService;
import com.muzhi.service.InventoryService;
import com.muzhi.service.RestaurantService;
import com.muzhi.service.SignboardService;
import com.muzhi.service.UserService;
import com.muzhi.service.config.InitConfig;
import com.muzhi.util.ResultUtil;

@Service
public class FacilityInfoServiceImpl implements FacilityInfoService {

	@Autowired
	private CookBenchService cookBenchService;
	@Autowired
	private InventoryService inventoryService;
	@Autowired
	private SignboardService signboardService;
	@Autowired
	private FridgeService fridgeService;
	@Autowired
	private UserService userService;
	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private UserRedisService userRedisService;

	private static final Integer COOKBENCH = 1;
	private static final Integer INVENTORY = 2;
	private static final Integer SIGNBOARD = 4;
	private static final Integer FRIDGE = 3;

	@Override
	public List<FacilityInfo> facilityUpgradeInfo(Integer userid) {

		List<FacilityInfo> list = new ArrayList<>();
		CookBench cookBench = cookBenchService.getCookBench(userid);
		Inventory inventory = inventoryService.getInventory(userid);
		Signboard signboard = signboardService.getSignboard(userid);
		Fridge fridge = fridgeService.getFridge(userid);
		list.add(new FacilityInfo(COOKBENCH, cookBench.getLevel()));
		list.add(new FacilityInfo(INVENTORY, inventory.getLevel()));
		list.add(new FacilityInfo(SIGNBOARD, signboard.getLevel()));
		list.add(new FacilityInfo(FRIDGE, fridge.getLevel()));
		return list;
	}

	/**
	 * 1) 餐厅设施的升级会受到餐厅等级（庄园建筑）的限制。 2) 餐厅设施的升级需要花费金币。 3)
	 * 餐厅设施的属性只和等级有关，和数量无关。具体属性通过读取配置表确定。 4) 目前的设施包括灶台，冰箱，柜台和招牌。 5) 升级灶台会提升餐厅的厨力。 6)
	 * 升级冰箱会提升餐厅存放食材仓库的容量。 7) 升级柜台会提升餐厅存放菜品仓库的容量。 8) 升级招牌会提升名气。
	 */
	@Override
	public Result facilityUpgrade(String token, Integer fid) {

		User user = userService.getUserByToken(token);
		Restaurant restaurant = restaurantService.getRestaurant(user.getId());
		Map<Integer, ConfigFacilities> facilitiesMap = InitConfig.getInstance().getFacilitiesMap();
		CookBench cookBench = null;
		Inventory inventory = null;
		Signboard signboard = null;
		Fridge fridge = null;

		int restaurantLevel = 0;// 升级所需的餐厅等级
		int needgolad = 0;// 升级所需的金币

		if (fid.equals(COOKBENCH)) {
			cookBench = cookBenchService.getCookBench(user.getId());
			restaurantLevel = facilitiesMap.get(Integer.parseInt(fid + "" + cookBench.getLevel()))
					.getNeedlevel();
			needgolad = facilitiesMap.get(Integer.parseInt(fid + "" + cookBench.getLevel()))
					.getNeedgold();
		}
		if (fid.equals(INVENTORY)) {
			inventory = inventoryService.getInventory(user.getId());
			restaurantLevel = facilitiesMap.get(Integer.parseInt(fid + "" + inventory.getLevel()))
					.getNeedlevel();
			needgolad = facilitiesMap.get(Integer.parseInt(fid + "" + inventory.getLevel()))
					.getNeedgold();
		}
		if (fid.equals(SIGNBOARD)) {
			signboard = signboardService.getSignboard(user.getId());
			restaurantLevel = facilitiesMap.get(Integer.parseInt(fid + "" + signboard.getLevel()))
					.getNeedlevel();
			needgolad = facilitiesMap.get(Integer.parseInt(fid + "" + signboard.getLevel()))
					.getNeedgold();
		}
		if (fid.equals(FRIDGE)) {
			fridge = fridgeService.getFridge(user.getId());
			restaurantLevel = facilitiesMap.get(Integer.parseInt(fid + "" + fridge.getLevel()))
					.getNeedlevel();
			needgolad = facilitiesMap.get(Integer.parseInt(fid + "" + fridge.getLevel())).getNeedgold();
		}
		if (restaurant.getLevel() < restaurantLevel) {
			return ResultUtil.error(1005, "餐厅等级不足");
		}
		if (user.getGold() < needgolad) {
			return ResultUtil.error(1004, "金币不足");
		}
		if (fid.equals(COOKBENCH)) {
			if (cookBench.getLevel()>=InitConfig.getInstance().getFacilitiesListMap().get(fid).size()) {
				return ResultUtil.error(3001, "炤台已升至满级");
			}
			if (cookBench.getLevel() + 1>restaurant.getLevel()) {
				return ResultUtil.error(3005, "提升餐厅等级才能继续升级");
			}
			cookBench.setLevel(cookBench.getLevel() + 1);
			int addcooknumber = facilitiesMap.get(Integer.parseInt(fid + "" + cookBench.getLevel()))
					.getAttribute();//升级后的厨力
			cookBench.setAddition(addcooknumber);
			cookBenchService.updateCookBench(cookBench);
		}
		if (fid.equals(INVENTORY)) {
			if (inventory.getLevel()>=InitConfig.getInstance().getFacilitiesListMap().get(fid).size()) {
				return ResultUtil.error(3002, "橱柜已升至满级");
			}
			if (inventory.getLevel() + 1>restaurant.getLevel()) {
				return ResultUtil.error(3005, "提升餐厅等级才能继续升级");
			}
			int invebtorynumber = facilitiesMap.get(Integer.parseInt(fid + "" + (inventory.getLevel()+1)))
					.getAttribute();// 柜台升级增加的仓库的容量
			inventory.setIndexNum(/*inventory.getIndexNum() + */invebtorynumber);
			inventory.setLevel(inventory.getLevel() + 1);
			inventoryService.updateInventory(inventory);
		}
		if (fid.equals(SIGNBOARD)) {
			if (signboard.getLevel()>=InitConfig.getInstance().getFacilitiesListMap().get(fid).size()) {
				return ResultUtil.error(3003, "招牌已升至满级");
			}
			if (signboard.getLevel() + 1>restaurant.getLevel()) {
				return ResultUtil.error(3005, "提升餐厅等级才能继续升级");
			}
			int signnumber = facilitiesMap.get(Integer.parseInt(fid + "" + signboard.getLevel()))
					.getAttribute();// 增加名气
			signboard.setFame(signboard.getFame()+signnumber);
			signboard.setLevel(signboard.getLevel() + 1);
			signboardService.updateSignboard(signboard);
		}
		if (fid.equals(FRIDGE)) {
			if (fridge.getLevel()>=InitConfig.getInstance().getFacilitiesListMap().get(fid).size()) {
				return ResultUtil.error(3004, "冰箱已升至满级");
			}
			if (fridge.getLevel() + 1>restaurant.getLevel()) {
				return ResultUtil.error(3005, "提升餐厅等级才能继续升级");
			}
			int fridgenumber = facilitiesMap.get(Integer.parseInt(fid + "" + fridge.getLevel()))
					.getAttribute();// 冰箱升级升级增加的食材的存储上限
			fridge.setIndexNum(fridge.getIndexNum() + fridgenumber);
			fridge.setLevel(fridge.getLevel() + 1);
			fridgeService.updateFridge(fridge);
		}
		//升级扣除金币
		user.setGold(user.getGold()-needgolad);
		userService.updateUser(user);
		userRedisService.setUser(token, user);
		return ResultUtil.success();
	}

}
