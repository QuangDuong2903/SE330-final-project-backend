package com.quangduong.SE330backend.repository;

import com.quangduong.SE330backend.entity.NumberAttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NumberAttributeRepository extends JpaRepository<NumberAttributeEntity, Long> {
}