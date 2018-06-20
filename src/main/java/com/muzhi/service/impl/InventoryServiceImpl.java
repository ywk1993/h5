package com.muzhi.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muzhi.dao.InventoryDao;
import com.muzhi.dao.PropDao;
import com.muzhi.model.CookBook;
import com.muzhi.model.Inventory;
import com.muzhi.model.Prop;
import com.muzhi.model.Result;
import com.muzhi.model.User;
import com.muzhi.model.configbean.ConfigAdvancedProp;
import com.muzhi.model.configbean.ConfigFacilities;
import com.muzhi.model.configbean.ConfigFood;
import com.muzhi.model.vo.ActivateInfo;
import com.muzhi.service.CookBookSerivce;
import com.muzhi.service.InventoryService;
import com.muzhi.service.UserService;
import com.muzhi.service.config.InitConfig;
import com.muzhi.util.ResultUtil;

@Service
public class InventoryServiceImpl implements InventoryService {
	@Autowired
	private InventoryDao inventoryDao;
	@Autowired
	private PropDao propDao;
	@Autowired
	private UserService userService;
	@Autowired
	private CookBookSerivce cookBookSerivce;
	
	@Override
	public Inventory getInventory(Integer id) {
		Inventory inventory = getInventoryWithFood(id);
		return inventory != null ? inventory : getInventoryWithOutFood(id);
	}
	
	@Override
	public Inventory getInventoryWithFood(Integer id) {
		Inventory inventory = inventoryDao.getInventoryWithFood(id);
		return inventory;
	}

	@Override
	public void updateInventory(Inventory inventory) {
		inventoryDao.updateInventory(inventory);
		
	}

	@Override
	public void inventoryInit(User userInit) {
		Map<Integer, ConfigFacilities> facilitiesMap = InitConfig.getInstance().getFacilitiesMap();
		int initNumber = facilitiesMap.get(Integer.parseInt(2 + "" + 1)).getAttribute();
		Inventory inventory = new Inventory(userInit.getId(), initNumber, 1, null);
		inventoryDao.insert(inventory);
	}

	@Override
	public Inventory getInventoryWithOutFood(Integer id) {
		return inventoryDao.getInventoryWithOutFood(id);
	}
	
	@Override
	public Result activateCookbook(String token, Integer foodId) {
		User user =userService.getUserByToken(token);
		//判断道具是否足够
		ConfigFood configFood = InitConfig.getInstance().getFoodMap().get(foodId);
		if (configFood.getPropId()==0) {
			return ResultUtil.error(2103, "没有可激活的菜品");
		}
		Prop prop = propDao.selectByPrimaryKey(user.getId(), configFood.getPropId());
		if (prop==null||configFood.getNumber()>prop.getNumber()) {
			return ResultUtil.error(2100, "道具数量不足");
		}
		//判断食物是否已经开放
		CookBook oneCookbook = cookBookSerivce.getOneCookbook(user.getId() , foodId);
		if (oneCookbook!=null) {
			return ResultUtil.error(2101, "已经获取了该食谱");
		}
		//减少道具数量
		prop.setNumber(prop.getNumber()-configFood.getNumber());
		propDao.updateByPrimaryKey(prop);
		//添加食谱等级默认1
		List<CookBook> list =new ArrayList<>();
		list.add(new CookBook(user.getId(), foodId, configFood.getFoodqualify(), configFood.getMaketime(), configFood.getMetarial(), configFood.getStrength(), configFood.getPrice(), 0, 0, 0));
		cookBookSerivce.addList(list);
		return ResultUtil.success();
	}

	@Override
	public Result getActivateInfo(String token, Integer propId) {
		User user =userService.getUserByToken(token);
		ConfigFood configFood =null;
		Map<Integer, ConfigFood> configFoods = InitConfig.getInstance().getFoodMap();
		ConfigAdvancedProp configAdvancedProp = InitConfig.getInstance().getAdvancedpropMap().get(propId);
		for (Entry<Integer, ConfigFood> nconfigFood : configFoods.entrySet()) {
			if (nconfigFood.getValue().getPropId().equals(propId)) {
				configFood=nconfigFood.getValue();
				break;
			}
		}
		if (configFood==null) {
			return ResultUtil.error(2102, "不用弹窗了");
		}
		Prop prop = propDao.selectByPrimaryKey(user.getId(),propId);
		CookBook oneCookbook = cookBookSerivce.getOneCookbook(user.getId() , configFood.getFoodid());
		ActivateInfo activateInfo =new ActivateInfo();
		activateInfo.setDescribe(configAdvancedProp.getDescribe());
		activateInfo.setFoodId(configFood.getFoodid());
		
		int haveNumber=0;
		if (prop!=null) {
			haveNumber =prop.getNumber();
		}
		activateInfo.setHaveNumber(haveNumber);
		int isActivate=0;
		if (oneCookbook!=null) {
			isActivate=1;
		}
		activateInfo.setIsActivate(isActivate);
		activateInfo.setNeedNumber(configFood.getNumber());
		activateInfo.setPropId(propId);
		activateInfo.setPropLevel(configAdvancedProp.getPropLevel());
		activateInfo.setPropQualify(configAdvancedProp.getPropQualify());
		activateInfo.setPropType(configAdvancedProp.getPropType());
		
		return ResultUtil.success(activateInfo);
	}


}
