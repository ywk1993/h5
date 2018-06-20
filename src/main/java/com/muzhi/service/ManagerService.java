package com.muzhi.service;

/**
* @author ykw
* @version 创建时间：2018年5月17日 下午2:27:12
*/
public interface ManagerService {
	/**
	 * 用户登录后置通知，这里调用游戏后台接口
	 * @param args
	 * @param loginIp
	 * @param phoneModel
	 */
	public void loginAfter(Object[] args,String loginIp,String phoneModel,String token);

}
