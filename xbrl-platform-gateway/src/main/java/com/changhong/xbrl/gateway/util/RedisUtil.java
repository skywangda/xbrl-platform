package com.changhong.xbrl.gateway.util;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * 
 * <p>
 * Title: RedisUtil
 * </p>
 * <p>
 * Description: 缓存工具类
 * </p>
 * <p>
 * Company: changhong
 * </p>
 * 
 * @author huzhiyong
 * @date 2016-6-12
 * @version 1.0
 */
@Repository
public class RedisUtil {
	private Logger logger = Logger.getLogger(RedisUtil.class);

	@Autowired
	private RedisTemplate<Serializable, Object> redisTemplate;

	/**
	 *  * 批量删除对应的value  *  * @param keys  
	 */
	public void remove(final String... keys) {
		for (String key : keys) {
			remove(key);
		}
	}

	/**
	 *  * 批量删除key  *  * @param pattern  
	 */
	public void removePattern(final String pattern) {
		Set<Serializable> keys = redisTemplate.keys(pattern);
		if (keys.size() > 0)
			redisTemplate.delete(keys);
	}

	/**
	 *  * 删除对应的value  *  * @param key  
	 */
	public void remove(final String key) {
		if (exists(key)) {
			redisTemplate.delete(key);
		}
	}

	public boolean remove(final String modelName, String key) {
		boolean result = false;
		try {
			redisTemplate.opsForHash().delete(modelName, key);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 *  * 判断缓存中是否有对应的value  *  * @param key  * @return  
	 */
	public boolean exists(final String key) {
		return redisTemplate.hasKey(key);
	}

	/**
	 *  * 读取缓存  *  * @param key  * @return  
	 */
	public Object get(final String modelName, final String key) {
		Object result = null;
		HashOperations<Serializable, Object, Object> operations = redisTemplate
				.opsForHash();
		result = operations.get(modelName, key);
		return result;
	}
	/**
	 *  * 读取缓存  *  * @param key  * @return  
	 */
	public Object get(final String key) {
        Object result = redisTemplate.boundValueOps(key).get();
		return result;
	}
	

	/**
	 *  * 写入缓存  *  * @param key  * @param value  * @return  
	 */
	public boolean set(final String modelName, final String key, Object value) {
		boolean result = false;
		try {
			redisTemplate.opsForHash().put(modelName, key, value);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 *  * 写入缓存  *  * @param key  * @param value  * @return  
	 */
	public boolean set(final String modelName, final String key, Object value,
                       Long expireTime) {
		boolean result = false;
		try {
			redisTemplate.opsForHash().put(modelName, key, value);
			redisTemplate.expire(modelName, expireTime, TimeUnit.SECONDS);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void setRedisTemplate(RedisTemplate<Serializable, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

}
