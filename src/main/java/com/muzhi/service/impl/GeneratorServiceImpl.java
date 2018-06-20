package com.muzhi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muzhi.dao.GeneratorDao;
import com.muzhi.model.Generator;
import com.muzhi.service.GeneratorService;

@Service
public class GeneratorServiceImpl implements GeneratorService{

	@Autowired
	GeneratorDao generatorDao;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return generatorDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Generator record) {
		return generatorDao.insert(record);
	}

	@Override
	public int insertSelective(Generator record) {
		return generatorDao.insertSelective(record);
	}

	@Override
	public Generator selectByPrimaryKey(Integer id) {
		return generatorDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Generator record) {
		return generatorDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Generator record) {
		return generatorDao.updateByPrimaryKey(record);
	}

	@Override
	public void initGenerator(Integer userId) {
		long currentTimeMillis = System.currentTimeMillis();
		insert(new Generator(userId, currentTimeMillis, currentTimeMillis, currentTimeMillis,
				currentTimeMillis, currentTimeMillis, currentTimeMillis, currentTimeMillis, currentTimeMillis,
				currentTimeMillis, currentTimeMillis));
		
	}

}
