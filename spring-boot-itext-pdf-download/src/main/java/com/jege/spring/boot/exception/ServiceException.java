package com.jege.spring.boot.exception;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:自定义异常类
 */
public class ServiceException extends RuntimeException {
  public ServiceException(String msg) {
    super(msg);
  }
}
