package com.jege.spring.boot.es;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:控制器类
 */
@RestController
@RequestMapping(value = "/es/")
public class UserController {
  @Autowired
  private UserService userService;

  @RequestMapping("/list")
  public Iterable<User> list() {
    userService.save(new User("je_ge", 33));
    return userService.findAll();
  }
}
