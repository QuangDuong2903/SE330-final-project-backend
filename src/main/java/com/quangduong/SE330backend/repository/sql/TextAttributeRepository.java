package com.quangduong.SE330backend.repository.sql;

import com.quangduong.SE330backend.entity.sql.TextAttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TextAttributeRepository extends JpaRepository<TextAttributeEntity, Long> {
}
