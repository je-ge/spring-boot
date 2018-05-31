package com.jege.spring.boot.xml;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:输出xml&提交xml
 */
@Controller
public class UserController {

  // 输出xml
  @RequestMapping(value = "/get", method = RequestMethod.GET, produces = { "application/xml" })
  @ResponseBody
  public User get() {
    return new User(1L, "JE-GE");
  }

  // 提交xml
  @RequestMapping(value = "/user", method = RequestMethod.POST, consumes = { "text/xml" }, produces = {
      "application/xml" })
  @ResponseBody
  public User post(@RequestBody User user) {
    return user;
  }
}
