package com.muzhi.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muzhi.dao.FoodDao;
import com.muzhi.dao.MakeFoodInfoDao;
import com.muzhi.model.Chef;
import com.muzhi.model.Constant;
import com.muzhi.model.CookBook;
import com.muzhi.model.CurrencyType;
import com.muzhi.model.Food;
import com.muzhi.model.Foodcenter;
import com.muzhi.model.Fridge;
import com.muzhi.model.Inventory;
import com.muzhi.model.MakeFoodInfo;
import com.muzhi.model.Result;
import com.muzhi.model.Reused;
import com.muzhi.model.SkillReward;
import com.muzhi.model.TaskMethod;
import com.muzhi.model.User;
import com.muzhi.model.configbean.ConfigFood;
import com.muzhi.model.configbean.ConfigSkill;
import com.muzhi.model.vo.MakeDetail;
import com.muzhi.model.vo.MakeFoodState;
import com.muzhi.model.vo.StockResultl;
import com.muzhi.redis.service.MakeFoodRedisService;
import com.muzhi.service.ChefService;
import com.muzhi.service.CookBookSerivce;
import com.muzhi.service.FoodService;
import com.muzhi.service.FoodcenterService;
import com.muzhi.service.FridgeService;
import com.muzhi.service.InventoryService;
import com.muzhi.service.MakeFoodInfoService;
import com.muzhi.service.SkillRewardService;
import com.muzhi.service.TaskService;
import com.muzhi.service.UserService;
import com.muzhi.service.config.InitConfig;
import com.muzhi.task.MakeStateJobManager;
import com.muzhi.util.ResultUtil;
import com.muzhi.util.StringUtil;

@Service
public class FoodServiceImpl implements FoodService {
	private static int MATERIAL = 1;
	private static int TIME = 2;
	private static int COOKBOOK = 3;
	private static int PRICE = 4;
	private static int QUALITY = 5;
	@Autowired
	private UserService userService;
	@Autowired
	private FoodDao foodDao;
	@Autowired
	private InventoryService inventoryService;
	@Autowired
	private MakeFoodInfoService makeFoodInfoService;
	@Autowired
	private FridgeService fridgeService;
	@Autowired
	private CookBookSerivce cookBookSerivce;
	@Autowired
	private MakeFoodRedisService makeFoodRedisService;
	@Autowired
	private MakeFoodInfoDao makeFoodInfoDao;
	@Autowired
	private MakeStateJobManager makeStateJobManager;
	@Autowired
	private SkillRewardService skillRewardService;
	@Autowired
	private ChefService chefService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private FoodcenterService foodcenterService;
	
	@Override
	public void addList(List<Food> foodList) {
		foodDao.addList(foodList);

	}

	@Override
	public void getList(Food food) {
		foodDao.getList(food);

	}

