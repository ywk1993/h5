package com.muzhi.model.state;

/**
 * 消息类型
 * @author yany
 *
 */
public enum MessageType {
	ADD_FRIEND_REQUEST(10, "添加好友请求"),
	ADD_FRIEND_AGRREE(11, "同意添加好友"),
	ADD_FRIEND_IGNORE(12, "忽略添加好友"),
	DELETE_FRIEND(13,"删除好友"),
	UNAVAILABLE(0, "无效");
	
	Integer code;
	String desc;

	public static MessageType getMesssageType(Integer code) {
		if (code==ADD_FRIEND_REQUEST.getCode()) {
			return ADD_FRIEND_REQUEST;
		} else if (code==ADD_FRIEND_AGRREE.getCode()) {
			return ADD_FRIEND_AGRREE;
		} else if (code==ADD_FRIEND_IGNORE.getCode()) {
			return ADD_FRIEND_IGNORE;
		}else if(code==DELETE_FRIEND.getCode()){
			return DELETE_FRIEND;
		}else if (code==UNAVAILABLE.getCode()) {
			return UNAVAILABLE;
		} else {
			return null;
		}
	}
	

	public Integer getCode() {
		return code;
	}



	public void setCode(Integer code) {
		this.code = code;
	}



	public String getDesc() {
		return desc;
	}



	public void setDesc(String desc) {
		this.desc = desc;
	}



	/**
	 * @param code
	 * @param desc
	 */
	private MessageType(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}
}
