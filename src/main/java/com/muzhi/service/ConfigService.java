package com.muzhi.service;

import java.util.List;
import com.muzhi.model.configbean.ConfigAdvancedProp;
import com.muzhi.model.configbean.ConfigArticle;
import com.muzhi.model.configbean.ConfigCookcenter;
import com.muzhi.model.configbean.ConfigFacilities;
import com.muzhi.model.configbean.ConfigFood;
import com.muzhi.model.configbean.ConfigLove;
import com.muzhi.model.configbean.ConfigManor;
import com.muzhi.model.configbean.ConfigOrder;
import com.muzhi.model.configbean.ConfigRate;
import com.muzhi.model.configbean.ConfigResearch;
import com.muzhi.model.configbean.ConfigResources;
import com.muzhi.model.configbean.ConfigRestaurant;
import com.muzhi.model.configbean.ConfigRole;
import com.muzhi.model.configbean.ConfigSkill;
import com.muzhi.model.configbean.ConfigStrength;
import com.muzhi.model.configbean.ConfigTask;
import com.muzhi.model.configbean.Configadvertise;
import com.muzhi.model.configbean.Configfame;


public interface ConfigService {
	public List<ConfigFood> getConfigFood();
	public List<ConfigSkill> getConfigSkill();
	public List<ConfigStrength> getConfigStrength();
	public List<ConfigRole> getConfigRole();
	public List<ConfigRestaurant> getConfigRestaurant();
	public List<ConfigManor> getConfigManor();
	public List<ConfigResearch> getConfigResearch();
	public List<ConfigResources> getConfigResources();
	public List<ConfigCookcenter> getConfigCookcenter();
	public List<ConfigFacilities> getConfigFacilities();
	public List<ConfigArticle> getConfigArticle();
	public List<ConfigOrder> getConfigOrder();
	public List<ConfigRate> getConfigRate();
	public List<ConfigTask> getConfigTask();
	public List<ConfigAdvancedProp> getConfigAdvancedProp();
	public List<ConfigLove> getConfigLove();//16
	public List<Configfame> getConfigfame();//
	public List<Configadvertise> getConfigadvertise();//18
	
	void insertConfigFood(List<ConfigFood> configFoodList);
	void insertConfigSkill(List<ConfigSkill> configSkillList);
	void insertConfigStrength(List<ConfigStrength> configStrengthList);
	void insertConfigRole(List<ConfigRole> configRoleList);
	void insertConfigRestaurant(List<ConfigRestaurant> configRestaurantList);
	void insertConfigManor(List<ConfigManor> configManorList);
	void insertConfigResearch(List<ConfigResearch> configResearchList);
	void insertConfigResources(List<ConfigResources> configResourcesList);
	void insertConfigCookcenter(List<ConfigCookcenter> configCookcenterList);
	void insertConfigFacilities(List<ConfigFacilities> configFacilitiesList);
	void insertConfigArticle(List<ConfigArticle> configArticleList);
	void insertConfigOrder(List<ConfigOrder> configOrderList);
	void insertConfigRate(List<ConfigRate> configRateList);
	void insertConfigTask(List<ConfigTask> configTaskList);
	void insertConfigAdvancedProp(List<ConfigAdvancedProp> configAdvancedPropList);
	void insertConfigLove(List<ConfigLove> configLoveList);
	void insertConfigfame(List<Configfame> configfameList);
	void insertConfigadvertise(List<Configadvertise> configadvertiseList);
}
