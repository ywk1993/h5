package com.muzhi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muzhi.dao.ChefDao;
import com.muzhi.model.Chef;
import com.muzhi.service.ChefService;

@Service
public class ChefServiceImpl implements ChefService {

	@Autowired
	ChefDao chefDao;
	
	@Override
	public int insertList(List<Chef> recordList) {
		return chefDao.insertList(recordList);
	}

	@Override
	public List<Chef> selectList(Chef chef) {
		return chefDao.selectList(chef);
	}

	@Override
	public Chef selectByIndex(Integer userId,Integer index) {
		return chefDao.selectByIndex(userId,index);
	}

	@Override
	public List<Chef> selectChefs(Integer userId) {
		
		return null;
	}

	@Override
	public int updateByPrimaryKey(Chef record) {
		return chefDao.updateByPrimaryKey(record);
	}
	
}
