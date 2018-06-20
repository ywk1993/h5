package com.muzhi.service;

import java.util.List;

import com.muzhi.model.Friend;
import com.muzhi.model.FriendN;
import com.muzhi.model.Result;
import com.muzhi.model.vo.FriendVo;

public interface FriendService {
	public List<Friend> selectList(Friend friend);
	public void insert(Friend friend);
	public void insertN(FriendN friend);
	public FriendVo selectFriendsList(Integer id);
	/**
	 * 获取好友大厅
	 * @param token
	 * @param friendId
	 * @return
	 */
	public Result getFriendHall(String token,Integer friendId);
	/**
	 * 获取好友庄园
	 * @param token
	 * @param friendId
	 * @return
	 */
	public Result getFriendBuildAll(String token,Integer friendId);
}
