package com.muzhi.service.impl;

import java.util.List;

import com.muzhi.model.Friend;

public interface FriendService {
	List<Friend> selectList(Friend friend);
	void insert(Friend friend);
}
