package com.muzhi.redis;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.Assert;

import com.fasterxml.jackson.core.type.TypeReference;
import com.muzhi.util.LockCallback;
import com.muzhi.util.RedisLockException;

public class SpringRedisServiceImpl implements SpringRedisService {
	
	private static final Logger logger = LoggerFactory.getLogger(SpringRedisServiceImpl.class);
	
    private RedisTemplate<String, Object> redisTemplate;
    
    /**
	 * key 使用的序列化
	 */
	private StringRedisSerializer keySerializer = null;
	
	/**
	 * value 使用的序列化 
	 */
	private GenericJackson2JsonRedisSerializer valueSerializer = null;
	
	/**
	 * 最大等待锁时间(秒)
	 */
	private static final long MAXWAITSECONDS = 5;
	
	/**
	 * 最大持有锁时间(秒)
	 */
	private static final long MAXLOCKSECONDS = 5;

	
	
	// 设置值
	@Override
	public Boolean set(String key, Object value) {
		boolean setSucess = false;
		try {
			// 序列化key value
			final byte[] rawKey = serializeKey(key);
			final byte[] rawValue = serializeValue(value);
			setSucess = redisTemplate.execute(new RedisCallback<Boolean>() {
				@Override
				public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				    connection.set(rawKey, rawValue);// 设置值
				    return true;
				}
			}, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return setSucess;
	}

	// 设置值 并设置key过期时间(秒)
	@Override
	public Boolean set(String key, Object value, final long seconds) {
		boolean setSucess = false;
		if (seconds <= 0) {
			return setSucess;
		}
		try {
			// 序列化key value
			final byte[] rawKey = serializeKey(key);
			final byte[] rawValue = serializeValue(value);
			setSucess = redisTemplate.execute(new RedisCallback<Boolean>() {
				@Override
				public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
					connection.setEx(rawKey, seconds, rawValue);// 设置值、过期时间
				    return true;
				}
			}, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return setSucess;
	}

	// 获取值
	@Override
	public <T> T get(String key, final Class<T> beanType) {
		try {
			// 序列化key
			final byte[] rawKey = serializeKey(key);
			return redisTemplate.execute(new RedisCallback<T>() {
				@Override
				public T doInRedis(RedisConnection connection) throws DataAccessException {
					byte[] value = connection.get(rawKey);
					return deserialize(value, beanType);// 反序列为对象
				}
			}, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 获取值 typeReference 序列化泛型   List<T> Map<String,T>等  示例：传入new TypeReference<List<T>>()
	@Override
	public <T> T get(String key, final TypeReference<T> typeReference) {
		try {
			// 序列化key
			final byte[] rawKey = serializeKey(key);
			return redisTemplate.execute(new RedisCallback<T>() {
				@Override
				public T doInRedis(RedisConnection connection) throws DataAccessException {
					byte[] value = connection.get(rawKey);
					return deserialize(value, typeReference);// 反序列为对象
				}
			}, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	// 根据key删除
	@Override
	public Long delete(String key) {
		try {
			// 序列化key
			final byte[] rawKey = serializeKey(key);
			return redisTemplate.execute(new RedisCallback<Long>() {
				@Override
				public Long doInRedis(RedisConnection connection) throws DataAccessException {
					return connection.del(rawKey);// 删除
				}
			}, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0L;
	}

	// 设置key过期时间(秒)
	@Override
	public Boolean expire(String key, final long seconds) {
		boolean expireSucess = false;
		if (seconds <= 0) {
			return expireSucess;
		}
		try {
			// 序列化key
			final byte[] rawKey = serializeKey(key);
			expireSucess = redisTemplate.execute(new RedisCallback<Boolean>() {
				@Override
				public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				    return connection.expire(rawKey, seconds);// 设置过期时间
				}
			}, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return expireSucess;
	}
	// 设置key过期时间(毫秒)
	@Override
	public Boolean pexpire(String key, final long millis) {
		boolean expireSucess = false;
		if (millis <= 0) {
			return expireSucess;
		}
		try {
			// 序列化key
			final byte[] rawKey = serializeKey(key);
			expireSucess = redisTemplate.execute(new RedisCallback<Boolean>() {
				@Override
				public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				    return connection.pExpire(rawKey, millis);// 设置过期时间
				}
			}, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return expireSucess;
	}
	// 设置key在某个时间点过期
	@Override
	public Boolean expireAt(String key, final Date date) {
		boolean expireAtSucess = false;
		try {
			// 序列化key
			final byte[] rawKey = serializeKey(key);
			expireAtSucess = redisTemplate.execute(new RedisCallback<Boolean>() {
				@Override
				public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
					return connection.expireAt(rawKey, date.getTime()/1000);// 设置过期时间
				}
			}, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return expireAtSucess;
	}

	// 设置key在某个时间点过期 并设置值
	@Override
	public Boolean expireAt(String key, Object value, final Date date) {
		boolean expireAtSucess = false;
		try {
			// 序列化key value
			final byte[] rawKey = serializeKey(key);
			final byte[] rawValue = serializeValue(value);
			expireAtSucess = redisTemplate.execute(new RedisCallback<Boolean>() {
				@Override
				public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
					long seconds = (date.getTime() - System.currentTimeMillis()) / 1000;// 计算N秒后过期
					if (seconds <= 0) {// 马上过期
						connection.del(rawKey);
					} else {
						connection.setEx(rawKey, seconds, rawValue);// 设置值、过期时间
					}
					return true;
				}
			}, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return expireAtSucess;
	}

	// 查询key的剩余时间(秒)
	@Override
	public Long getExpire(String key) {
		try {
			// 序列化key
			final byte[] rawKey = serializeKey(key);
			return redisTemplate.execute(new RedisCallback<Long>() {
				@Override
				public Long doInRedis(RedisConnection connection) throws DataAccessException {
					return connection.ttl(rawKey);// 查询
				}
			}, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2L;
	}

	// 查询key的剩余时间(毫秒)
	@Override
	public Long getpExpire(String key) {
		try {
			// 序列化key
			final byte[] rawKey = serializeKey(key);
			return redisTemplate.execute(new RedisCallback<Long>() {
				@Override
				public Long doInRedis(RedisConnection connection) throws DataAccessException {
					return connection.pTtl(rawKey);// 查询
				}
			}, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2L;
	}

	// 判断key是否存在
	@Override
	public Boolean hasKey(String key) {
		boolean hasKey = false;
		try {
			// 序列化key
			final byte[] rawKey = serializeKey(key);
			hasKey = redisTemplate.execute(new RedisCallback<Boolean>() {
				@Override
				public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
					return connection.exists(rawKey);// 判断
				}
			}, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hasKey;
	}

	// 取出当前值并设置新值
	@Override
	public <T> T getAndset(String key, Object value, final Class<T> beanType) {
		try {
			// 序列化key value
			final byte[] rawKey = serializeKey(key);
			final byte[] rawValue = serializeValue(value);
			return redisTemplate.execute(new RedisCallback<T>() {
				@Override
				public T doInRedis(RedisConnection connection) throws DataAccessException {
					byte[] value = connection.getSet(rawKey, rawValue);;
					return deserialize(value, beanType);// 反序列为对象
				}
			}, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 设置值  若key存在 不设置
	@Override
	public Boolean setNX(String key, Object value) {
		boolean setNXSucess = false;
		try {
			// 序列化key value
			final byte[] rawKey = serializeKey(key);
			final byte[] rawValue = serializeValue(value);
			setNXSucess = redisTemplate.execute(new RedisCallback<Boolean>() {
				@Override
				public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
					return connection.setNX(rawKey, rawValue);// 设置值
				}
			}, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return setNXSucess;
	}

	@Override
	public RedisTemplate<String, Object> getRedisTemplate() {
		return redisTemplate;
	}

	@Override
	public StringRedisSerializer getKeySerializer() {
		if (keySerializer == null) {
			keySerializer = (StringRedisSerializer)redisTemplate.getKeySerializer();
		}
		return keySerializer;
	}

	@Override
	public GenericJackson2JsonRedisSerializer getValueSerializer() {
		if (valueSerializer == null) {
			valueSerializer = (GenericJackson2JsonRedisSerializer)redisTemplate.getValueSerializer();
		}
		return valueSerializer;
	}

	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	/**
	 * 序列化key为 byte[]
	 * @param key
	 * @return
	 */
	private byte[] serializeKey(String key) {
		Assert.notNull(key, "redis non null key required");
		return getKeySerializer().serialize(key);
	}

	/**
	 * 序列化value为 byte[]
	 * @param value
	 * @return
	 */
	private byte[] serializeValue(Object value) {
		return getValueSerializer().serialize(value);
	}
	
	/**
	 * byte[] 反序列化为对象
	 * @param source
	 * @param beanType
	 * @return
	 */
	private <T> T deserialize(byte[] source, Class<T> beanType){
		return getValueSerializer().deserialize(source, beanType);
	}

	/**
	 * byte[] 反序列化为对象
	 * @param source
	 * @param typeReference 序列化泛型   List<T> Map<String,T>等  示例：传入new TypeReference<List<T>>()
	 * @return
	 */
	private <T> T deserialize(byte[] source, TypeReference<T> typeReference){
		return getValueSerializer().deserialize(source, typeReference);
	}
	// 清空当前使用的db库
	@Override
	public Boolean flushDb() {
		boolean flushDb = false;
		try {
			flushDb = redisTemplate.execute(new RedisCallback<Boolean>() {
				@Override
				public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
					connection.flushDb();// 清空
					return true;
				}
			}, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flushDb;
	}

	// 分布式锁 默认最大等待锁时间为5秒 最大持有锁时间为5秒
	@Override
	public <T> T lock(String key, LockCallback<T> action) throws RedisLockException {
		return lock(key, action, MAXWAITSECONDS, MAXLOCKSECONDS);
	}

	// 分布式锁
	@Override
	public <T> T lock(String key, LockCallback<T> action, long maxWaitSeconds, final long maxLockSeconds)
			throws RedisLockException {
		// 验证参数
		Assert.notNull(action, "LockCallback action must not be null");
		Assert.notNull(key, "redis lockKey must not be null");
		// 序列化key value
		// 重要 生成锁唯一标识 也是释放锁唯一依据  防止redis master宕机或设置锁有效时间失败 导致删除其它客户端锁
		String lockValue = getUuid();
		String lockKey = "lock_" + key;
		final byte[] rawKey = serializeKey(lockKey);
		final byte[] rawValue = serializeValue(lockValue);
		try {
			// 等待获取锁截止时间
			long waitTime = System.currentTimeMillis() + maxWaitSeconds * 1000;
			boolean tryLock = false;
			// 等待时间内 竞争锁
			while (System.currentTimeMillis() < waitTime) {
				// 尝试锁定 判断其它线程是否已经获取锁
				tryLock = redisTemplate.execute(new RedisCallback<Boolean>() {
					@Override
					public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
						// 如果键已存在则返回false(已有线程锁定)
						boolean locked = connection.setNX(rawKey, rawValue);
						if (locked) {
							//为防止解锁失败,设置自动解锁
							connection.expire(rawKey, maxLockSeconds);
						}
						return locked;
					}
				});
				// 获取锁成功 停止竞争
				if (tryLock) {
					break;
				} else {// 继续竞争锁
					try {
						Thread.sleep(100);// 等待
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			// 执行锁定函数
			if (tryLock) {
				logger.info("分布式锁成功锁定 key:" + lockKey + " " + "value:" + lockValue);
				T result = action.doInLock();// 锁定函数返回结果
				logger.info("doInLock 代码函数执行成功!");
				return result;
			} else {
				throw new RedisLockException("获取分布式锁超时(maxWaitSeconds:"+ maxWaitSeconds +"秒) 请检查redis服务器状态或者是否发生key死锁:" + lockKey);
			}
		} finally {// 释放锁
			// 验证锁的唯一性
			String del_value = redisTemplate.execute(new RedisCallback<String>() {
				@Override
				public String doInRedis(RedisConnection connection) throws DataAccessException {
					byte[] byte_value = connection.get(rawKey);
					return deserialize(byte_value, String.class);
				}
			});
			if (lockValue.equals(del_value)) {// 验证锁唯一 释放锁
				redisTemplate.execute(new RedisCallback<Long>() {
					@Override
					public Long doInRedis(RedisConnection connection) throws DataAccessException {
						return connection.del(rawKey);
					}
				});
			}
		}
	}
	
	
	
	/**
	 * 生成唯一id
	 * @return
	 */
	private String getUuid() {
		UUID uuid = UUID.randomUUID();
		// 替换uuid中的'-',保证长度为32位
		String id = uuid.toString().replaceAll("-", "");
		return id;
	}

	// 取出当前值并设置新值 序列化泛型   List<T> Map<String,T>等  示例：传入new TypeReference<List<T>>()
	@Override
	public <T> T getAndset(String key, Object value, final TypeReference<T> typeReference) {
		try {
			// 序列化key value
			final byte[] rawKey = serializeKey(key);
			final byte[] rawValue = serializeValue(value);
			return redisTemplate.execute(new RedisCallback<T>() {
				@Override
				public T doInRedis(RedisConnection connection) throws DataAccessException {
					byte[] value = connection.getSet(rawKey, rawValue);;
					return deserialize(value, typeReference);// 反序列为对象
				}
			}, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Long Incr(String key) {
		try {
			// 序列化key
			final byte[] rawKey = serializeKey(key);
			return redisTemplate.execute(new RedisCallback<Long>() {
				@Override
				public Long doInRedis(RedisConnection connection) throws DataAccessException {
						return connection.incr(rawKey);
				}
			}, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1L;
	}

	@Override
	public Long IncrBy(String key,final Long increment) {
		try {
			// 序列化key
			final byte[] rawKey = serializeKey(key);
			return redisTemplate.execute(new RedisCallback<Long>() {
				@Override
				public Long doInRedis(RedisConnection connection) throws DataAccessException {
						return connection.incrBy(rawKey, increment);
				}
			}, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1L;
	}
    
	@Override
	public Long Decr(String key) {
		try {
			// 序列化key
			final byte[] rawKey = serializeKey(key);
			return redisTemplate.execute(new RedisCallback<Long>() {
				@Override
				public Long doInRedis(RedisConnection connection) throws DataAccessException {
						return connection.decr(rawKey);
				}
			}, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1L;
	}

	
	
}
