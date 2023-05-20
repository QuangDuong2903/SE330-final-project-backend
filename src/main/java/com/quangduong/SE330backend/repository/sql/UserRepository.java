package com.quangduong.SE330backend.repository.sql;

import com.quangduong.SE330backend.constant.UserStatus;
import com.quangduong.SE330backend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findOneByEmailAndStatus(String email, UserStatus status);
}
