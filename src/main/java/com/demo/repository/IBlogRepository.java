package com.demo.repository;

import com.demo.entity.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBlogRepository extends JpaRepository<BlogEntity, Long> {
}
