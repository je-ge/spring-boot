package com.jege.spring.boot.data.jpa;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jege.spring.boot.jdbc.dao.IUserDao;
import com.jege.spring.boot.jdbc.entity.User;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class UserDaoTest {
  @Autowired
  IUserDao userDao;

  // 每次执行Test之前先删除表，创建表
  @Before
  public void before() throws Exception {
    userDao.dropTable();
    userDao.createTable();
  }

  @Test
  public void save() throws Exception {
    for (int i = 0; i < 10; i++) {
      User user = new User("jege" + i, 25 + i);
      userDao.save(user);
    }
  }

  @Test
  public void all() throws Exception {
    save();
    assertThat(userDao.findAll()).hasSize(10);
  }

  @Test
  public void findByName() throws Exception {
    save();
    assertThat(userDao.findByNameLike("jege%")).hasSize(10);
  }

  @After
  public void destroy() throws Exception {
    userDao.deleteAll();
  }

}
