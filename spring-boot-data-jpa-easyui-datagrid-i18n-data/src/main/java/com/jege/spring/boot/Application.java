package com.jege.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:spring boot 启动类
 */

@SpringBootApplication
@PropertySources(value = { @PropertySource("classpath:messages.properties") })
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
