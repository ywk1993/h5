package com.muzhi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muzhi.dao.AsherDao;
import com.muzhi.model.Asher;

@Service
public class AsherServiceImpl implements com.muzhi.service.AsherService {

	@Autowired
	AsherDao asherDao;
	
	@Override
	public int insertList(List<Asher> recordList) {
		return asherDao.insertList(recordList);
	}

	@Override
	public List<Asher> selectList(Asher asher) {
		return asherDao.selectList(asher);
	}

	@Override
	public Asher selectAshers(Integer userId,Integer index) {
		return asherDao.selectAshers(userId,index);
	}

	@Override
	public int updateByPrimaryKey(Asher record) {
		return asherDao.updateByPrimaryKey(record);
	}

	@Override
	public List<Asher> selectByUid(Integer userId) {
		
		return asherDao.selectByUid(userId);
	}

}
