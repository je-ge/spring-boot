package com.jege.spring.boot.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author JE哥
 * @email 1272434821@qq.com
 * @description:持久层类
 */
public interface UserRepository extends ElasticsearchRepository<User, Long> {

}
