package com.jege.spring.boot.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.jege.spring.boot.data.jpa.entity.User;
import com.jege.spring.boot.data.jpa.repository.UserRepository;
import com.jege.spring.boot.json.AjaxResult;

import freemarker.template.Template;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:用户CRUD操作
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

  @Autowired
  private UserRepository userRepository;

  // 显示用户列表页面
  @RequestMapping("/list")
  public String list() {
    return "user";
  }

  // 从user.jsp列表页面由easyui-datagrid发出ajax请求获取json数据
  @RequestMapping("/json")
  @ResponseBody
  public Map<String, Object> json(@RequestParam(name = "page", defaultValue = "1") int page,
      @RequestParam(name = "rows", defaultValue = "10") int rows, final String q) {
    // 按照id降序
    Sort sort = new Sort(Sort.Direction.DESC, "id");
    // 封装分页查询条件
    Pageable pageable = new PageRequest(page - 1, rows, sort);
    if (!StringUtils.isEmpty(q)) {
      // 拼接查询条件
      Specification<User> specification = new Specification<User>() {
	@Override
	public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	  List<Predicate> list = new ArrayList<Predicate>();
	  list.add(cb.like(root.get("name").as(String.class), "%" + q + "%"));
	  Predicate[] p = new Predicate[list.size()];
	  return cb.and(list.toArray(p));

	}
      };
      return findEasyUIData(userRepository.findAll(specification, pageable));
    } else {
      return findEasyUIData(userRepository.findAll(pageable));
    }

  }

  // 处理保存
  @RequestMapping("/save")
  @ResponseBody
  public AjaxResult save(User user) {
    userRepository.save(user);
    return new AjaxResult().success();
  }

  // 处理删除
  @RequestMapping("/delete")
  @ResponseBody
  public AjaxResult delete(Long id) {
    // int a = 1 / 0;
    userRepository.delete(id);
    return new AjaxResult().success();
  }

  @Autowired
  private FreeMarkerConfigurer freeMarkerConfigurer;
  private Base64 b64Encoder = new Base64();

  // 处理下载word文档
  @RequestMapping("/download")
  public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
    // 告诉浏览器用什么软件可以打开此文件
    response.setHeader("content-Type", "application/msword");
    // 下载文件的默认名称
    response.setHeader("Content-Disposition", "attachment;filename=xx.doc");
    freeMarkerConfigurer.getConfiguration().setClassForTemplateLoading(getClass(), "/");
    Template template = freeMarkerConfigurer.getConfiguration().getTemplate("userList.ftl");

    String webapp = request.getServletContext().getRealPath("/");

    List<User> list = userRepository.findAll();
    for (int i = 0; i < list.size(); i++) {
      User user = list.get(i);
      File file = new File(webapp, user.getHeadPortrait());
      FileInputStream fis = new FileInputStream(file);
      byte[] imgData = new byte[fis.available()];
      fis.read(imgData);
      fis.close();
      String headPortrait = b64Encoder.encodeAsString(imgData);
      user.setHeadPortrait(headPortrait);
    }
    Map<String, Object> root = new HashMap<String, Object>();
    root.put("list", list);
    template.process(root, new OutputStreamWriter(response.getOutputStream()));
  }
}
