package com.muzhi.service;
/**
 * 微信服务
 * @author yany
 *
 */
public interface WeixinService {
	/**
	 * 获取openid
	 * @param code由前端传
	 * @return 
	 */
	public String getOpenId(String js_code);
	/**
	 * 查看是否有openid对应的用户，如果没有创建用户，如果有，最后都返回用户username
	 * @param openid
	 * @return
	 */
	public String queryAccount(String openid);
}
