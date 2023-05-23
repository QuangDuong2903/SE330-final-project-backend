package com.quangduong.SE330backend.service.impl;

import com.quangduong.SE330backend.dto.board.BoardDTO;
import com.quangduong.SE330backend.dto.board.BoardDetailsDTO;
import com.quangduong.SE330backend.dto.board.BoardUpdateDTO;
import com.quangduong.SE330backend.entity.BoardEntity;
import com.quangduong.SE330backend.exception.NoPermissionException;
import com.quangduong.SE330backend.exception.ResourceNotFoundException;
import com.quangduong.SE330backend.mapper.BoardMapper;
import com.quangduong.SE330backend.repository.sql.BoardRepository;
import com.quangduong.SE330backend.service.BoardService;
import com.quangduong.SE330backend.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private SecurityUtils securityUtils;

    @Override
    public BoardDetailsDTO getBoardDetails(long id) {
        BoardEntity entity = boardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found board with id: " + id));
        if (entity.getAdmin().getId() != securityUtils.getCurrentUserId() && entity.getMembers().stream().noneMatch(m -> m.getId() == securityUtils.getCurrentUserId()))
            throw new NoPermissionException("Not allowed");
        return boardMapper.toDetailsDTO(entity);
    }

    @Override
    @Transactional
    public BoardDTO createBoard(BoardDTO dto) {
        return boardMapper.toDTO(boardRepository.save(boardMapper.toEntity(dto)));
    }

    @Override
    @Transactional
    public BoardDTO updateBoard(BoardUpdateDTO dto) {
        long id = dto.getId();
        if (securityUtils.getCurrentUserId() != boardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found board with id: " + id)).getAdmin().getId())
            throw new NoPermissionException("Update board with id: " + id + " not allowed");
        return boardMapper.toDTO(
                boardRepository.save(boardMapper.toEntity(dto, boardRepository.findById(dto.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Not found board with id: " + dto.getId()))
                ))
        );
    }

    @Override
    @Transactional
    public void deleteBoardById(long id) {
        if (securityUtils.getCurrentUserId() != boardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found board with id: " + id)).getId())
            throw new NoPermissionException("Delete board with id: " + id + " not allowed");
        boardRepository.deleteById(id);
    }
}
