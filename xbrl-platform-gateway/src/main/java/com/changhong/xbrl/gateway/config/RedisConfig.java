package com.changhong.xbrl.gateway.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

/**
 * Created by Liang on 2017/11/15.
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    @SuppressWarnings("rawtypes")
    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
        return rcm;
    }

    @Bean
    public RedisTemplate<Serializable, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<Serializable, Object> template = new RedisTemplate<Serializable, Object>();
        template.setConnectionFactory(factory);
        StringRedisSerializer keySerializer =new StringRedisSerializer();
        JdkSerializationRedisSerializer  valueSerializer = new JdkSerializationRedisSerializer();

        template.setKeySerializer(keySerializer);
        template.setValueSerializer(valueSerializer);
        template.afterPropertiesSet();
        return template;
    }

}