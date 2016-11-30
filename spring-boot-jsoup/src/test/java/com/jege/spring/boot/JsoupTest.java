package com.jege.spring.boot;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Template;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:获取连接，写出hibernate文件
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class JsoupTest {
  // 读取博客信息的地址
  private final static String URL_ADDRESS = "http://blog.csdn.net/je_ge?viewmode=contents";

  // <h1>
  // <span class="link_title"><a href="/je_ge/article/details/53366556">
  // Spring Boot 系列教程9-swagger-前后端分离后的标准
  // </a></span>
  // </h1>

  private Elements getLinks() throws Exception {
    Document document = Jsoup.connect(URL_ADDRESS)
	.userAgent("Mozilla/5.0 (Windows NT 7.0; Win64; x64; rv:49.0) Gecko/20100101 Firefox/49.0").get();
    return document.select("h1 a");
  }

  @Test
  public void testGetLinks() throws Exception {
    Elements links = getLinks();
    for (int i = links.size() - 1; i >= 0; i--) {
      Element link = links.get(i);
      String attr = link.attr("href");
      String linkText = link.text();
      System.out.println(linkText);
      System.out.println(attr);
    }
    System.out.println("size:" + links.size());
  }

  @Autowired
  private FreeMarkerConfigurer freeMarkerConfigurer;

  @Test
  public void writeHibernate() throws Exception {
    String title = "Hibernate 系列教程";
    String content = "Hibernate是一个开放源代码的对象关系映射框架，它对JDBC进行了非常轻量级的对象封装，它将POJO与数据库表建立映射关系，是一个全自动的orm框架，hibernate可以自动生成SQL语句，自动执行，使得Java程序员可以随心所欲的使用对象编程思维来操纵数据库。 Hibernate可以应用在任何使用JDBC的场合，既可以在Java的客户端程序使用，也可以在Servlet/JSP的Web应用中使用，最具革命意义的是，Hibernate可以在应用EJB的J2EE架构中取代CMP，完成数据持久化的重任。";
    Elements links = getLinks();
    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    for (Element link : links) {
      String linkText = link.text();
      if (linkText.contains(title)) {
	Map<String, Object> map = new HashMap<String, Object>();
	map.put("href", "http://blog.csdn.net/" + link.attr("href"));
	map.put("title", linkText.replaceAll("Hibernate 系列教程", ""));
	list.add(map);
      }
    }
    Collections.reverse(list);
    System.out.println("size:" + links.size());

    freeMarkerConfigurer.getConfiguration().setClassForTemplateLoading(getClass(), "/");
    Template template = freeMarkerConfigurer.getConfiguration().getTemplate("Hibernate.ftl");
    Map<String, Object> root = new HashMap<String, Object>();
    root.put("title", title);
    root.put("content", content);
    root.put("list", list);
    template.process(root, new FileWriter(new File(title + ".doc")));
  }
}
