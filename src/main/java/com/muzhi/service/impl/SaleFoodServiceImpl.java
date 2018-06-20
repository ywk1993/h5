package com.muzhi.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muzhi.dao.IncomeDao;
import com.muzhi.dao.PropDao;
import com.muzhi.model.CookBook;
import com.muzhi.model.Food;
import com.muzhi.model.Income;
import com.muzhi.model.Inventory;
import com.muzhi.model.Prop;
import com.muzhi.model.Restaurant;
import com.muzhi.model.Result;
import com.muzhi.model.SaleFoodInfo;
import com.muzhi.model.TaskMethod;
import com.muzhi.model.User;
import com.muzhi.model.configbean.ConfigFood;
import com.muzhi.model.configbean.ConfigRole;
import com.muzhi.model.configbean.ConfigSkill;
import com.muzhi.model.vo.ComplimentResult;
import com.muzhi.model.vo.FoodInfo;
import com.muzhi.model.vo.InventoryInfo;
import com.muzhi.model.vo.InventoryVo;
import com.muzhi.model.vo.PropInfo;
import com.muzhi.model.vo.RefuseRes;
import com.muzhi.model.vo.RoleVO;
import com.muzhi.model.vo.SaleInfo;
import com.muzhi.model.vo.SaleResult;
import com.muzhi.model.vo.SellFoodInfo;
import com.muzhi.redis.service.UserRedisService;
import com.muzhi.service.ArticleService;
import com.muzhi.service.CookBookSerivce;
import com.muzhi.service.FoodService;
import com.muzhi.service.InventoryService;
import com.muzhi.service.PublicityService;
import com.muzhi.service.RestaurantService;
import com.muzhi.service.SaleFoodInfoService;
import com.muzhi.service.SaleFoodlService;
import com.muzhi.service.TaskService;
import com.muzhi.service.UserService;
import com.muzhi.service.config.InitConfig;
import com.muzhi.util.ResultUtil;
/**
 * 食物售卖
 * 
 * @author Yuwk
 *
 *         2017年11月23日
 */
@Service
public class SaleFoodServiceImpl implements SaleFoodlService {
	@Autowired
	private UserService userService;
	@Autowired
	private UserRedisService userRedisService;
	@Autowired
	private SaleFoodInfoService saleFoodInfoService;
	@Autowired
	private FoodService foodService;
	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private CookBookSerivce cookBookSerivce;
	@Autowired
	private InventoryService inventoryService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private PropDao propDao;
	@Autowired
	private PublicityService publicityService;
	@Autowired
	private IncomeDao incomeDao;
	
	
	// private static final Logger logger =
	// LoggerFactory.getLogger(SaleFoodServiceImpl.class);

