package com.muzhi.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muzhi.model.BuildDictionary;
import com.muzhi.model.Farm;
import com.muzhi.model.Fishpond;
import com.muzhi.model.Foodcenter;
import com.muzhi.model.Fridge;
import com.muzhi.model.Generator;
import com.muzhi.model.Manor;
import com.muzhi.model.ManorKey;
import com.muzhi.model.Orchard;
import com.muzhi.model.OrderCenter;
import com.muzhi.model.Pasture;
import com.muzhi.model.Restaurant;
import com.muzhi.model.Reused;
import com.muzhi.model.TaskMethod;
import com.muzhi.model.User;
import com.muzhi.model.VegetableGarden;
import com.muzhi.model.configbean.ConfigCookcenter;
import com.muzhi.model.configbean.ConfigManor;
import com.muzhi.model.configbean.ConfigResources;
import com.muzhi.model.configbean.ConfigRestaurant;
import com.muzhi.model.state.BuildState;
import com.muzhi.model.state.LEVEL_STATE;
import com.muzhi.model.vo.Build;
import com.muzhi.redis.service.impl.ResearchRedisService;
import com.muzhi.service.BuildMaxLevelException;
import com.muzhi.service.BuildService;
import com.muzhi.service.BuildUpgradingException;
import com.muzhi.service.FarmService;
import com.muzhi.service.FishpondService;
import com.muzhi.service.FoodcenterService;
import com.muzhi.service.FridgeService;
import com.muzhi.service.GeneratorService;
import com.muzhi.service.ManorService;
import com.muzhi.service.OrchardService;
import com.muzhi.service.OrderCenterService;
import com.muzhi.service.PastureService;
import com.muzhi.service.ResearchService;
import com.muzhi.service.RestaurantService;
import com.muzhi.service.TaskService;
import com.muzhi.service.UserService;
import com.muzhi.service.VegetableGardenService;
import com.muzhi.service.config.InitConfig;
import com.muzhi.service.exception.DiamondNoEnoughException;
import com.muzhi.service.exception.GoldNoEnoughException;
import com.muzhi.service.exception.NoUpgradingException;
import com.muzhi.util.StringUtil;

@Service
public class BuildServiceImpl implements BuildService, BuildState {

	@Autowired
	FarmService farmService;
	@Autowired
	FishpondService fishpondService;
	@Autowired
	OrchardService orchardService;
	@Autowired
	PastureService pastureService;
	@Autowired
	VegetableGardenService vegetableGardenService;
	@Autowired
	ManorService manorService;
	@Autowired
	ResearchService researchService;
	@Autowired
	RestaurantService restaurantService;
	@Autowired
	FoodcenterService foodcenterService;
	@Autowired
	UserService userService;
	@Autowired
	FridgeService fridgeService;
	@Autowired
	GeneratorService generatorService;
	@Autowired
	ResearchRedisService researchRedisService;
	@Autowired
	OrderCenterService orderCenterService;
	@Autowired
	TaskService taskService;

