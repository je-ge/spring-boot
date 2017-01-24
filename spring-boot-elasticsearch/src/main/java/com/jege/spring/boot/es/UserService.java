package com.jege.spring.boot.es;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:业务接口
 */
public interface UserService {
  void save(User user);

  Iterable<User> findAll();
}
