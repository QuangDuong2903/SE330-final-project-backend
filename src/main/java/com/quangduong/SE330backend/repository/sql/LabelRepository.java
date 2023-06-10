package com.quangduong.SE330backend.repository.sql;

import com.quangduong.SE330backend.entity.sql.LabelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository extends JpaRepository<LabelEntity, Long> {
}
