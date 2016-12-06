package com.jege.spring.boot.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:控制器父类
 */
public class BaseController {

  // 把spring data的page进行转换，变成easyui需要的数据
  public <T> Map<String, Object> findEasyUIData(Page<T> page) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("rows", page.getContent());
    map.put("total", page.getTotalElements());
    System.out.println("size:" + page.getTotalElements());
    return map;
  }
}
