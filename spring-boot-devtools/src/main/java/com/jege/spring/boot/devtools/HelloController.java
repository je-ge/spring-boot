package com.jege.spring.boot.devtools;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:看看devtools模块的快速
 */
@RestController
public class HelloController {

  @RequestMapping("/hello")
  public String hello() {
//    System.out.println("test");
    return "Hello World";
  }
}
