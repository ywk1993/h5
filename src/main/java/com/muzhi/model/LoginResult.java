package com.muzhi.model;

import java.io.Serializable;

/**
 * 登录信息
 * @author Yuwk
 *
 * 2018年1月22日
 */
public class LoginResult implements Serializable{
   
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 短token
	 */
	private  String  token;
	
	/**
	 * 是否为新用户
	 */
	private  int isNewUser;
	
	/**
	 * 刷新token
	 */
	private String refreshToken;
	
	public LoginResult(String token, int isNewUser, String refreshToken) {
		super();
		this.token = token;
		this.isNewUser = isNewUser;
		this.refreshToken = refreshToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}



	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}



	public LoginResult() {
		super();
	}

	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getIsNewUser() {
		return isNewUser;
	}
	public void setIsNewUser(int isNewUser) {
		this.isNewUser = isNewUser;
	}

	@Override
	public String toString() {
		return "LoginResult [token=" + token + ", isNewUser=" + isNewUser + ", refreshToken=" + refreshToken + "]";
	}
	
	
}
