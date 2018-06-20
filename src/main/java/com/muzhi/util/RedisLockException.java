package com.muzhi.util;

/**
 * 
 * 功能描述:自定义redis 锁异常
 * 创建时间:2016年10月12日 上午11:48:24
 */
public class RedisLockException extends Exception {
	
	private static final long serialVersionUID = 4818906615369642748L;
	/**
	 * 自定义错误代码
	 */
	private String errorCode;
	/**
	 * 自定义错误信息
	 */
	private String message;

	public RedisLockException() {
	}

	public RedisLockException(Throwable cause) {
		super(cause);
	}

	public RedisLockException(String message) {
		super(message);
		this.message = message;
	}

	public RedisLockException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
		this.message = message;
	}

	public RedisLockException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
	}

	public RedisLockException(String errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
		this.message = message;
	}

	/**
	 * 获取自定义错误代码
	 * @return errorCode 自定义错误代码
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * 设置自定义错误代码
	 * @param errorCode 自定义错误代码
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * 获取自定义错误信息
	 * @return message 自定义错误信息
	 */
	@Override
	public String getMessage() {
		return message;
	}

	/**
	 * 设置自定义错误信息
	 * @param message 自定义错误信息
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
}