	@Override
	public Build getBuildAll(User userByToken) {
		Build build = new Build();

		Generator generator = generatorService.selectByPrimaryKey(userByToken.getId());
		setFarm(getFarm(userByToken), generator, build);
		setFishpond(getFishpond(userByToken), generator, build);
		setOrchard(getOrchard(userByToken), generator, build);
		setPasture(getPasture(userByToken), generator, build);
		setVegetableGarden(getVegetableGarden(userByToken), generator, build);

		// 资源建筑
		build.setFarm(getFarm(userByToken));
		build.setFishpond(getFishpond(userByToken));
		build.setOrchard(getOrchard(userByToken));
		build.setPasture(getPasture(userByToken));
		build.setVegetableGarden(getVegetableGarden(userByToken));
		build.setRestaurant(getRestaurant(userByToken));
		build.setFoodcenter(getFoodcenter(userByToken));
		build.setOrderCenter(orderCenterService.selectByPrimaryKey(userByToken.getId()));
		// 升级信息
		// Map<Integer, ConfigManor> configManorMap = configService.getConfigManor();
		ManorKey manorKey = new ManorKey();
		manorKey.setId(userByToken.getId());
		List<Manor> updateManorList = manorService.selectBySelectivePrimaryKey(manorKey);
		for (Manor manor : updateManorList) {
			if (manor.getStartTime() == 0 && manor.getLeftTime() == 0) {
				// 完成升级状态
			} else {
				if (System.currentTimeMillis() - manor.getStartTime() >= manor.getTime() * 1000) {
					manor.setLeftTime(0L);
					manor.setStartTime(0L);
					this.refresh(userByToken, manor.getBuildId(), build);
				} else {
					// 计算剩余时间
					manor.setLeftTime(manor.getTime() * 1000 - (System.currentTimeMillis() - manor.getStartTime()));
					System.out.println("剩余时间：" + manor.getLeftTime());
					manorService.updateByPrimaryKey(manor);
				}
			}
		}
		build.setManorFarm(getManorBuild(userByToken, BuildDictionary.FARM.getTypeId()));
		build.setManorFishpond(getManorBuild(userByToken, BuildDictionary.FISHPOND.getTypeId()));
		build.setManorPasture(getManorBuild(userByToken, BuildDictionary.PASTURE.getTypeId()));
		build.setManorOrchard(getManorBuild(userByToken, BuildDictionary.ORCHARD.getTypeId()));
		build.setManorVegetableGarden(getManorBuild(userByToken, BuildDictionary.VEGETABLEGARDEN.getTypeId()));
		build.setManorResearch(getManorBuild(userByToken, BuildDictionary.FOODCENTER.getTypeId()));
		build.setManorRestaurant(getManorBuild(userByToken, BuildDictionary.RESTAURANT.getTypeId()));
		build.setManorOrderCenter(getManorBuild(userByToken, BuildDictionary.ORDERCENTER.getTypeId()));

		build.setFoodID(researchRedisService.getFoodId(userByToken.getId()));

		// 建筑相关任务， 业务待分离
		taskService.updateTaskNumAutoAddtion(userByToken.getId(), TaskMethod.X_BUILD_Y_LEVEL.getTaskCondition(),
				map(build));

		return build;
	}

