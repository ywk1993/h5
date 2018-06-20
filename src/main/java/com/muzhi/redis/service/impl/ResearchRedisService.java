package com.muzhi.redis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muzhi.redis.SpringRedisService;
@Service
public class ResearchRedisService implements com.muzhi.redis.service.ResearchRedisService {
	@Autowired
	private SpringRedisService springRedis;
	private static final String RESEARCH = "Research:";
	private static final String FOODID = "FoodID:";
	@Override
	public void setTime(Integer userid) {
		springRedis.set(RESEARCH+userid, userid);

	}

	@Override
	public Integer getTime(Integer userid) {
		return springRedis.get(RESEARCH+userid, Integer.class);
	}

	@Override
	public Boolean expireToken(Integer userid, long seconds) {
		return springRedis.pexpire(RESEARCH+userid, seconds);
	}

	@Override
	public Long getExpire(Integer userid) {
		return springRedis.getpExpire(RESEARCH+userid);
	}

	@Override
	public void setFoodId(int userid, int foodID) {
		springRedis.set(FOODID + userid, foodID);
	}
	
	@Override
	public Integer getFoodId(Integer userid) {
		return springRedis.get(FOODID + userid, Integer.class);
	}

}
