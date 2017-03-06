package com.jege.spring.boot.redirect;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:301/302跳转
 */
@Controller
public class RedirectController {

  // 302临时跳转
  // http://localhost:8080/url1
  @RequestMapping(value = "/url1", method = RequestMethod.GET)
  public String url1() {
    return "redirect:http://www.baidu.com";
  }

  // 301永久跳转
  // http://localhost:8080/url2
  @RequestMapping(value = "/url2", method = RequestMethod.GET)
  public ModelAndView url2() {
    RedirectView redirectView = new RedirectView();
    redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
    redirectView.setUrl("http://www.baidu.com");
    ModelAndView mv = new ModelAndView(redirectView);
    return mv;
  }
}
