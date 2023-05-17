package com.quangduong.SE330backend.mapper;

import com.quangduong.SE330backend.dto.attribute.LabelAttributeDTO;
import com.quangduong.SE330backend.entity.LabelAttributeEntity;
import com.quangduong.SE330backend.exception.ResourceNotFoundException;
import com.quangduong.SE330backend.repository.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LabelAttributeMapper {

    @Autowired
    private LabelRepository labelRepository;

    public LabelAttributeEntity toEntity(LabelAttributeDTO dto) {
        LabelAttributeEntity entity = new LabelAttributeEntity();
        entity.setName(dto.getName());
        entity.setValue(labelRepository.findById(dto.getLabelId())
                .orElseThrow(() -> new ResourceNotFoundException("Not found label with id: " + dto.getLabelId())));
        return entity;
    }

    public LabelAttributeDTO toDTO(LabelAttributeEntity entity) {
        LabelAttributeDTO dto = new LabelAttributeDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setLabelId(entity.getValue().getId());
        return dto;
    }

}