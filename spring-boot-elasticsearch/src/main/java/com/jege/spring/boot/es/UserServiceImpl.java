package com.jege.spring.boot.es;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:业务实现类
 */
@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserRepository userRepository;

  @Override
  public void save(User user) {
    userRepository.save(user);
  }

  @Override
  public Iterable<User> findAll() {
    return userRepository.findAll();
  }

}
