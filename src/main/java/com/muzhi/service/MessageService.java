package com.muzhi.service;

import java.util.List;

import com.muzhi.model.Message;
import com.muzhi.model.Result;
import com.muzhi.model.state.MessageType;

/**
 * 消息服务
 * @author yany
 *
 */
public interface MessageService {
	/**
	 * 发送消息
	 */
	public Result sendMessage(MessageType messageType, String self, String[] others);
	/**
	 * 接收消息
	 * @return 
	 */
	public List<Message> receiveMessage(MessageType messageType, String self);
}
