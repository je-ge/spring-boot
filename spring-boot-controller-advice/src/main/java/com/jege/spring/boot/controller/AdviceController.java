package com.jege.spring.boot.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jege.spring.boot.exception.ServiceException;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:全局异常处理演示入口
 */
@RestController
public class AdviceController {

  @RequestMapping("/hello")
  public String hello() {
    int i = 1 / 0;
    return "hello";
  }

  @RequestMapping("/hello1")
  public String hello1(Long id) {
    String string = null;
    string.length();
    return "hello";
  }

  @RequestMapping("/hello2")
  public List<String> hello2() {
    throw new ServiceException("xxx");
  }
}
