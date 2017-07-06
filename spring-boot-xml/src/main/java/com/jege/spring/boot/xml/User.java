package com.jege.spring.boot.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:模型对象
 */
@JacksonXmlRootElement(localName = "user")
public class User {
  private Long id;
  @JacksonXmlCData
  @JacksonXmlProperty(localName = "Content")
  private String content;

  public User() {

  }

  public User(Long id, String content) {
    this.id = id;
    this.content = content;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

}
