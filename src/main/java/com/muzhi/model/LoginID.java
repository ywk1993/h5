package com.muzhi.model;

import java.io.Serializable;

public class LoginID implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String userID;

	
	/**
	 * 
	 */
	public LoginID() {
		super();
	}

	/**
	 * @param userID
	 */
	public LoginID(String userID) {
		super();
		this.userID = userID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	
}