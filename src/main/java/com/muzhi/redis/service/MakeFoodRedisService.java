package com.muzhi.redis.service;

/**
 * 
 * @author Yuwk
 *
 * 2017年11月22日
 */
public interface MakeFoodRedisService {

    /**
     * 根据index设置做菜的键值
     * @param index
     * 
     */
	public void setTime(Integer index,Integer userid);
	/**
	 * 根据index获取键值
	 * @param index
	 * @return
	 */
	public Integer getTime(Integer index,Integer userid);
	/**
	 * 设置键存活时间
	 * @param uid
	 * @return 成功 true--失败 false
	 */
	public Boolean expireToken(Integer index,Integer userid,long seconds);
	/**
	 * 获取键值存活时间
	 * @return
	 */
	public Long getExpire(Integer index,Integer userid);

}
