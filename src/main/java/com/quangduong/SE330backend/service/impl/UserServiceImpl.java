package com.quangduong.SE330backend.service.impl;

import com.quangduong.SE330backend.constant.UserStatus;
import com.quangduong.SE330backend.dto.user.UserDTO;
import com.quangduong.SE330backend.dto.user.UserInfoDTO;
import com.quangduong.SE330backend.entity.UserEntity;
import com.quangduong.SE330backend.mapper.UserMapper;
import com.quangduong.SE330backend.repository.sql.UserRepository;
import com.quangduong.SE330backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public UserDTO createUser(UserDTO dto) {
        UserEntity user = userRepository.findOneByEmailAndStatus(dto.getEmail(), UserStatus.ACTIVE);
        if(user != null) {
            if (!dto.getPhotoUrl().equals("null")) {
                user.setPhotoUrl(dto.getPhotoUrl());
                return userMapper.toDTO(userRepository.save(user));
            }
            return userMapper.toDTO(user);
        }
        return userMapper.toDTO(userRepository.save(userMapper.toEntity(dto)));
    }

    @Override
    public List<UserInfoDTO> findUser(String keyword, Pageable pageable) {
        return userRepository.findByEmailContainingOrDisplayNameContaining(keyword, keyword, pageable)
                .stream().map(u -> userMapper.userInfoDTO(u)).collect(Collectors.toList());
    }
}
