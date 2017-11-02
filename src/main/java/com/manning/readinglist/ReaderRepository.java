package com.manning.readinglist;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用来持久化读者信息的仓库接口
 * 无需自己实现ReaderRepository,因为它扩展了JpaRepository,
 * Spring Data Jpa 会在运行时自动创建他的实现
 */
public interface ReaderRepository extends JpaRepository<Reader, String> {
}
