package com.muzhi.redis;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import com.muzhi.dao.LoginRecordDao;
import com.muzhi.model.Constant;
import com.muzhi.model.LoginRecord;
import com.muzhi.redis.service.UserRedisService;

public class KeyExpirationListener extends MessageListenerAdapter   {
	
    @Autowired
    private UserRedisService userRedisImpl;
	
	@Autowired
	private LoginRecordDao loginRecordDao;
	
	@Autowired
	private SpringRedisService springRedis;
	
	private static final Logger logger = LoggerFactory.getLogger(KeyExpirationListener.class);
	
	@Override
	public void onMessage(Message message, byte[] pattern) {
		/*if (message.toString().contains("UserAccess:")) {
			logger.info("====================================用户登出=============================================");
			String token = message.toString().substring(11);
			String id=springRedis.get(token,String.class);
			if (!StringUtils.isBlank(id)) {
				LoginRecord loginRecord =loginRecordDao.selectByPrimaryKey(id);
				Date loginTime = loginRecord.getLoginTime();
				loginRecord.setLogoutTime(new Date(System.currentTimeMillis()-Constant.TOKENEXPIRE*1000));
				//计算在线时长
				long l = loginRecord.getLogoutTime().getTime() - loginTime.getTime();
		        Long onlineTimes = l/1000;
				loginRecord.setOnlineTime(onlineTimes);
				loginRecordDao.updateByPrimaryKey(loginRecord);
			}
			springRedis.delete(token);
			userRedisImpl.DecrUser();
		}*/
	}
	

}
