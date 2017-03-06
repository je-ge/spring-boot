package com.jege.spring.boot.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:直接操作String数据类型
 */
@Service
public class StringRedisService {
  @Autowired
  public StringRedisTemplate stringRedisTemplate;

  // 获取某个key的剩余过期时间
  public long residualExpirationTime(String key) {
    return stringRedisTemplate.getExpire(key);
  }

  // 当key不存在时，为key赋值
  public boolean setValue(String key, String value) {
    ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
    return ops.setIfAbsent(key, value);
  }

  // 为key赋值，同时设置过期时间
  public void set(String key, String value, long time) {
    BoundValueOperations<String, String> ops = stringRedisTemplate.boundValueOps(key);
    ops.set(value, time, TimeUnit.SECONDS);
  }

  // 删除某个key
  public void delete(String key) {
    stringRedisTemplate.delete(key);
  }

  // 判断某个key是否存在
  public boolean exist(String key) {
    return stringRedisTemplate.hasKey(key);
  }

  // 同redis命令的leftpush
  public void leftPush(String key, String value) {
    stringRedisTemplate.boundListOps(key).leftPush(value);
  }

  // 同redis命令的rightpop
  public String rightPop(String key) {
    return stringRedisTemplate.boundListOps(key).rightPop();
  }
}
