package com.quangduong.SE330backend.mapper;

import com.quangduong.SE330backend.dto.attribute.DateAttributeDTO;
import com.quangduong.SE330backend.dto.attribute.TextAttributeDTO;
import com.quangduong.SE330backend.entity.DateAttributeEntity;
import com.quangduong.SE330backend.entity.TextAttributeEntity;
import org.springframework.stereotype.Component;

@Component
public class DateAttributeMapper {

    public DateAttributeEntity toEntity(DateAttributeDTO dto) {
        DateAttributeEntity entity = new DateAttributeEntity();
        entity.setName(dto.getName());
        entity.setValue(dto.getValue());
        return entity;
    }

    public DateAttributeDTO toDTO(DateAttributeEntity entity) {
        DateAttributeDTO dto = new DateAttributeDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setValue(entity.getValue());
        return dto;
    }
}
