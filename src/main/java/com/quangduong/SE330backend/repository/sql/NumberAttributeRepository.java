package com.quangduong.SE330backend.repository.sql;

import com.quangduong.SE330backend.entity.sql.NumberAttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NumberAttributeRepository extends JpaRepository<NumberAttributeEntity, Long> {
}
