package com.muzhi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muzhi.dao.FarmDao;
import com.muzhi.model.Farm;
import com.muzhi.model.configbean.ConfigResources;
import com.muzhi.model.state.LEVEL_STATE;
import com.muzhi.service.FarmService;
import com.muzhi.service.config.InitConfig;
@Service
public class FarmServiceImpl implements FarmService {

	@Autowired
	FarmDao farmDao;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return farmDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Farm record) {
		return farmDao.insert(record);
	}

	@Override
	public int insertSelective(Farm record) {
		return farmDao.insertSelective(record);
	}

	@Override
	public Farm selectByPrimaryKey(Integer id) {
		return farmDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Farm record) {
		return farmDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Farm record) {
		return farmDao.updateByPrimaryKey(record);
	}
	// 建筑的ID。1，餐厅；2，农场；3，果园；4，菜园；5，牧场；6，鱼塘；7，美食中心；8，订单中心；9，交易中心
	@Override
	public void initFarm(Integer userId) {
		ConfigResources farmConfig = InitConfig.getInstance().getResourcesMap().get(Integer.parseInt(2+""+ LEVEL_STATE.INIT_LEVEL.getNum()));
		Farm farm =new Farm(userId, farmConfig.getLevel(), farmConfig.getId(), farmConfig.getBuildid(),
				farmConfig.getOutput(), farmConfig.getMaxcapacity(), LEVEL_STATE.INIT_LEVEL.getStockNum());
		insert(farm);	
	}

}
