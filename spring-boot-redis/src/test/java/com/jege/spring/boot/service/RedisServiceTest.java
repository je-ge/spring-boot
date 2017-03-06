package com.jege.spring.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:
 */
public class RedisServiceTest {
  @Autowired
  public StringRedisTemplate template;
  @Autowired
  public RedisTemplate redisTemplate;
}
