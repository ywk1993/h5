package com.muzhi.redis.service;

/**
 * 
 * @author Yuwk
 *
 * 2017年11月22日
 */
public interface ResearchRedisService {
	 /**
     * 根据index设置做菜的键值
     * @param index
     * 
     */
	public void setTime(Integer userid);
	/**
	 * 根据index获取键值
	 * @param index
	 * @return
	 */
	public Integer getTime(Integer userid);
	/**
	 * 设置键存活时间
	 * @param uid
	 * @return 成功 true--失败 false
	 */
	public Boolean expireToken(Integer userid,long seconds);
	/**
	 * 获取键值存活时间
	 * @return
	 */
	public Long getExpire(Integer userid);
	/**
	 * 设置FoodID
	 * @param foodID
	 */
	void setFoodId(int userid, int foodID);
	/**
	 * 释放FoodID
	 * @param foodID
	 * @param seconds
	 * @return
	 */
	Integer getFoodId(Integer foodId);
	
}
