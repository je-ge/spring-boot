package com.jege.spring.boot.data.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:jpa模型对象
 */
@Entity
@Table(name = "t_user")
public class User {
  @Id
  @GeneratedValue
  private Long id;
  @Size(min = 5, max = 30)
  private String name;
  @Min(18)
  @Max(60)
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
