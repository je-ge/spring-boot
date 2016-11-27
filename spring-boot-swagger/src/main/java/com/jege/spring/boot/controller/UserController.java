package com.jege.spring.boot.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jege.spring.boot.data.jpa.entity.User;
import com.jege.spring.boot.data.jpa.repository.UserRepository;
import com.jege.spring.boot.json.AjaxResult;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:用户CRUD操作
 */
@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  // 显示用户列表
  @RequestMapping("/list")
  public String list() {
    return "user";
  }

  // 显示用户json数据
  @ApiOperation(value = "获取用户列表，支持分页", notes = "json方法获取用户列表")
  @ApiImplicitParams({ @ApiImplicitParam(name = "page", value = "当前页码", required = true, dataType = "int"),
      @ApiImplicitParam(name = "rows", value = "每页条数", required = true, dataType = "int") })
  @RequestMapping("/json")
  @ResponseBody
  public Map<String, Object> json(@RequestParam(name = "page", defaultValue = "1") int page,
      @RequestParam(name = "rows", defaultValue = "10") int rows) {
    Pageable pageable = new PageRequest(page - 1, rows);
    return findEasyUidata(userRepository.findAll(pageable));
  }

  private <T> Map<String, Object> findEasyUidata(Page<T> page) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("rows", page.getContent());
    map.put("total", page.getTotalElements());
    return map;
  }

  // 处理保存
  @ApiOperation(value = "保存用户", notes = "根据User对象操作用户")
  @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
  @RequestMapping("/save")
  @ResponseBody
  public AjaxResult save(User user) {
    userRepository.save(user);
    return new AjaxResult().success();
  }

  // 处理删除
  @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象")
  @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long")
  @RequestMapping("/delete")
  @ResponseBody
  public AjaxResult delete(Long id) {
    userRepository.delete(id);
    return new AjaxResult().success();
  }
}
