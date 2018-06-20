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
public class Friend {
	/**
	 * 用户id
	 */
	private Integer id;
	
	/**
	 * 好友id
	 */
	private Integer friendid;
	
	public Friend() {
		super();
	}

	public Friend(Integer id, Integer friendid) {
		super();
		this.id = id;
		this.friendid = friendid;
	}
	
	/**
	 * @param id
	 */
	public Friend(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFriendid() {
		return friendid;
	}

	public void setFriendid(Integer friendid) {
		this.friendid = friendid;
	}
	
	
}
