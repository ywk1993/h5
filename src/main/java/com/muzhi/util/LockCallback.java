package com.muzhi.util;

/**
 * 
 * 功能描述:回调锁函数 锁定执行代码
 * 创建时间:2016年10月12日 下午1:48:53
 */
public interface LockCallback<T> {

	/**
	 * 执行锁定模块代码
	 * @return
	 */
	public T doInLock();
}
