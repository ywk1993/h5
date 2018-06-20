package com.muzhi.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muzhi.dao.CookBenchDao;
import com.muzhi.dao.PropDao;
import com.muzhi.model.CookBench;
import com.muzhi.model.CookBook;
import com.muzhi.model.Inventory;
import com.muzhi.model.TaskMethod;
import com.muzhi.model.User;
import com.muzhi.model.configbean.ConfigFood;
import com.muzhi.model.configbean.ConfigSkill;
import com.muzhi.model.vo.FoodDetail;
import com.muzhi.model.vo.FoodN;
import com.muzhi.model.vo.Info;
import com.muzhi.model.vo.Menu;
import com.muzhi.model.vo.MenuAll;
import com.muzhi.service.CookBenchService;
import com.muzhi.service.CookBookNService;
import com.muzhi.service.CookBookSerivce;
import com.muzhi.service.FoodService;
import com.muzhi.service.FridgeService;
import com.muzhi.service.InteractiveService;
import com.muzhi.service.InventoryService;
import com.muzhi.service.TaskService;
import com.muzhi.service.UserService;
import com.muzhi.service.config.InitConfig;
import com.muzhi.util.StringUtil;

@Service
public class CookBookNServiceImpl implements CookBookNService {

	public static Integer CHINESE = 1;
	private static final Integer WEST = 2;
	private static final Integer BARBECUE = 3;
	private static final Integer DESSERT = 4;
	private static final Integer DRINK = 5;

	@Autowired
	CookBookSerivce cookBookService;
	@Autowired
	InventoryService inventoryService;
	@Autowired
	CookBenchDao cookBenchDao;
	@Autowired
	InteractiveService interactiveService;
	@Autowired
	UserService userService;
	@Autowired
	FridgeService fridgeService;
	@Autowired
	CookBenchService cookBenchService;
	@Autowired
	TaskService taskService;
	@Autowired
	FoodService foodService;
	@Autowired
	PropDao propDao;
	
