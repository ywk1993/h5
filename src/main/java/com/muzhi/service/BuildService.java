package com.muzhi.service;

import com.muzhi.model.Farm;
import com.muzhi.model.Fishpond;
import com.muzhi.model.Foodcenter;
import com.muzhi.model.Fridge;
import com.muzhi.model.Generator;
import com.muzhi.model.Orchard;
import com.muzhi.model.Pasture;
import com.muzhi.model.Restaurant;
import com.muzhi.model.User;
import com.muzhi.model.VegetableGarden;
import com.muzhi.model.vo.Build;
import com.muzhi.service.exception.DiamondNoEnoughException;
import com.muzhi.service.exception.GoldNoEnoughException;
import com.muzhi.service.exception.NoUpgradingException;

/**
 * 庄园接口
 * @author yany
 *
 */
public interface BuildService {
	public Build getBuildAll(User userByToken);
	/**
	 * 设置农场
	 * @param farm
	 * @param generator
	 */
	public void setFarm(Farm farm, Generator generator, Build build);
	/**
	 * 获取农场
	 * @param user
	 * @return
	 */
	public Farm getFarm(User user);
	/**
	 * 设置鱼塘
	 * @param farm
	 * @param generator
	 */
	public void setFishpond(Fishpond fishpond, Generator generator, Build build);
	/**
	 * 获取鱼塘
	 * @param user
	 * @return
	 */
	public Fishpond getFishpond(User user);
	/**
	 * 设置牧场
	 * @param farm
	 * @param generator
	 */
	public void setPasture(Pasture pasture, Generator generator, Build build);
	/**
	 * 获取牧场
	 * @param user
	 * @return
	 */
	public Pasture getPasture(User user);
	/**
	 * 设置果园
	 * @param farm
	 * @param generator
	 */
	public void setOrchard(Orchard orchard, Generator generator, Build build);
	/**
	 * 获取果园
	 */
	public Orchard getOrchard(User user);
	/**
	 * 设置餐厅
	 * @param farm
	 * @param generator
	 */
	public void setVegetableGarden(VegetableGarden vegetableGarden, Generator generator, Build build);
	/**
	 * 获取菜园 
	 */
	public VegetableGarden getVegetableGarden(User user);
	/**
	 * 获取美食中心
	 * @param user
	 * @return
	 */
	public Foodcenter getFoodcenter(User user);
	/**
	 * 获取餐厅
	 * @param user
	 * @return
	 */
	public Restaurant getRestaurant(User user);
	/**
	 * 1允许；0不允许
	 * @param user
	 * @param type
	 * @return
	 * @throws GoldNoEnoughException 
	 */
	public Build isAllowUpgrade(String token, Integer type) throws BuildUpgradingException, GoldNoEnoughException,BuildMaxLevelException;

	/**
	 * 初始化庄园
	 * @param userInit
	 */
	public void buildInit(User userInit);

	/**
	 * 收取
	 * @param userByToken
	 * @param type
	 * @return
	 */
	public Fridge collect(User userByToken, Integer type);
	
	/**
	 * 加速升级
	 * @param userByToken
	 * @param type
	 * @return
	 */
	public Build accelerateAllowUpgrade(User userByToken, Integer type) throws DiamondNoEnoughException, NoUpgradingException;
	
	/**
	 * 取消升级
	 * @param userByToken
	 * @param type
	 * @return
	 */
	public Build quitAllowUpgrade(String token, Integer type) throws NoUpgradingException;
	
	
}
