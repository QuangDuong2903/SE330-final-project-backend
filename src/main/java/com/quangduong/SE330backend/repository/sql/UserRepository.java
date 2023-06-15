package com.quangduong.SE330backend.repository.sql;

import com.quangduong.SE330backend.constant.UserStatus;
import com.quangduong.SE330backend.entity.sql.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findOneByEmailAndStatus(String email, UserStatus status);
    List<UserEntity> findByEmailContainingOrDisplayNameContaining(String email, String name, Pageable pageable);
}
