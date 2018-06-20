package com.muzhi.model;

import java.util.Date;

public class Message {
	String uuid;
	String uid;
	String friendid;
	String messageContent;
	Integer messageResult;
	Date date;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getFriendid() {
		return friendid;
	}
	public void setFriendid(String friendid) {
		this.friendid = friendid;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public Integer getMessageResult() {
		return messageResult;
	}
	public void setMessageResult(Integer messageResult) {
		this.messageResult = messageResult;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @param uuid
	 * @param uid
	 * @param friendid
	 * @param messageContent
	 * @param messageResult
	 * @param date
	 */
	public Message(String uuid, String uid, String friendid, String messageContent, Integer messageResult, Date date) {
		super();
		this.uuid = uuid;
		this.uid = uid;
		this.friendid = friendid;
		this.messageContent = messageContent;
		this.messageResult = messageResult;
		this.date = date;
	}
	/**
	 * 
	 */
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}