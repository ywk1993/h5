package com.muzhi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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


public interface ConfigDao {
	//命名按照规范统一
	List<ConfigFood> getConfigFood();//1
	List<ConfigSkill> getConfigSkill();//2
	List<ConfigStrength> getConfigStrength();//3
	List<ConfigRole> getConfigRole();//4
	List<ConfigRestaurant> getConfigRestaurant();//5
	List<ConfigManor> getConfigManor();//6
	List<ConfigResearch> getConfigResearch();//7
	List<ConfigResources> getConfigResources();//8
	List<ConfigCookcenter> getConfigCookcenter();//9
	List<ConfigFacilities> getConfigFacilities();//10
	List<ConfigArticle> getConfigArticle();//11
	List<ConfigOrder> getConfigOrder();//12
	List<ConfigRate> getConfigRate();//13
	List<ConfigTask> getConfigTask();//14
	List<ConfigAdvancedProp> getConfigAdvancedProp();//15
	List<ConfigLove> getConfigLove();//16
	List<Configfame> getConfigfame();//17
	List<Configadvertise> getConfigadvertise();//18
	
	
	void insertConfigFood(@Param("configFoodList")List<ConfigFood> configFoodList);
	void insertConfigSkill(@Param("configSkillList")List<ConfigSkill> configSkillList);
	void insertConfigStrength(@Param("configStrengthList")List<ConfigStrength> configStrengthList);
	void insertConfigRole(@Param("configRoleList")List<ConfigRole> configRoleList);
	void insertConfigRestaurant(@Param("configRestaurantList")List<ConfigRestaurant> configRestaurantList);
	void insertConfigManor(@Param("configManorList")List<ConfigManor> configManorList);
	void insertConfigResearch(@Param("configResearchList")List<ConfigResearch> configResearchList);
	void insertConfigResources(@Param("configResourcesList")List<ConfigResources> configResourcesList);
	void insertConfigCookcenter(@Param("configCookcenterList")List<ConfigCookcenter> configCookcenterList);
	void insertConfigFacilities(@Param("configFacilitiesList")List<ConfigFacilities> configFacilitiesList);
	void insertConfigArticle(@Param("configArticleList")List<ConfigArticle> configArticleList);
	void insertConfigOrder(@Param("configOrderList")List<ConfigOrder> configOrderList);
	void insertConfigRate(@Param("configRateList")List<ConfigRate> configRateList);
	void insertConfigTask(@Param("configTaskList")List<ConfigTask> configTaskList);
	void insertConfigAdvancedProp(@Param("configAdvancedPropList")List<ConfigAdvancedProp> configAdvancedPropList);
	void insertConfigLove(@Param("configLoveList")List<ConfigLove> configLoveList);
	void insertConfigfame(@Param("configfameList")List<Configfame> configfameList);
	void insertConfigadvertise(@Param("configadvertiseList")List<Configadvertise> configadvertiseList);
}
