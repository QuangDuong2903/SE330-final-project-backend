package com.quangduong.SE330backend.security;

import com.quangduong.SE330backend.constant.UserStatus;
import com.quangduong.SE330backend.entity.UserEntity;
import com.quangduong.SE330backend.repository.sql.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findOneByEmailAndStatus(username, UserStatus.ACTIVE);
        if(user == null)
            throw new UsernameNotFoundException("User not found" + username);
        return new CustomUserDetails(user.getId(), user.getEmail());
    }
}
