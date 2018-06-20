package com.muzhi.service;

import com.muzhi.model.Result;
import com.muzhi.model.User;

/**
* @author ykw
* @version 创建时间：2018年6月6日 下午3:37:48
*/
public interface PublicityService {
	/**
	 * 获取宣传页面详情
	 * @param token
	 * @return
	 */
	public Object getPublicityPage(String token);
	/**
	 * 宣传
	 * @param token
	 * @param type
	 * @return
	 */
	public Object publicity(String token,Integer type);
	/**
	 * 用户宣传名气初始化
	 * @param userInit
	 */
	public void publicityInit(User userInit);
	/**
	 * 获取名气信息
	 * @param token
	 * @return
	 */
	public Result getFameInfo(String token);
	/**
	 * 获取名气信息
	 * @param totalfame
	 * @return
	 */
	public int getFameLevel(Integer totalfame);
	/**
	 * 获取名气
	 * @param token
	 * @return
	 */
	public int getTotalFame(Integer uid);
	/**
	 * 获取宣传总名气
	 * @param uid
	 * @return
	 */
	public Integer getPublicityFame(Integer uid);
}
