package com.muzhi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muzhi.dao.ConfigDao;
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
import com.muzhi.service.ConfigService;

@Service
public class ConfigServiceImpl implements ConfigService {
    @Autowired
    private ConfigDao configDao;
	@Override
	public List<ConfigFood> getConfigFood() {
		return configDao.getConfigFood();
	}

	@Override
	public List<ConfigSkill> getConfigSkill() {
		return configDao.getConfigSkill();
	}

	@Override
	public List<ConfigStrength> getConfigStrength() {
		return configDao.getConfigStrength();
	}

	@Override
	public List<ConfigRole> getConfigRole() {
		return configDao.getConfigRole();
	}

	@Override
	public List<ConfigRestaurant> getConfigRestaurant() {
		return configDao.getConfigRestaurant();
	}

	@Override
	public List<ConfigManor> getConfigManor() {
		return configDao.getConfigManor();
	}

	@Override
	public List<ConfigResearch> getConfigResearch() {
		return configDao.getConfigResearch();
	}

	@Override
	public List<ConfigResources> getConfigResources() {
		return configDao.getConfigResources();
	}

	@Override
	public List<ConfigCookcenter> getConfigCookcenter() {
		return configDao.getConfigCookcenter();
	}

	@Override
	public List<ConfigFacilities> getConfigFacilities() {
		return configDao.getConfigFacilities();
	}

	@Override
	public List<ConfigArticle> getConfigArticle() {
		return configDao.getConfigArticle();
	}

	@Override
	public List<ConfigOrder> getConfigOrder() {
		return configDao.getConfigOrder();
	}

	@Override
	public List<ConfigRate> getConfigRate() {
		return configDao.getConfigRate();
	}

	@Override
	public List<ConfigTask> getConfigTask() {
		return configDao.getConfigTask();
	}

	@Override
	public List<ConfigAdvancedProp> getConfigAdvancedProp() {
		return configDao.getConfigAdvancedProp();
	}

	@Override
	public List<ConfigLove> getConfigLove() {
		return configDao.getConfigLove();
	}

	@Override
	public List<Configfame> getConfigfame() {
		return configDao.getConfigfame();
	}

	@Override
	public List<Configadvertise> getConfigadvertise() {
		return configDao.getConfigadvertise();
	}

	@Override
	public void insertConfigFood(List<ConfigFood> configFoodList) {
		configDao.insertConfigFood(configFoodList);
		
	}

	@Override
	public void insertConfigSkill(List<ConfigSkill> configSkillList) {
		configDao.insertConfigSkill(configSkillList);
		
	}

	@Override
	public void insertConfigStrength(List<ConfigStrength> configStrengthList) {
		configDao.insertConfigStrength(configStrengthList);
		
	}

	@Override
	public void insertConfigRole(List<ConfigRole> configRoleList) {
		configDao.insertConfigRole(configRoleList);
		
	}

	@Override
	public void insertConfigRestaurant(List<ConfigRestaurant> configRestaurantList) {
		configDao.insertConfigRestaurant(configRestaurantList);
		
	}

	@Override
	public void insertConfigManor(List<ConfigManor> configManorList) {
		configDao.insertConfigManor(configManorList);
		
	}

	@Override
	public void insertConfigResearch(List<ConfigResearch> configResearchList) {
		configDao.insertConfigResearch(configResearchList);
		
	}

	@Override
	public void insertConfigResources(List<ConfigResources> configResourcesList) {
		configDao.insertConfigResources(configResourcesList);
		
	}

	@Override
	public void insertConfigCookcenter(List<ConfigCookcenter> configCookcenterList) {
		configDao.insertConfigCookcenter(configCookcenterList);
		
	}

	@Override
	public void insertConfigFacilities(List<ConfigFacilities> configFacilitiesList) {
		configDao.insertConfigFacilities(configFacilitiesList);
		
	}

	@Override
	public void insertConfigArticle(List<ConfigArticle> configArticleList) {
		configDao.insertConfigArticle(configArticleList);
		
	}

	@Override
	public void insertConfigOrder(List<ConfigOrder> configOrderList) {
		configDao.insertConfigOrder(configOrderList);
		
	}

	@Override
	public void insertConfigRate(List<ConfigRate> configRateList) {
		configDao.insertConfigRate(configRateList);
		
	}

	@Override
	public void insertConfigTask(List<ConfigTask> configTaskList) {
		configDao.insertConfigTask(configTaskList);
		
	}

	@Override
	public void insertConfigAdvancedProp(List<ConfigAdvancedProp> configAdvancedPropList) {
		configDao.insertConfigAdvancedProp(configAdvancedPropList);
		
	}

	@Override
	public void insertConfigLove(List<ConfigLove> configLoveList) {
		configDao.insertConfigLove(configLoveList);
		
	}

	@Override
	public void insertConfigfame(List<Configfame> configfameList) {
		configDao.insertConfigfame(configfameList);
		
	}

	@Override
	public void insertConfigadvertise(List<Configadvertise> configadvertiseList) {
		configDao.insertConfigadvertise(configadvertiseList);
		
	}

	

}
