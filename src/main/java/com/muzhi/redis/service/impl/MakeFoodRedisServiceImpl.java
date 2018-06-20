package com.muzhi.redis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muzhi.redis.SpringRedisService;
import com.muzhi.redis.service.MakeFoodRedisService;
@Service
public class MakeFoodRedisServiceImpl implements MakeFoodRedisService{

	@Autowired
	private SpringRedisService springRedis;
	private static final String MAKEFOOD = "MakeFood:";
	@Override
	public void setTime(Integer index, Integer userid) {
		springRedis.set(MAKEFOOD+index+userid, index);
		
	}

	@Override
	public Integer getTime(Integer index, Integer userid) {
		return springRedis.get(MAKEFOOD+index+userid, Integer.class);
	}

	@Override
	public Boolean expireToken(Integer index, Integer userid, long seconds) {
		return springRedis.pexpire(MAKEFOOD+index+userid, seconds);
	}

	@Override
	public Long getExpire(Integer index, Integer userid) {
		return springRedis.getpExpire(MAKEFOOD+index+userid);
	}

}
