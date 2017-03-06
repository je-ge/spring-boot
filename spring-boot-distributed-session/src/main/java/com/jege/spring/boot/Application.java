package com.jege.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:spring boot 启动类
 */

@SpringBootApplication
@EnableRedisHttpSession
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
