package com.cityiot.guanxin.config.shiro;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

public class SessionRedisUtil {
	 @SuppressWarnings("rawtypes")
	 @Autowired
	 private RedisTemplate redisTemplate;
	 
	     /**
	     * 写入缓存
	     *
	     * @param key
	     * @param value
	     * @return
	     */
	    @SuppressWarnings("unchecked")
	    public boolean set(final String key, Object value) {
	        boolean result = false;
	        try {
	            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
	            operations.set(key, value);
	            result = true;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return result;
	    }
	    
	    /**
	     * 读取缓存
	     *
	     * @param key
	     * @return
	     */
	    @SuppressWarnings("unchecked")
	    public Object get(final String key) {
	        Object result = null;
	        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
	        result = operations.get(key);
	        return result;
	    }


}
