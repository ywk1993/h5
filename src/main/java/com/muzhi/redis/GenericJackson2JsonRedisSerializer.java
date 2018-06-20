package com.muzhi.redis;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;

/**
 * 
 * 功能描述:参照 org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer(spring-4.2.0.RELEASE)
 * 由于无法使用原来的 mapper 重新定义一个GenericJackson2JsonRedisSerializer 新增deserialize(byte[] source, TypeReference<T> typeReference)
 * @author LJC
 * 创建时间:2016年11月30日 下午1:24:20
 */
public class GenericJackson2JsonRedisSerializer implements RedisSerializer<Object> {

	private final ObjectMapper mapper;
	
	/**
	 * 原来的org.springframework.data.redis.serializer.SerializationUtils.EMPTY_ARRAY
	 */
	private final byte[] empty_array = new byte[0];

	public GenericJackson2JsonRedisSerializer() {
		this((String) null);
	}

	public GenericJackson2JsonRedisSerializer(String classPropertyTypeName) {
		this(new ObjectMapper());
		if (StringUtils.hasText(classPropertyTypeName)) {
			mapper.enableDefaultTypingAsProperty(DefaultTyping.NON_FINAL, classPropertyTypeName);
		} else {
			mapper.enableDefaultTyping(DefaultTyping.NON_FINAL, As.PROPERTY);
		}
	}

	public GenericJackson2JsonRedisSerializer(ObjectMapper mapper) {
		Assert.notNull(mapper, "ObjectMapper must not be null!");
		this.mapper = mapper;
	}

	@Override
	public byte[] serialize(Object source) throws SerializationException {
		if (source == null) {
			return empty_array;
		}
		try {
			return mapper.writeValueAsBytes(source);
		} catch (JsonProcessingException e) {
			throw new SerializationException("Could not write JSON: " + e.getMessage(), e);
		}
	}

	@Override
	public Object deserialize(byte[] source) throws SerializationException {
		return deserialize(source, Object.class);
	}

	public <T> T deserialize(byte[] source, Class<T> type) throws SerializationException {
		Assert.notNull(type,
				"Deserialization type must not be null! Pleaes provide Object.class to make use of Jackson2 default typing.");

		if (isEmpty(source)) {
			return null;
		}
		try {
			return mapper.readValue(source, type);
		} catch (Exception ex) {
			throw new SerializationException("Could not read JSON: " + ex.getMessage(), ex);
		}
	}
	
	/**
	 * 新增 处理嵌套泛型 扩展原来的GenericJackson2JsonRedisSerializer
	 * @param source
	 * @param typeReference
	 * @return
	 * @throws SerializationException
	 */
	public <T> T deserialize(byte[] source, TypeReference<T> typeReference) throws SerializationException {
		Assert.notNull(typeReference,
				"Deserialization typeReference must not be null! Example:to make new TypeReference<List<T>>().");
		if (isEmpty(source)) {
			return null;
		}
		try {
			return mapper.readValue(source, typeReference);
		} catch (Exception ex) {
			throw new SerializationException("Could not read JSON: " + ex.getMessage(), ex);
		}
	}
	
	
	/**
	 * 原来的org.springframework.data.redis.serializer.SerializationUtils.isEmpty(byte[] data)
	 * @param data
	 * @return
	 */
	private boolean isEmpty(byte[] data) {
		return (data == null || data.length == 0);
	}
}