	@Override
	public Result refuseCustomer(String token, Integer foodId, Integer roleId, Integer index) {
		// 获取用户信息
		User user = userService.getUserByToken(token);
		SaleFoodInfo oneSaleFoodInfo = saleFoodInfoService.getOneSaleFoodInfo(user.getId(), index);
		Restaurant restaurant = restaurantService.getRestaurant(user.getId());
		// 跟新用户的爱心值
		int loveNumber = user.getLove() + oneSaleFoodInfo.getRefusenumber();
		if (loveNumber < 0) {
			loveNumber = 0;
		}
		if (loveNumber > restaurant.getLoveLimit()) {
			loveNumber =  restaurant.getLoveLimit();
		}
		user.setLove(loveNumber);
		userService.updateUser(user);
		System.out.println("refuse：" + user.getGold());
		// 刷新redis
		userRedisService.setUser(token, user);
		
		// 下一个角色
		List<ConfigRole> configRoles = InitConfig.getInstance().getRoleList();//角色列表
		ConfigRole configRole = ConfigRole.getConfigRole(configRoles);//随机角色
		CookBook cookBook = new CookBook();
		cookBook.setId(user.getId());
		List<CookBook> cookBooks = cookBookSerivce.getList(cookBook);//食谱列表
		int totalFame = publicityService.getTotalFame(user.getId());
		int fameLevel = publicityService.getFameLevel(totalFame);
		CookBook cb =CookBook.getCookBook(cookBooks, fameLevel);
		//CookBook cb = CookBook.getCookBook(cookBooks);/**随机食谱@@@@@@@@@@@@@@@@@@@@@@@@@待改*/
		Food food = new Food();
		food.setFoodID(cb.getFoodId());
		food.setFoodQualify(cb.getFoodQualify());
		food.setId(user.getId());
		food.setNum(1);
		
		RoleVO role = new RoleVO();
		role.setId(configRole.getRoleid());
		role.setName(configRole.getDescribe());
		role.setRoleType(1);
		role.setSeatNumber(index);
		role.setFood(food);
		role.setShopType(1);
		
		oneSaleFoodInfo.setCookbookId(cb.getFoodId());
		oneSaleFoodInfo.setCookbookLevel(cb.getFoodQualify());
		int add =(cb.getFoodQualify()-1)*10;
        Integer price =cb.getPrice()*(100+add)/100;
        oneSaleFoodInfo.setCurrentprice(price);
		oneSaleFoodInfo.setIscompliment(0);
		oneSaleFoodInfo.setIsdiscount(0);
		oneSaleFoodInfo.setIsriseprice(0);
		oneSaleFoodInfo.setRefusenumber(0);
		oneSaleFoodInfo.setSellcount(0);
		oneSaleFoodInfo.setRoleId(role.getId());
		saleFoodInfoService.updateSaleFoodInfo(oneSaleFoodInfo);
		
		RefuseRes refuseRes  = new RefuseRes();
		refuseRes.setHeartNumber(user.getLove());
		refuseRes.setNextRole(role);
		return ResultUtil.success(refuseRes);

	}

	@Override
	public Result saleInfo(String token, Integer index) {
		// 获取用户信息
		User user = userService.getUserByToken(token);

		SaleFoodInfo saleFoodInfo = saleFoodInfoService.getOneSaleFoodInfo(user.getId(), index);

		SaleInfo saleInfo = makeSaleInfo(user, saleFoodInfo);
		SellFoodInfo sellFoodInfo = getSellFoodInfo(saleFoodInfo);
		saleInfo.setSellFoodInfo(sellFoodInfo);

		return ResultUtil.success(saleInfo);
	}

	@Override
	public Result sellSale(String token, Integer index, Integer foodId,Integer cookbookLevel) {
		// 获取用户信息
		User user = userService.getUserByToken(token);

		// 促销接口将当前的食物id替换原来的食物id 相关信息也替换掉 同一次商品来回多次改变促销物品促销消耗的爱心值线性增加  食物的初始消耗值加上递进增量*次数
		SaleFoodInfo saleFoodInfo = saleFoodInfoService.getOneSaleFoodInfo(user.getId(), index);
		if (user.getLove() < (-getSellFoodInfo(saleFoodInfo).getHeartnumber())) {
			return ResultUtil.error(15, "爱心值不够");
		}
		// 跟新用户的爱心值 
		user.setLove(user.getLove() + getSellFoodInfo(saleFoodInfo).getHeartnumber());
		userService.updateUser(user);
		
		System.out.println("sell;：" + user.getGold());
		
		// 刷新redis
		userRedisService.setUser(token, user);

		// 此值来之食物的原始价格 
		CookBook cookbook = cookBookSerivce.getOneCookbook(user.getId(), foodId);
		Integer originprice = cookbook.getPrice();
		int add =(cookbookLevel-1)*10;
        Integer price =originprice*(100+add)/100;
		saleFoodInfo.setCurrentprice(price);
		saleFoodInfo.setCookbookId(foodId);
		saleFoodInfo.setSellcount(saleFoodInfo.getSellcount() + 1);
		saleFoodInfo.setCookbookLevel(cookbookLevel);
		saleFoodInfoService.updateSaleFoodInfo(saleFoodInfo);
		//新的推销产品
		SellFoodInfo sellFoodInfo = getSellFoodInfo(saleFoodInfo);
		SaleInfo saleInfo = makeSaleInfo(user, saleFoodInfo);
		saleInfo.setSellFoodInfo(sellFoodInfo);

		return ResultUtil.success(saleInfo);
	}

