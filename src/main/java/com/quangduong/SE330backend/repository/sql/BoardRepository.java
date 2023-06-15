package com.quangduong.SE330backend.repository.sql;

import com.quangduong.SE330backend.entity.sql.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
}