	@Override
	public Result stockFood(String token, Integer foodId, Integer index) {

		User user = userService.getUserByToken(token);
		MakeFoodInfo oneMakeFoodInfo = makeFoodInfoService.getOneMakeFoodInfo(user.getId(), index);
		if (oneMakeFoodInfo.getStatus() != 2) {
			return ResultUtil.error(1005, "尚未完成制作");
		}
		int totalNumber = getTotalCount(user.getId());// 库存量
		Inventory inventory = inventoryService.getInventory(user.getId());
		int totalLimit = inventory.getIndexNum();// 库存上限
		if (totalNumber >= totalLimit) {
			return ResultUtil.error(1004, "库存已满",totalLimit);
		}	
		int critQuality = 0;//是否暴击的标志 0否1是
		Integer addQuality = cookBookSerivce.getAddQuality(user.getId(), foodId);
		if (addQuality!=0) {
			critQuality = 1;
		}
		int totalQuality=critQuality+oneMakeFoodInfo.getFoodQualify();
		if (totalQuality>Constant.QUALITYLEVEL) {
			totalQuality=Constant.QUALITYLEVEL;
		}
		int quality = totalQuality;
		// 加入库存
		Food oneFood = getOneFood(user.getId(), foodId, totalQuality);
		if (null != oneFood) {
			oneFood.setNum(oneFood.getNum() + 1);
			updateFood(oneFood);
		} else {
			oneFood = new Food(user.getId(), foodId, totalQuality, 1);
			List<Food> list = new ArrayList<>();
			list.add(oneFood);
			this.addList(list);
		}
		// 制作完成菜品熟练度加+1
		CookBook oneCookbook = cookBookSerivce.getOneCookbook(user.getId(), foodId);
	    oneCookbook.setProcess(oneCookbook.getProcess() + 1);
	    cookBookSerivce.updateCookBook(oneCookbook);

		// 熟练度奖励 达到当前熟练度的相应的加成 如果下一级是奖励食谱添加数据库状态为有图纸 （优化点 此处奖励一次性的可以直接判断条件为等于需求 不用存数据库 ）
		// 食材 1 材料减少；和食材需求一致 1,0,0,0,0
		// 时间 2 制作时间减少；减少百分比。50
		// 图纸 3 新的图纸；填写菜品ID。101003
		// 售价 4 售价提高；增加百分比。25
		// 品质 5 品质加成：加成的数值。1

		List<ConfigSkill> list = InitConfig.getInstance().getSkillListMap().get(foodId);
		/*for (ConfigSkill configSkill : list) {
			System.out.println(configSkill);
		}*/
		int indexNumber = getIndex(list, oneCookbook.getProcess());
		
		int skillStatus = 0;//熟练度奖励状态
		
		String reward = null;
		if (indexNumber != -1) {
			//材料减少  ****************改动注意的地方
			if (list.get(indexNumber).getRewardtype().equals(MATERIAL)) {
				if (!skillRewardService.isReceive(user.getId(), list.get(indexNumber).getId())) {
					String metarial =getNewMetarial(oneCookbook.getMetarial(),list.get(indexNumber).getReward());
					oneCookbook.setMetarial(metarial);
					cookBookSerivce.updateCookBook(oneCookbook);
					skillRewardService.insert(new SkillReward(user.getId(), list.get(indexNumber).getId()));
					skillStatus = MATERIAL;
					reward = list.get(indexNumber).getReward();
				}
			}
			//时间减少
			if (list.get(indexNumber).getRewardtype().equals(TIME)) {
				if (!skillRewardService.isReceive(user.getId(), list.get(indexNumber).getId())) {
					oneCookbook.setMakeTime(oneCookbook.getMakeTime()*(100-Integer.parseInt(list.get(indexNumber).getReward()))/100);
					cookBookSerivce.updateCookBook(oneCookbook);
					skillRewardService.insert(new SkillReward(user.getId(), list.get(indexNumber).getId()));
					skillStatus = TIME;
					reward = list.get(indexNumber).getReward();
				}
			

			}
			//售价提高
			if (list.get(indexNumber).getRewardtype().equals(PRICE)) {
				if (!skillRewardService.isReceive(user.getId(), list.get(indexNumber).getId())) {
					oneCookbook.setPrice(oneCookbook.getPrice() * (100 + Integer.parseInt(list.get(indexNumber).getReward()))/100);
					cookBookSerivce.updateCookBook(oneCookbook);
					skillRewardService.insert(new SkillReward(user.getId(), list.get(indexNumber).getId()));
					skillStatus = PRICE;
					reward = list.get(indexNumber).getReward();
				}
				
			}
			//品质加成
			if (list.get(indexNumber).getRewardtype().equals(QUALITY)) {
				if (!skillRewardService.isReceive(user.getId(), list.get(indexNumber).getId())) {
					int totalQualify = oneCookbook.getFoodQualify() + Integer.parseInt(list.get(indexNumber).getReward());
					if (totalQualify > Constant.QUALITYLEVEL) {
						totalQualify = Constant.QUALITYLEVEL;
					}
					oneCookbook.setFoodQualify(totalQualify);
					cookBookSerivce.updateCookBook(oneCookbook);
					skillRewardService.insert(new SkillReward(user.getId(), list.get(indexNumber).getId()));
					skillStatus = QUALITY;
					reward = list.get(indexNumber).getReward();
				}
				
			}
			//新的图纸 
			if (/*indexNumber != list.size() - 1 && */list.get(indexNumber).getRewardtype().equals(COOKBOOK)) {
				if (!skillRewardService.isReceive(user.getId(), list.get(indexNumber).getId())) {
					CookBook onecookBook = cookBookSerivce.getOneCookbook(user.getId(), Integer.parseInt(list.get(indexNumber).getReward()));
					if (onecookBook==null) {
						List<CookBook> cookbookList = new ArrayList<>();
						ConfigFood configFood = InitConfig.getInstance().getFoodMap().get(Integer.parseInt(list.get(indexNumber).getReward()));
						CookBook cookBook = new CookBook(user.getId(), Integer.parseInt(list.get(indexNumber).getReward()),
								1, configFood.getMaketime(), configFood.getMetarial(), configFood.getStrength(),
								configFood.getPrice(), 0, 0,0);
						cookbookList.add(cookBook);
						cookBookSerivce.addList(cookbookList);
						skillRewardService.insert(new SkillReward(user.getId(), list.get(indexNumber).getId()));
						skillStatus = COOKBOOK;
					}
					reward = list.get(indexNumber).getReward();
				}
			}
		}

		// 存储制作信息
		oneMakeFoodInfo.setFoodId(null);
		oneMakeFoodInfo.setFoodQualify(null);
		oneMakeFoodInfo.setIndex(index);
		oneMakeFoodInfo.setStatus(0);
		oneMakeFoodInfo.setUserId(user.getId());
		makeFoodInfoService.updateMakeFoodInfo(oneMakeFoodInfo);

		// 返回结果
		MakeFoodState makeFoodState = new MakeFoodState();
		makeFoodState.setFoodID(null);
		makeFoodState.setIndex(index);
		makeFoodState.setStatus(0);
		makeFoodState.setLeftTime(null);
		makeFoodState.setTotalTime(0);
		
		//厨师保存菜品状态改为0
		Chef chef = chefService.selectByIndex(user.getId(), index);
		if(null!=chef) {
			chef.setIstempkeep(0);
			chefService.updateByPrimaryKey(chef);
		}
		
		StockResultl stockResultl = new StockResultl(makeFoodState, totalNumber + 1, totalLimit,skillStatus,critQuality,quality,reward);
		return ResultUtil.success(stockResultl);
	}
	//减少食材消耗 前提是五种食材消耗都需要配置即使为零也需配出来 并且1，1，1，1，1是按约定的顺序
	//***********************厨力奖励的减少食材 配置表没改成三维数组 默认还是搞普通食材 还要考虑配的数据对不上
    private static String getNewMetarial(String ckstring,String string) {
    	List<Reused> cookbook = StringUtil.getMetarial(ckstring);
    	List<Integer> config = StringUtil.splitHandle(string);
    	List<String> strings = new ArrayList<>();
    	for (int i = 0; i < cookbook.size(); i++) {
			if (cookbook.get(i).getType().equals(4)) {
				Integer number =cookbook.get(i).getNumber()-config.get(i);
	    		if (number<0) {
	    			number=0;
				}
	    		cookbook.get(i).setNumber(number);
			}
			strings.add(cookbook.get(i).getType()+","+cookbook.get(i).getType()+","+cookbook.get(i).getType());
		}
    	String join = StringUtils.join(cookbook.toArray(), ";");
		return join;
    	
    }
//    public static void main(String[] args) {
//    	String newMetarial = getNewMetarial("1,0,1,0,0","0,0,1,0,0");
//    	System.out.println(newMetarial);
//	}
	// 返回区间
	private int getIndex(List<ConfigSkill> list, int skill) {
		//Collections.sort(list);
		int a = -1;
		for (int i = 0; i < list.size(); i++) {
			if (skill < list.get(0).getRequest()) {
				break;
			}
			if (i != list.size()-1&&list.get(i).getRequest() <= skill && skill < list.get(i + 1).getRequest()) {
				a = i;
				break;
			}
			if (i == list.size()-1) {
				a = i;
				break;
			}
		}
		return a;

	}

