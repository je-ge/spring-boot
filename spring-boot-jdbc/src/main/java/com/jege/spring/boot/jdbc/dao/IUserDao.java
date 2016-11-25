package com.jege.spring.boot.jdbc.dao;

import java.util.List;

import com.jege.spring.boot.jdbc.entity.User;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:持久层接口，由spring自动生成其实现
 */
public interface IUserDao {

  void dropTable();

  void createTable();

  void save(User user);

  List<User> findAll();

  void deleteAll();

  List<User> findByNameLike(String name);

}
