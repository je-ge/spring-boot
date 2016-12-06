package com.jege.spring.boot.data.jpa.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.jege.spring.boot.data.jpa.entity.User;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:业务层接口
 */
public interface UserService {

  Page<User> findAll(Pageable pageable);

  Page<User> findAll(Specification<User> specification, Pageable pageable);

  void save(User user);

  void delete(Long id);

}
