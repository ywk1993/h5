package com.muzhi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.muzhi.model.Friend;
import com.muzhi.model.FriendN;
import com.muzhi.model.vo.Friends;

public interface FriendDao {
	List<Friend> selectList(Friend friend);
	List<Friends> selectFriendsList(Friend friend);
	void insert(Friend friend);
	void insertN(FriendN friend);
	Friend getOneFriend(@Param("userId") Integer userId,@Param("friendId") Integer friendId);
	List<Friends> selectStrangers(Friend friend);
	/**
	 * 删除好友
	 * @param userId
	 * @param friendId
	 */
	void deleteFriend(@Param("userId") Integer userId,@Param("friendId") Integer friendId);
}
