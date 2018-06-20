package com.muzhi.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muzhi.dao.FridgeDao;
import com.muzhi.dao.PropDao;
import com.muzhi.model.Food;
import com.muzhi.model.Fridge;
import com.muzhi.model.Prop;
import com.muzhi.model.Reused;
import com.muzhi.model.User;
import com.muzhi.model.configbean.ConfigFacilities;
import com.muzhi.model.state.LEVEL_STATE;
import com.muzhi.redis.service.impl.UserRedisImpl;
import com.muzhi.service.FoodService;
import com.muzhi.service.FridgeService;
import com.muzhi.service.UserService;
import com.muzhi.service.config.InitConfig;

@Service
public class FridgeServiceImpl implements FridgeService {

	// 高级食材 30 30+000 用于高级菜品的制作
	// 建材 31 31+ 用于建筑升级
	// 菜谱碎片 32 32+ 用于激活新的菜品
	// 金钱 1 1
	// 钻石 1 2
	// 爱心 1 3
	// 普通食材 4 1到5
	// 菜品（成品） 10 菜品ID

	public static Integer GAOJISHICAI = 30;
	public static Integer JIANCAI = 31;
	public static Integer SHIPUSUIPIAN = 32;
	public static Integer GOLD = 1;
	public static Integer ZUANSHI = 2;
	public static Integer HEART = 3;
	public static Integer PUTONGSHICAI = 4;
	public static Integer COOKBOOK = 10;

	public static Integer FRIDGE = 3;
	@Autowired
	private FridgeDao fridgeDao;
	@Autowired
	private PropDao propDao;
	@Autowired
	private UserService userService;
	@Autowired
	private UserRedisImpl userRedisImpl;
	@Autowired
	private FoodService FoodService;

	@Override
	public Fridge getFridge(Integer id) {
		return fridgeDao.selectByPrimaryKey(id);
	}

	@Override
	public void updateFridge(Fridge fridge) {
		fridgeDao.updateByPrimaryKey(fridge);

	}
	// 高级食材 30 30+000 用于高级菜品的制作
	// 建材 31 31+ 用于建筑升级
	// 菜谱碎片 32 32+ 用于激活新的菜品
	// 金钱 1 1
	// 钻石 1 2
	// 爱心 1 3
	// 普通食材 4 1到5
	// 菜品（成品） 10 菜品ID
	
	//爱心 15  钻石2008 食物1011 金币1004 食材2002 高级建材4001 高级食材4002 食物碎片4003 
	@Override
	public int isEnough(Integer uid, List<Reused> needlist) {
		for (Reused reused : needlist) {
			if (reused.getType().equals(30)) {
				Prop prop = propDao.selectByPrimaryKey(uid, reused.getId());
				if (prop==null||prop.getNumber() < reused.getNumber()) {
					return 4200;
				}
			}
			if (reused.getType().equals(31)) {
				Prop prop = propDao.selectByPrimaryKey(uid, reused.getId());
				if (prop==null||prop.getNumber() < reused.getNumber()) {
					return 4100;
				}
			}
			if (reused.getType().equals(32)) {
				Prop prop = propDao.selectByPrimaryKey(uid, reused.getId());
				if (prop==null||prop.getNumber() < reused.getNumber()) {
					return 4300;
				}
			}
			if (reused.getType().equals(1)&&reused.getId().equals(1) && reused.getNumber() > userService.getUser(uid).getGold()) {
				return 1004;
			}
			if (reused.getType().equals(1)&& reused.getId().equals(2)&& reused.getNumber() > userService.getUser(uid).getDiamond()) {
				return 2008;
			}
			if (reused.getType().equals(1)&& reused.getId().equals(3)&& reused.getNumber() > userService.getUser(uid).getLove()) {
				return 15;
			}
			if (reused.getType().equals(4)) {
				Fridge fridge = getFridge(uid);
				if (reused.getId().equals(1) && reused.getNumber() > fridge.getRice()) {
					return 2002;
				}
				if (reused.getId().equals(2) && reused.getNumber() > fridge.getBanana()) {
					return 2002;
				}
				if (reused.getId().equals(3) && reused.getNumber() > fridge.getVegetable()) {
					return 2002;
				}
				if (reused.getId().equals(4) && reused.getNumber() > fridge.getMeat()) {
					return 2002;
				}
				if (reused.getId().equals(5) && reused.getNumber() > fridge.getFish()) {
					return 2002;
				}
			}
			if (reused.getType().equals(10)) {
				List<Food> foodByFoodId = FoodService.getFoodByFoodId(uid, reused.getId());
				int total =0;
				for (Food food : foodByFoodId) {
					total+=food.getNum();
				}
				if (total<reused.getNumber()) {
					return 1011;
				}
			}

		}
		return 0;
	}
	@Override
	public Fridge expendMaterial(String token, List<Reused> needlist) {
		User user = userService.getUserByToken(token);
		Fridge fridge = getFridge(user.getId());
		for (Reused reused : needlist) {
			if (reused.getType().equals(30) || reused.getType().equals(31) || reused.getType().equals(32)) {
				Prop prop = propDao.selectByPrimaryKey(user.getId(), reused.getId());
				prop.setNumber(prop.getNumber()-reused.getNumber());
	            propDao.updateByPrimaryKey(prop);	
			}
			if (reused.getType().equals(1)&&reused.getId().equals(1)) {
				user.setGold(user.getGold()-reused.getNumber());
				userService.updateUser(user);
				userRedisImpl.setUser(token, user);
			}
			if (reused.getType().equals(1)&&reused.getId().equals(2)) {
				user.setDiamond(user.getDiamond()-reused.getNumber());
				userService.updateUser(user);
				userRedisImpl.setUser(token, user);
			}
			if (reused.getType().equals(1)&&reused.getId().equals(3)) {
				user.setLove(user.getLove()-reused.getNumber());
				userService.updateUser(user);
				userRedisImpl.setUser(token, user);
			}
			if (reused.getType().equals(4)) {
				
				if (reused.getId().equals(1)) {
					fridge.setRice(fridge.getRice()-reused.getNumber());	
				}
				if (reused.getId().equals(2)) {
					fridge.setBanana(fridge.getBanana()-reused.getNumber());
				}
				if (reused.getId().equals(3)) {
					fridge.setVegetable(fridge.getVegetable()-reused.getNumber());
				}
				if (reused.getId().equals(4)) {
					fridge.setMeat(fridge.getMeat()-reused.getNumber());
				}
				if (reused.getId().equals(5)) {
					fridge.setFish(fridge.getFish()-reused.getNumber());
				}
				updateFridge(fridge);
			}
			if (reused.getType().equals(10)) {
				List<Food> foods = FoodService.getFoodByFoodId(user.getId(), reused.getId());
				int needNum = reused.getNumber();
				for (int i = 0; i < foods.size() && needNum != 0; i++) {
					if (foods.get(i).getNum() >= needNum) {
						foods.get(i).setNum(foods.get(i).getNum() - needNum);
						FoodService.updateFood(foods.get(i));
						break;
					} else {
						needNum -= foods.get(i).getNum();
						foods.get(i).setNum(0);
						FoodService.updateFood(foods.get(i));
					}
				}
			}

		}
		return fridge;
	}

