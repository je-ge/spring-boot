package com.jege.spring.boot.data.jpa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jege.spring.boot.data.jpa.entity.User;
import com.jege.spring.boot.data.jpa.repository.UserRepository;
import com.jege.spring.boot.data.jpa.service.UserService;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:业务层接口实现
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
@CacheConfig(cacheNames = "user")
public class UserServiceImpl implements UserService {
  @Autowired
  UserRepository userRepository;

  @Override
  @Cacheable()
  public Page<User> findAll(Pageable pageable) {
    return userRepository.findAll(pageable);
  }

  @Override
  @Cacheable()
  public Page<User> findAll(Specification<User> specification, Pageable pageable) {
    return userRepository.findAll(specification, pageable);
  }

  @Override
  @Transactional
  @CacheEvict(allEntries=true)
  public void save(User user) {
    userRepository.save(user);
  }

  @Override
  @Transactional
  @CacheEvict(allEntries=true)
  public void delete(Long id) {
    userRepository.delete(id);
  }

}
