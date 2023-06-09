package com.quangduong.SE330backend.service.impl;

import com.quangduong.SE330backend.dto.table.TableDTO;
import com.quangduong.SE330backend.dto.table.TableDetailsDTO;
import com.quangduong.SE330backend.dto.table.TableUpdateDTO;
import com.quangduong.SE330backend.entity.BoardEntity;
import com.quangduong.SE330backend.entity.TableEntity;
import com.quangduong.SE330backend.exception.NoPermissionException;
import com.quangduong.SE330backend.exception.ResourceNotFoundException;
import com.quangduong.SE330backend.mapper.TableMapper;
import com.quangduong.SE330backend.repository.sql.BoardRepository;
import com.quangduong.SE330backend.repository.sql.TableRepository;
import com.quangduong.SE330backend.service.TableService;
import com.quangduong.SE330backend.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TableServiceImpl implements TableService {

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private TableMapper tableMapper;

    @Autowired
    private SecurityUtils securityUtils;

    @Override
    @Transactional
    public TableDetailsDTO createTable(TableDTO dto) {
        BoardEntity boardEntity = boardRepository.findById(dto.getBoardId())
                .orElseThrow(() -> new ResourceNotFoundException("Not found board with id: " + dto.getBoardId()));
        if (boardEntity.getAdmin().getId() != securityUtils.getCurrentUserId()
                && boardEntity.getMembers().stream().noneMatch(m -> m.getId() == securityUtils.getCurrentUserId())
        ) throw new NoPermissionException("Not allowed to create table in this board");
        return tableMapper.toDetailsDTO(tableRepository.save(tableMapper.toEntity(dto)));
    }

    @Override
    @Transactional
    public TableDetailsDTO updateTable(TableUpdateDTO dto) {
        long id = dto.getId();
        TableEntity entity = tableRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found table with id: " + id));
        if (!entity.getCreatedBy().equals(securityUtils.getCurrentUser().getEmail()))
            throw new NoPermissionException("Update table with id: " + id + " not allowed");
        return tableMapper.toDetailsDTO(tableRepository.save(tableMapper.toEntity(dto, entity)));
    }

    @Override
    @Transactional
    public void deleteTable(long id) {
        TableEntity entity = tableRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found table with id: " + id));
        if (!entity.getCreatedBy().equals(securityUtils.getCurrentUser().getEmail()))
            throw new NoPermissionException("Update table with id: " + id + " not allowed");
        tableRepository.deleteById(id);
    }
}