	@Override
	public void fridgeInit(User user) {
		Map<Integer, ConfigFacilities> facilitiesMap = InitConfig.getInstance().getFacilitiesMap();
		int fridgenumber = facilitiesMap.get(Integer.parseInt(FRIDGE + "" + LEVEL_STATE.INIT_LEVEL.getNum()))
				.getAttribute();// 冰箱升级升级增加的食材的存储上限
		Fridge fridge = new Fridge(user.getId(), LEVEL_STATE.INIT_LEVEL.getNum(), fridgenumber, 2000, 2000, 2000, 2000,
				2000);
		fridgeDao.insert(fridge);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return fridgeDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Fridge record) {
		return fridgeDao.insert(record);
	}

	@Override
	public int insertSelective(Fridge record) {
		return fridgeDao.insertSelective(record);
	}

	@Override
	public Fridge selectByPrimaryKey(Integer id) {
		return fridgeDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Fridge record) {
		return fridgeDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Fridge record) {
		return fridgeDao.updateByPrimaryKey(record);
	}
    
	@Override
	public Fridge addMaterial(String token, List<Reused> needlist) {
		User user = userService.getUserByToken(token);
		Fridge fridge = getFridge(user.getId());
		for (Reused reused : needlist) {
			if (reused.getType().equals(30) || reused.getType().equals(31) || reused.getType().equals(32)) {
				Prop prop = propDao.selectByPrimaryKey(user.getId(), reused.getId());
				prop.setNumber(prop.getNumber()+reused.getNumber());
	            propDao.updateByPrimaryKey(prop);
			}
			if (reused.getType().equals(1)&&reused.getId().equals(1)) {
				user.setGold(user.getGold()+reused.getNumber());
				userService.updateUser(user);
				userRedisImpl.setUser(token, user);
			}
			if (reused.getType().equals(1)&&reused.getId().equals(2)) {
				user.setDiamond(user.getDiamond()+reused.getNumber());
				userService.updateUser(user);
				userRedisImpl.setUser(token, user);
			}
			if (reused.getType().equals(1)&&reused.getId().equals(3)) {
				user.setLove(user.getLove()+reused.getNumber());
				userService.updateUser(user);
				userRedisImpl.setUser(token, user);
			}
			if (reused.getType().equals(4)) {
				
				if (reused.getId().equals(1)) {
					fridge.setRice(fridge.getRice()+reused.getNumber());	
				}
				if (reused.getId().equals(2)) {
					fridge.setBanana(fridge.getBanana()+reused.getNumber());
				}
				if (reused.getId().equals(3)) {
					fridge.setVegetable(fridge.getVegetable()+reused.getNumber());
				}
				if (reused.getId().equals(4)) {
					fridge.setMeat(fridge.getMeat()+reused.getNumber());
				}
				if (reused.getId().equals(5) && reused.getNumber() > fridge.getFish()) {
					fridge.setFish(fridge.getFish()+reused.getNumber());
				}
				updateFridge(fridge);
			}
			if (reused.getType().equals(10)) {
				Food food =FoodService.getOneFood(user.getId(), reused.getId(), 1);//退回的统统到一级
				food.setNum(food.getNum()+reused.getNumber());
				FoodService.updateFood(food);
				return null;
			}

		}
		return fridge;
	}

}
