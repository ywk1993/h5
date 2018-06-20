package com.muzhi.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.muzhi.dao.FoodcenterDao;
import com.muzhi.dao.PropDao;
import com.muzhi.model.Constant;
import com.muzhi.model.CookBook;
import com.muzhi.model.Foodcenter;
import com.muzhi.model.Result;
import com.muzhi.model.Reused;
import com.muzhi.model.TaskMethod;
import com.muzhi.model.User;
import com.muzhi.model.configbean.ConfigCookcenter;
import com.muzhi.model.configbean.ConfigFood;
import com.muzhi.model.configbean.ConfigResearch;
import com.muzhi.model.state.LEVEL_STATE;
import com.muzhi.model.vo.Build;
import com.muzhi.model.vo.Draw;
import com.muzhi.model.vo.Draws;
import com.muzhi.model.vo.ResearchAll;
import com.muzhi.model.vo.ResearchFood;
import com.muzhi.model.vo.ResearchMenu;
import com.muzhi.model.vo.ResearchState;
import com.muzhi.redis.service.ResearchRedisService;
import com.muzhi.redis.service.UserRedisService;
import com.muzhi.service.BuildService;
import com.muzhi.service.CookBookSerivce;
import com.muzhi.service.FoodService;
import com.muzhi.service.FoodcenterService;
import com.muzhi.service.FridgeService;
import com.muzhi.service.TaskService;
import com.muzhi.service.UserService;
import com.muzhi.service.config.InitConfig;
import com.muzhi.task.MakeStateJobManager;
import com.muzhi.util.ResultUtil;
import com.muzhi.util.StringUtil;

