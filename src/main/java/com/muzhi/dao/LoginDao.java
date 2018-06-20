package com.muzhi.dao;

import org.apache.ibatis.annotations.Param;

import com.muzhi.model.Login;

/**
 * 登录相关数据访问对象
 * @author yan
 *
 */
public interface LoginDao {
//	void insert(@Param("username") String username, @Param("passwd") String passwd);
	Login selectOne(@Param("username") String username, @Param("passwd") String passwd);
	Integer insert(Login login);
	String selectFromWeixin(@Param("openid") String openid);
	Integer updateUsername(@Param("id")Integer id, @Param("username")String username);
	Integer updateUid(@Param("id")Integer id, @Param("uid")String uid);
	String selectById(@Param("id")Integer id);
	/**
	 * 通过uid获取用户id
	 * @param uid
	 * @return
	 */
	Integer getUserIdByUid(@Param("uid") Integer uid);
	
	Login selectByPrimaryKey(@Param("id") Integer id);
	/**
	 * 更新对象
	 * @param selectOne
	 */
	void updateObject(Login selectOne);
}