	@Override
	public Result makeFood(String token, Integer foodId, Integer index) {

		// 获取用户信息
		User user = userService.getUserByToken(token);
		Map<Integer, ConfigFood> foodMap = InitConfig.getInstance().getFoodMap();
		ConfigFood configFood = foodMap.get(foodId);
		CookBook oneCookbook = cookBookSerivce.getOneCookbook(user.getId(), foodId);

		// 制作菜品的食材需求 b f v m r
		List<Reused> list = StringUtil.getMetarial(oneCookbook.getMetarial());

		//判断雇用厨师是否已经到期
		Chef chef = chefService.selectByIndex(user.getId(), index);
		if(0!=index && 0==chef.getLefttime()) {
			return ResultUtil.error(1008, "厨师合同已到期");
		}
		if(null!=chef) {
			chef.setIstempkeep(1);
			chefService.updateByPrimaryKey(chef);
		}
		
		Integer needchuli = configFood.getStrength();// 制作菜品需要的厨力
		//用户总厨力
		int totalStrenth = userService.getTotalStrength(user);
		// 厨力判断
		if (totalStrenth < needchuli) {
			return ResultUtil.error(1001, "厨力不足",new Strength(totalStrenth,needchuli));
		}

		// 食材是否足够
		int materialenough = fridgeService.isEnough(user.getId(), list);// 食材足够返回true 否则返回false
		if (materialenough!=0) {
			return ResultUtil.error(materialenough, "材料不足",list);
		}

		// 厨师位是否足够
		boolean chefenough = makeFoodInfoService.isEnough(user.getId());// true足够 false不足
		if (!chefenough) {
			return ResultUtil.error(1003, "厨师位不足");
		}
		//美食中心允许的做菜的菜品等级
		Integer foodlevel = InitConfig.getInstance().getFoodMap().get(foodId).getFoodlevel();
		Foodcenter foodcenter = foodcenterService.selectByPrimaryKey(user.getId());
		Integer level = InitConfig.getInstance().getCookcenterMap().get(foodcenter.getLevel()).getMakelevel();
		if (foodlevel>level) {
			return ResultUtil.error(1113, "升级美食中心制作高等级菜品");
		}
		// 消耗材料
		Fridge fridge = fridgeService.expendMaterial(token, list);

		// 存储制作信息
		MakeFoodInfo oneMakeFoodInfo = makeFoodInfoService.getOneMakeFoodInfo(user.getId(), index);
		oneMakeFoodInfo.setFoodId(foodId);
		oneMakeFoodInfo.setFoodQualify(oneCookbook.getFoodQualify());
		oneMakeFoodInfo.setIndex(index);
		oneMakeFoodInfo.setStatus(1);
		oneMakeFoodInfo.setUserId(user.getId());
		makeFoodInfoService.updateMakeFoodInfo(oneMakeFoodInfo);

		// 设置制作倒计时时间
		makeFoodRedisService.setTime(index, user.getId());
		makeFoodRedisService.expireToken(index, user.getId(), oneCookbook.getMakeTime()*1000);
		makeStateJobManager.makeFoodStateChange(user.getId(), index, oneCookbook.getMakeTime()*1000);
//		makeFoodRedisService.expireToken(index, user.getId(), 1000);
//		makeStateJobManager.makeFoodStateChange(user.getId(), index, 1000);

		// 返回制作结果信息
		MakeFoodState makeFoodState = new MakeFoodState();
		makeFoodState.setFoodID(foodId);
		makeFoodState.setIndex(index);
		makeFoodState.setLeftTime(oneCookbook.getMakeTime()*1000);
		makeFoodState.setTotalTime(oneCookbook.getMakeTime()*1000);
//		makeFoodState.setLeftTime(1000);
//		makeFoodState.setTotalTime(1000);
		makeFoodState.setStatus(1);// 正在制作

		List<CurrencyType> remainInfo = new ArrayList<>();
		remainInfo.add(new CurrencyType(1, fridge.getMeat()));
		remainInfo.add(new CurrencyType(2, fridge.getRice()));
		remainInfo.add(new CurrencyType(3, fridge.getFish()));
		remainInfo.add(new CurrencyType(4, fridge.getBanana()));
		remainInfo.add(new CurrencyType(5, fridge.getVegetable()));

		MakeDetail makeDetail = new MakeDetail(makeFoodState, list, remainInfo);
		
		// 制作相关的任务, 业务待分离
		taskService.updateTaskNum(user.getId(), TaskMethod.MAKE_Y_FOOD.getTaskCondition());
		taskService.updateTaskNumAutoAddtion(user.getId(), TaskMethod.MAKE_X_QUALIFY_Y.getTaskCondition(), oneMakeFoodInfo.getFoodQualify());
		taskService.updateTaskNumAutoAddtion(user.getId(), TaskMethod.MAKE_X_Y.getTaskCondition(), oneMakeFoodInfo.getFoodId());
		taskService.updateTaskNumAutoAddtion(user.getId(), TaskMethod.MAKEFOOD_X_LEVEL_Y_NUM.getTaskCondition(), configFood.getFoodlevel());
		
		return ResultUtil.success(makeDetail);
	}

