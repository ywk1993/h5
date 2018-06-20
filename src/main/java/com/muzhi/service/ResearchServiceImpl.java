package com.muzhi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muzhi.dao.ResearchDao;
import com.muzhi.model.Research;
import com.muzhi.model.ResearchKey;
@Service
public class ResearchServiceImpl implements ResearchService{
	@Autowired
	ResearchDao ResearchDao;

	@Override
	public int insert(Research record) {
		return ResearchDao.insert(record);
	}

	@Override
	public int insertSelective(Research record) {
		return ResearchDao.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(Research record) {
		return ResearchDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Research record) {
		return ResearchDao.updateByPrimaryKey(record);
	}
	@Override
	public int deleteByPrimaryKey(ResearchKey key) {
		return ResearchDao.deleteByPrimaryKey(key);
	}

	@Override
	public Research selectByPrimaryKey(ResearchKey key) {
		return ResearchDao.selectByPrimaryKey(key);
	}

}
