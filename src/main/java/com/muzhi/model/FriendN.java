package com.muzhi.model;
/**
 * 好友
 * @author yany
 *
 */
/*data {
	FriendList{
		id:1
		friendid:2	
	}, 
	nick : {
		friendid:2, 
		nickname:
	} , 
	avatarUrl : {
		
	}
}
*/
public class FriendN {
	/**
	 * 用户id
	 */
	private String id;
	
	/**
	 * 好友id
	 */
	private String friendid;
	
	public FriendN() {
		super();
	}

	public FriendN(String id, String friendid) {
		super();
		this.id = id;
		this.friendid = friendid;
	}
	
	/**
	 * @param id
	 */
	public FriendN(String id) {
		super();
		this.id = id;
	}

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
	
	
}
