package com.muzhi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.muzhi.model.User;

public interface UserDao {
	User getUser(Integer id);
	void updateUser(User user);
	List<User> selectList(User user);
	Integer insert(User user);
	List<User> selectByIdList(@Param("idList") List<Integer> idList);
}