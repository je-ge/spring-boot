package com.jege.spring.boot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jege.spring.boot.es.UserService;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MainTest {

  @Autowired
  private UserService userService;

  @Test
  public void test() throws Exception {
    System.out.println(userService.findAll());
  }
  
}
