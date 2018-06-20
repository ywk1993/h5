package com.muzhi.service.impl;

import java.util.Date;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muzhi.dao.LoginDao;
import com.muzhi.model.Login;
import com.muzhi.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	LoginDao loginDao;
	
	@Override
	public Integer insert(String username, String passwd, String openid, String nickName, String avatarUrl) {
		Login login = new Login();
		login.setPasswd(passwd);
		login.setUsername(username);
		login.setRegisterTime(new Date());
		login.setNickName(nickName);
		login.setAvatarUrl(avatarUrl);
		login.setOpenid(openid);
		loginDao.insert(login);
		return login.getId();
	}

	@Override
	public Login selectOne(String username, String passwd) {
		return loginDao.selectOne(username, passwd);
	}

	@Override
	public String selectWeixin(String openid) {
		return loginDao.selectFromWeixin(openid);
	}

	@Override
	public Integer updateUid(Integer id, String uid) {
		return loginDao.updateUid(id, uid);
	}

	@Override
	public void updateUsername(Integer id, String username) {
		loginDao.updateUsername(id, username);
	}

	@Override
	public String selectById(Integer id) {
		return loginDao.selectById(id);
	}

	@Override
	public void updateObject(Login selectOne) {
		loginDao.updateObject(selectOne);
	}
	
}