	@Override
	public Result discountSale(String token, Integer index) {
		// 获取用户信息
		User user = userService.getUserByToken(token);

		// 打折接口将涨价接口状态设置为不可点击状态 拒绝的爱心值设定为相应的值 同时将推销商品设为空不可再推销了 价格参照一定规则减少 加爱心值
		SaleFoodInfo saleFoodInfo = saleFoodInfoService.getOneSaleFoodInfo(user.getId(), index);
		if (saleFoodInfo.getIsdiscount().equals(1)) {
			return ResultUtil.error(16, "已经打折过");
		}
		
		ConfigFood configFood = InitConfig.getInstance().getFoodMap().get(saleFoodInfo.getCookbookId());
		int discountNumber = InitConfig.getInstance().getLoveMap().get(configFood.getFoodlevel()).getNumber();//打折爱心值？？？？？？？？？？？？？？？？

		saleFoodInfo.setIsdiscount(1);
		saleFoodInfo.setIsriseprice(1);
		saleFoodInfo.setCurrentprice(saleFoodInfo.getCurrentprice() * 75 / 100);// 暂时定为75折
		saleFoodInfo.setRefusenumber(-discountNumber);
		saleFoodInfoService.updateSaleFoodInfo(saleFoodInfo);

		// 跟新用户的爱心值
		Restaurant restaurant = restaurantService.getRestaurant(user.getId());
		int number = user.getLove() + discountNumber;
		if (number > restaurant.getLoveLimit()) {
			number = restaurant.getLoveLimit();
		}
		user.setLove(number);
		userService.updateUser(user);
		System.out.println("discountSale：" + user.getGold());
		
		// 刷新redis
		userRedisService.setUser(token, user);

		SaleInfo saleInfo = makeSaleInfo(user, saleFoodInfo);
		return ResultUtil.success(saleInfo);
	}

	@Override
	public Result risePriceSale(String token, Integer index) {
		// 获取用户信息
		User user = userService.getUserByToken(token);

		// 涨价接口将打折接口状态设置为不可点击状态 拒绝的爱心值设定为相应的值 同时将推销商品设为空不可再推销了 价格参照一定规则增加 减爱心值
		SaleFoodInfo saleFoodInfo = saleFoodInfoService.getOneSaleFoodInfo(user.getId(), index);
		if (saleFoodInfo.getIsriseprice().equals(1)) {
			return ResultUtil.error(16, "已经涨价过");
		}
		ConfigFood configFood = InitConfig.getInstance().getFoodMap().get(saleFoodInfo.getCookbookId());
		int risePriceNumber = -InitConfig.getInstance().getLoveMap().get(configFood.getFoodlevel()).getNumber();//涨价爱心值??????????
		if (user.getLove() < (-risePriceNumber)) {
			return ResultUtil.error(15, "爱心值不够");
		}

		saleFoodInfo.setIsdiscount(1);
		saleFoodInfo.setIsriseprice(1);
		saleFoodInfo.setCurrentprice(saleFoodInfo.getCurrentprice() * 125 / 100);// 暂时定为75折
		saleFoodInfo.setRefusenumber(-risePriceNumber);
		saleFoodInfoService.updateSaleFoodInfo(saleFoodInfo);

		// 跟新用户的爱心值
		user.setLove(user.getLove() + risePriceNumber);
		userService.updateUser(user);
		
		System.out.println("risePrice：" + user.getGold());
		
		// 刷新redis
		userRedisService.setUser(token, user);

		SaleInfo saleInfo = makeSaleInfo(user, saleFoodInfo);
		return ResultUtil.success(saleInfo);
	}

