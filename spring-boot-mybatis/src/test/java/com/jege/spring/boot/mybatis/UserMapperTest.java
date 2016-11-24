package com.jege.spring.boot.mybatis;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jege.spring.boot.mybatis.entity.User;
import com.jege.spring.boot.mybatis.mapper.UserMapper;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class UserMapperTest {
  @Autowired
  UserMapper userMapper;

  // 每次执行Test之前先删除表，创建表
  @Before
  public void before() throws Exception {
    userMapper.dropTable();
    userMapper.createTable();
  }

  // 打印出class com.sun.proxy.$Proxy66表示spring注入通过jdk动态代理获取接口的子类
  @Test
  public void proxy() throws Exception {
    System.out.println(userMapper.getClass());
  }

  @Test
  public void save() throws Exception {
    for (int i = 0; i < 10; i++) {
      User user = new User("jege" + i, 25 + i);
      userMapper.insert(user);
    }
  }

  @Test
  public void all() throws Exception {
    save();
    assertThat(userMapper.findAll()).hasSize(10);
  }

  @Test
  public void findByName() throws Exception {
    save();
    assertThat(userMapper.findByNameLike("jege%")).hasSize(10);
  }

  // 每次执行Test之后清空数据
  @After
  public void destroy() throws Exception {
    userMapper.deleteAll();
  }

}
