package com.jege.spring.boot.session;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:输出sessionId
 */
@RestController
public class SessionController {

  @RequestMapping("/session")
  public String hello1(HttpSession session) {
    return session.getId();
  }
}