	private SaleInfo makeSaleInfo(User user, SaleFoodInfo saleFoodInfo) {

		SaleInfo saleInfo = new SaleInfo();

		// 食物对应的爱心值配置
		ConfigFood configFood = InitConfig.getInstance().getFoodMap().get(saleFoodInfo.getCookbookId());
		int discountNumber = InitConfig.getInstance().getLoveMap().get(configFood.getFoodlevel()).getNumber();//打折爱心值？？？？？？？？？？？？？？？？
		int tradeNumber = 3;
		int articleAdd =articleService.rewardLove(saleFoodInfo.getCookbookId(), user.getId());//摆件爱心值卖出加成
		int saleNumber = 1+articleAdd;
		int risePriceNumber = -InitConfig.getInstance().getLoveMap().get(configFood.getFoodlevel()).getNumber();//涨价爱心值？？？？？？？？？？？？？？？？;
		Integer[] complimentNumber = { 1+configFood.getFoodlevel(), -1 };

		FoodInfo foodInfo = new FoodInfo();
		foodInfo.setFoodId(saleFoodInfo.getCookbookId());
		foodInfo.setIsHave(
				foodService.isExist(user.getId(), saleFoodInfo.getCookbookId(), saleFoodInfo.getCookbookLevel()));// 判断当前的库存是否有
		foodInfo.setPrice(saleFoodInfo.getCurrentprice());

		saleInfo.setComplimentNumber(complimentNumber);
		saleInfo.setDiscountNumber(discountNumber);
		saleInfo.setFoodInfo(foodInfo);
		saleInfo.setIsCompliment(saleFoodInfo.getIscompliment());
		saleInfo.setIsRisePrice(saleFoodInfo.getIsriseprice());
		saleInfo.setIsDiscount(saleFoodInfo.getIsdiscount());
		saleInfo.setLoveNumber(user.getLove());
		saleInfo.setRefusedNumber(saleFoodInfo.getRefusenumber());
		saleInfo.setFoodInfo(foodInfo);
		saleInfo.setTradeNumber(tradeNumber);
		saleInfo.setSaleNumber(saleNumber);
		saleInfo.setRisePriceNumber(risePriceNumber);
		saleInfo.setFoodQualify(saleFoodInfo.getCookbookLevel());
		return saleInfo;

	}

	/**
	 * 按照一定的规则获取促销商品的信息
	 * 暂定这样  按照品质等级排序 按照推销次数轮流获取
	 * @return
	 */
	private SellFoodInfo getSellFoodInfo(SaleFoodInfo bar) {
		List<Food> sellFood = foodService.getSellFood(bar.getUserId(), bar.getCookbookId(), bar.getCookbookLevel());
		SellFoodInfo sellFoodInfo = new SellFoodInfo();
		if (sellFood!=null&&sellFood.size()!=0) {
			int index =bar.getSellcount()%sellFood.size();
			sellFoodInfo.setFoodId(sellFood.get(index).getFoodID());
			ConfigFood configFood = InitConfig.getInstance().getFoodMap().get(sellFoodInfo.getFoodId());
			int add =bar.getSellcount()/sellFood.size();
			sellFoodInfo.setHeartnumber(-(add*configFood.getFoodlevel() + 1));//1为菜品推销消耗爱心的初始值
			sellFoodInfo.setFoodQualify(sellFood.get(index).getFoodQualify());
		}
		return sellFoodInfo;

	}
	@Override
	public Result complimentSale(String token, Integer index) {
		// 获取用户信息
		User user = userService.getUserByToken(token);

		SaleFoodInfo saleFoodInfo = saleFoodInfoService.getOneSaleFoodInfo(user.getId(), index);
		if (saleFoodInfo.getIscompliment().equals(1)) {
			return ResultUtil.error(17, "已经恭维过");
		}
		if (user.getLove() == 0) {
			return ResultUtil.error(18, "爱心值为0，无法恭维");
		}

		saleFoodInfo.setIscompliment(1);
		saleFoodInfoService.updateSaleFoodInfo(saleFoodInfo);

		// 恭维随机成功
		ConfigFood configFood = InitConfig.getInstance().getFoodMap().get(saleFoodInfo.getCookbookId());
		int complimentNum = 0;
		int incrComplimentNum = configFood.getFoodlevel()+1;
		int decrComplimentNum = -1;

		Random random = new Random();
		int nextInt = random.nextInt(2);
		if (nextInt == 1) {
			complimentNum = incrComplimentNum;
		} else {
			complimentNum = decrComplimentNum;
		}

		// 跟新用户的爱心值
		int number = user.getLove() + complimentNum;
		Restaurant restaurant = restaurantService.getRestaurant(user.getId());
		if (number >  restaurant.getLoveLimit()) {
			number =  restaurant.getLoveLimit();
		}
		if (number < 0) {
			number = 0;
		}
		user.setLove(number);
		userService.updateUser(user);
		System.out.println("complimentSale：" + user.getGold());
		// 刷新redis
		userRedisService.setUser(token, user);

		ComplimentResult complimentResult = new ComplimentResult(user, saleFoodInfo, complimentNum);
		return ResultUtil.success(complimentResult);
	}

