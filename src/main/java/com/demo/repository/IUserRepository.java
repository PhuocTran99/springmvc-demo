package com.demo.repository;

import com.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findOneByUsernameAndStatus(String username, int status);
}
