package com.muzhi.redis;

import java.util.Date;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.muzhi.util.LockCallback;
import com.muzhi.util.RedisLockException;

/**
 * (execute 方法第二个参数未搞清楚)实行RedisCallback多个操作必须开启事务 
 * redis的事务有好多小坑坑 并不是真正意义上的事务 把它当成 static Map 就行
 * 适用spring4.0 redis3.0 底层通过byte[]存储 通过RedisCallback实现
 * ============================================================
 * 功能描述:spring4.0 redis3.0以上 工具类 可直接存放对象 使用GenericJackson2JsonRedisSerializer
 * 只封装常用的 key(string) value redis的list set暂时不做
 * 注意 bean对象属性如果使用了Jackson的@JsonIgnore 同样适用(这是坑) 不要直接在客户端使用命令存值(因为没序列化 代码取出来就报错)
 * 存储的bean必须Serializer
 * 创建时间:2016年10月10日 上午10:47:39
 */
public interface SpringRedisService {

	/**
	 * 设置值
	 * @param key
	 * @param t
	 * @return
	 */
	public Boolean set(String key, Object value);
	
	/**
	 * 设置值 并设置key过期时间(秒)
	 * @param key
	 * @param t
	 * @param seconds
	 * @return
	 */
	public Boolean set(String key, Object value, final long seconds);
	
	/**
	 * 获取值
	 * @param key
	 * @param beanType
	 * @return
	 */
	public <T> T get(String key, Class<T> beanType);
	
	/**
	 * 获取值
	 * @param key
	 * @param typeReference 序列化泛型   List<T> Map<String,T>等  示例：传入new TypeReference<List<T>>()
	 * @return
	 */
	public <T> T get(String key, TypeReference<T> typeReference);
	
	/**
	 * 根据key删除
	 * @param key
	 * @return
	 */
	public Long delete(String key);
	
	/**
	 * 设置key过期时间(秒)
	 * @param key
	 * @param seconds
	 * @return
	 */
	public Boolean expire(String key, final long seconds);
	/**
	 * 设置key过期时间(毫秒)
	 * @param key
	 * @param seconds
	 * @return
	 */
	public Boolean pexpire(String key, final long seconds);
	
	/**
	 * 设置key在某个时间点过期 小于当前时间 马上过期
	 * @param key
	 * @param date
	 * @return
	 */
	public Boolean expireAt(String key, final Date date);
	
	/**
	 * 设置key在某个时间点过期 并设置值
	 * @param key
	 * @param t
	 * @param date
	 * @return
	 */
	public Boolean expireAt(String key, Object value, final Date date);
	/**
	 * 查询key的剩余时间(秒) 
	 * @param key
	 * @return 返回-1(无限) 返回null或-2(key不存在)
	 */
	public Long getExpire(String key);
	/**
	 * 查询key的剩余时间(毫秒) 
	 * @param key
	 * @return 返回-1(无限) 返回null或-2(key不存在)
	 */
	public Long getpExpire(String key);
	
	/**
	 * 判断key是否存在
	 * @param key
	 * @return
	 */
	public Boolean hasKey(String key);
	
	/**
	 * 取出当前值并设置新值
	 * @param key
	 * @param value 新值
	 * @param beanType
	 * @return 旧值
	 */
	public <T> T getAndset(String key, Object value, Class<T> beanType);
	
	
	/**
	 * 取出当前值并设置新值
	 * @param key
	 * @param value 新值
	 * @param typeReference 序列化泛型   List<T> Map<String,T>等  示例：传入new TypeReference<List<T>>()
	 * @return 旧值
	 */
	public <T> T getAndset(String key, Object value, TypeReference<T> typeReference);
	
	/**
	 * 设置值  若key存在 不设置 返回false 防止覆盖
	 * @param key
	 * @param value
	 * @return
	 */
	public Boolean setNX(String key, Object value);
	
	/**
	* 获取spring redisTemplate
	* @return RedisTemplate<String, Object> spring redisTemplate
	*/
	public RedisTemplate<String, Object> getRedisTemplate();
	
	/**
	* 获取key使用的序列化
	* @return StringRedisSerializer key使用的序列化
	*/
	public StringRedisSerializer getKeySerializer();
	
	/**
	* 获取value使用的序列化
	* @return GenericJackson2JsonRedisSerializer value使用的序列化
	*/
	public GenericJackson2JsonRedisSerializer getValueSerializer();
	
	/**
	 * 清空当前使用的db库
	 * @return
	 */
	public Boolean flushDb();
	
	/**
	 * 分布式锁同步锁 串行 默认最大等待锁时间为5秒 最大持有锁时间为5秒
	 * @param key
	 * @param action 回调锁函数
	 * @return 返回 回调锁函数值
	 * @throws RedisLockException 锁异常 执行获取锁失败
	 */
	public <T> T lock(String key, LockCallback<T> action) throws RedisLockException;
	
	/**
	 * 分布式锁同步锁 串行
	 * @param key
	 * @param action 回调锁函数
	 * @param maxWaitSeconds 最大等待锁时间
	 * @param maxLockSeconds 最大持有锁时间
	 * @return 返回 回调锁函数值
	 * @throws RedisLockException 执行获取锁失败
	 */
	public <T> T lock(String key, LockCallback<T> action, long maxWaitSeconds, final long maxLockSeconds) throws RedisLockException;
	
	/**
	 * 原子性自增1
	 * @param key
	 */
	public Long Incr(String key);
	
	/**
	 * 原子性自增指定数
	 * @param key
	 * @param increment
	 */
	public Long IncrBy(String key,final Long increment);
	/**
	 * 原子性自减1
	 * @param key
	 */
	public Long Decr(String key);
}