	@Override
	public Result saleFood(String token, Integer foodId, Integer roleId, Integer index) {
		// 获取用户信息
		User user = userService.getUserByToken(token);
		SaleFoodInfo oneSaleFoodInfo = saleFoodInfoService.getOneSaleFoodInfo(user.getId(), index);
		
		int exist = foodService.isExist(user.getId(), foodId, oneSaleFoodInfo.getCookbookLevel());
		if (0 == exist) {
			return ResultUtil.error(19, "库存不足");
		}
		// 爱心值 金钱
		Restaurant restaurant = restaurantService.getRestaurant(user.getId());
		int saleNumber = 1;
		int articleAdd =articleService.rewardLove(foodId, user.getId());//摆件爱心值卖出加成
		int loveNumber = user.getLove() + saleNumber+articleAdd;
		if (loveNumber > restaurant.getLoveLimit()) {
			loveNumber = restaurant.getLoveLimit();
		}
		user.setLove(loveNumber);
		int oldprice =oneSaleFoodInfo.getCurrentprice();
        user.setGold(user.getGold()+oldprice);
        
        try {
			Income income = incomeDao.selectByPrimaryKey(user.getId());
			income.setLastIncome(income.getLastIncome()+oldprice);
			incomeDao.updateByPrimaryKey(income);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("增加收益出错");
		}
        
		// 更新用户
		userService.updateUser(user);
		System.out.println("sale：" + user.getGold());
		
		
		// 更新redis用户数据 food表相应的数量减1
		userRedisService.setUser(token, user);
		
		// 减少库存
		Food oneFood = foodService.getOneFood(user.getId(), foodId, oneSaleFoodInfo.getCookbookLevel());
		oneFood.setNum(oneFood.getNum()-1);
		foodService.updateFood(oneFood);
		
		//添加下一个角色
		List<ConfigRole> configRoles = InitConfig.getInstance().getRoleList();//角色列表
		ConfigRole configRole = ConfigRole.getConfigRole(configRoles);//随机角色
		CookBook cookBook = new CookBook();
		cookBook.setId(user.getId());
		List<CookBook> cookBooks = cookBookSerivce.getList(cookBook);//食谱列表
		int totalFame = publicityService.getTotalFame(user.getId());
		int fameLevel = publicityService.getFameLevel(totalFame);
		CookBook cb =CookBook.getCookBook(cookBooks, fameLevel);
		//CookBook cb = CookBook.getCookBook(cookBooks);/**随机食谱@@@@@@@@@@@@@@@@@@@@@@@@@待改*/
		Food food = new Food();
		food.setFoodID(cb.getFoodId());
		food.setFoodQualify(cb.getFoodQualify());
		food.setId(user.getId());
		food.setNum(1);
		
		RoleVO roleVO = new RoleVO();
		roleVO.setId(configRole.getRoleid());
		roleVO.setName(configRole.getDescribe());
		roleVO.setRoleType(1);
		roleVO.setSeatNumber(index);
		roleVO.setFood(food);
		roleVO.setShopType(1);
		
		//将数据库角色信息修改
		/*Role role = new Role();
		role.setCookbookId(roleVO.getFood().getFoodID());
		role.setCreateTime(new Date());
		role.setId(roleVO.getId());
		role.setName(roleVO.getName());
		role.setRoleType(1);
		role.setSeatIndex(index);
		role.setShopType(1);
		role.setState(1);
		role.setUserId(user.getId());
		
		roleDao.updateByPrimaryKey(role);*/
		
		oneSaleFoodInfo.setCookbookId(cb.getFoodId());
		oneSaleFoodInfo.setCookbookLevel(cb.getFoodQualify());
		int add =(cb.getFoodQualify()-1)*10;
        Integer price =cb.getPrice()*(100+add)/100;
        oneSaleFoodInfo.setCurrentprice(price);
		oneSaleFoodInfo.setIscompliment(0);
		oneSaleFoodInfo.setIsdiscount(0);
		oneSaleFoodInfo.setIsriseprice(0);
		oneSaleFoodInfo.setRefusenumber(0);
		oneSaleFoodInfo.setSellcount(0);
		oneSaleFoodInfo.setRoleId(roleVO.getId());
		saleFoodInfoService.updateSaleFoodInfo(oneSaleFoodInfo);
		
		SaleResult saleResult = new SaleResult();
		saleResult.setGoldNumber(user.getGold());
		saleResult.setGoldVariable(oldprice);
		saleResult.setHeartNumber(user.getLove());
		saleResult.setHeartVariable(2);
		saleResult.setNextRole(roleVO);
		
		// 售卖任务
		taskService.updateTaskNum(user.getId(), TaskMethod.SALE_Y_FOOD.getTaskCondition());
		return ResultUtil.success(saleResult);
	}

