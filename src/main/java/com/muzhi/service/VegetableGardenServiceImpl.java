package com.muzhi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muzhi.dao.VegetableGardenDao;
import com.muzhi.model.VegetableGarden;
import com.muzhi.model.configbean.ConfigResources;
import com.muzhi.model.state.LEVEL_STATE;
import com.muzhi.service.config.InitConfig;
@Service
public class VegetableGardenServiceImpl implements VegetableGardenService{
	@Autowired
	VegetableGardenDao VegetableGardenDao;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return VegetableGardenDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(VegetableGarden record) {
		return VegetableGardenDao.insert(record);
	}

	@Override
	public int insertSelective(VegetableGarden record) {
		return VegetableGardenDao.insertSelective(record);
	}

	@Override
	public VegetableGarden selectByPrimaryKey(Integer id) {
		return VegetableGardenDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(VegetableGarden record) {
		return VegetableGardenDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(VegetableGarden record) {
		return VegetableGardenDao.updateByPrimaryKey(record);
	}
	// 建筑的ID。1，餐厅；2，农场；3，果园；4，菜园；5，牧场；6，鱼塘；7，美食中心；8，订单中心；9，交易中心
	@Override
	public void initVegetableGarden(Integer userId) {
		ConfigResources vegetableGardenConfig = InitConfig.getInstance().getResourcesMap().get(Integer.parseInt(4+""+ LEVEL_STATE.INIT_LEVEL.getNum()));
		insert(new VegetableGarden(userId, vegetableGardenConfig.getLevel(),
				vegetableGardenConfig.getId(), vegetableGardenConfig.getBuildid(), vegetableGardenConfig.getOutput(),
				vegetableGardenConfig.getMaxcapacity(), LEVEL_STATE.INIT_LEVEL.getStockNum()));
		
	}

}
