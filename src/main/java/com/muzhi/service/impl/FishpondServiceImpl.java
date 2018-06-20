package com.muzhi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muzhi.dao.FishpondDao;
import com.muzhi.model.Fishpond;
import com.muzhi.model.configbean.ConfigResources;
import com.muzhi.model.state.LEVEL_STATE;
import com.muzhi.service.FishpondService;
import com.muzhi.service.config.InitConfig;
@Service
public class FishpondServiceImpl implements FishpondService {

	@Autowired
	FishpondDao fishpondDao;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return fishpondDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Fishpond record) {
		return fishpondDao.insert(record);
	}

	@Override
	public int insertSelective(Fishpond record) {
		return fishpondDao.insertSelective(record);
	}

	@Override
	public Fishpond selectByPrimaryKey(Integer id) {
		return fishpondDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Fishpond record) {
		return fishpondDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Fishpond record) {
		return fishpondDao.updateByPrimaryKey(record);
	}
	// 建筑的ID。1，餐厅；2，农场；3，果园；4，菜园；5，牧场；6，鱼塘；7，美食中心；8，订单中心；9，交易中心
	@Override
	public void initFishpond(Integer userId) {
		ConfigResources fishpondConfig = InitConfig.getInstance().getResourcesMap().get(Integer.parseInt(6+""+ LEVEL_STATE.INIT_LEVEL.getNum()));
		insert(new Fishpond(userId, fishpondConfig.getLevel(), fishpondConfig.getId(),
				fishpondConfig.getBuildid(), fishpondConfig.getOutput(), fishpondConfig.getMaxcapacity(),
				LEVEL_STATE.INIT_LEVEL.getStockNum()));
		
	}
	

}
