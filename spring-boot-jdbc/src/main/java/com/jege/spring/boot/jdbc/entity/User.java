package com.jege.spring.boot.jdbc.entity;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:模型对象
 */
public class User {
  private Long id;
  private String name;
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
