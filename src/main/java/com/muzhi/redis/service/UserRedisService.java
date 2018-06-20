package com.muzhi.redis.service;

import com.muzhi.model.User;
/**
 * 
 * @author Yuwk
 *
 * 2017年11月22日
 */
public interface UserRedisService {

    /**
     * 根据token设置用户
     * @param token
     * @param user
     */
	public void setUser(String token, User user);
	/**
	 * 根据token获取用户
	 * @param token
	 * @return
	 */
	public User getUser(String token);
	/**
	 * 设置键存活时间
	 * @param uid
	 * @return 成功 true--失败 false
	 */
    public Boolean expireToken(String token,long seconseconds);
    /**
     * 增加人数
     */
    public void IncrUser();
    /**
     * 减少人数
     */
    public void DecrUser();
    /**
     * 获取在线人数
     * @return
     */
    public int getOnlineNumber();
    /**
	 * 刷新token
	 * @param token
	 * @param user
	 */
	public void setRefreshUser(String token,User user);
    /**
     * 设置刷新token的存活时间
     * @param token
     * @param seconseconds
     * @return
     */
    public Boolean expireRefreshToken(String token,long seconseconds);
    /**
     * 
     * @param token
     * @param user
     */
    public User getRefreshUser(String token);
}
