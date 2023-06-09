package com.quangduong.SE330backend.mapper;

import com.quangduong.SE330backend.constant.UserStatus;
import com.quangduong.SE330backend.dto.user.UserBoardDTO;
import com.quangduong.SE330backend.dto.user.UserDTO;
import com.quangduong.SE330backend.dto.user.UserInfoDTO;
import com.quangduong.SE330backend.entity.elasticsearch.UserModel;
import com.quangduong.SE330backend.entity.sql.BoardEntity;
import com.quangduong.SE330backend.entity.sql.UserEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserDTO toDTO(UserEntity entity) {
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setDisplayName(entity.getDisplayName());
        dto.setFamilyName(entity.getFamilyName());
        dto.setGivenName(entity.getGivenName());
        dto.setEmail(entity.getEmail());
        dto.setPhotoUrl(entity.getPhotoUrl());
        List<BoardEntity> boardEntities = new ArrayList<>();
        boardEntities.addAll(entity.getOwnerBoards());
        boardEntities.addAll(entity.getBoards());
        boardEntities = boardEntities.stream().sorted((o1, o2) -> o2.getCreatedDate().compareTo(o1.getCreatedDate())).collect(Collectors.toList());
        dto.setBoards(boardEntities.stream().map(b -> new UserBoardDTO(b.getId(), b.getName())).collect(Collectors.toList()));
        dto.setHasNonReadNotification(entity.getNotifications().stream().anyMatch(n -> !n.isRead()));
        dto.setNew(dto.getBoards().size() == 0);
        return dto;
    }

    public UserModel toUserModel(UserEntity entity) {
        UserModel userModel = new UserModel();
        userModel.setUserId(entity.getId());
        userModel.setEmail(entity.getEmail());
        userModel.setDisplayName(entity.getDisplayName());
        return userModel;
    }

    public UserInfoDTO userInfoDTO(UserEntity entity) {
        UserInfoDTO dto = new UserInfoDTO();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setPhotoUrl(entity.getPhotoUrl());
        dto.setDisplayName(entity.getDisplayName());
        return dto;
    }

    public UserEntity toEntity(UserDTO dto) {
        UserEntity entity = new UserEntity();
        entity.setDisplayName(dto.getDisplayName());
        entity.setFamilyName(dto.getFamilyName());
        entity.setGivenName(dto.getGivenName());
        entity.setEmail(dto.getEmail());
        entity.setPhotoUrl(dto.getPhotoUrl());
        entity.setStatus(UserStatus.ACTIVE);
        return entity;
    }

}
