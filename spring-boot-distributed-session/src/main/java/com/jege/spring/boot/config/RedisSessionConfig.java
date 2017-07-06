package com.jege.spring.boot.config;
//package com.jege.spring.boot.Config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.session.data.redis.config.ConfigureRedisAction;
//import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
//
///**
// * @author JE哥
// * @email 1272434821@qq.com
// * @description:可在不改变Redis配置的前提下使用Redis来存储Session了
// */
//@Configuration
//// 在这里设置Session过期时间，单位：秒
//@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 36000)
//public class RedisSessionConfig {
//  @Bean
//  public static ConfigureRedisAction configureRedisAction() {
//    return ConfigureRedisAction.NO_OP;
//  }
//}