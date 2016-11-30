package com.jege.spring.boot.data.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:jpa模型对象
 */
@Entity
@Table(name = "t_user")
@ExcelTarget("user")
public class User {
  @Id
  @GeneratedValue
  @Excel(name = "编号", orderNum = "1", mergeVertical = true, isImportField = "id")
  private Long id;
  @Excel(name = "姓名", orderNum = "2", mergeVertical = true, isImportField = "name")
  private String name;
  @Excel(name = "年龄", orderNum = "3", mergeVertical = true, isImportField = "age")
  private Integer age;

  public User() {

  }

  public User(String name, Integer age) {
    this.name = name;
    this.age = age;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

}
