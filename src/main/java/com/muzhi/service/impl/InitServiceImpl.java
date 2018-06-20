package com.muzhi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muzhi.dao.IncomeDao;
import com.muzhi.dao.ProvinceDao;
import com.muzhi.dao.RankingDao;
import com.muzhi.dao.RefreshManagerDao;
import com.muzhi.model.Income;
import com.muzhi.model.Province;
import com.muzhi.model.Ranking;
import com.muzhi.model.RefreshManager;
import com.muzhi.model.User;
import com.muzhi.service.ArticleService;
import com.muzhi.service.BuildService;
import com.muzhi.service.CookBenchService;
import com.muzhi.service.CookBookSerivce;
import com.muzhi.service.FoodService;
import com.muzhi.service.FridgeService;
import com.muzhi.service.InitService;
import com.muzhi.service.InteractiveService;
import com.muzhi.service.InventoryService;
import com.muzhi.service.MakeFoodInfoService;
import com.muzhi.service.PublicityService;
import com.muzhi.service.RankService;
import com.muzhi.service.RestaurantService;
import com.muzhi.service.SignboardService;
import com.muzhi.service.UserService;

@Service
public class InitServiceImpl implements InitService {

	@Autowired
	UserService userService;
	@Autowired
	CookBenchService cookBenchService;
	@Autowired
	CookBookSerivce cookBookSerivce;
	@Autowired
	FoodService foodService;
	@Autowired
	FridgeService fridgeService;
	@Autowired
	InteractiveService interactiveService;
	@Autowired
	RestaurantService restaurantService;
	@Autowired
	InventoryService inventoryService;
	@Autowired
	MakeFoodInfoService makeFoodInfoService;
	@Autowired
	SignboardService signboardService;
	@Autowired
	BuildService buildService;
	@Autowired
	RefreshManagerDao refreshManagerDao;
	@Autowired
	RankService rankService;
	@Autowired
	PublicityService publicityService;
	@Autowired
	ProvinceDao provinceDao;
	@Autowired
	IncomeDao incomeDao;
	@Autowired
	ArticleService articleService;
	@Autowired
	RankingDao rankingDao;
	
	@Override
	public void loginInit(Integer id) {
		User userInit = userService.userInit(id);
		cookBenchService.cookBenchInit(userInit);
		cookBookSerivce.cookBookInit(userInit);
		foodService.foodInit(userInit);
		fridgeService.fridgeInit(userInit);
		interactiveService.staffInit(userInit);
		restaurantService.restaurantInit(userInit);
		inventoryService.inventoryInit(userInit);
		makeFoodInfoService.makeFoodInfoInit(userInit);
		signboardService.insertSignboard(userInit);
		buildService.buildInit(userInit);
		RefreshManager refreshManager = new RefreshManager();
		
		refreshManager.setTotleNum(0);
		refreshManager.setNum(3);
		refreshManager.setId(id);
		
		rankService.initRank(userInit.getId());
		
		refreshManagerDao.insert(refreshManager);
		publicityService.publicityInit(userInit);
		
		articleService.getUserArticel(id);
		//初始化用户省份、收益信息
		provinceDao.insert(new Province(id, null));
		incomeDao.insert(new Income(id, 0,articleService.getTotalHaoHua(id)));
		//初始化用户排行信息
		rankingDao.insert(new Ranking(id, 0, 1, articleService.getTotalHaoHua(id)));
	}
	
}
