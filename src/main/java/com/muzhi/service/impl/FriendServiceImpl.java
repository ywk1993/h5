package com.muzhi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muzhi.dao.FriendDao;
import com.muzhi.dao.MessageDao;
import com.muzhi.model.Friend;
import com.muzhi.model.FriendN;
import com.muzhi.model.FriendT;
import com.muzhi.model.Hall;
import com.muzhi.model.Result;
import com.muzhi.model.User;
import com.muzhi.model.vo.Build;
import com.muzhi.model.vo.FriendVo;
import com.muzhi.model.vo.Friends;
import com.muzhi.model.vo.Weixin;
import com.muzhi.service.BuildService;
import com.muzhi.service.FriendService;
import com.muzhi.service.HallService;
import com.muzhi.service.LoginService;
import com.muzhi.service.UserService;
import com.muzhi.util.ResultUtil;

@Service
public class FriendServiceImpl implements FriendService{

	@Autowired
	FriendDao friendDao;
	@Autowired
	LoginService loginService;
	@Autowired
	MessageDao messageDao;
	@Autowired
	UserService userService;
	@Autowired
	HallService hallService;
	@Autowired
	BuildService buildService;
	
	
	@Override
	public List<Friend> selectList(Friend friend) {
		return friendDao.selectList(friend);
	}

	@Override
	public FriendVo selectFriendsList(Integer id) {
		List<Friends> selectList = friendDao.selectFriendsList(new Friend(id));
		List<Friends> selectStrangers = friendDao.selectStrangers(new Friend(id));
		String selectById = loginService.selectById(id);
		List<Friends> selectFriendList = messageDao.selectFriendList(selectById);
		FriendVo friendVo = new FriendVo();
		friendVo.setUid(selectById);
		ArrayList<FriendT> friendList = new ArrayList<FriendT>();
		ArrayList<Weixin> weixinList = new ArrayList<Weixin>();
		ArrayList<Weixin> strangers = new ArrayList<Weixin>();
		ArrayList<Weixin> newFriends = new ArrayList<Weixin>();
		for (Friends friends : selectList) {
			FriendT friendTemp = new FriendT();
			friendTemp.setId(selectById);
			friendTemp.setFriendid(friends.getFriendid());
			friendList.add(friendTemp);
			
			Weixin weixin = new Weixin();
			weixin.setFriendid(Integer.parseInt(friends.getFriendid()));
			// 暂时处理
			weixin.setNickName(friends.getNickname() == null ? friends.getUsername() : friends.getNickname());
			weixin.setOpenid(friends.getOpenid());
			weixin.setAvatarUrl(friends.getAvatarUrl());
			weixin.setRank(friends.getRank());
			weixin.setRankName(friends.getRankName());
			weixinList.add(weixin);
		}
		for (Friends friends : selectStrangers) {
			Weixin weixin = new Weixin();
			weixin.setFriendid(Integer.parseInt(friends.getFriendid()));
			// 暂时处理
			weixin.setNickName(friends.getNickname() == null ? friends.getUsername() : friends.getNickname());
			weixin.setOpenid(friends.getOpenid());
			weixin.setAvatarUrl(friends.getAvatarUrl());
			weixin.setRank(friends.getRank());
			weixin.setRankName(friends.getRankName());
			strangers.add(weixin);
		}
		for (Friends friends : selectFriendList) {
			Weixin weixin = new Weixin();
			weixin.setFriendid(Integer.parseInt(friends.getFriendid()));
			// 暂时处理
			weixin.setNickName(friends.getNickname() == null ? friends.getUsername() : friends.getNickname());
			weixin.setOpenid(friends.getOpenid());
			weixin.setAvatarUrl(friends.getAvatarUrl());
			weixin.setRank(friends.getRank());
			weixin.setRankName(friends.getRankName());
			newFriends.add(weixin);
		}
		
		friendVo.setFriends(friendList);
		friendVo.setWeixins(weixinList);
		friendVo.setNum(friendList.size());
		friendVo.setStrangers(strangers);
		friendVo.setNewFriends(newFriends);
		return friendVo;
	}
	
	
	@Override
	public void insertN(FriendN friend) {
		friendDao.insertN(friend);
	}

	@Override
	public void insert(Friend friend) {
		friendDao.insert(friend);
	}

	@Override
	public Result getFriendHall(String token, Integer friendId) {
		User user = userService.getUserByToken(token);
		if(null==user) {
			return ResultUtil.error(1, "登录已过期");
		}
		
		User friend = userService.getUserByUid(friendId);
		if(null==friend) {
			return ResultUtil.error(500, "访问好友大厅失败");
		}
		
		Hall hall = hallService.getHall(friend);
		
		return ResultUtil.success(hall, 0);
	}

	@Override
	public Result getFriendBuildAll(String token, Integer friendId) {
		User user = userService.getUserByToken(token);
		if(null==user) {
			return ResultUtil.error(1, "登录已过期");
		}
		
		User friend = userService.getUserByUid(friendId);
		if(null==friend) {
			return ResultUtil.error(500, "访问好友庄园失败");
		}
		
		Build build = buildService.getBuildAll(friend);
		
		return ResultUtil.success(build, 0);
	}

}



