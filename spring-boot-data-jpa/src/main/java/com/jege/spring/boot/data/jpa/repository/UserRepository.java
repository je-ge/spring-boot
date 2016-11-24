package com.jege.spring.boot.data.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jege.spring.boot.data.jpa.entity.User;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:持久层接口，由spring自动生成其实现
 */
public interface UserRepository extends JpaRepository<User, Long> {

  List<User> findByNameLike(String name);

}
