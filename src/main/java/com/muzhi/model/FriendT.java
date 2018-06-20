package com.muzhi.model;

public class FriendT {
	/**
	 * 用户id
	 */
	private String id;
	
	/**
	 * 好友id
	 */
	private String friendid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFriendid() {
		return friendid;
	}

	public void setFriendid(String friendid) {
		this.friendid = friendid;
	}

	/**
	 * @param id
	 * @param friendid
	 */
	public FriendT(String id, String friendid) {
		super();
		this.id = id;
		this.friendid = friendid;
	}

	/**
	 * 
	 */
	public FriendT() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
