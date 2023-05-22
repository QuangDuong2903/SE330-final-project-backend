package com.quangduong.SE330backend.service.impl;

import com.quangduong.SE330backend.constant.UserStatus;
import com.quangduong.SE330backend.dto.user.UserDTO;
import com.quangduong.SE330backend.dto.user.UserInfoDTO;
import com.quangduong.SE330backend.entity.UserEntity;
import com.quangduong.SE330backend.mapper.UserMapper;
//import com.quangduong.SE330backend.repository.elastic.UserElasticRepository;
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

//    @Autowired
//    private UserElasticRepository userElasticRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public UserDTO createUser(UserDTO dto) {
        UserEntity user = userRepository.findOneByEmailAndStatus(dto.getEmail(), UserStatus.ACTIVE);
        if(user != null)
            return userMapper.toDTO(user);
        user = userRepository.save(userMapper.toEntity(dto));
//        userElasticRepository.save(userMapper.toModel(user));
        return userMapper.toDTO(user);
    }

    @Override
    public List<UserInfoDTO> findUser(String keyword, Pageable pageable) {
        return userRepository.findByEmailContainingOrDisplayNameContaining(keyword, keyword, pageable)
                .stream().map(u -> userMapper.userInfoDTO(u)).collect(Collectors.toList());
    }

//    @Override
//    public List<UserDTO> findUser(String keyword, Pageable pageable) {
//        return userElasticRepository.findByEmailContainingOrDisplayNameContaining(keyword,keyword,pageable)
//                .stream().map(u -> userMapper.toDTO(u)).collect(Collectors.toList());
//    }
}
