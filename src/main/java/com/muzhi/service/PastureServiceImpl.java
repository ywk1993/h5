package com.muzhi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muzhi.dao.PastureDao;
import com.muzhi.model.Pasture;
import com.muzhi.model.configbean.ConfigResources;
import com.muzhi.model.state.LEVEL_STATE;
import com.muzhi.service.config.InitConfig;
@Service
public class PastureServiceImpl implements PastureService{
	@Autowired
	PastureDao pastureDao;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return pastureDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Pasture record) {
		return pastureDao.insert(record);
	}

	@Override
	public int insertSelective(Pasture record) {
		return pastureDao.insertSelective(record);
	}

	@Override
	public Pasture selectByPrimaryKey(Integer id) {
		return pastureDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Pasture record) {
		return pastureDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Pasture record) {
		return pastureDao.updateByPrimaryKey(record);
	}
	// 建筑的ID。1，餐厅；2，农场；3，果园；4，菜园；5，牧场；6，鱼塘；7，美食中心；8，订单中心；9，交易中心
	@Override
	public void initPasture(Integer userId) {
		ConfigResources pastureConfig = InitConfig.getInstance().getResourcesMap().get(Integer.parseInt(5+""+ LEVEL_STATE.INIT_LEVEL.getNum()));
		insert(new Pasture(userId, pastureConfig.getLevel(), pastureConfig.getId(),
				pastureConfig.getBuildid(), pastureConfig.getOutput(), pastureConfig.getMaxcapacity(),
				LEVEL_STATE.INIT_LEVEL.getStockNum()));
		
	}

}
