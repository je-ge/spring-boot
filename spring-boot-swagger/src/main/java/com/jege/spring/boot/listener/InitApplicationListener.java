package com.jege.spring.boot.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.jege.spring.boot.data.jpa.entity.User;
import com.jege.spring.boot.data.jpa.repository.UserRepository;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:spring的事件监听器的处理机制：在启动服务器的时候，插入默认数据
 */
@Component
public class InitApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    ApplicationContext context = event.getApplicationContext();
    UserRepository userRepository = context.getBean("userRepository", UserRepository.class);
    for (int i = 1; i < 21; i++) {
      User user = new User("user" + i, 25 + i);
      userRepository.save(user);
    }
  }

}
