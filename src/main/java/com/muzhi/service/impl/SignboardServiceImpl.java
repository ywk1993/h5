package com.muzhi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muzhi.dao.SignboardDao;
import com.muzhi.model.Signboard;
import com.muzhi.model.User;
import com.muzhi.service.SignboardService;

@Service
public class SignboardServiceImpl implements SignboardService {
	
    @Autowired
    private SignboardDao signboardDao;
    
	@Override
	public Signboard getSignboard(Integer id) {
		
		return signboardDao.getSignboard(id);
	}

	@Override
	public void updateSignboard(Signboard signboard) {
		signboardDao.updateSignboard(signboard);
		
	}

	@Override
	public void insertSignboard(User userInit) {
		Signboard signboard =new Signboard(userInit.getId(), 1, 20);//暂定20 日后要改
		signboardDao.insertSignboard(signboard);
		
	}

}