@Service
public class FoodcenterServiceImpl implements FoodcenterService {
	private static Integer CHINESE = 1;
	private static final Integer WEST = 2;
	private static final Integer BARBECUE = 3;
	private static final Integer DESSERT = 4;
	private static final Integer DRINK = 5;
	@Autowired
	FoodcenterDao FoodcenterDao;
	@Autowired
	UserService userService;
	@Autowired
	CookBookSerivce cookBookSerivce;
	@Autowired
	FridgeService fridgeService;
	@Autowired
	ResearchRedisService researchRedisService;
	@Autowired
	MakeStateJobManager makeStateJobManager;
	@Autowired
	BuildService buildService;
	@Autowired
	UserRedisService userRedisService;
	@Autowired
	TaskService taskService;
	@Autowired
	FoodService foodService;
	@Autowired
	PropDao propDao;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return FoodcenterDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Foodcenter record) {
		return FoodcenterDao.insert(record);
	}

	@Override
	public int insertSelective(Foodcenter record) {
		return FoodcenterDao.insertSelective(record);
	}

	@Override
	public Foodcenter selectByPrimaryKey(Integer id) {
		return FoodcenterDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Foodcenter record) {
		return FoodcenterDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Foodcenter record) {
		return FoodcenterDao.updateByPrimaryKey(record);
	}

	@Override
	public Result getBookResearch(String token) {
		User user = userService.getUserByToken(token);
		List<CookBook> cookBooks = cookBookSerivce.getListByUserId(user.getId());
		Map<Integer, ConfigResearch> researchMap = InitConfig.getInstance().getResearchMap();

		// 组装数据
		List<ResearchFood> chineseList = new ArrayList<ResearchFood>();
		List<ResearchFood> westList = new ArrayList<ResearchFood>();
		List<ResearchFood> barbecueList = new ArrayList<ResearchFood>();
		List<ResearchFood> dessertList = new ArrayList<ResearchFood>();
		List<ResearchFood> drinkList = new ArrayList<ResearchFood>();
		Integer totalStrength = userService.getTotalStrength(user);
		
		for (CookBook cookBook : cookBooks) {
			
			ConfigFood configFood = InitConfig.getInstance().getFoodMap().get(cookBook.getFoodId());
			Integer maxResearchlevel =InitConfig.getInstance().getResearchListMap().get(cookBook.getFoodId()).size();
			ResearchFood researchFood = new ResearchFood();
			researchFood.setFoodID(cookBook.getFoodId());
			researchFood.setFoodLevel(configFood.getFoodlevel());
			researchFood.setFoodQulify(cookBook.getFoodQualify());
			researchFood.setResearchLevel(cookBook.getResearchLevel());
			if (cookBook.getResearchLevel()>=maxResearchlevel) {
				researchFood.setResearchStatus(2);
				researchFood.setRequestStrength(0);
				researchFood.setCurrencyList(StringUtil.getMetarial("0,0,0"));
			}else {
				ConfigResearch configResearch = researchMap
						.get(Integer.parseInt(cookBook.getFoodId() + "" + (cookBook.getResearchLevel()+1)));
				researchFood.setRequestStrength(configResearch.getStrengh());
				researchFood.setCurrencyList(StringUtil.getMetarial(configResearch.getReward()));
				if (configResearch.getStrengh() > totalStrength) {
					researchFood.setResearchStatus(1);
				}else {
					researchFood.setResearchStatus(0);
				}
			}
			Integer foodtype = configFood.getFoodtype();
			if (CHINESE.equals(foodtype)) {
				chineseList.add(researchFood);
			} else if (WEST.equals(foodtype)) {
				westList.add(researchFood);
			} else if (BARBECUE.equals(foodtype)) {
				barbecueList.add(researchFood);
			} else if (DESSERT.equals(foodtype)) {
				dessertList.add(researchFood);
			} else if (DRINK.equals(foodtype)) {
				drinkList.add(researchFood);
			}
		}

		List<ResearchMenu> menuList = new ArrayList<ResearchMenu>();
		menuList.add(new ResearchMenu(CHINESE, chineseList));
		menuList.add(new ResearchMenu(WEST, westList));
		menuList.add(new ResearchMenu(BARBECUE, barbecueList));
		menuList.add(new ResearchMenu(DESSERT, dessertList));
		menuList.add(new ResearchMenu(DRINK, drinkList));

		Foodcenter foodcenter = FoodcenterDao.selectByPrimaryKey(user.getId());
		ConfigCookcenter configCookcenter = InitConfig.getInstance().getCookcenterMap().get(foodcenter.getLevel());

		// 研究力任务, 业务待分离
		taskService.updateTaskNum(user.getId(), TaskMethod.RESEARCH_Y.getTaskCondition(), foodcenter.getPower());
		
		return ResultUtil.success(new ResearchAll(menuList, foodcenter.getPower(), configCookcenter.getPower(),foodService.getFood(user.getId()),propDao.getPropByUid(user.getId())));

	}

	@Override
	public Result doBookResearch(String token, int foodID) {
		User user = userService.getUserByToken(token);
		CookBook cookbook = cookBookSerivce.getOneCookbook(user.getId(), foodID);
		// 品质已经达到满级
		if (cookbook.getFoodQualify() >= Constant.QUALITYLEVEL) {
			return ResultUtil.error(2002, "品质已经达到满级");
		}	
		// 研究等级到满级
		Integer maxResearchlevel =InitConfig.getInstance().getResearchListMap().get(cookbook.getFoodId()).size();
		if (cookbook.getResearchLevel()>=maxResearchlevel) {
			return ResultUtil.error(2003, "研究等级到满级");
		}		
		ConfigResearch configResearch = InitConfig.getInstance().getResearchMap().get(Integer.parseInt(cookbook.getFoodId() + "" + (cookbook.getResearchLevel() + 1)));
		
		boolean isRemain = makeStateJobManager.isTaskRemain(user.getId() + "_cookCenter");
		if (isRemain) {
			return ResultUtil.error(2007, "正在研究中");
		}
		Build build = buildService.getBuildAll(user);
		// 当前美食中心正在升级中
		Foodcenter foodcenters = build.getFoodcenter();
		if (foodcenters.getlefttime()!=0) {
			return ResultUtil.error(2004, "当前美食中心正在升级中");
		}
		// 厨力不足
		if (configResearch.getStrengh() > userService.getTotalStrength(user)) {
			return ResultUtil.error(2001, "厨力不足");
		}
		// 食材不足 修改2018-5-28
		List<Reused> list = StringUtil.getMetarial(configResearch.getReward());
		int materialenough = fridgeService.isEnough(user.getId(), list);// 食材足够返回true 否则返回false
		if (materialenough!=0) {
			return ResultUtil.error(materialenough, "材料不足",list);
		}
		// 当前研究力是否足够
		Foodcenter foodcenter = FoodcenterDao.selectByPrimaryKey(user.getId());
		if (foodcenter.getPower() < configResearch.getUsepower()) {
			return ResultUtil.error(2005, "当前研究力不足");
		}
		// 食材减少 研究力减少
		fridgeService.expendMaterial(token, list);
		foodcenter.setPower(foodcenter.getPower() - configResearch.getUsepower());
		FoodcenterDao.updateByPrimaryKey(foodcenter);
		
		// 存储倒计时时间
		researchRedisService.setTime(user.getId());
		researchRedisService.setFoodId(user.getId(), foodID);
		researchRedisService.expireToken(user.getId(), configResearch.getTime() * 1000);

		// 倒计时 统一设置下状态 开发品质加一
		cookbook.setFoodQualify(cookbook.getFoodQualify() + 1);
		cookbook.setResearchLevel(cookbook.getResearchLevel() + 1);
		cookbook.setStatus(0);
		makeStateJobManager.ResearchStateChange(cookbook, user.getId(), configResearch.getTime() * 1000);

		// 返回数据
		return ResultUtil.success(new ResearchState(configResearch.getTime() * 1000, configResearch.getTime() * 1000, foodID));
	}

	@Override
	public Result cancerBookResearch(String token, int foodID) {
		User user = userService.getUserByToken(token);
		boolean isRemain = makeStateJobManager.isTaskRemain(user.getId() + "_cookCenter");
		// 确认还在研究状态否则返回错误
		if (!isRemain) {
			return ResultUtil.error(2006, "当前没有在研究的任务");
		}
		CookBook cookbook = cookBookSerivce.getOneCookbook(user.getId(), foodID);
		ConfigResearch configResearch = InitConfig.getInstance().getResearchMap().get(Integer.parseInt(cookbook.getFoodId() + "" + cookbook.getFoodQualify()));
		// 返还研究力
		Foodcenter foodcenter = FoodcenterDao.selectByPrimaryKey(user.getId());
		foodcenter.setPower(foodcenter.getPower() + configResearch.getUsepower());
		FoodcenterDao.updateByPrimaryKey(foodcenter);
		// 返还食材
		List<Reused> list = StringUtil.getMetarial(configResearch.getReward());
		fridgeService.addMaterial(token, list);
		// 停止定时器
		researchRedisService.expireToken(user.getId(),1);//不要填0
		System.out.println(researchRedisService.getExpire(user.getId())+"——————取消计时");
		makeStateJobManager.stopTime(user.getId() + "_cookCenter");
		// 返回数据
		return ResultUtil.success();
	}

	@Override
	public Result getDraw(String token) {
		User user = userService.getUserByToken(token);
		List<CookBook> cookBooks = cookBookSerivce.getListByUserId(user.getId());
		// 组装数据
		List<Draw> chineseList = new ArrayList<Draw>();
		List<Draw> westList = new ArrayList<Draw>();
		List<Draw> barbecueList = new ArrayList<Draw>();
		List<Draw> dessertList = new ArrayList<Draw>();
		List<Draw> drinkList = new ArrayList<Draw>();
		Map<Integer, ConfigFood> configFoodMap = InitConfig.getInstance().getFoodMap();
		for (Entry<Integer, ConfigFood> configFood : configFoodMap.entrySet()) {
			Draw draw = new Draw();
			draw.setFoodID(configFood.getValue().getFoodid());
			draw.setState(1);
			draw.setLevel(configFood.getValue().getFoodlevel());
			for (CookBook cookBook : cookBooks) {
				if (configFood.getValue().getFoodid().equals(cookBook.getFoodId())) {
					draw.setFoodID(cookBook.getFoodId());
					draw.setState(0);
					draw.setLevel(configFood.getValue().getFoodlevel());
					break;
				}
			}
			Integer foodtype = configFood.getValue().getFoodtype();
			if (CHINESE.equals(foodtype)) {
				chineseList.add(draw);
			} else if (WEST.equals(foodtype)) {
				westList.add(draw);
			} else if (BARBECUE.equals(foodtype)) {
				barbecueList.add(draw);
			} else if (DESSERT.equals(foodtype)) {
				dessertList.add(draw);
			} else if (DRINK.equals(foodtype)) {
				drinkList.add(draw);
			}
		}
		List<Draws> menuList = new ArrayList<Draws>();
		Collections.sort(chineseList);
		Collections.sort(westList);
		Collections.sort(barbecueList);
		Collections.sort(dessertList);
		Collections.sort(drinkList);
		menuList.add(new Draws(CHINESE, chineseList));
		menuList.add(new Draws(WEST, westList));
		menuList.add(new Draws(BARBECUE, barbecueList));
		menuList.add(new Draws(DESSERT, dessertList));
		menuList.add(new Draws(DRINK, drinkList));
		return ResultUtil.success(menuList);
	}

	@Override
	public Result addSpeed(String token) {
		User user = userService.getUserByToken(token);
		boolean isRemain = makeStateJobManager.isTaskRemain(user.getId() + "_cookCenter");
		Integer foodId = researchRedisService.getFoodId(user.getId());
		// 确认还在研究状态否则返回错误
		if (!isRemain||foodId==null) {
			return ResultUtil.error(2006, "当前没有在研究的任务");
		}
		
		// 设置研究菜品的倒计时
		Long expire = researchRedisService.getExpire(user.getId());
		//消耗钻石
		double reducenumber =Math.ceil(expire/1000/60.0);
		if (user.getDiamond()<reducenumber) {
			return ResultUtil.error(2008, "钻石不足");
		}
		user.setDiamond((int) (user.getDiamond()-reducenumber));
		userService.updateUser(user);
		userRedisService.setUser(token, user);
		researchRedisService.expireToken(user.getId(), 1);
		makeStateJobManager.resumeTime(user.getId() + "_cookCenter", 1);
		return ResultUtil.success();
	}

	@Override
	public void initFoodcenter(Integer userId) {
		insert(new Foodcenter(userId, LEVEL_STATE.INIT_LEVEL.getNum(), 7, 0, 10));
		
	}
}
