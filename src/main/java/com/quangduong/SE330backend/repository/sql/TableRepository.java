package com.quangduong.SE330backend.repository.sql;

import com.quangduong.SE330backend.entity.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<TableEntity, Long> {
}
