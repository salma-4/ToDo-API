package com.app.userservice.mapper.impl;

import com.app.userservice.entity.User;
import com.app.userservice.mapper.UserMapper;
import com.app.userservice.model.request.UserRequestDTO;
import com.app.userservice.model.response.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {
    private final BCryptPasswordEncoder passwordEncoder;
    @Override
    public UserResponseDTO toDTO(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .enabled(user.isEnabled())
                .build();
    }

    @Override
    public User toEntity(UserRequestDTO user) {
           return User.builder()
                   .email(user.getEmail())
                   .password(passwordEncoder.encode(user.getPassword()))
                   .enabled(false)
                   .build();
    }
}
