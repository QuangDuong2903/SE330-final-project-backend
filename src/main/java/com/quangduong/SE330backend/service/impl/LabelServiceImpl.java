package com.quangduong.SE330backend.service.impl;

import com.quangduong.SE330backend.dto.label.LabelDTO;
import com.quangduong.SE330backend.dto.label.LabelUpdateDTO;
import com.quangduong.SE330backend.entity.LabelEntity;
import com.quangduong.SE330backend.exception.NoPermissionException;
import com.quangduong.SE330backend.exception.ResourceNotFoundException;
import com.quangduong.SE330backend.mapper.LabelMapper;
import com.quangduong.SE330backend.repository.sql.LabelRepository;
import com.quangduong.SE330backend.service.LabelService;
import com.quangduong.SE330backend.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LabelServiceImpl implements LabelService {

    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private LabelMapper labelMapper;

    @Autowired
    private SecurityUtils securityUtils;

    @Override
    @Transactional
    public LabelDTO createLabel(LabelDTO dto) {
        return labelMapper.toDTO(labelRepository.save(labelMapper.toEntity(dto)));
    }

    @Override
    @Transactional
    public LabelDTO updateLabel(LabelUpdateDTO dto) {
        long id = dto.getId();
        LabelEntity entity = labelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found label with id: " + id));
        if (entity.getUser().getId() != securityUtils.getCurrentUserId())
            throw new NoPermissionException("Not allowed");
        return labelMapper.toDTO(labelRepository.save(labelMapper.toEntity(dto, entity)));
    }

    @Override
    @Transactional
    public void deleteLabel(long id) {
        LabelEntity entity = labelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found label with id: " + id));
        if (entity.getUser().getId() != securityUtils.getCurrentUserId())
            throw new NoPermissionException("Not allowed");
        labelRepository.deleteById(id);
    }
}
