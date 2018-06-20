package com.muzhi.service;

import org.apache.ibatis.annotations.Param;

import com.muzhi.model.Login;

public interface LoginService {
	public Integer insert(@Param("username") String username, @Param("passwd") String passwd, @Param("openid") String openid, @Param("nickName") String nickName, @Param("avatarUrl") String avatarUrl);
	public Login selectOne(@Param("username") String username, @Param("passwd") String passwd);
	public String selectWeixin(@Param("openid") String openid);
	public Integer updateUid(Integer id, String uid);
	public void updateUsername(Integer id, String username);
	public String selectById(Integer id);
	public void updateObject(Login selectOne);
}
