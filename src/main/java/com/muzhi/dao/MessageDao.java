package com.muzhi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.muzhi.model.Message;
import com.muzhi.model.vo.Friends;

public interface MessageDao {
	
	void insertList(@Param("list")List<Message> list);

	List<Message> selectList(String self);

	void updateList(@Param("list")List<Message> list);
	
	List<Friends> selectFriendList(String self);
	
}