	@Override
	public MenuAll getCookBook(User user) {
		CookBook cookBook = new CookBook();
		cookBook.setId(user.getId());
		List<CookBook> list = cookBookService.getList(cookBook);
		List<FoodN> foodList = new ArrayList<FoodN>();
		
		Map<Integer, List<ConfigSkill>> configList =InitConfig.getInstance().getSkillListMap();//熟练度配置表
		List<OpenSoon> openSoons =new ArrayList<>();
		
		//Map<Integer, ConfigFood> configFoodMap = configService.getConfigFood();
		Map<Integer, ConfigFood> configFoodMap = InitConfig.getInstance().getFoodMap();
		Inventory inventory = inventoryService.getInventory(user.getId());
		
		Integer totalStrength = userService.getTotalStrength(user);
		
		// 组装数据
		for (CookBook cookBookTemp : list) {
			
			List<ConfigSkill> configSkills =configList.get(cookBookTemp.getFoodId());
			int processTotal = configSkills.get(configSkills.size()-1).getRequest();
			for (ConfigSkill configSkill : configSkills) {
				if(configSkill.getRequest()>cookBookTemp.getProcess()) {
					processTotal = configSkill.getRequest();
					break;
				}
			}
			
			FoodN foodN = new FoodN();
			foodN.setFoodID(cookBookTemp.getFoodId());
			foodN.setFoodLevel(configFoodMap.get(cookBookTemp.getFoodId()).getFoodlevel());
			foodN.setFoodType(configFoodMap.get(cookBookTemp.getFoodId()).getFoodtype());
			foodN.setCurrencyList(StringUtil.getMetarial(cookBookTemp.getMetarial()));
			foodN.setNeedNum(0);
			//0可制作状态；1厨力不足状态；2研究状态；3食物不足状态；4获取图纸状态。
			ConfigFood configFood = InitConfig.getInstance().getFoodMap().get(cookBookTemp.getFoodId());
			List<ConfigSkill> configSkilllist = InitConfig.getInstance().getSkillListMap().get(cookBookTemp.getFoodId());
			Collections.sort(configSkilllist);
			for (int i=0;i<configSkilllist.size()-1;i++) {
				if (cookBookTemp.getProcess()<configSkilllist.get(i).getRequest()) {
					if (configSkilllist.get(i).getRewardtype()==3) {
						CookBook oneCookbook = cookBookService.getOneCookbook(user.getId(), Integer.parseInt(configSkilllist.get(i).getReward()));
						if (oneCookbook==null) {
							OpenSoon openSoon = new OpenSoon(Integer.parseInt(configSkilllist.get(i).getReward()), configSkilllist.get(i).getRequest()-cookBookTemp.getProcess(), cookBookTemp.getFoodId());
							openSoons.add(openSoon);
						}	
					}
					break;
				}
			}
			if (totalStrength<configFood.getStrength()) {
				foodN.setFoodStatus(1);
			}/*else if (cookBookTemp.getStatus()==1) {
				foodN.setFoodStatus(2);//研究激活状态
			}*/else if (fridgeService.isEnough(user.getId(), StringUtil.getMetarial(configFood.getMetarial()))!=0) {
				foodN.setFoodStatus(3);
			}else {
				foodN.setFoodStatus(0);
			}
			foodN.setProcess(cookBookTemp.getProcess());
			foodN.setProcessTotal(processTotal);
			foodN.setRequestStrength(configFoodMap.get(cookBookTemp.getFoodId()).getStrength());
			foodN.setSkillStatus(foodN.getProcess() < foodN.getProcessTotal() ? 0 : 1);
			foodN.setStock(inventory);
			foodN.setUid(user.getId());
			foodN.setFoodQualify(cookBookTemp.getFoodQualify());
			foodN.setStatus(cookBookTemp.getStatus());
			foodList.add(foodN);
		}
		if (openSoons!=null&&openSoons.size()!=0) {
			for (OpenSoon openSoon : openSoons) {
				ConfigFood configFood = InitConfig.getInstance().getFoodMap().get(openSoon.mayfooid);
				FoodN foodN = new FoodN();
				foodN.setCurrencyList(StringUtil.getMetarial(configFood.getMetarial()));
				foodN.setFoodID(openSoon.mayfooid);
				foodN.setFoodLevel(configFood.getFoodlevel());
				foodN.setFoodQualify(1);
				foodN.setFoodStatus(4);
				foodN.setFoodType(configFood.getFoodtype());
				foodN.setNeedNum(openSoon.needNum);
				foodN.setProcess(0);
				foodN.setProcessTotal(0);
				foodN.setRequestStrength(configFood.getStrength());
				foodN.setSkillStatus(0);
				foodN.setStatus(0);
				foodN.setStock(0);
				foodN.setUid(user.getId());
				foodN.setResourceFoodId(openSoon.resourceFoodId);
				foodList.add(foodN);
			}
		}
		// 组装数据
		List<FoodN> chineseList = new ArrayList<FoodN>();
		List<FoodN> westList = new ArrayList<FoodN>();
		List<FoodN> barbecueList = new ArrayList<FoodN>();
		List<FoodN> dessertList = new ArrayList<FoodN>();
		List<FoodN> drinkList = new ArrayList<FoodN>();

		for (FoodN foodNTemp : foodList) {

			if (CHINESE.equals(foodNTemp.getFoodType())) {
				chineseList.add(foodNTemp);
			} else if (WEST.equals(foodNTemp.getFoodType())) {
				westList.add(foodNTemp);
			} else if (BARBECUE.equals(foodNTemp.getFoodType())) {
				barbecueList.add(foodNTemp);
			} else if (DESSERT.equals(foodNTemp.getFoodType())) {
				dessertList.add(foodNTemp);
			} else if (DRINK.equals(foodNTemp.getFoodType())) {
				drinkList.add(foodNTemp);
			}
		}

		List<Menu> menuList = new ArrayList<Menu>();
		menuList.add(new Menu(CHINESE, chineseList));
		menuList.add(new Menu(WEST, westList));
		menuList.add(new Menu(BARBECUE, barbecueList));
		menuList.add(new Menu(DESSERT, dessertList));
		menuList.add(new Menu(DRINK, drinkList));
		
		//用户总厨力
		int employStrength = interactiveService.getEmployStrength(user);//雇佣厨师加的厨力
		CookBench cookBench = cookBenchService.getCookBench(user.getId());
		int cookBenchStrength = cookBench.getAddition();//灶台厨力
		int userStrength = user.getStrength();//用户本身厨力
		
		int currentStrength = employStrength + cookBenchStrength + userStrength;
		
		// 收集菜谱任务，业务待分离
		taskService.updateTaskNum(user.getId(), TaskMethod.COLLECT_Y.getTaskCondition(), 
				chineseList.size() + westList.size() + barbecueList.size() + dessertList.size() + drinkList.size());
		
		return new MenuAll(menuList,currentStrength,foodService.getFood(user.getId()),propDao.getPropByUid(user.getId()));
	}

	@Override
	public FoodDetail getFoodDetail(User user, Integer id) {
		CookBook cookBook = new CookBook();
		cookBook.setId(user.getId());
		cookBook.setFoodId(id);
		CookBook cookBookTemp = cookBookService.getOneCookbook(user.getId(), id);
		FoodDetail foodDetail = new FoodDetail();
		Info info = new Info();
		info.setFoodID(cookBookTemp.getFoodId());
		info.setFoodLevel(InitConfig.getInstance().getFoodMap().get(cookBookTemp.getFoodId()).getFoodlevel());
		info.setFoodType(InitConfig.getInstance().getFoodMap().get(cookBookTemp.getFoodId()).getFoodtype());
		info.setCurrencyList(StringUtil.getMetarial(cookBookTemp.getMetarial()));
		info.setStock(inventoryService.getInventory(user.getId()));
		info.setFoodID(id);
		info.setFoodQulify(cookBookTemp.getFoodQualify());
		info.setTimes(cookBookTemp.getProcess());

		//用户总厨力
		int currentStrength = userService.getTotalStrength(user);
		
		foodDetail.setInfo(info);
		foodDetail.setProficiency(cookBookTemp.getProcess());
		foodDetail.setStrength(currentStrength);

		return foodDetail;
	}

	@Override
	public void setCookBook() {
		//Map<Integer, ConfigFood> configFood = configService.getConfigFood();
		@SuppressWarnings("unused")
		Map<Integer, ConfigFood> configFood = InitConfig.getInstance().getFoodMap();
	}
	public class OpenSoon{
		public Integer mayfooid;
		public int needNum;
		public Integer resourceFoodId;
		public OpenSoon(Integer mayfooid, int needNum, Integer resourceFoodId) {
			super();
			this.mayfooid = mayfooid;
			this.needNum = needNum;
			this.resourceFoodId = resourceFoodId;
		}
		
	} 

}
