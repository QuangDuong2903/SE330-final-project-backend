package com.quangduong.SE330backend.service.impl;

import com.quangduong.SE330backend.dto.table.TableDTO;
import com.quangduong.SE330backend.dto.table.TableUpdateDTO;
import com.quangduong.SE330backend.entity.TableEntity;
import com.quangduong.SE330backend.exception.NoPermissionException;
import com.quangduong.SE330backend.exception.ResourceNotFoundException;
import com.quangduong.SE330backend.mapper.TableMapper;
import com.quangduong.SE330backend.repository.TableRepository;
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
    private TableMapper tableMapper;

    @Autowired
    private SecurityUtils securityUtils;

    @Override
    @Transactional
    public TableDTO createTable(TableDTO dto) {
        return tableMapper.toDTO(tableRepository.save(tableMapper.toEntity(dto)));
    }

    @Override
    @Transactional
    public TableDTO updateTable(TableUpdateDTO dto) {
        long id = dto.getId();
        TableEntity entity = tableRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found table with id: " + id));
        if (!entity.getCreatedBy().equals(securityUtils.getCurrentUser().getEmail()))
            throw new NoPermissionException("Update table with id: " + id + " not allowed");
        return tableMapper.toDTO(tableRepository.save(tableMapper.toEntity(dto, entity)));
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
