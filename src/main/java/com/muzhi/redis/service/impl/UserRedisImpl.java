package com.muzhi.redis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.muzhi.model.User;
import com.muzhi.redis.SpringRedisService;
import com.muzhi.redis.service.UserRedisService;
/**
 * 
 * @author Yuwk
 *
 * 2017年11月22日
 */
@Service
public class UserRedisImpl implements UserRedisService {
	@Autowired
	private SpringRedisService springRedis;
	
	//所有一级键或者二级键都应加英文冒号，便于redis工具分级查看
	private static final String TAG = "UserAccess:";
	private static final String ONLINE_USER_NUMBER = "OnlineUserNumber";
	@Override
	public void setUser(String token, User user) {
		springRedis.set(TAG+token, user);
	}

	@Override
	public User getUser(String token) {
		
		return springRedis.get(TAG+token, User.class);
	}

	@Override
	public Boolean expireToken(String token,long seconds) {
		
		return springRedis.expire(TAG+token, seconds);
	}

	@Override
	public void IncrUser() {
		Integer number =springRedis.get(ONLINE_USER_NUMBER,Integer.class);
		if (null==number) {
			springRedis.set(ONLINE_USER_NUMBER, 0);	
		}
		springRedis.Incr(ONLINE_USER_NUMBER);
		
	}

	@Override
	public void DecrUser() {
		springRedis.Decr(ONLINE_USER_NUMBER);
		
	}

	@Override
	public int getOnlineNumber() {
		Integer number =springRedis.get(ONLINE_USER_NUMBER,Integer.class);
		if (null==number) {
			return 0;
		}
		return number;
	}

	@Override
	public void setRefreshUser(String token, User user) {
		springRedis.set(token, user);
		
	}

	@Override
	public Boolean expireRefreshToken(String token, long seconds) {
		return springRedis.expire(token, seconds);
	}

	@Override
	public User getRefreshUser(String token) {
		return springRedis.get(token, User.class);
		
	}

}