	public static Map<Integer, Integer> map(Build build) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(build.getFarm().getBuildId(), build.getFarm().getLevel());
		map.put(build.getFishpond().getBuildId(), build.getFishpond().getLevel());
		map.put(build.getFoodcenter().getBuildId(), build.getFoodcenter().getLevel());
		map.put(build.getVegetableGarden().getBuildId(), build.getVegetableGarden().getLevel());
		map.put(1, build.getRestaurant().getLevel());// 1为餐厅
		map.put(build.getOrchard().getBuildId(), build.getOrchard().getLevel());
		map.put(8, build.getOrderCenter().getLevel());// 8 为订单中心
		map.put(build.getPasture().getBuildId(), build.getPasture().getLevel());
		return map;
	}

	private Manor getManorBuild(User user, Integer buildId) {
		Manor manor = manorService.selectByPrimaryKey(new ManorKey(user.getId(), buildId));
		String needgold = InitConfig.getInstance().getManorMap().get(Integer.parseInt(manor.getBuildId() + "" + manor.getLevel())).getNeedgold();
		manor.setNeedGold(needgold);
		ConfigManor configManor = InitConfig.getInstance().getManorMap().get(Integer.parseInt(manor.getBuildId() + "" + (manor.getLevel()+1)));
		manor.setNextLevelUp(configManor);
		if (BuildDictionary.FOODCENTER.getTypeId().equals(buildId)) {
			Foodcenter foodcenter = foodcenterService.selectByPrimaryKey(user.getId());
    		ConfigCookcenter configCookcenter = InitConfig.getInstance().getCookcenterMap().get(foodcenter.getLevel()+1);
    		manor.setNextLevelAttr(configCookcenter);
		}else if (BuildDictionary.RESTAURANT.getTypeId().equals(buildId)) {
			Restaurant restaurant = restaurantService.getRestaurant(user.getId());
			ConfigRestaurant configRestaurant = InitConfig.getInstance().getRestaurantMap().get(restaurant.getLevel()+1);
			manor.setNextLevelAttr(configRestaurant);
		}else if (BuildDictionary.ORDERCENTER.getTypeId().equals(buildId)) {
			manor.setNextLevelAttr(null);
		}else {
			manor.setNextLevelAttr(getConfigResources(user,buildId));
		}
		return manor;
	}
    private ConfigResources getConfigResources(User user, Integer buildId) {
    	
    	if (BuildDictionary.FARM.getTypeId().equals(buildId)) {
    		Farm farm = farmService.selectByPrimaryKey(user.getId());
    		Integer index =Integer.parseInt(buildId + "" + (farm.getLevel()+1));
    		return InitConfig.getInstance().getResourcesMap().get(index);
		}
    	if (BuildDictionary.ORCHARD.getTypeId().equals(buildId)) {
    		Orchard orchard = orchardService.selectByPrimaryKey(user.getId());
    		Integer index =Integer.parseInt(buildId + "" + (orchard.getLevel()+1));
    		return InitConfig.getInstance().getResourcesMap().get(index);
    	}
    	if (BuildDictionary.VEGETABLEGARDEN.getTypeId().equals(buildId)) {
    		VegetableGarden vegetableGarden = vegetableGardenService.selectByPrimaryKey(user.getId());
    		Integer index =Integer.parseInt(buildId + "" + (vegetableGarden.getLevel()+1));
    		return InitConfig.getInstance().getResourcesMap().get(index);
    	}
    	if (BuildDictionary.PASTURE.getTypeId().equals(buildId)) {
    		Pasture pasture = pastureService.selectByPrimaryKey(user.getId());
    		Integer index =Integer.parseInt(buildId + "" + (pasture.getLevel()+1));
    		return InitConfig.getInstance().getResourcesMap().get(index);
    	}
    	if (BuildDictionary.FISHPOND.getTypeId().equals(buildId)) {
    		Fishpond fishpond = fishpondService.selectByPrimaryKey(user.getId());
    		Integer index =Integer.parseInt(buildId + "" + (fishpond.getLevel()+1));
    		return InitConfig.getInstance().getResourcesMap().get(index);
    	}
		return null;
    	
    }
	@Override
	public Farm getFarm(User user) {
		return farmService.selectByPrimaryKey(user.getId());
	}

	@Override
	public Fishpond getFishpond(User user) {
		return fishpondService.selectByPrimaryKey(user.getId());
	}

	@Override
	public Pasture getPasture(User user) {
		return pastureService.selectByPrimaryKey(user.getId());
	}

	@Override
	public Orchard getOrchard(User user) {
		return orchardService.selectByPrimaryKey(user.getId());
	}

	@Override
	public VegetableGarden getVegetableGarden(User user) {
		return vegetableGardenService.selectByPrimaryKey(user.getId());
	}

	// TODO 此处代码需要逻辑优化，重复代码太多 @Y.Y
	@Override
	public Build isAllowUpgrade(String token, Integer type)
			throws GoldNoEnoughException, BuildUpgradingException, BuildMaxLevelException {
		User user =userService.getUserByToken(token);
		List<Integer> listDto = new ArrayList<Integer>();
		List<ConfigManor> config = InitConfig.getInstance().getManorListMap().get(type);
		for (ConfigManor configManor : config) {
			listDto.add(configManor.getLevel());
		}
		int maxLevel = Collections.max(listDto);

		Build buildAll = getBuildAll(user);
		// 7为美食中心
		if (buildAll.getFoodcenter().getlefttime() != 0 && type == 7) {
			throw new BuildUpgradingException();
		} else {
			// 判断是否允许升级
			Manor record = manorService.selectByPrimaryKey(new ManorKey(user.getId(), type));
			// 升到满级无法再升级
			if (record.getLevel() == maxLevel) {
				throw new BuildMaxLevelException();
			}
			if (record.getStartTime() == 0 && record.getLeftTime() == 0) {
				// 扣除升级材料
				String needgold = InitConfig.getInstance().getManorMap().get(Integer.parseInt(record.getBuildId() + "" + record.getLevel())).getNeedgold();
				List<Reused> needlist =StringUtil.getMetarial(needgold);
				int enough = fridgeService.isEnough(user.getId(), needlist);
				if (enough==0) {
					fridgeService.expendMaterial(token, needlist);
					record.setStartTime(System.currentTimeMillis());
					manorService.updateByPrimaryKey(record);
				} else {
					throw new GoldNoEnoughException(enough+"");
				}
			} else {
				throw new BuildUpgradingException();
			}
		}
		return getBuildAll(user);
	}

	public void refresh(User user, Integer type, Build buildAll) {
		Map<Integer, ConfigCookcenter> configCookcenterMap = InitConfig.getInstance().getCookcenterMap();
		switch (type) {
		// 1：餐厅
		case 1:
			Restaurant restaurant = buildAll.getRestaurant();
			ConfigRestaurant configByDup = InitConfig.getInstance().getRestaurantMap().get(restaurant.getLevel() + 1);
			restaurantService.updateByPrimaryKeySelective(new Restaurant(user.getId(), restaurant.getCustomNum(),
					configByDup.getLevel(), configByDup.getArea(), configByDup.getLovelimit(),
					configByDup.getMaxemploy(), configByDup.getMaxchef()));
			System.out.println("refresh：1" + user.getGold());

			// 更新时间
			ConfigManor configManor1 = InitConfig.getInstance().getManorMap()
					.get(Integer.parseInt(1 + "" + (restaurant.getLevel() + 1)));
			manorService.updateByPrimaryKey(new Manor(user.getId(), configManor1.getId(), configManor1.getBuildid(),
					configManor1.getLevel(), configManor1.getNeedgold(), configManor1.getTime().longValue(),
					LEVEL_STATE.INIT_LEVEL.getleftTime().longValue(), 0L));

			break;
		// 2:农场
		case 2:
			// 建筑的ID。1，餐厅；2，农场；3，果园；4，菜园；5，牧场；6，鱼塘；7，美食中心；8，订单中心；9，交易中心
			Farm farm = buildAll.getFarm();
			ConfigResources farmConfig = InitConfig.getInstance().getResourcesMap()
					.get(Integer.parseInt(farm.getBuildId() + "" + (farm.getLevel() + 1)));
			farmService.updateByPrimaryKeySelective(new Farm(user.getId(), farmConfig.getLevel(), farmConfig.getId(),
					farmConfig.getBuildid(), farmConfig.getOutput(), farmConfig.getMaxcapacity(), null));
			System.out.println("refresh：2" + user.getGold());

			// 更新时间
			ConfigManor configManor2 = InitConfig.getInstance().getManorMap()
					.get(Integer.parseInt(2 + "" + (farm.getLevel() + 1)));
			manorService.updateByPrimaryKey(new Manor(user.getId(), configManor2.getId(), configManor2.getBuildid(),
					configManor2.getLevel(), configManor2.getNeedgold(), configManor2.getTime().longValue(),
					LEVEL_STATE.INIT_LEVEL.getleftTime().longValue(), 0L));
			break;
		// 3:果园
		case 3:
			// 建筑的ID。1，餐厅；2，农场；3，果园；4，菜园；5，牧场；6，鱼塘；7，美食中心；8，订单中心；9，交易中心
			Orchard orchard = buildAll.getOrchard();
			ConfigResources orchardConfig = InitConfig.getInstance().getResourcesMap()
					.get(Integer.parseInt(orchard.getBuildId() + "" + (orchard.getLevel() + 1)));
			orchardService.updateByPrimaryKeySelective(new Orchard(user.getId(), orchardConfig.getLevel(),
					orchardConfig.getId(), orchardConfig.getBuildid(), orchardConfig.getOutput(),
					orchardConfig.getMaxcapacity(), null));
			System.out.println("refresh：3" + user.getGold());

			// 更新时间
			ConfigManor configMano3 = InitConfig.getInstance().getManorMap()
					.get(Integer.parseInt(3 + "" + (orchard.getLevel() + 1)));
			manorService.updateByPrimaryKey(new Manor(user.getId(), configMano3.getId(), configMano3.getBuildid(),
					configMano3.getLevel(), configMano3.getNeedgold(), configMano3.getTime().longValue(),
					LEVEL_STATE.INIT_LEVEL.getleftTime().longValue(), 0L));
			break;
		// 4:菜园
		case 4:
			// 建筑的ID。1，餐厅；2，农场；3，果园；4，菜园；5，牧场；6，鱼塘；7，美食中心；8，订单中心；9，交易中心
			VegetableGarden vegetableGarden = buildAll.getVegetableGarden();
			ConfigResources vegetableGardenConfig = InitConfig.getInstance().getResourcesMap()
					.get(Integer.parseInt(vegetableGarden.getBuildId() + "" + (vegetableGarden.getLevel() + 1)));
			vegetableGardenService.updateByPrimaryKeySelective(new VegetableGarden(user.getId(),
					vegetableGardenConfig.getLevel(), vegetableGardenConfig.getId(), vegetableGardenConfig.getBuildid(),
					vegetableGardenConfig.getOutput(), vegetableGardenConfig.getMaxcapacity(), null));
			System.out.println("refresh：4" + user.getGold());

			// 更新时间
			ConfigManor configMano4 = InitConfig.getInstance().getManorMap()
					.get(Integer.parseInt(4 + "" + (vegetableGarden.getLevel() + 1)));
			manorService.updateByPrimaryKey(new Manor(user.getId(), configMano4.getId(), configMano4.getBuildid(),
					configMano4.getLevel(), configMano4.getNeedgold(), configMano4.getTime().longValue(),
					LEVEL_STATE.INIT_LEVEL.getleftTime().longValue(), 0L));
			break;
		// 5:牧场
		case 5:
			// 建筑的ID。1，餐厅；2，农场；3，果园；4，菜园；5，牧场；6，鱼塘；7，美食中心；8，订单中心；9，交易中心
			Pasture pasture = buildAll.getPasture();
			ConfigResources pastureConfig = InitConfig.getInstance().getResourcesMap()
					.get(Integer.parseInt(pasture.getBuildId() + "" + (pasture.getLevel() + 1)));
			pastureService.updateByPrimaryKeySelective(new Pasture(user.getId(), pastureConfig.getLevel(),
					pastureConfig.getId(), pastureConfig.getBuildid(), pastureConfig.getOutput(),
					pastureConfig.getMaxcapacity(), null));
			System.out.println("refresh：5" + user.getGold());
			// 更新时间
			ConfigManor configMano5 = InitConfig.getInstance().getManorMap()
					.get(Integer.parseInt(5 + "" + (pasture.getLevel() + 1)));
			manorService.updateByPrimaryKey(new Manor(user.getId(), configMano5.getId(), configMano5.getBuildid(),
					configMano5.getLevel(), configMano5.getNeedgold(), configMano5.getTime().longValue(),
					LEVEL_STATE.INIT_LEVEL.getleftTime().longValue(), 0L));
			break;
		// 6:鱼塘
		case 6:
			// 建筑的ID。1，餐厅；2，农场；3，果园；4，菜园；5，牧场；6，鱼塘；7，美食中心；8，订单中心；9，交易中心
			Fishpond fishpond = buildAll.getFishpond();
			ConfigResources fishpondConfig = InitConfig.getInstance().getResourcesMap()
					.get(Integer.parseInt(fishpond.getBuildId() + "" + (fishpond.getLevel() + 1)));
			fishpondService.updateByPrimaryKeySelective(new Fishpond(user.getId(), fishpondConfig.getLevel(),
					fishpondConfig.getId(), fishpondConfig.getBuildid(), fishpondConfig.getOutput(),
					fishpondConfig.getMaxcapacity(), null));
			System.out.println("refresh：6" + user.getGold());

			// 更新时间
			ConfigManor configMano6 = InitConfig.getInstance().getManorMap()
					.get(Integer.parseInt(6 + "" + (fishpond.getLevel() + 1)));
			manorService.updateByPrimaryKey(new Manor(user.getId(), configMano6.getId(), configMano6.getBuildid(),
					configMano6.getLevel(), configMano6.getNeedgold(), configMano6.getTime().longValue(),
					LEVEL_STATE.INIT_LEVEL.getleftTime().longValue(), 0L));
			break;
		// 7: 美食中心
		case 7:
			ConfigCookcenter configCookcenter = configCookcenterMap.get(buildAll.getFoodcenter().getLevel() + 1);
			ConfigCookcenter configCookcenterOld = configCookcenterMap.get(buildAll.getFoodcenter().getLevel());
			foodcenterService.updateByPrimaryKeySelective(new Foodcenter(user.getId(),
					buildAll.getFoodcenter().getLevel() + 1, 7, 0, buildAll.getFoodcenter().getPower()
							+ configCookcenter.getPower() - configCookcenterOld.getPower())); // 7类型 ，0 开始时间，
			ConfigManor configMano7 = InitConfig.getInstance().getManorMap()
					.get(Integer.parseInt(7 + "" + (buildAll.getFoodcenter().getLevel() + 1)));
			manorService.updateByPrimaryKey(new Manor(user.getId(), configMano7.getId(), configMano7.getBuildid(),
					configMano7.getLevel(), configMano7.getNeedgold(), configMano7.getTime().longValue(),
					LEVEL_STATE.INIT_LEVEL.getleftTime().longValue(), 0L));
			break;
		case 8:
			OrderCenter orderCenter = buildAll.getOrderCenter();
			// 更新时间
			ConfigManor configManor8 = InitConfig.getInstance().getManorMap()
					.get(Integer.parseInt(8 + "" + (orderCenter.getLevel() + 1)));
			manorService.updateByPrimaryKey(new Manor(user.getId(), configManor8.getId(), configManor8.getBuildid(),
					configManor8.getLevel(), configManor8.getNeedgold(), configManor8.getTime().longValue(),
					LEVEL_STATE.INIT_LEVEL.getleftTime().longValue(), 0L));

			System.out.println("refresh：8" + user.getGold());
			orderCenter.setLevel(orderCenter.getLevel() + 1);
			orderCenterService.updateByPrimaryKey(orderCenter);
		}
	}

	@Override
	public Restaurant getRestaurant(User user) {
		return restaurantService.selectByPrimaryKey(user.getId());
	}

	// TODO 此处需优化重复代码
	@Override
	public void buildInit(User userInit) {

		farmService.initFarm(userInit.getId());
		fishpondService.initFishpond(userInit.getId());
		orchardService.initOrchard(userInit.getId());
		pastureService.initPasture(userInit.getId());
		vegetableGardenService.initVegetableGarden(userInit.getId());
		foodcenterService.initFoodcenter(userInit.getId());
		orderCenterService.initOrderCenter(userInit.getId());
		generatorService.initGenerator(userInit.getId());
		manorService.initManor(userInit.getId());
	}
	@Override
	public Foodcenter getFoodcenter(User user) {
		Foodcenter selectByPrimaryKey = foodcenterService.selectByPrimaryKey(user.getId());
		// TODO 设置菜品研究的时间（从redis中获取）, 需持久化到mysql Y.Y
		int lefttime = researchRedisService.getExpire(user.getId()).intValue();
		if (lefttime == -2 || lefttime == -1) {
			lefttime = 0;
		}
		ConfigCookcenter configCookcenter = InitConfig.getInstance().getCookcenterMap().get(selectByPrimaryKey.getLevel());
		selectByPrimaryKey.setMaxPower(configCookcenter.getPower());
		selectByPrimaryKey.setlefttime(lefttime);
		selectByPrimaryKey.setMakeLevel(configCookcenter.getMakelevel());
		return selectByPrimaryKey;
	}

	@Override
	public Fridge collect(User userByToken, Integer type) {
		getBuildAll(userByToken);

		Fridge fridge = fridgeService.selectByPrimaryKey(userByToken.getId());
		Generator generator = generatorService.selectByPrimaryKey(userByToken.getId());

		// 判断
		Manor record = manorService.selectByPrimaryKey(new ManorKey(userByToken.getId(), type));
		// 完成升级状态，可收取
		if (record.getStartTime() == 0 && record.getLeftTime() == 0) {
			switch (type) {
			case 2:
				// 判断是否满一个
				if (!(generator.getRiceTime() - generator.getRiceStartTime() >= 60 * 1000)) {
					return fridge;
				}

				Farm selectByPrimaryKey = farmService.selectByPrimaryKey(userByToken.getId());

				fridge.setRice(
						(fridge.getRice() + selectByPrimaryKey.getRice() >= fridge.getIndexNum()) ? fridge.getIndexNum()
								: (fridge.getRice() + selectByPrimaryKey.getRice()));
				fridgeService.updateFridge(fridge);

				Farm farm = new Farm();
				farm.setId(userByToken.getId());
				farm.setRice(0);
				farmService.updateByPrimaryKeySelective(farm);

				generator.setRiceTime(System.currentTimeMillis());
				generator.setRiceStartTime(System.currentTimeMillis());

				generatorService.updateByPrimaryKey(generator);

				break;
			case 3:
				// 判断是否满一个
				if (!(generator.getBananaTime() - generator.getBananaStartTime() >= 60 * 1000)) {
					return fridge;
				}

				Orchard selectByPrimaryKey2 = orchardService.selectByPrimaryKey(userByToken.getId());

				fridge.setBanana((fridge.getBanana() + selectByPrimaryKey2.getBanana() >= fridge.getIndexNum())
						? fridge.getIndexNum()
						: (fridge.getBanana() + selectByPrimaryKey2.getBanana()));
				fridgeService.updateFridge(fridge);

				Orchard orchard = new Orchard();
				orchard.setId(userByToken.getId());
				orchard.setBanana(0);
				orchardService.updateByPrimaryKeySelective(orchard);

				generator.setBananaTime(System.currentTimeMillis());
				generator.setBananaStartTime(System.currentTimeMillis());
				generatorService.updateByPrimaryKey(generator);

				break;

			case 4:
				// 判断是否满一个
				if (!(generator.getVegetableTime() - generator.getVegetableStartTime() >= 60 * 1000)) {
					return fridge;
				}

				VegetableGarden selectByPrimaryKey3 = vegetableGardenService.selectByPrimaryKey(userByToken.getId());

				fridge.setVegetable((fridge.getVegetable() + selectByPrimaryKey3.getVegetable() >= fridge.getIndexNum())
						? fridge.getIndexNum()
						: (fridge.getVegetable() + selectByPrimaryKey3.getVegetable()));
				fridgeService.updateFridge(fridge);

				VegetableGarden vegetablegarden = new VegetableGarden();
				vegetablegarden.setId(userByToken.getId());
				vegetablegarden.setVegetable(0);
				vegetableGardenService.updateByPrimaryKeySelective(vegetablegarden);

				generator.setVegetableTime(System.currentTimeMillis());
				generator.setVegetableStartTime(System.currentTimeMillis());
				generatorService.updateByPrimaryKey(generator);

				break;

			case 5:
				// 判断是否满一个
				if (!(generator.getMeatTime() - generator.getMeatStartTime() >= 60 * 1000)) {
					return fridge;
				}

				Pasture selectByPrimaryKey4 = pastureService.selectByPrimaryKey(userByToken.getId());

				fridge.setMeat((fridge.getMeat() + selectByPrimaryKey4.getMeat() >= fridge.getIndexNum())
						? fridge.getIndexNum()
						: (fridge.getMeat() + selectByPrimaryKey4.getMeat()));
				fridgeService.updateFridge(fridge);

				Pasture pasture = new Pasture();
				pasture.setId(userByToken.getId());
				pasture.setMeat(0);
				pastureService.updateByPrimaryKeySelective(pasture);

				generator.setMeatTime(System.currentTimeMillis());
				generator.setMeatStartTime(System.currentTimeMillis());
				generatorService.updateByPrimaryKey(generator);

				break;

			case 6:
				// 判断是否满一个
				if (!(generator.getFishTime() - generator.getFishStartTime() >= 60 * 1000)) {
					return fridge;
				}

				Fishpond selectByPrimaryKey5 = fishpondService.selectByPrimaryKey(userByToken.getId());

				fridge.setFish((fridge.getFish() + selectByPrimaryKey5.getFish() >= fridge.getIndexNum())
						? fridge.getIndexNum()
						: (fridge.getFish() + selectByPrimaryKey5.getFish()));
				fridgeService.updateFridge(fridge);

				Fishpond fishpond = new Fishpond();
				fishpond.setId(userByToken.getId());
				fishpond.setFish(0);
				fishpondService.updateByPrimaryKeySelective(fishpond);

				generator.setFishTime(System.currentTimeMillis());
				generator.setFishStartTime(System.currentTimeMillis());
				generatorService.updateByPrimaryKey(generator);

				break;
			}
		} else {
			try {
				throw new BuildUpgradingException();
			} catch (BuildUpgradingException e) {
				e.printStackTrace();
			}
		}

		return fridge;

	}

	@Override
	public void setFarm(Farm farm, Generator generator, Build build) {
		// 更新食材信息
		Long generatorTime = (System.currentTimeMillis() - generator.getRiceTime()) / (60 * 1000); // 取分钟
		Long generatorUnitTime = (System.currentTimeMillis() - generator.getRiceStartTime()) % (60 * 1000); // 取分钟
		Long additionNum = farm.getRice() + generatorTime * farm.getOutput(); // 增加数值

		// 如果少于一小时即刷新资源建筑
		farm.setRice(additionNum >= farm.getMaxCapacity() ? farm.getMaxCapacity() : additionNum.intValue());// 不能爆仓
		farmService.updateByPrimaryKey(farm);
		// 取余数时间放在生产信息里
		Generator generatorFarm = generatorService.selectByPrimaryKey(farm.getId());
		generatorFarm.setRiceTime(System.currentTimeMillis() - generatorUnitTime);
		generatorService.updateByPrimaryKey(generatorFarm);

		build.setRiceStartTime(generatorUnitTime);
	}

	@Override
	public void setFishpond(Fishpond fishpond, Generator generator, Build build) {
		// 更新食材信息
		Long generatorTime = (System.currentTimeMillis() - generator.getFishTime()) / (60 * 1000); // 取分钟
		Long generatorUnitTime = (System.currentTimeMillis() - generator.getFishStartTime()) % (60 * 1000); // 取分钟
		Long additionNum = fishpond.getFish() + generatorTime * fishpond.getOutput(); // 增加数值

		fishpond.setFish(additionNum >= fishpond.getMaxCapacity() ? fishpond.getMaxCapacity() : additionNum.intValue());// 不能爆仓
		fishpondService.updateByPrimaryKey(fishpond);
		// 取余数时间放在生产信息里
		Generator generatorFishpond = generatorService.selectByPrimaryKey(fishpond.getId());
		generatorFishpond.setFishTime(System.currentTimeMillis() - generatorUnitTime);
		generatorService.updateByPrimaryKey(generatorFishpond);

		build.setFishStartTime(generatorUnitTime);

	}

	@Override
	public void setPasture(Pasture pasture, Generator generator, Build build) {
		// 更新食材信息
		Long generatorTime = (System.currentTimeMillis() - generator.getMeatTime()) / (60 * 1000); // 取分钟
		Long generatorUnitTime = (System.currentTimeMillis() - generator.getMeatStartTime()) % (60 * 1000); // 取分钟
		Long additionNum = pasture.getMeat() + generatorTime * pasture.getOutput(); // 增加数值

		pasture.setMeat(additionNum >= pasture.getMaxCapacity() ? pasture.getMaxCapacity() : additionNum.intValue());// 不能爆仓
		pastureService.updateByPrimaryKey(pasture);
		// 取余数时间放在生产信息里
		Generator generatorPasture = generatorService.selectByPrimaryKey(pasture.getId());
		generatorPasture.setMeatTime(System.currentTimeMillis() - generatorUnitTime);
		generatorService.updateByPrimaryKey(generatorPasture);

		build.setMeatStartTime(generatorUnitTime);
	}

	@Override
	public void setOrchard(Orchard orchard, Generator generator, Build build) {
		// 更新食材信息
		Long generatorTime = (System.currentTimeMillis() - generator.getBananaTime()) / (60 * 1000); // 取分钟
		Long generatorUnitTime = (System.currentTimeMillis() - generator.getBananaStartTime()) % (60 * 1000); // 取分钟
		Long additionNum = orchard.getBanana() + generatorTime * orchard.getOutput(); // 增加数值

		orchard.setBanana(additionNum >= orchard.getMaxCapacity() ? orchard.getMaxCapacity() : additionNum.intValue());// 不能爆仓
		orchardService.updateByPrimaryKey(orchard);
		// 取余数时间放在生产信息里
		Generator generatorOrchard = generatorService.selectByPrimaryKey(orchard.getId());
		generatorOrchard.setBananaTime(System.currentTimeMillis() - generatorUnitTime);
		generatorService.updateByPrimaryKey(generatorOrchard);

		build.setBananaStartTime(generatorUnitTime);
	}

	@Override
	public void setVegetableGarden(VegetableGarden vegetableGarden, Generator generator, Build build) {
		// 更新食材信息
		Long generatorTime = (System.currentTimeMillis() - generator.getVegetableTime()) / (60 * 1000); // 取分钟
		Long generatorUnitTime = (System.currentTimeMillis() - generator.getVegetableStartTime()) % (60 * 1000); // 取分钟
		Long additionNum = vegetableGarden.getVegetable() + generatorTime * vegetableGarden.getOutput(); // 增加数值

		vegetableGarden.setVegetable(additionNum >= vegetableGarden.getMaxCapacity() ? vegetableGarden.getMaxCapacity()
				: additionNum.intValue());// 不能爆仓
		vegetableGardenService.updateByPrimaryKey(vegetableGarden);
		// 取余数时间放在生产信息里
		Generator generatorVegetableGarden = generatorService.selectByPrimaryKey(vegetableGarden.getId());
		generatorVegetableGarden.setVegetableTime(System.currentTimeMillis() - generatorUnitTime);
		generatorService.updateByPrimaryKey(generatorVegetableGarden);

		build.setVegetableStartTime(generatorUnitTime);
	}

	@Override
	public Build accelerateAllowUpgrade(User userByToken, Integer type)
			throws DiamondNoEnoughException, NoUpgradingException {
		// 判断是否允许升级
		Manor record = manorService.selectByPrimaryKey(new ManorKey(userByToken.getId(), type));
		if (record.getStartTime() != 0 && record.getLeftTime() != 0) {
			// 扣除钻石
			Long diamond = (System.currentTimeMillis() - record.getStartTime()) / 1000;
			if (userByToken.getDiamond() - diamond >= 0) {
				userByToken.setDiamond(userByToken.getDiamond() - diamond.intValue());
				userService.updateUser(userByToken);
				System.out.println("加速：" + userByToken.getGold());
				refresh(userByToken, type, getBuildAll(userByToken));
			} else {
				throw new DiamondNoEnoughException();
			}
		} else {
			throw new NoUpgradingException();
		}

		return getBuildAll(userByToken);
	}

	@Override
	public Build quitAllowUpgrade(String token, Integer type) throws NoUpgradingException {
		User user =userService.getUserByToken(token);
		Manor record = manorService.selectByPrimaryKey(new ManorKey(user.getId(), type));
		if (record.getStartTime() != 0 && record.getLeftTime() != 0) {
			record.setLeftTime(0L);
			record.setStartTime(0L);
			manorService.updateByPrimaryKey(record);
			String needgold = InitConfig.getInstance().getManorMap().get(Integer.parseInt(record.getBuildId() + "" + record.getLevel())).getNeedgold();
			List<Reused> needlist =StringUtil.getMetarial(needgold);
			fridgeService.addMaterial(token, needlist);
		} else {
			throw new NoUpgradingException();
		}
		return getBuildAll(user);
	}
}
