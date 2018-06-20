package com.muzhi.model.vo;

import java.util.List;

import com.muzhi.model.Friend;
import com.muzhi.model.FriendT;

public class FriendVo {
	String uid;
	List<FriendT> friends;
	List<Weixin> weixins;
	List<Weixin> strangers;
	List<Weixin> newFriends;
	Integer num;
	
	public List<Weixin> getNewFriends() {
		return newFriends;
	}
	public void setNewFriends(List<Weixin> newFriends) {
		this.newFriends = newFriends;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public List<FriendT> getFriends() {
		return friends;
	}
	public void setFriends(List<FriendT> friends) {
		this.friends = friends;
	}
	public List<Weixin> getWeixins() {
		return weixins;
	}
	public void setWeixins(List<Weixin> weixins) {
		this.weixins = weixins;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public List<Weixin> getStrangers() {
		return strangers;
	}
	public void setStrangers(List<Weixin> strangers) {
		this.strangers = strangers;
	}
}
