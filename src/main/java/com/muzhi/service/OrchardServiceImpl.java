package com.muzhi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muzhi.dao.OrchardDao;
import com.muzhi.model.Orchard;
import com.muzhi.model.configbean.ConfigResources;
import com.muzhi.model.state.LEVEL_STATE;
import com.muzhi.service.config.InitConfig;
@Service
public class OrchardServiceImpl implements OrchardService {

	@Autowired
	OrchardDao orchardDao;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return orchardDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Orchard record) {
		return orchardDao.insert(record);
	}

	@Override
	public int insertSelective(Orchard record) {
		return orchardDao.insertSelective(record);
	}

	@Override
	public Orchard selectByPrimaryKey(Integer id) {
		return orchardDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Orchard record) {
		return orchardDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Orchard record) {
		return orchardDao.updateByPrimaryKey(record);
	}
	// 建筑的ID。1，餐厅；2，农场；3，果园；4，菜园；5，牧场；6，鱼塘；7，美食中心；8，订单中心；9，交易中心
	@Override
	public void initOrchard(Integer userId) {
		ConfigResources orchardConfig = InitConfig.getInstance().getResourcesMap().get(Integer.parseInt(3+""+ LEVEL_STATE.INIT_LEVEL.getNum()));
		insert(new Orchard(userId, orchardConfig.getLevel(), orchardConfig.getId(),
				orchardConfig.getBuildid(), orchardConfig.getOutput(), orchardConfig.getMaxcapacity(),
				LEVEL_STATE.INIT_LEVEL.getStockNum()));
		
	}

}
