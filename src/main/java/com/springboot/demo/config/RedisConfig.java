package com.springboot.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by yg.jang on 2016. 7. 20..
 */
@Configuration
public class RedisConfig {

  @Bean
  public JedisPoolConfig jedisPoolConfig() {
    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
    return jedisPoolConfig;
  }

  @Bean
  public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig) {

    JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig);
    return jedisConnectionFactory;
  }

  @Bean(name="redisTemplate")
  public RedisTemplate redisTemplateConfig(JedisConnectionFactory jedisConnectionFactory) {

    RedisTemplate redisTemplate = new RedisTemplate();

    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
    redisTemplate.setConnectionFactory(jedisConnectionFactory);


    return redisTemplate;
  }
}
