package com.jege.spring.boot.data.jpa;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jege.spring.boot.data.jpa.entity.User;
import com.jege.spring.boot.data.jpa.repository.UserRepository;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class UserRepositoryTest {
  @Autowired
  UserRepository userRepository;

  // 打印出class com.sun.proxy.$Proxy66表示spring注入通过jdk动态代理获取接口的子类
  @Test
  public void proxy() throws Exception {
    System.out.println(userRepository.getClass());
  }

  @Test
  public void save() throws Exception {
    for (int i = 0; i < 10; i++) {
      User user = new User("jege" + i, 25 + i);
      userRepository.save(user);
    }
  }

  @Test
  public void all() throws Exception {
    save();
    assertThat(userRepository.findAll()).hasSize(10);
  }

  @Test
  public void findByName() throws Exception {
    save();
    assertThat(userRepository.findByNameLike("jege%")).hasSize(10);
  }

  @After
  public void destroy() throws Exception {
    userRepository.deleteAll();
  }

}
