package com.jege.spring.boot.exception;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.jege.spring.boot.json.AjaxResult;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:全局异常处理
 */
@ControllerAdvice
@ResponseBody
public class CommonExceptionAdvice {

  private static Logger logger = LoggerFactory.getLogger(CommonExceptionAdvice.class);

  /**
   * 400 - Bad Request
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MissingServletRequestParameterException.class)
  public AjaxResult handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
    logger.error("缺少请求参数", e);
    return new AjaxResult().failure("required_parameter_is_not_present");
  }

  /**
   * 400 - Bad Request
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public AjaxResult handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
    logger.error("参数解析失败", e);
    return new AjaxResult().failure("could_not_read_json");
  }

  /**
   * 400 - Bad Request
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public AjaxResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    logger.error("参数验证失败", e);
    BindingResult result = e.getBindingResult();
    FieldError error = result.getFieldError();
    String field = error.getField();
    String code = error.getDefaultMessage();
    String message = String.format("%s:%s", field, code);
    return new AjaxResult().failure(message);
  }

  /**
   * 400 - Bad Request
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(BindException.class)
  public AjaxResult handleBindException(BindException e) {
    logger.error("参数绑定失败", e);
    BindingResult result = e.getBindingResult();
    FieldError error = result.getFieldError();
    String field = error.getField();
    String code = error.getDefaultMessage();
    String message = String.format("%s:%s", field, code);
    return new AjaxResult().failure(message);
  }

  /**
   * 400 - Bad Request
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ConstraintViolationException.class)
  public AjaxResult handleServiceException(ConstraintViolationException e) {
    logger.error("参数验证失败", e);
    Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
    ConstraintViolation<?> violation = violations.iterator().next();
    String message = violation.getMessage();
    return new AjaxResult().failure("parameter:" + message);
  }

  /**
   * 400 - Bad Request
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ValidationException.class)
  public AjaxResult handleValidationException(ValidationException e) {
    logger.error("参数验证失败", e);
    return new AjaxResult().failure("validation_exception");
  }

  /**
   * 405 - Method Not Allowed
   */
  @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public AjaxResult handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
    logger.error("不支持当前请求方法", e);
    return new AjaxResult().failure("request_method_not_supported");
  }

  /**
   * 415 - Unsupported Media Type
   */
  @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  public AjaxResult handleHttpMediaTypeNotSupportedException(Exception e) {
    logger.error("不支持当前媒体类型", e);
    return new AjaxResult().failure("content_type_not_supported");
  }

  /**
   * 500 - Internal Server Error
   */
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(ServiceException.class)
  public AjaxResult handleServiceException(ServiceException e) {
    logger.error("业务逻辑异常", e);
    return new AjaxResult().failure("业务逻辑异常：" + e.getMessage());
  }

  /**
   * 500 - Internal Server Error
   */
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Exception.class)
  public AjaxResult handleException(Exception e) {
    logger.error("通用异常", e);
    return new AjaxResult().failure("通用异常：" + e.getMessage());
  }

  /**
   * 操作数据库出现异常:名称重复，外键关联
   */
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(DataIntegrityViolationException.class)
  public AjaxResult handleException(DataIntegrityViolationException e) {
    logger.error("操作数据库出现异常:", e);
    return new AjaxResult().failure("操作数据库出现异常：字段重复、有外键关联等");
  }
}