	@Override
	public InventoryInfo getAllInventory(User user) {
		List<Food> foods = foodService.getFood(user.getId());
		Inventory inventory = inventoryService.getInventory(user.getId());
		if (foods==null||foods.size()==0) {
			ArrayList<InventoryVo> empteList = new ArrayList<InventoryVo>() ;
			return new InventoryInfo(empteList, empteList.size(), inventory.getIndexNum());
		}
		List<InventoryVo> inventoryList =new ArrayList<>();
        for (Food food : foods) {
        	CookBook cookbook = cookBookSerivce.getOneCookbook(user.getId(), food.getFoodID());
        	ConfigFood configFood = InitConfig.getInstance().getFoodMap().get(food.getFoodID());
        	Map<Integer, List<ConfigSkill>> skillListMap = InitConfig.getInstance().getSkillListMap();
        	List<ConfigSkill> configSkills =skillListMap.get(food.getFoodID());
        	InventoryVo inventoryvo = new InventoryVo();
        	inventoryvo.setFoodId(cookbook.getFoodId());
        	inventoryvo.setFoodType(configFood.getFoodtype());
			inventoryvo.setTypeList(configFood.getMetarial());
			inventoryvo.setGold(cookbook.getPrice());
			inventoryvo.setSkill(cookbook.getProcess());
			int i=food.getFoodQualify();
			if (i>configSkills.size()-1) {
				i=configSkills.size()-1;
			}
			inventoryvo.setSkillLimit(configSkills.get(i).getRequest());
			inventoryvo.setFoodNum(food.getNum());
			inventoryvo.setFoodLevel(configFood.getFoodlevel());
			inventoryvo.setFoodQualify(food.getFoodQualify());
			inventoryList.add(inventoryvo);
		}
		InventoryInfo inventoryInfo = new InventoryInfo(inventoryList,foodService.getTotalCount(user.getId()), inventory.getIndexNum());
		return inventoryInfo;
	}

	@Override
	public List<PropInfo> getPropInfo(User user) {
		
		List<Prop> props = propDao.getPropByUid(user.getId());
		Map<Integer, List<Prop>> map =new HashMap<>();
		for (Prop prop : props) {
			Integer propType = InitConfig.getInstance().getAdvancedpropMap().get(prop.getPropId()).getPropType();
			List<Prop> proplist = map.get(propType);
			if (proplist==null) {
				proplist =new ArrayList<>();
				proplist.add(prop);
				map.put(propType, proplist);
			}else {
				proplist.add(prop);
			}
			
		}
		
		List<PropInfo> propInfos =new ArrayList<>();
		for (Entry<Integer, List<Prop>> entry : map.entrySet()) {
			propInfos.add(new PropInfo(entry.getKey(), entry.getValue()));
		}
		return propInfos;
	}
}
