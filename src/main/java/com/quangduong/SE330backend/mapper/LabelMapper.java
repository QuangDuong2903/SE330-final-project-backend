package com.quangduong.SE330backend.mapper;

import com.quangduong.SE330backend.dto.label.LabelDTO;
import com.quangduong.SE330backend.dto.label.LabelUpdateDTO;
import com.quangduong.SE330backend.entity.sql.BoardEntity;
import com.quangduong.SE330backend.entity.sql.LabelEntity;
import com.quangduong.SE330backend.exception.NoPermissionException;
import com.quangduong.SE330backend.exception.ResourceNotFoundException;
import com.quangduong.SE330backend.repository.sql.BoardRepository;
import com.quangduong.SE330backend.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LabelMapper {

    @Autowired
    private SecurityUtils securityUtils;

    @Autowired
    private BoardRepository boardRepository;

    public LabelDTO toDTO(LabelEntity entity) {
        LabelDTO dto = new LabelDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setColor(entity.getColor());
        dto.setBoardId(entity.getBoard().getId());
        return dto;
    }

    public LabelEntity toEntity(LabelDTO dto) {
        LabelEntity entity = new LabelEntity();
        BoardEntity board = boardRepository.findById(dto.getBoardId())
                .orElseThrow(() -> new ResourceNotFoundException("Not found board with id: " + dto.getBoardId()));
        if (securityUtils.getCurrentUserId() != board.getAdmin().getId()
                && board.getMembers().stream().noneMatch(m -> m.getId() == securityUtils.getCurrentUserId()))
            throw new NoPermissionException("You are not in board to create label");
        entity.setName(dto.getName());
        entity.setColor(dto.getColor());
        entity.setBoard(board);
        return entity;
    }

    public LabelEntity toEntity(LabelUpdateDTO dto, LabelEntity entity) {
        if(dto.getName() != null && !dto.getName().isBlank())
            entity.setName(dto.getName());
        if(dto.getColor() != null && !dto.getColor().isBlank())
            entity.setColor(dto.getColor());
        return entity;
    }
}