	@Override
	public int getTotalCount(Integer id) {

		Integer totalCount = foodDao.getTotalCount(id);
		if (null == totalCount) {
			totalCount = 0;
		}
		return totalCount;
	}

	@Override
	public void updateFood(Food food) {

		foodDao.updateFood(food);

	}

	@Override
	public Food getOneFood(Integer id, Integer foodID, Integer foodQualify) {

		return foodDao.getOneFood(id, foodID, foodQualify);
	}

	@Override
	public int isExist(Integer id, Integer foodID, Integer foodQualify) {
		Food oneFood = foodDao.getOneFood(id, foodID, foodQualify);
		if (null == oneFood || oneFood.getNum() == 0) {
			return 0;
		}
		return 1;
	}

	@Override
	public void foodInit(User userInit) {
		// do nothing
	}

	@Override
	public Result isChefEnough(String token) {
		User user = userService.getUserByToken(token);
		List<MakeFoodInfo> free = makeFoodInfoDao.getFree(user.getId());
		boolean flag = false;//表示没有空位
		int index = 0;
		if(null!=free) {
			for (MakeFoodInfo makeFoodInfo : free) {
				//厨师没到期，并且没做菜（status==0）
				if(0==makeFoodInfo.getIndex()) {//自己的厨师
					flag = true;
					index = makeFoodInfo.getIndex();
					break;
				}
				
				Chef chef = chefService.selectByIndex(user.getId(), makeFoodInfo.getIndex());
				if(null!=chef && 0!=chef.getLefttime()) {//雇佣的厨师
					flag = true;//表示有空位可以做菜
					index = makeFoodInfo.getIndex();
					break;
				}
			}
		}
		if(!flag) {
			return ResultUtil.error(1008, "空位不足");
		}
		/*if (null == free || free.size() == 0) {
			return ResultUtil.error(1008, "空位不足");
		}*/
		return ResultUtil.success(index);
	}

	@Override
	public List<Food> getFood(Integer id) {

		return foodDao.getFood(id);
	}

	@Override
	public List<Food> getSellFood(Integer id, Integer foodID, Integer foodQualify) {
		
		return foodDao.getSellFood(id, foodID, foodQualify);
	}
    public class Strength{
    	public int totalStrenth;
    	public int needStrenth;
		public Strength(int totalStrenth, int needStrenth) {
			super();
			this.totalStrenth = totalStrenth;
			this.needStrenth = needStrenth;
		}
    	
    }
	@Override
	public List<Food> getOrderFood(Integer id, Integer foodID, Integer foodQualify) {
		return foodDao.getOrderFood(id, foodID, foodQualify);
	}

	@Override
	public List<Food> getFoodByFoodId(Integer id, Integer foodID) {
		
		return foodDao.getFoodByFoodId(id, foodID);
	}
}
