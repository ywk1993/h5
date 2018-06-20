package com.muzhi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muzhi.dao.CookBenchDao;
import com.muzhi.model.CookBench;
import com.muzhi.model.User;
import com.muzhi.service.CookBenchService;

@Service
public class CookBenchServiceImpl implements CookBenchService {

	@Autowired
	CookBenchDao cookBenchDao;
	
	@Override
	public CookBench getCookBench(Integer id) {
		return cookBenchDao.getCookBench(id);
	}

	@Override
	public void cookBenchInit(User user) {
		CookBench cookBench = new CookBench(user.getId(), 1, 100);
		cookBenchDao.insert(cookBench);
	}

	@Override
	public void updateCookBench(CookBench cookBench) {
		cookBenchDao.updateCookBench(cookBench);
		
	}

}
