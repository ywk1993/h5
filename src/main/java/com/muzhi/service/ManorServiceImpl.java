package com.muzhi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muzhi.dao.ManorDao;
import com.muzhi.model.BuildDictionary;
import com.muzhi.model.Manor;
import com.muzhi.model.ManorKey;
import com.muzhi.model.configbean.ConfigManor;
import com.muzhi.model.state.LEVEL_STATE;
import com.muzhi.service.config.InitConfig;

@Service
public class ManorServiceImpl implements ManorService {
	@Autowired
	ManorDao manorDao;

	@Override
	public int deleteByPrimaryKey(ManorKey key) {
		return manorDao.deleteByPrimaryKey(key);
	}

	@Override
	public int insert(Manor record) {
		return manorDao.insert(record);
	}

	@Override
	public int insertSelective(Manor record) {
		return manorDao.insertSelective(record);
	}

	@Override
	public Manor selectByPrimaryKey(ManorKey key) {
		return manorDao.selectByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKeySelective(Manor record) {
		return manorDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Manor record) {
		return manorDao.updateByPrimaryKey(record);
	}

	@Override
	public List<Manor> selectBySelectivePrimaryKey(ManorKey record) {
		return manorDao.selectBySelectivePrimaryKey(record);
	}

	@Override
	public void initManor(Integer userId) {
		BuildDictionary[] values = BuildDictionary.values();
		for (BuildDictionary buildDictionary : values) {
			ConfigManor configManor = InitConfig.getInstance().getManorMap().get(Integer.parseInt(buildDictionary.getTypeId() + "" + LEVEL_STATE.INIT_LEVEL.getNum()));
			insert(new Manor(userId, configManor.getId(), configManor.getBuildid(), configManor.getLevel(),
					configManor.getNeedgold(), configManor.getTime().longValue(),
					(Long) LEVEL_STATE.INIT_LEVEL.getleftTime().longValue(), 0L));
		}

	}

}
