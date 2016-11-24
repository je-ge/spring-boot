package com.jege.spring.boot.hello.world;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:看看spring-boot的强大和方便
 */
@RestController
public class HelloWorldController {

  @RequestMapping("/hello1")
  public String hello1() {
    return "Hello World";
  }

  @RequestMapping("/hello2")
  public List<String> hello2() {
    return Arrays.asList(new String[] { "A", "B", "C" });
  }
}
