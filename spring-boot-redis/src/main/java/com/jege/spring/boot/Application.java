package com.jege.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:spring boot 启动类
 */

@SpringBootApplication
@EnableRedisRepositories
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
