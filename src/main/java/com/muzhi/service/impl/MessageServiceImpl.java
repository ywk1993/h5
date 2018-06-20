package com.muzhi.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muzhi.dao.FriendDao;
import com.muzhi.dao.LoginDao;
import com.muzhi.dao.MessageDao;
import com.muzhi.model.FriendN;
import com.muzhi.model.Message;
import com.muzhi.model.Result;
import com.muzhi.model.state.MessageType;
import com.muzhi.service.FriendService;
import com.muzhi.service.MessageService;
import com.muzhi.util.ResultUtil;
@Service
public class MessageServiceImpl implements MessageService {
	@Autowired
	MessageDao messageDao;
	@Autowired
	FriendService friendService;
	@Autowired
	FriendDao friendDao;
	@Autowired
	LoginDao loginDao;
	
	public static final Logger log = LoggerFactory.getLogger("com.muzhi.service.impl.MessageServiceImpl");
	
	// 暂时适配添加一个，广播需修改
	@Override
	public Result sendMessage(MessageType messageType, String self, String[] others) {
		List<String> asList = Arrays.asList(others);
		if (asList.contains(self)) {
			return ResultUtil.error(5001, "不能添加自己为好友");
		}
		
		if (messageType == messageType.ADD_FRIEND_REQUEST) {
			for (int i = 0; i < others.length; i++) {
				Integer userId = loginDao.getUserIdByUid(Integer.parseInt(others[0]));
				if(null==userId) {
					return ResultUtil.error(5002, "添加的用户不存在");
				}
			}
			List<Message> myList = messageDao.selectList(self);
			for (Message message : myList) {
				// 判断是否发送过同样类型的有效消息
				if (message.getMessageResult().equals(messageType.getCode()) && self.equals(message.getUid())) {
					// do nothing
					return ResultUtil.success();
				}
			}
			
			List<Message> list = new ArrayList<Message>();
			for (String other : others) {
				UUID uuid = UUID.randomUUID();
				Message message = new Message(uuid.toString(), self, other, messageType.ADD_FRIEND_REQUEST.getDesc(), messageType.ADD_FRIEND_REQUEST.getCode(), new Date());
				list.add(message);
			}
			messageDao.insertList(list);
		} else if (messageType == messageType.ADD_FRIEND_AGRREE) {
			List<Message> list = messageDao.selectList(self);
			for (Message other : list) {
				if (other.getUid().equals(others[0])) {
					other.setMessageResult(messageType.UNAVAILABLE.getCode());
					other.setMessageContent(messageType.ADD_FRIEND_AGRREE.getDesc());
					other.setDate(new Date());
				}
			}
			messageDao.updateList(list);
			friendService.insertN(new FriendN(self, others[0]));
			
		} else if (messageType == messageType.ADD_FRIEND_IGNORE){
			List<Message> list = messageDao.selectList(self);
			for (Message other : list) {
				if (other.getUid().equals(others[0])) {
					other.setMessageResult(messageType.UNAVAILABLE.getCode());
					other.setMessageContent(messageType.ADD_FRIEND_IGNORE.getDesc());
					other.setDate(new Date());
				}
			}
			messageDao.updateList(list);
		}else if(messageType == messageType.DELETE_FRIEND) {
			Integer uid = Integer.parseInt(self);
			Integer fid = Integer.parseInt(others[0]);
			if(null==uid ||fid==null) {
				return ResultUtil.error(5003, "删除失败");
			}
			Integer userId = loginDao.getUserIdByUid(uid);
			Integer friendId = loginDao.getUserIdByUid(fid);
			//删除好友
			friendDao.deleteFriend(userId, friendId);
			//把我从好友列表移除
			friendDao.deleteFriend(friendId, userId);
		} else {
			log.error("参数错误");
		}
		return ResultUtil.success();
	}

	@Override
	public List<Message> receiveMessage(MessageType messageType, String self) {
		List<Message> selectList = messageDao.selectList(self);
		return selectList;
	}
}

